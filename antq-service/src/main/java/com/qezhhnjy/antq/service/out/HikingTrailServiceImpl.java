package com.qezhhnjy.antq.service.out;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.entity.out.HikingTrail;
import com.qezhhnjy.antq.mapper.out.HikingTrailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoyangfu
 * @since 2022/7/2-14:26
 */
@Slf4j
@Service
public class HikingTrailServiceImpl extends ServiceImpl<HikingTrailMapper, HikingTrail>
        implements HikingTrailService {

    @Override
    public PageInfo<HikingTrail> list(Query query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        String search = query.getSearch();
        String order = query.getOrder();
        String orderColumn = query.getOrderBy();
        List<HikingTrail> list = lambdaQuery().and(StrUtil.isNotBlank(search), i -> i.like(HikingTrail::getTitle, search)
                .or().like(HikingTrail::getSummary, search)
                .or().like(HikingTrail::getTags, search))
                .last(StrUtil.isAllNotBlank(order, orderColumn), String.format("ORDER BY %s %s", orderColumn, order))
                .list();
        return new PageInfo<>(list);
    }
}
