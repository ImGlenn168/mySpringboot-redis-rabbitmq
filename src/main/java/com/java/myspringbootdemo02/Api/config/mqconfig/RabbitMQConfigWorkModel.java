package com.java.myspringbootdemo02.Api.config.mqconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigWorkModel {

    // 交换机
    public final static String fanoutExchangeWork="fanout_work_exchange";

    // 创建交换机
    @Bean(name = "fanoutExchangeWork")
    public FanoutExchange fanoutExchange(){
        /**
         * 三个参数
         * param1:交换机的名称
         * param2:是否持久化，默认是false，表示暂存队列，只有当前链接有效；
         *        如果为true，表示持久化队列，会被存储在磁盘上
         * param3:是否自动删除，默认是false。当没有生产者或消费者使用这个交换机时将会被删除
         *        一般设置一下队列的持久化就好
         */
        return new FanoutExchange(fanoutExchangeWork,true,false);
    }

    // 创建队列
    @Bean
    public Queue workQueue(){
        /**
         * param1:队列名称
         * param2:是否持久化，默认是false
         * 持久化队列：会被存储在磁盘上，当消息代理重启时，仍然存在
         * 暂存队列：当前连接有效
         */
        return new Queue("workQueue",true);
    }

    // 绑定交换机和队列
    @Bean
    public Binding bindingWorkQueue(){
        return BindingBuilder.bind(workQueue()).to(fanoutExchange());
    }
}
