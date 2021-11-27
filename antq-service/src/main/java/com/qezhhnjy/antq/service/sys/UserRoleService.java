package com.qezhhnjy.antq.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qezhhnjy.antq.entity.sys.UserRole;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-18:17
 */
public interface UserRoleService extends IService<UserRole> {

    void removeByUserId(Long userId);

    void removeByRoleId(Long roleId);
}
