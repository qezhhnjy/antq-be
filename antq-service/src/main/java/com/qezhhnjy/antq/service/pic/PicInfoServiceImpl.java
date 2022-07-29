package com.qezhhnjy.antq.service.pic;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qezhhnjy.antq.entity.pic.PicInfo;
import com.qezhhnjy.antq.mapper.pic.PicInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyangfu
 * @since 2022/7/27-23:03
 */
@Service
@Slf4j
public class PicInfoServiceImpl extends ServiceImpl<PicInfoMapper, PicInfo> implements PicInfoService {
}
