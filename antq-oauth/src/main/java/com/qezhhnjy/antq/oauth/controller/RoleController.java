package com.qezhhnjy.antq.oauth.controller;

import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.RoleVO;
import com.qezhhnjy.antq.service.sys.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = "role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping("/add")
    @CacheEvict(beforeInvocation = true, allEntries = true)
    public BaseResult<Void> add(@RequestBody RoleVO vo) {
        roleService.add(vo);
        return BaseResult.success();
    }

    @DeleteMapping("/delete")
    @CacheEvict(beforeInvocation = true, allEntries = true)
    public BaseResult<Void> delete(Long id) {
        roleService.remove(id);
        return BaseResult.success();
    }

    @PutMapping("/update")
    @CacheEvict(beforeInvocation = true, allEntries = true)
    public BaseResult<RoleVO> update(@RequestBody RoleVO vo) {
        roleService.update(vo);
        return BaseResult.success(vo);
    }

    @GetMapping("/detail")
    @Cacheable(key = "#id")
    public BaseResult<RoleVO> detail(Long id) {
        RoleVO vo = roleService.detail(id);
        return BaseResult.success(vo);
    }

    @PostMapping("/list")
    @Cacheable
    public BaseResult<List<RoleVO>> list(@RequestBody Query query) {
        List<RoleVO> list = roleService.list(query);
        return BaseResult.success(list);
    }

    @PostMapping("/query")
    @Cacheable
    public BaseResult<PageInfo<RoleVO>> query(@RequestBody Query query) {
        PageInfo<RoleVO> pageInfo = roleService.query(query);
        return BaseResult.success(pageInfo);
    }

}
