package com.qezhhnjy.antq.service.sys.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.UserVO;
import com.qezhhnjy.antq.entity.sys.Role;
import com.qezhhnjy.antq.entity.sys.User;
import com.qezhhnjy.antq.entity.sys.UserRole;
import com.qezhhnjy.antq.mapper.sys.UserMapper;
import com.qezhhnjy.antq.service.sys.UserRoleService;
import com.qezhhnjy.antq.service.sys.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:11
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserRoleService userRoleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UserVO vo) {
        User user = vo.getUser();
        Objects.requireNonNull(user);
        save(user);

        List<Role> roleList = vo.getRoleList();
        if (CollUtil.isEmpty(roleList)) return;
        Long userId = user.getId();
        List<UserRole> list = roleList.stream()
                .map(role -> new UserRole().setRoleId(role.getId()).setUserId(userId))
                .collect(Collectors.toList());

        userRoleService.saveBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long id) {
        removeById(id);
        userRoleService.removeByUserId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserVO vo) {
        User user = vo.getUser();
        Objects.requireNonNull(user);
        Long userId = user.getId();
        Objects.requireNonNull(userId);
        updateById(user);
        userRoleService.removeByUserId(userId);

        List<Role> roleList = vo.getRoleList();
        if (CollUtil.isEmpty(roleList)) return;
        List<UserRole> list = roleList.stream()
                .map(role -> new UserRole().setRoleId(role.getId()).setUserId(userId))
                .collect(Collectors.toList());
        userRoleService.saveBatch(list);
    }

    @Override
    public UserVO detail(Long id) {
        User user = getById(id);
        List<Role> roleList = baseMapper.roleListById(id);
        return new UserVO(user, roleList);
    }

    @Override
    public List<UserVO> list(Query query) {
        return list().stream().map(user -> detail(user.getId())).collect(Collectors.toList());
    }
}
