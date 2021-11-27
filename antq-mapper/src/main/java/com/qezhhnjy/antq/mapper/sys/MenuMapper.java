package com.qezhhnjy.antq.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qezhhnjy.antq.entity.sys.Menu;
import com.qezhhnjy.antq.entity.sys.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:09
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Role> roleListById(@Param("id") Long id);
}
