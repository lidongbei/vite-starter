package com.my.demo.rabbitmq.receiver;

import com.my.demo.rabbitmq.config.TopicRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Li
 * @date 2018/12/8 23:17
 */
@Component
@RabbitListener(queues = TopicRabbitConfig.TOPIC_A)
public class TcpicReceiverA {

    @RabbitHandler
    public void process(String context) {
        System.out.println("TcpicReceiverA  : " + context);
    }
}
