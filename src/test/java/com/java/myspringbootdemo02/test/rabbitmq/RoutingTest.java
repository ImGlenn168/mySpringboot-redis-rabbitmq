package com.java.myspringbootdemo02.test.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RoutingTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    // 交换机
    public final static String fanoutExchangeRouting="direct_routing_exchange";

    @Test
    public void send1(){
        String message="Hello routing1 RabbitMQ";
        rabbitTemplate.convertAndSend(fanoutExchangeRouting,"routing1",message);
    }

    @Test
    public void send2(){
        String message="Hello routing2 RabbitMQ";
        rabbitTemplate.convertAndSend(fanoutExchangeRouting,"routing2",message);
    }

    @Test
    public void send3(){
        String message="Hello routing3 RabbitMQ";
        rabbitTemplate.convertAndSend(fanoutExchangeRouting,"routing3",message);
    }
}
