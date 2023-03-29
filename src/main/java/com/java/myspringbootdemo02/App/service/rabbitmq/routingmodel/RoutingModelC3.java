package com.java.myspringbootdemo02.App.service.rabbitmq.routingmodel;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "routingQueue3")
public class RoutingModelC3 {
    @RabbitHandler
    public void c1(String message){
        System.out.println("C3: "+message);
    }
}
