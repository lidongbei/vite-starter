package com.my.demo.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Li
 * @date 2018/12/8 22:44
 */
public class BaseRabbitMQSend {

    @Autowired
    protected AmqpTemplate rabbitTemplate;

    public void send(){
        System.err.println("没有实现 send() 方法");
    }
}
