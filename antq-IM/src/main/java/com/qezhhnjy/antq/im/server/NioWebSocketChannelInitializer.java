package com.qezhhnjy.antq.im.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

/**
 * @author zhaoyangfu
 * @date 2022/4/17-18:33
 */
@Component
public class NioWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        // 设置log监听器，并且日志级别为debug，方便观察运行流程
        // ch.pipeline().addLast("logging", new LoggingHandler(LogLevel.INFO));
        // 设置解码器
        ch.pipeline().addLast("http-codec", new HttpServerCodec());
        // 聚合器，使用websocket会用到
        ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
        // 用于大数据的分区传输
        ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        // 自定义的业务handler
        ch.pipeline().addLast("handler", new NioWebSocketHandler());
    }
}
