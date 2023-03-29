package com.java.myspringbootdemo02.App.service.rabbitmq.publishmodel;

import com.java.myspringbootdemo02.Api.config.mqconfig.RabbitMQConfigPublishModel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = RabbitMQConfigPublishModel.publishQueue1)
public class PublishModelC1 {
    @RabbitHandler
    public void c1(String message){
        System.out.println("C1: "+message);

    }}
