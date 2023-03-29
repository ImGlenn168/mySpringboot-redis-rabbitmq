package com.java.myspringbootdemo02.test.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublishTest {
    @Resource
    private RabbitTemplate rabbitTemplate;

    // 交换机
    public final static String fanoutExchangePublish="fanout_publish_exchange";

    @Test
    public void send(){
        String message="Hello Publish RabbitMQ";
        rabbitTemplate.convertAndSend(fanoutExchangePublish,"",message);
    }
}
