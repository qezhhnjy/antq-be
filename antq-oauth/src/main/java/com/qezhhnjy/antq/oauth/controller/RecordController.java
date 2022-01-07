package com.qezhhnjy.antq.oauth.controller;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.entity.sys.TimelineRecord;
import com.qezhhnjy.antq.service.sys.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2022/1/7-0:25
 */
@RestController
@RequestMapping("/record")
@Slf4j
public class RecordController {

    @Resource
    private RecordService recordService;

    @PostMapping("/add")
    public BaseResult<Void> add(@RequestBody TimelineRecord record) {
        record.setRecordTime(LocalDateTime.now());
        recordService.save(record);
        return BaseResult.success();
    }

    @DeleteMapping("/delete")
    public BaseResult<Void> delete(Long id) {
        recordService.removeById(id);
        return BaseResult.success();
    }

    @PutMapping("/update")
    public BaseResult<Void> update(@RequestBody TimelineRecord record) {
        recordService.updateById(record);
        return BaseResult.success();
    }

    @PostMapping("/list")
    public BaseResult<List<TimelineRecord>> list() {
        return BaseResult.success(recordService.list());
    }
}
