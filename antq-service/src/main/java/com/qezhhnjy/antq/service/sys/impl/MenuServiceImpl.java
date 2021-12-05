package com.qezhhnjy.antq.service.sys.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.MenuVO;
import com.qezhhnjy.antq.entity.sys.Menu;
import com.qezhhnjy.antq.entity.sys.Role;
import com.qezhhnjy.antq.entity.sys.RoleMenu;
import com.qezhhnjy.antq.mapper.sys.MenuMapper;
import com.qezhhnjy.antq.service.sys.MenuService;
import com.qezhhnjy.antq.service.sys.RoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:13
 */
@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private RoleMenuService roleMenuService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MenuVO vo) {
        Menu menu = vo.getMenu();
        Objects.requireNonNull(menu);
        String path = menu.getPath();
        if (lambdaQuery().eq(Menu::getPath, path).count() > 0) throw new RuntimeException("菜单路径已存在");
        save(menu);

        List<Role> roleList = vo.getRoleList();
        if (CollUtil.isEmpty(roleList)) return;
        Long menuId = menu.getId();
        List<RoleMenu> list = roleList.stream()
                .map(role -> new RoleMenu().setRoleId(role.getId()).setMenuId(menuId))
                .collect(Collectors.toList());

        roleMenuService.saveBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MenuVO vo) {
        Menu menu = vo.getMenu();
        Objects.requireNonNull(menu, "菜单信息不能为空");
        Long menuId = menu.getId();
        Objects.requireNonNull(menuId);
        updateById(menu);
        roleMenuService.removeByMenuId(menuId);

        List<Role> roleList = vo.getRoleList();
        if (CollUtil.isEmpty(roleList)) return;
        List<RoleMenu> list = roleList.stream()
                .map(role -> new RoleMenu().setRoleId(role.getId()).setMenuId(menuId))
                .collect(Collectors.toList());
        roleMenuService.saveBatch(list);
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
        removeById(id);
        roleMenuService.removeByMenuId(id);
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
}
