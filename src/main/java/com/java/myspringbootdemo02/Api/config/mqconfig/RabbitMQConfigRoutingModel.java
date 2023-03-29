package com.java.myspringbootdemo02.Api.config.mqconfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigRoutingModel {

    /**
     * 队列1绑定两个路由键
     * 队列2和队列3绑定相同的路由键
     */

    public final static String fanoutExchangeRouting="direct_routing_exchange";
    public final static String routingQueue1="routingQueue1";
    public final static String routingQueue2="routingQueue2";
    public final static String routingQueue3="routingQueue3";
    public final static String routing1="routing1";
    public final static String routing2="routing2";
    public final static String routing3="routing3";

    // 创建交换机
    @Bean(name = "fanoutExchangeRouting")
    public DirectExchange directExchange(){
        return new DirectExchange(fanoutExchangeRouting,true,false);
    }

    // 创建队列1
    @Bean
    public Queue routingQueue1(){
        return new Queue(routingQueue1,true);
    }

    // 创建队列2
    @Bean
    public Queue routingQueue2(){
        return new Queue(routingQueue2,true);
    }

    // 创建队列3
    @Bean
    public Queue routingQueue3(){
        return new Queue(routingQueue3,true);
    }

    //绑定交换机和队列1，并指定队列的路由键1
    @Bean
    public Binding bindingRoutingQueue1(){
        return BindingBuilder.bind(routingQueue1()).to(directExchange()).with(routing1);
    }

    //绑定交换机和队列1，并指定队列的路由键3，即队列1绑定了两个路由键
    @Bean
    public Binding bindingRoutingQueue1_3(){
        return BindingBuilder.bind(routingQueue1()).to(directExchange()).with(routing3);
    }

    //绑定交换机和队列2，并指定队列的路由键2
    @Bean
    public Binding bindingRoutingQueue2(){
        return BindingBuilder.bind(routingQueue2()).to(directExchange()).with(routing2);
    }

    //绑定交换机和队列3，并指定队列的路由键2
    @Bean
    public Binding bindingRoutingQueue3(){
        return BindingBuilder.bind(routingQueue3()).to(directExchange()).with(routing2);
    }
}
