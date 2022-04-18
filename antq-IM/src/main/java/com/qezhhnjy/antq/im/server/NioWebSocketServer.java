package com.qezhhnjy.antq.im.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author zhaoyangfu
 * @date 2022/4/17-18:32
 */
@Slf4j
@Component
public class NioWebSocketServer {

    @Resource
    private NioWebSocketChannelInitializer nioWebSocketChannelInitializer;

    private final NioEventLoopGroup boss = new NioEventLoopGroup();
    private final NioEventLoopGroup work = new NioEventLoopGroup();

    private Channel channel;

    @PostConstruct
    public void init() throws InterruptedException {
        log.info("正在启动websocket服务器");
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, work);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(nioWebSocketChannelInitializer);
        channel = bootstrap.bind(11009).sync().channel();
        log.info("webSocket服务器启动成功：" + channel);
    }

    @PreDestroy
    public void shutdown() {
        if (channel != null) {
            channel.close();
        }
        boss.shutdownGracefully();
        work.shutdownGracefully();
        log.info("websocket服务器已关闭");
    }

}
