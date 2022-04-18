package com.qezhhnjy.antq.service.im;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qezhhnjy.antq.common.vo.im.GroupVO;
import com.qezhhnjy.antq.entity.im.Group;
import com.qezhhnjy.antq.entity.sys.User;

import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2022/4/18-14:59
 */
public interface GroupService extends IService<Group> {

    void add(GroupVO groupVO);

    List<GroupVO> list(User user);
}
