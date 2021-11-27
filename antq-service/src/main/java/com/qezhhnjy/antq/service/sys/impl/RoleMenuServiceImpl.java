package com.qezhhnjy.antq.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qezhhnjy.antq.entity.sys.RoleMenu;
import com.qezhhnjy.antq.mapper.sys.RoleMenuMapper;
import com.qezhhnjy.antq.service.sys.RoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-18:18
 */
@Service
@Slf4j
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public void removeByRoleId(Long roleId) {
        Objects.requireNonNull(roleId);
        lambdaUpdate().eq(RoleMenu::getRoleId, roleId).remove();
    }

    @Override
    public void removeByMenuId(Long menuId) {
        Objects.requireNonNull(menuId);
        lambdaUpdate().eq(RoleMenu::getMenuId, menuId).remove();
    }
}
