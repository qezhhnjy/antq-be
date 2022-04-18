package com.qezhhnjy.antq.service.im;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qezhhnjy.antq.common.vo.im.GroupVO;
import com.qezhhnjy.antq.entity.im.Group;
import com.qezhhnjy.antq.entity.im.GroupMember;
import com.qezhhnjy.antq.entity.sys.User;
import com.qezhhnjy.antq.mapper.im.GroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoyangfu
 * @date 2022/4/18-14:59
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

    @Resource
    private GroupMemberService groupMemberService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(GroupVO groupVO) {
        Group group = new Group();
        group.setName(groupVO.getGroupName());
        save(group);
        List<String> userList = groupVO.getUserList();

    }

    @Override
    public List<GroupVO> list(User user) {
        return groupMemberService.lambdaQuery().eq(GroupMember::getUsername, user.getUsername())
                .list()
                .stream()
                .map(groupMember -> BeanUtil.toBean(groupMember, GroupVO.class))
                .collect(Collectors.toList());
    }
}
