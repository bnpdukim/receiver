package com.bnpinnovation.tcp.receiver.service;

import com.bnpinnovation.tcp.receiver.dto.OverlordDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.*;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class APProtocolInitialize extends ChannelInitializer<Channel> {
    @Autowired
    ObjectMapper objectMapper;
    @Override
    protected void initChannel(Channel ch) {
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast( new LengthFieldBasedFrameDecoder(64*1024, 0 ,4));
        pipeline.addFirst(new LoggingHandler(LogLevel.INFO));
        pipeline.addLast( new JsonObjectDecoder() );
        pipeline.addLast(new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ByteBuf jsonByteBuf = (ByteBuf) msg;
                OverlordDto.Ap ap = objectMapper.readValue(ByteBufUtil.getBytes(jsonByteBuf), OverlordDto.Ap.class);
                System.out.println("ap : "+ ap.toString());
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                cause.printStackTrace();
                ctx.close();
            }
        });
    }
}
