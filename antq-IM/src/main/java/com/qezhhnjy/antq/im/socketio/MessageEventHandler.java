package com.qezhhnjy.antq.im.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zhaoyangfu
 * @date 2022/4/17-19:56
 */
@Component
@Slf4j
public class MessageEventHandler {

    @Resource
    private SocketIOServer socketIoServer;

    public static ConcurrentMap<String, SocketIOClient> socketIOClientMap = new ConcurrentHashMap<>();

    /**
     * 客户端连接的时候触发
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        String username = client.getHandshakeData().getSingleUrlParam("username");
        socketIOClientMap.put(username, client);
        //回发消息
        client.sendEvent("messageEvent", "connect success");
        log.info("客户端已连接{}:username=>{}", client.getSessionId(),username);
    }

    /**
     * 客户端关闭连接时触发
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String username = client.getHandshakeData().getSingleUrlParam("username");
        socketIOClientMap.remove(username);
        log.warn("客户端断开连接{}:username=>{}",client.getSessionId(), username);
    }

    /**
     * 客户端事件
     */
    @OnEvent(value = "messageEvent")
    public void messageEvent(SocketIOClient client, String data, AckRequest request) {
        log.info("发来消息：" + data);
        //回发消息
        client.sendEvent("messageEvent", "我是服务器发送的信息");
        //广播消息
        sendBroadcast();
    }

    /**
     * 广播消息
     */
    public void sendBroadcast() {
        for (SocketIOClient client : socketIOClientMap.values()) {
            if (client.isChannelOpen()) {
                client.sendEvent("Broadcast", "当前时间", System.currentTimeMillis());
            }
        }

    }
}
