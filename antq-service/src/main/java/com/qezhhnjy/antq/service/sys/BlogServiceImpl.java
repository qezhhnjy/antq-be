package com.qezhhnjy.antq.service.sys;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.entity.sys.Blog;
import com.qezhhnjy.antq.mapper.sys.BlogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2022/1/8-18:26
 */
@Slf4j
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Override
    public PageInfo<Blog> query(Query query) {
        String search = query.getSearch();
        boolean isSearch = StrUtil.isNotBlank(search);

        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<Blog> blogList = lambdaQuery()
                .select(Blog::getId, Blog::getUsername, Blog::getAvatar, Blog::getTitle, Blog::getSummary,
                        Blog::getIcon, Blog::getTags, Blog::getEditTime)
                .like(isSearch, Blog::getUsername, search)
                .or().like(isSearch, Blog::getTitle, search)
                .or().like(isSearch, Blog::getSummary, search)
                .or().like(isSearch, Blog::getTags, search)
                .orderByDesc(Blog::getEditTime).list();

        return new PageInfo<>(blogList);
    }
}
