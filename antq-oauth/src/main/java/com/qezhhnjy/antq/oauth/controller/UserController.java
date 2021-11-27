package com.qezhhnjy.antq.oauth.controller;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.UserVO;
import com.qezhhnjy.antq.service.sys.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:29
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService     userService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @PostMapping("/add")
    public BaseResult<Void> add(@RequestBody UserVO vo) {
        userService.add(vo);
        return BaseResult.success();
    }

    @DeleteMapping("/delete")
    public BaseResult<Void> delete(Long id) {
        userService.remove(id);
        return BaseResult.success();
    }

    @PutMapping("/update")
    public BaseResult<UserVO> update(@RequestBody UserVO vo) {
        userService.update(vo);
        return BaseResult.success(vo);
    }

    @GetMapping("/detail")
    public BaseResult<UserVO> detail(Long id) {
        UserVO vo = userService.detail(id);
        return BaseResult.success(vo);
    }

    @PostMapping("/list")
    public BaseResult<List<UserVO>> list(@RequestBody Query query) {
        userService.list(query);
        List<UserVO> userList = userService.list().stream().map(user -> new UserVO(user, null)).collect(Collectors.toList());
        return BaseResult.success(userList);
    }
}
