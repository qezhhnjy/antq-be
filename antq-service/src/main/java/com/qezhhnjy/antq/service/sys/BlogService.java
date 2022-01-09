package com.qezhhnjy.antq.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.entity.sys.Blog;

/**
 * @author zhaoyangfu
 * @date 2022/1/8-18:26
 */
public interface BlogService extends IService<Blog> {

    PageInfo<Blog> query(Query query);
}
