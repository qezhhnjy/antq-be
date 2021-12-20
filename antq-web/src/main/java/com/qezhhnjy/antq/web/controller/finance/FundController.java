package com.qezhhnjy.antq.web.controller.finance;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.entity.finance.Fund;
import com.qezhhnjy.antq.service.finance.FundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2021/12/19-0:47
 */
@RestController
@Slf4j
@RequestMapping("/finance-fund")
public class FundController {

    @Resource
    private FundService fundService;

    @PostMapping("/add")
    public BaseResult<Void> add(@RequestBody List<Fund> fundList) {
        fundService.saveBatch(fundList);
        return BaseResult.success();
    }

    @GetMapping("/all")
    public BaseResult<List<Fund>> all(String search) {
        return BaseResult.success(fundService.lambdaQuery()
                .like(Fund::getName, search).last("LIMIT 20").list());
    }
}
