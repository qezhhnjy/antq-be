package com.qezhhnjy.antq.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.UserVO;
import com.qezhhnjy.antq.entity.sys.User;

import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:10
 */
public interface UserService extends IService<User> {

    void add(UserVO vo);

    void remove(Long id);

    void update(UserVO vo);

    UserVO detail(Long id);

    List<UserVO> list(Query query);

    UserVO getByUserName(String username);
}
