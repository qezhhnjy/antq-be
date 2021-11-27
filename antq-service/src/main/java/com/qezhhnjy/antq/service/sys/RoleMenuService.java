package com.qezhhnjy.antq.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qezhhnjy.antq.entity.sys.RoleMenu;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-18:18
 */
public interface RoleMenuService extends IService<RoleMenu> {

    void removeByRoleId(Long roleId);

    void removeByMenuId(Long menuId);
}
