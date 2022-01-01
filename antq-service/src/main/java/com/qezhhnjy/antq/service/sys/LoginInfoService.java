package com.qezhhnjy.antq.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.entity.sys.LoginInfo;

/**
 * @author zhaoyangfu
 * @date 2022/1/1-19:14
 */
public interface LoginInfoService extends IService<LoginInfo> {

    PageInfo<LoginInfo> query(Query query);
}
