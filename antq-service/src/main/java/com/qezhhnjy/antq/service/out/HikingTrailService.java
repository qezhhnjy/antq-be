package com.qezhhnjy.antq.service.out;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.entity.out.HikingTrail;

/**
 * @author zhaoyangfu
 * @since 2022/7/2-14:25
 */
public interface HikingTrailService extends IService<HikingTrail> {

    PageInfo<HikingTrail> list(Query query);
}
