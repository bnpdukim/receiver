package com.bnpinnovation.tcp.receiver.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

public interface ReceiveService {
    void start();

    @Service
    @Slf4j
    class Default implements ReceiveService {
        @Autowired
        @Qualifier("tcpSocketAddr")
        private InetSocketAddress port;

        @Autowired
        private APProtocolInitialize apProtocolInitialize;

        @Override
        public void start(){
            EventLoopGroup bossGroup = new NioEventLoopGroup(1);
            EventLoopGroup workerGroup = new NioEventLoopGroup();

            try{
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(apProtocolInitialize);
                ChannelFuture future = b.bind(port).sync();
                future.channel().closeFuture().sync();
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        }
    }


}
