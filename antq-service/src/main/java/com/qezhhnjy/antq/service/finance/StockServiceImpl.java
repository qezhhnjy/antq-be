package com.qezhhnjy.antq.service.finance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qezhhnjy.antq.entity.finance.Stock;
import com.qezhhnjy.antq.mapper.finance.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyangfu
 * @date 2021/12/19-0:52
 */
@Service
@Slf4j
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {
}
