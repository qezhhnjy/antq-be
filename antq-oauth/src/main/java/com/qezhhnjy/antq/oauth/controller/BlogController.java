package com.qezhhnjy.antq.oauth.controller;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.entity.sys.Blog;
import com.qezhhnjy.antq.entity.sys.User;
import com.qezhhnjy.antq.oauth.holder.LoginUserHolder;
import com.qezhhnjy.antq.service.sys.BlogService;
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
public class BlogController {

    @Resource
    private BlogService     blogService;
    @Resource
    private LoginUserHolder loginUserHolder;

    @PostMapping("/add")
    public BaseResult<Void> add(@RequestBody Blog blog) {
        blog.setEditTime(LocalDateTime.now());
        User currentUser = loginUserHolder.getCurrentUser().getUser();
        blog.setUsername(currentUser.getUsername());
        blog.setAvatar(currentUser.getAvatar());
        blogService.save(blog);
        return BaseResult.success();
    }

    @DeleteMapping("/delete")
    public BaseResult<Void> delete(Long id) {
        blogService.removeById(id);
        return BaseResult.success();
    }

    @PutMapping("/update")
    public BaseResult<Void> update(@RequestBody Blog blog) {
        blog.setEditTime(LocalDateTime.now());
        blogService.updateById(blog);
        return BaseResult.success();
    }

    @PostMapping("/list")
    public BaseResult<List<Blog>> list() {
        return BaseResult.success(blogService.list());
    }

}
