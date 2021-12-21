package com.qezhhnjy.antq.web.controller;

import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.NoticeVO;
import com.qezhhnjy.antq.entity.sys.Notice;
import com.qezhhnjy.antq.service.sys.NoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2021/12/21-15:42
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    @PostMapping("/add")
    public BaseResult<NoticeVO> add(@RequestBody NoticeVO vo) {
        noticeService.add(vo);
        return BaseResult.success(vo);
    }

    @DeleteMapping("/delete")
    public BaseResult<Void> delete(@RequestParam Long id) {
        noticeService.removeById(id);
        return BaseResult.success();
    }

    @PutMapping("/update")
    public BaseResult<NoticeVO> update(@RequestBody NoticeVO vo) {
        noticeService.updateById(vo.getNotice());
        return BaseResult.success(vo);
    }

    @GetMapping("/detail")
    public BaseResult<NoticeVO> detail(Long id) {
        Notice notice = noticeService.getById(id);
        return BaseResult.success(new NoticeVO(notice));
    }

    @PostMapping("/list")
    public BaseResult<List<NoticeVO>> list(@RequestBody Query query) {
        List<NoticeVO> list = noticeService.list(query);
        return BaseResult.success(list);
    }

    @PostMapping("/query")
    public BaseResult<PageInfo<NoticeVO>> query(@RequestBody Query query) {
        PageInfo<NoticeVO> pageInfo = noticeService.query(query);
        return BaseResult.success(pageInfo);
    }
}
