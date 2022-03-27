package com.qezhhnjy.antq.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qezhhnjy.antq.entity.sys.QuartzLog;
import com.qezhhnjy.antq.mapper.sys.QuartzLogMapper;
import com.qezhhnjy.antq.service.sys.QuartzLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyangfu
 * @date 2022/3/27-16:22
 */
@Service
@Slf4j
public class QuartzLogServiceImpl extends ServiceImpl<QuartzLogMapper, QuartzLog>
        implements QuartzLogService {
}
