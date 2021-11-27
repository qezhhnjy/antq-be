package com.qezhhnjy.antq.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qezhhnjy.antq.entity.sys.UserRole;
import com.qezhhnjy.antq.mapper.sys.UserRoleMapper;
import com.qezhhnjy.antq.service.sys.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-18:17
 */
@Service
@Slf4j
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public void removeByUserId(Long userId) {
        Objects.requireNonNull(userId);
        lambdaUpdate().eq(UserRole::getUserId, userId).remove();
    }

    @Override
    public void removeByRoleId(Long roleId) {
        Objects.requireNonNull(roleId);
        lambdaUpdate().eq(UserRole::getRoleId, roleId).remove();
    }
}
