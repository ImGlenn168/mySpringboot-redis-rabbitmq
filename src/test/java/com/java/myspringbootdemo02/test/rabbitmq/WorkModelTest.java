package com.java.myspringbootdemo02.test.rabbitmq;

import com.java.myspringbootdemo02.Api.config.mqconfig.RabbitMQConfigWorkModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WorkModelTest {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void send(){
        for (int i = 0; i < 20; i++) {
            /**
             *  1、第一个参数是交换机，与配置文件中的交换机名称要一样，因为我们要通过这个交换机发送消息到队列中
             * 	2、第二个参数是routingKey，fanout交换机是一种广播机制，会把消息发送到所有与它绑定的队列中
             *     没有routingKey的概念，所以这里用空字符串代替
             *  3、i就是发送到队列中的消息，这里循环20次，发送20条消息到队列中
             */
            rabbitTemplate.convertAndSend(RabbitMQConfigWorkModel.fanoutExchangeWork,"",i);
        }
    }
}
