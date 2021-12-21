package com.qezhhnjy.antq.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.NoticeVO;
import com.qezhhnjy.antq.entity.sys.Notice;

import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2021/12/21-15:37
 */
public interface NoticeService extends IService<Notice> {

    void add(NoticeVO vo);

    List<NoticeVO> list(Query query);

    PageInfo<NoticeVO> query(Query query);
}
