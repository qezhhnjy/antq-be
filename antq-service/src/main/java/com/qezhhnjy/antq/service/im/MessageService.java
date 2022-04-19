package com.qezhhnjy.antq.service.im;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.query.MessageQuery;
import com.qezhhnjy.antq.entity.im.Message;

/**
 * @author zhaoyangfu
 * @date 2022/4/18-11:42
 */
public interface MessageService extends IService<Message> {

    PageInfo<Message> message(MessageQuery query);
}
