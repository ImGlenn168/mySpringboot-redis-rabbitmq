package com.java.myspringbootdemo02.App.service.rabbitmq.publishmodel;

import com.java.myspringbootdemo02.Api.config.mqconfig.RabbitMQConfigPublishModel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = RabbitMQConfigPublishModel.publishQueue2)
public class PublishModelC2 {
    @RabbitHandler
    public void c2(String message){
        System.out.println("C2: "+message);

    }}
