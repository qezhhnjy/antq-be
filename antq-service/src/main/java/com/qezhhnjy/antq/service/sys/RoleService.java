package com.qezhhnjy.antq.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.RoleVO;
import com.qezhhnjy.antq.entity.sys.Role;

import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:12
 */
public interface RoleService extends IService<Role> {

    void add(RoleVO vo);

    void update(RoleVO vo);

    RoleVO detail(Long id);

    List<RoleVO> list(Query query);

    void remove(Long id);
}
