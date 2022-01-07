package com.qezhhnjy.antq.service.sys;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qezhhnjy.antq.entity.sys.TimelineRecord;
import com.qezhhnjy.antq.mapper.sys.RecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyangfu
 * @date 2022/1/7-0:23
 */
@Slf4j
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, TimelineRecord> implements RecordService {
}
