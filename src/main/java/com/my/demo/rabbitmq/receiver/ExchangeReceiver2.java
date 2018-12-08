package com.my.demo.rabbitmq.receiver;

import com.my.demo.rabbitmq.config.FanoutRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Li
 * @date 2018/12/8 22:49
 */
@Component
@RabbitListener(queues = FanoutRabbitConfig.FANOUT_B)
public class ExchangeReceiver2 {

    @RabbitHandler
    public void process(Map<String,Object> context) {
        System.out.println("Receiver2  : " + context);
    }
}
