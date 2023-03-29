package com.java.myspringbootdemo02.App.service.rabbitmq.workmodel;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "workQueue")
public class WorkModelServiceC1 {

    // 注意，生产者生产的是Integer类型，所以这里的参数应该接受的是Integer类型
    @RabbitHandler
    public void getWorkQueue(Integer message){
        try {
            Thread.sleep(100);
            System.out.println("c1: "+message);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
