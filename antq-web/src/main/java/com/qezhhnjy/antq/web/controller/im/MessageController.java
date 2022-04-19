package com.qezhhnjy.antq.web.controller.im;

import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.query.MessageQuery;
import com.qezhhnjy.antq.entity.im.Message;
import com.qezhhnjy.antq.service.im.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author zhaoyangfu
 * @date 2022/4/18-15:38
 */
@RestController
@Slf4j
@RequestMapping("/im-message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @PostMapping("/list")
    public BaseResult<PageInfo<Message>> list(@RequestBody @Valid MessageQuery query) {
        PageInfo<Message> info = messageService.message(query);
        return BaseResult.success(info);
    }

}
