package com.qezhhnjy.antq.service.sys.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.RoleVO;
import com.qezhhnjy.antq.entity.sys.Menu;
import com.qezhhnjy.antq.entity.sys.Role;
import com.qezhhnjy.antq.entity.sys.RoleMenu;
import com.qezhhnjy.antq.mapper.sys.RoleMapper;
import com.qezhhnjy.antq.service.sys.RoleMenuService;
import com.qezhhnjy.antq.service.sys.RoleService;
import com.qezhhnjy.antq.service.sys.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:12
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private UserRoleService userRoleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(RoleVO vo) {
        Role role = vo.getRole();
        Objects.requireNonNull(role);
        String roleName = role.getRoleName();
        if (StrUtil.isBlank(roleName)) throw new RuntimeException("角色名称不能为空");
        if (lambdaQuery().eq(Role::getRoleName, roleName).count() > 0) throw new RuntimeException("角色名已存在");
        save(role);
        List<Menu> menuList = vo.getMenuList();
        if (CollUtil.isEmpty(menuList)) return;
        Long roleId = role.getId();
        List<RoleMenu> list = menuList.stream()
                .map(menu -> new RoleMenu().setRoleId(roleId).setMenuId(menu.getId())).collect(Collectors.toList());
        roleMenuService.saveBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RoleVO vo) {
        Role role = vo.getRole();
        Objects.requireNonNull(role);
        Long roleId = role.getId();
        String roleName = role.getRoleName();
        Objects.requireNonNull(roleId);
        if (lambdaQuery().eq(Role::getRoleName, roleName).count() > 0) throw new RuntimeException("角色名已存在");
        updateById(role);
        roleMenuService.removeByRoleId(roleId);

        List<Menu> menuList = vo.getMenuList();
        if (CollUtil.isEmpty(menuList)) return;
        List<RoleMenu> list = menuList.stream()
                .map(menu -> new RoleMenu().setRoleId(roleId).setMenuId(menu.getId()))
                .collect(Collectors.toList());
        roleMenuService.saveBatch(list);

    }

    @Override
    public RoleVO detail(Long id) {
        Role role = getById(id);
        List<Menu> menuList = baseMapper.menuListById(id);
        return new RoleVO(role, menuList);
    }

    @Override
    public List<RoleVO> list(Query query) {
        return list().stream().map(role -> new RoleVO(role, baseMapper.menuListById(role.getId()))).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long id) {
        removeById(id);
        roleMenuService.removeByRoleId(id);
        userRoleService.removeByRoleId(id);
    }

}
