package com.qezhhnjy.antq.service.finance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qezhhnjy.antq.entity.finance.Fund;
import com.qezhhnjy.antq.mapper.finance.FundMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyangfu
 * @date 2021/12/19-15:18
 */
@Service
@Slf4j
public class FundServiceImpl extends ServiceImpl<FundMapper, Fund> implements FundService {
}
