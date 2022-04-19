package com.qezhhnjy.antq.service.im;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.query.MessageQuery;
import com.qezhhnjy.antq.entity.im.Message;
import com.qezhhnjy.antq.mapper.im.MessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public PageInfo<Message> message(MessageQuery query) {
        String send = query.getSend();
        String receive = query.getReceive();
        Long groupId = query.getGroupId();
        LocalDateTime time = query.getTime();
        String search = query.getSearch();
        boolean isSearch = StrUtil.isNotBlank(search);

        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<Message> list = lambdaQuery()
                .eq(StrUtil.isNotBlank(send), Message::getSend, send)
                .eq(StrUtil.isNotBlank(receive), Message::getReceive, receive)
                .eq(ObjectUtil.isNotNull(groupId), Message::getGroupId, groupId)
                .and(isSearch, i -> i.like(Message::getSend, search).or().like(Message::getReceive, search)
                        .or().like(Message::getContent, search))
                .ge(ObjectUtil.isNotNull(time), Message::getCreateTime, time)
                .orderByDesc(Message::getId)
                .list();
        return new PageInfo<>(list);
    }
}
