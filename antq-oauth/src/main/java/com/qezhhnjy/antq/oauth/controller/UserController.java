package com.qezhhnjy.antq.oauth.controller;

import com.qezhhnjy.antq.common.BaseResult;
import com.qezhhnjy.antq.entity.sys.User;
import com.qezhhnjy.antq.service.sys.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:29
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public BaseResult<Void> add(@RequestBody User user) {
        userService.save(user);
        return BaseResult.success();
    }

    @DeleteMapping("/delete")
    public BaseResult<Void> delete(Long id) {
        userService.removeById(id);
        return BaseResult.success();
    }

    @PutMapping("/update")
    public BaseResult<User> update(@RequestBody User user) {
        userService.updateById(user);
        return BaseResult.success(user);
    }

    @GetMapping("/detail")
    public BaseResult<User> detail(Long id) {
        User user = userService.getById(id);
        return BaseResult.success(user);
    }

    @GetMapping("/list")
    public BaseResult<List<User>> list() {
        List<User> userList = userService.list();
        return BaseResult.success(userList);
    }

    @PostMapping("/login")
    public BaseResult<User> login(@RequestBody User user) {
        log.info("user=>{}", user);
        return BaseResult.success(user);
    }

    @GetMapping("/currentUser")
    public BaseResult<User> currentUser() {
        User user = new User();
        user.setUsername("qezhhnjy");
        user.setPassword("123456");
        return BaseResult.success(user);
    }
}
