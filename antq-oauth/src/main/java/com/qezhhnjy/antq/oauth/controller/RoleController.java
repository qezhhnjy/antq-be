package com.qezhhnjy.antq.oauth.controller;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.RoleVO;
import com.qezhhnjy.antq.service.sys.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-17:39
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping("/add")
    public BaseResult<Void> add(@RequestBody RoleVO vo) {
        roleService.add(vo);
        return BaseResult.success();
    }

    @DeleteMapping("/delete")
    public BaseResult<Void> delete(Long id) {
        roleService.remove(id);
        return BaseResult.success();
    }

    @PutMapping("/update")
    public BaseResult<RoleVO> update(@RequestBody RoleVO vo) {
        roleService.update(vo);
        return BaseResult.success(vo);
    }

    @GetMapping("/detail")
    public BaseResult<RoleVO> detail(Long id) {
        RoleVO vo = roleService.detail(id);
        return BaseResult.success(vo);
    }

    @PostMapping("/list")
    public BaseResult<List<RoleVO>> list(@RequestBody Query query) {
        List<RoleVO> list = roleService.list(query);
        return BaseResult.success(list);
    }

}
