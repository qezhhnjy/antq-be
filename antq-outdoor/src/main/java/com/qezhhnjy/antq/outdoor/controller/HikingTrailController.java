package com.qezhhnjy.antq.outdoor.controller;

import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.entity.out.HikingTrail;
import com.qezhhnjy.antq.service.out.HikingTrailService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhaoyangfu
 * @since 2022/7/2-14:38
 */
@RestController
@RequestMapping("/hiking-trail")
@CacheConfig(cacheNames = "hiking-trail")
public class HikingTrailController {

    @Resource
    private HikingTrailService hikingTrailService;

    @PostMapping("/save")
    @CacheEvict(beforeInvocation = true, allEntries = true)
    public BaseResult<HikingTrail> save(@RequestBody HikingTrail hikingTrail) {
        HikingTrail.handler(hikingTrail);
        hikingTrailService.save(hikingTrail);
        return BaseResult.success(hikingTrail);
    }

    @PostMapping("/update")
    @CacheEvict(beforeInvocation = true, allEntries = true)
    public BaseResult<HikingTrail> update(@RequestBody HikingTrail hikingTrail) {
        HikingTrail.handler(hikingTrail);
        hikingTrailService.updateById(hikingTrail);
        return BaseResult.success(hikingTrail);
    }

    @GetMapping("/delete")
    @CacheEvict(beforeInvocation = true, allEntries = true)
    public BaseResult<?> delete(String id) {
        hikingTrailService.removeById(id);
        return BaseResult.success();
    }

    @PostMapping("/list")
    @Cacheable
    public BaseResult<PageInfo<HikingTrail>> list(@RequestBody Query query) {
        PageInfo<HikingTrail> pageInfo = hikingTrailService.list(query);
        return BaseResult.success(pageInfo);
    }
}
