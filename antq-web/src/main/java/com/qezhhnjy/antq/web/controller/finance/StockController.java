package com.qezhhnjy.antq.web.controller.finance;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.entity.finance.Stock;
import com.qezhhnjy.antq.service.finance.StockService;
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
@RequestMapping("/finance-stock")
public class StockController {

    @Resource
    private StockService stockService;

    @PostMapping("/add")
    public BaseResult<Void> add(@RequestBody List<Stock> stockList) {
        stockService.saveBatch(stockList);
        return BaseResult.success();
    }

    @GetMapping("/all")
    public BaseResult<List<Stock>> all(String search) {
        return BaseResult.success(stockService.lambdaQuery()
                .like(Stock::getName, search).last("LIMIT 20").list());
    }
}
