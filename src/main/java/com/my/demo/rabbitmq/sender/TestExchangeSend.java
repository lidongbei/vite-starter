package com.my.demo.rabbitmq.sender;

import com.my.demo.rabbitmq.config.FanoutRabbitConfig;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Li
 * @date 2018/12/8 22:42
 */
@Component
public class TestExchangeSend extends BaseRabbitMQSend {

    @Override
    public void send() {
        String context = "hi, fanout msg";
        Map<String , Object> map = new HashMap<>();
        map.put("context",context);
        map.put("code",200);

        System.err.println("Sender：" + context);
        rabbitTemplate.convertAndSend(FanoutRabbitConfig.FANOUT_EXCHANGE,"",map);
    }
}
