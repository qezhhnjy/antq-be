package com.qezhhnjy.antq.im.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zhaoyangfu
 * @date 2022/4/17-18:34
 */
public class ChannelSupervise {

    private static final String ATTR = "username";

    private static final ChannelGroup                     CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ConcurrentMap<String, ChannelId> USERNAME_MAP  = new ConcurrentHashMap<>();

    public static void addChannel(String username, Channel channel) {
        CHANNEL_GROUP.add(channel);
        USERNAME_MAP.put(username, channel.id());
        channel.attr(AttributeKey.valueOf(ATTR)).set(username);
    }

    public static void removeChannel(Channel channel) {
        CHANNEL_GROUP.remove(channel);
        USERNAME_MAP.remove(channel.attr(AttributeKey.valueOf(ATTR)).get().toString());
    }

    public static Channel findChannel(String username) {
        return CHANNEL_GROUP.find(USERNAME_MAP.get(username));
    }

    public static void send2All(TextWebSocketFrame tws) {
        CHANNEL_GROUP.writeAndFlush(tws);
    }
}
