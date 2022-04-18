package com.qezhhnjy.antq.service.im;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qezhhnjy.antq.common.query.MessageQuery;
import com.qezhhnjy.antq.entity.im.Message;

import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2022/4/18-11:42
 */
public interface MessageService extends IService<Message> {

    List<Message> message(MessageQuery query);
}
