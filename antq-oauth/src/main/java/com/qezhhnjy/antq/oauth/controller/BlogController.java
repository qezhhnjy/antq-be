package com.qezhhnjy.antq.oauth.controller;

import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.entity.sys.Blog;
import com.qezhhnjy.antq.entity.sys.User;
import com.qezhhnjy.antq.oauth.holder.LoginUserHolder;
import com.qezhhnjy.antq.service.sys.BlogService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2022/1/8-18:27
 */
@RestController
@RequestMapping("/blog")
@CacheConfig(cacheNames = "blog")
public class BlogController {

    @Resource
    private BlogService     blogService;
    @Resource
    private LoginUserHolder loginUserHolder;

    @PostMapping("/add")
    public BaseResult<Void> add(@RequestBody Blog blog) {
        blog.setEditTime(LocalDateTime.now());
        blogService.lambdaQuery().eq(Blog::getTitle, blog.getTitle())
                .oneOpt()
                .ifPresentOrElse(current -> {
                    blog.setId(current.getId());
                    update(blog);
                }, () -> {
                    User currentUser = loginUserHolder.getCurrentUser().getUser();
                    blog.setUsername(currentUser.getUsername());
                    blog.setAvatar(currentUser.getAvatar());
                    blogService.save(blog);
                });
        return BaseResult.success();
    }

    @DeleteMapping("/delete")
    @CacheEvict(beforeInvocation = true, key = "#id")
    public BaseResult<Void> delete(Long id) {
        blogService.removeById(id);
        return BaseResult.success();
    }

    @PutMapping("/update")
    @CacheEvict(beforeInvocation = true, key = "#blog.id")
    public BaseResult<Void> update(@RequestBody Blog blog) {
        blog.setEditTime(LocalDateTime.now());
        blogService.updateById(blog);
        return BaseResult.success();
    }

    @PostMapping("/list")
    public BaseResult<List<Blog>> list() {
        return BaseResult.success(blogService.lambdaQuery()
                .select(Blog::getId, Blog::getUsername, Blog::getAvatar, Blog::getTitle, Blog::getSummary,
                        Blog::getIcon, Blog::getTags, Blog::getEditTime)
                .orderByDesc(Blog::getEditTime).list());
    }

    @PostMapping("/query")
    public BaseResult<PageInfo<Blog>> query(@RequestBody Query query) {
        PageInfo<Blog> pageInfo = blogService.query(query);
        return BaseResult.success(pageInfo);
    }

    @GetMapping("/detail")
    @Cacheable(key = "#id")
    public BaseResult<Blog> detail(Long id) {
        return BaseResult.success(blogService.getById(id));
    }

}
