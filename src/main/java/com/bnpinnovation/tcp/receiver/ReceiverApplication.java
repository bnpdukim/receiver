package com.bnpinnovation.tcp.receiver;

import com.bnpinnovation.tcp.receiver.service.ReceiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ReceiverApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =  SpringApplication.run(ReceiverApplication.class, args);
        ReceiveService receiveService = context.getBean(ReceiveService.class);
        receiveService.start();
    }

}
