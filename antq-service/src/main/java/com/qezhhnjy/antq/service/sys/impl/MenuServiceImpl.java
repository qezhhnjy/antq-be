package com.qezhhnjy.antq.service.sys.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        Objects.requireNonNull(menu);
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
        return list().stream().map(menu -> detail(menu.getId())).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long id) {
        removeById(id);
        roleMenuService.removeByMenuId(id);
    }
}
