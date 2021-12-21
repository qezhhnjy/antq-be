package com.qezhhnjy.antq.service.sys;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.NoticeVO;
import com.qezhhnjy.antq.entity.sys.Notice;
import com.qezhhnjy.antq.mapper.sys.NoticeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoyangfu
 * @date 2021/12/21-15:37
 */
@Slf4j
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public void add(NoticeVO vo) {
        Notice notice = vo.getNotice();
        if (notice != null) {
            save(notice);
        }
    }

    @Override
    public List<NoticeVO> list(Query query) {
        String search = query.getSearch();
        boolean isSearch = StrUtil.isNotBlank(search);
        return lambdaQuery().like(isSearch, Notice::getTitle, search)
                .or()
                .like(isSearch, Notice::getMessage, search)
                .list()
                .stream()
                .map(NoticeVO::new)
                .collect(Collectors.toList());
    }

    @Override
    public PageInfo<NoticeVO> query(Query query) {
        String search = query.getSearch();
        boolean isSearch = StrUtil.isNotBlank(search);
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<NoticeVO> list = lambdaQuery().like(isSearch, Notice::getTitle, search)
                .or()
                .like(isSearch, Notice::getMessage, search)
                .list()
                .stream()
                .map(NoticeVO::new)
                .collect(Collectors.toList());
        return new PageInfo<>(list);
    }
}
