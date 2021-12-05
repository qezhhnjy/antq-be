package com.qezhhnjy.antq.oauth.controller;

import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.UserVO;
import com.qezhhnjy.antq.service.sys.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserService     userService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @PostMapping("/add")
    public BaseResult<UserVO> add(@RequestBody UserVO vo) {
        vo.getUser().setPassword(passwordEncoder.encode(vo.getUser().getPassword()));
        userService.add(vo);
        return BaseResult.success(vo);
    }

    @DeleteMapping("/delete")
    public BaseResult<Void> delete(@RequestParam Long id) {
        userService.remove(id);
        return BaseResult.success();
    }

    @PutMapping("/update")
    public BaseResult<UserVO> update(@RequestBody UserVO vo) {
        vo.getUser().setPassword(passwordEncoder.encode(vo.getUser().getPassword()));
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
        // throw new RuntimeException("用户列表为空");
        List<UserVO> list = userService.list(query);
        return BaseResult.success(list);
    }

    @PostMapping("/query")
    public BaseResult<PageInfo<UserVO>> query(@RequestBody Query query) {
        PageInfo<UserVO> pageInfo = userService.query(query);
        return BaseResult.success(pageInfo);
    }
}
