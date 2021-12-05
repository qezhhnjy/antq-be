package com.qezhhnjy.antq.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qezhhnjy.antq.entity.sys.Role;
import com.qezhhnjy.antq.entity.sys.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:06
 */
public interface UserMapper extends BaseMapper<User> {

    List<Role> roleListById(@Param("id") Long id);

    List<User> query();

}
