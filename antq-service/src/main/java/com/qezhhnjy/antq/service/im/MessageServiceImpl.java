package com.qezhhnjy.antq.service.im;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qezhhnjy.antq.common.query.MessageQuery;
import com.qezhhnjy.antq.entity.im.Message;
import com.qezhhnjy.antq.entity.sys.User;
import com.qezhhnjy.antq.mapper.im.MessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2022/4/18-11:42
 */
@Slf4j
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    /**
     * 获取私聊/群组的历史聊天记录
     */
    @Override
    public List<Message> message(MessageQuery query) {
        String from = query.getFrom();
        String to = query.getTo();
        Long groupId = query.getGroupId();
        LocalDateTime time = query.getTime();
        return lambdaQuery()
                .eq(StrUtil.isNotBlank(from), Message::getFrom, from)
                .eq(StrUtil.isNotBlank(to), Message::getTo, to)
                .eq(ObjectUtil.isNotNull(groupId), Message::getGroupId, groupId)
                .ge(ObjectUtil.isNotNull(time), Message::getCreateTime, time)
                .list();
    }
}
