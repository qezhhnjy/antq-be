package com.qezhhnjy.antq.im.server;

import cn.hutool.core.util.StrUtil;
import com.qezhhnjy.antq.entity.im.Message;
import com.qezhhnjy.antq.im.util.ConvertUtil;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaoyangfu
 * @date 2022/4/17-18:34
 */
public class ChannelManager {

    private static final String ATTR = "username";

    private static final ChannelGroup                       CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ConcurrentHashMap<String, Channel> USERNAME_MAP  = new ConcurrentHashMap<>();

    public static void addChannel(String username, Channel channel) {
        channel.attr(AttributeKey.valueOf(ATTR)).set(username);
        CHANNEL_GROUP.add(channel);
        USERNAME_MAP.put(username, channel);
    }

    public static void removeChannel(Channel channel) {
        if (channel != null) {
            channel.close();
            CHANNEL_GROUP.remove(channel);
            USERNAME_MAP.remove(channel.attr(AttributeKey.valueOf(ATTR)).get().toString());
        }
    }

    public static Optional<Channel> findChannel(String username) {
        return Optional.ofNullable(USERNAME_MAP.get(username));
    }

    public static void send(Message message) {
        String from = message.getSend();
        String to = message.getReceive();
        if (StrUtil.isAllNotBlank(from, to)) {
            findChannel(from).ifPresent(channel -> channel.writeAndFlush(new TextWebSocketFrame(ConvertUtil.format(message))));
            if (!StrUtil.equals(from, to))
                findChannel(to).ifPresent(channel -> channel.writeAndFlush(new TextWebSocketFrame(ConvertUtil.format(message))));
        } else {
            CHANNEL_GROUP.writeAndFlush(new TextWebSocketFrame(ConvertUtil.format(message)));
        }
    }

    public static ChannelGroup group() {
        return CHANNEL_GROUP;
    }

    public static ConcurrentHashMap<String, Channel> usernameMap() {
        return USERNAME_MAP;
    }

}
