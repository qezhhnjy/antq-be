package com.qezhhnjy.antq.service.finance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qezhhnjy.antq.entity.finance.FinanceOptional;
import com.qezhhnjy.antq.mapper.finance.OptionalMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyangfu
 * @date 2022/1/3-21:36
 */
@Slf4j
@Service
public class OptionalServiceImpl extends ServiceImpl<OptionalMapper, FinanceOptional>
        implements OptionalService {
}
