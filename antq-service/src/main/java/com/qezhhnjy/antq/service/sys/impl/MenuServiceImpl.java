package com.qezhhnjy.antq.service.sys.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.consts.AuthConstant;
import com.qezhhnjy.antq.common.consts.RedisConstant;
import com.qezhhnjy.antq.common.enums.ResultCode;
import com.qezhhnjy.antq.common.exception.BizException;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.MenuVO;
import com.qezhhnjy.antq.common.vo.sys.Tree;
import com.qezhhnjy.antq.entity.sys.Menu;
import com.qezhhnjy.antq.entity.sys.Role;
import com.qezhhnjy.antq.entity.sys.RoleMenu;
import com.qezhhnjy.antq.mapper.sys.MenuMapper;
import com.qezhhnjy.antq.service.sys.MenuService;
import com.qezhhnjy.antq.service.sys.RoleMenuService;
import com.qezhhnjy.antq.service.sys.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:13
 */
@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private RoleMenuService                     roleMenuService;
    @Resource
    private RoleService                         roleService;
    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MenuVO vo) {
        Menu menu = vo.getMenu();
        Objects.requireNonNull(menu);
        String path = menu.getPath();
        String permission = menu.getPermission();
        if (lambdaQuery().eq(Menu::getPath, path).count() > 0) throw new RuntimeException("菜单路径已存在");
        if (lambdaQuery().eq(Menu::getPermission, permission).count() > 0) throw new RuntimeException("菜单权限已存在");

        // 嵌套盒模型新增节点
        Long parentId = vo.getParentId();
        List<Menu> menuList = list();
        // 只能创建一个根节点
        if (CollUtil.isNotEmpty(menuList)) Objects.requireNonNull(parentId, "父节点不能为空");
        if (parentId == null) {
            // 创建根节点
            menu.setLeftBound(0);
            menu.setRightBound(1);
            menu.setDepth(0);
        } else {
            Menu parent = getById(parentId);
            Integer parentRight = parent.getRightBound();
            menu.setLeftBound(parentRight);
            menu.setRightBound(parentRight + 1);
            menu.setDepth(parent.getDepth() + 1);
            baseMapper.leftBoundRightMove(parentRight, 2);
            baseMapper.rightBoundRightMove(parentRight, 2);
        }
        save(menu);

        roleMenuService.save(new RoleMenu()
                .setRoleId(roleService.lambdaQuery().eq(Role::getRoleName, AuthConstant.ADMIN).one().getId())
                .setMenuId(menu.getId()));

        loadResourceRolesMap();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MenuVO vo) {
        Menu menu = vo.getMenu();
        Objects.requireNonNull(menu, "菜单信息不能为空");
        Long parentId = vo.getParentId();
        Objects.requireNonNull(parentId, "不能更新根节点");
        Long id = menu.getId();
        Objects.requireNonNull(id, "菜单Id不能为空");
        String permission = menu.getPermission();
        if (lambdaQuery().eq(Menu::getPermission, permission).count() > 1) throw new RuntimeException("菜单权限已存在");
        lambdaQuery().eq(Menu::getPermission, permission).oneOpt().ifPresent(current -> {
            if (!current.getId().equals(menu.getId())) throw new RuntimeException("菜单权限已存在");
        });

        updateById(menu);
        Menu current = getById(id);
        Menu parent = getById(parentId);
        int parentRight = parent.getRightBound();
        int left = current.getLeftBound();
        int right = current.getRightBound();
        int step = right - left + 1;
        baseMapper.leftBoundRightMove(parentRight, step);
        baseMapper.rightBoundRightMove(parentRight, step);
        baseMapper.move(left, right, parentRight - left);
    }

    /**
     * 默认ADMIN角色拥有所有权限,每次新增、更新菜单的时候都需要给ADMIN角色添加对应权限
     */
    public void addAdmin(List<Role> roleList) {
        if (roleList.stream().anyMatch(role -> StrUtil.equals(role.getRoleName(), AuthConstant.ADMIN))) return;
        roleList.add(roleService.lambdaQuery().eq(Role::getRoleName, AuthConstant.ADMIN).one());
    }

    @Override
    public MenuVO detail(Long id) {
        Menu menu = getById(id);
        List<Role> roleList = baseMapper.roleListById(id);
        return new MenuVO(menu, roleList);
    }

    @Override
    public List<MenuVO> list(Query query) {
        return list().stream().map(menu -> new MenuVO(menu, baseMapper.roleListById(menu.getId()))).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long id) {
        Menu menu = getById(id);
        if (menu == null) throw new BizException(ResultCode.DATA_NOT_EXIST, "菜单信息不存在");
        Integer left = menu.getLeftBound();
        Integer right = menu.getRightBound();
        List<Menu> menuList = lambdaQuery().ge(Menu::getLeftBound, left).le(Menu::getRightBound, right).list();
        menuList.forEach(m -> {
            Long menuId = m.getId();
            removeById(menuId);
            roleMenuService.removeByMenuId(menuId);
        });
    }

    @Override
    public PageInfo<MenuVO> query(Query query) {
        String search = query.getSearch();
        boolean isSearch = StrUtil.isNotBlank(search);
        PageHelper.startPage(query.getPageNum(), query.getPageSize());

        List<Menu> menuList = lambdaQuery().like(isSearch, Menu::getMenuName, search)
                .or().like(isSearch, Menu::getPermission, search)
                .or().like(isSearch, Menu::getPath, search)
                .list();

        PageInfo<MenuVO> pageInfo = new PageInfo(menuList);
        List<MenuVO> result = menuList.stream().map(menu -> new MenuVO(menu, baseMapper.roleListById(menu.getId())))
                .collect(Collectors.toList());
        pageInfo.setList(result);
        return pageInfo;
    }

    @Override
    public Tree tree(boolean menu) {
        Tree tree = new Tree();
        List<Menu> list;
        if (menu) list = lambdaQuery().eq(Menu::getType, 0).list();
        else list = list();
        if (CollUtil.isEmpty(list)) return tree;
        Map<Integer, List<Menu>> depthMap = list.stream().collect(Collectors.groupingBy(Menu::getDepth));

        List<Menu> menuList = depthMap.get(0);
        if (menuList.size() == 1) {
            Menu root = menuList.get(0);
            Long id = root.getId();
            tree.setTitle(root.getMenuName()).setKey(id).setValue(id);
            children(tree, root, depthMap);
        }
        return tree;
    }

    @Override
    public void loadResourceRolesMap() {
        Map<String, List<String>> resourceRolesMap = new TreeMap<>();
        List<MenuVO> menuList = list(new Query());
        if (CollUtil.isEmpty(menuList)) return;
        menuList.forEach(menu -> resourceRolesMap.put(menu.getMenu().getPath(),
                menu.getRoleList().stream().map(Role::getRoleName).collect(Collectors.toList())));
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }

    private void children(Tree parentTree, Menu parent, Map<Integer, List<Menu>> depthMap) {
        Integer left = parent.getLeftBound();
        Integer right = parent.getRightBound();
        Integer depth = parent.getDepth();
        List<Menu> list = depthMap.get(depth + 1);
        List<Tree> children = new ArrayList<>();

        if (CollUtil.isNotEmpty(list)) {
            list.removeIf(menu -> {
                boolean child = menu.getLeftBound() > left && menu.getRightBound() < right;
                if (child) {
                    Tree temp = new Tree();
                    Long id = menu.getId();
                    temp.setTitle(menu.getMenuName()).setKey(id).setValue(id);
                    children.add(temp);
                    children(temp, menu, depthMap);
                }
                return child;
            });
        }
        parentTree.setChildren(children);
    }
}
