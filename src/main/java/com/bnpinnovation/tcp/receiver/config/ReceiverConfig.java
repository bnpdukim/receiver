package com.bnpinnovation.tcp.receiver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class ReceiverConfig {
    @Value("${tcp.port}")
    private int port;

    @Bean(name="tcpSocketAddr")
    public InetSocketAddress tcpPort() {
        return new InetSocketAddress(port);
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }
}
