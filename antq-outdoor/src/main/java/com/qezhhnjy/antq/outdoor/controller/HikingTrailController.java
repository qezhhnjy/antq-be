package com.qezhhnjy.antq.outdoor.controller;

import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.entity.out.HikingTrail;
import com.qezhhnjy.antq.service.out.HikingTrailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhaoyangfu
 * @since 2022/7/2-14:38
 */
@RestController
@RequestMapping("/hiking-trail")
public class HikingTrailController {

    @Resource
    private HikingTrailService hikingTrailService;

    @PostMapping("/save")
    public BaseResult<HikingTrail> save(@RequestBody HikingTrail hikingTrail) {
        hikingTrailService.save(hikingTrail);
        return BaseResult.success(hikingTrail);
    }

    @PostMapping("/update")
    public BaseResult<HikingTrail> update(@RequestBody HikingTrail hikingTrail) {
        hikingTrailService.updateById(hikingTrail);
        return BaseResult.success(hikingTrail);
    }

    @GetMapping("/delete")
    public BaseResult<?> delete(String id) {
        hikingTrailService.removeById(id);
        return BaseResult.success();
    }

    @PostMapping("/list")
    public BaseResult<PageInfo<HikingTrail>> list(@RequestBody Query query) {
        PageInfo<HikingTrail> pageInfo = hikingTrailService.list(query);
        return BaseResult.success(pageInfo);
    }
}
