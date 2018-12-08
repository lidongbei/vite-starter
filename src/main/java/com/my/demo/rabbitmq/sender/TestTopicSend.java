package com.my.demo.rabbitmq.sender;

import com.my.demo.rabbitmq.config.TopicRabbitConfig;
import org.springframework.stereotype.Component;

/**
 * @author Li
 * @date 2018/12/8 23:08
 */
@Component
public class TestTopicSend extends BaseRabbitMQSend {

    @Override
    public void send() {
        String context = "hi, topic msg";
//        Map<String , Object> map = new HashMap<>();
//        map.put("context",context);
//        map.put("code",200);

        System.err.println("Sender：" + context);

        rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE,TopicRabbitConfig.TOPIC_ALL,context);
    }

    public void sendA() {
        String context = "hi, topicA msg";
        System.err.println("Sender：" + context);
        rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE,TopicRabbitConfig.TOPIC_A,context);
    }

    public void sendB() {
        String context = "hi, topicB msg";
        System.err.println("Sender：" + context);
        rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE,TopicRabbitConfig.TOPIC_B,context);
    }

    public void sendC() {
        String context = "hi, topicC msg";
        System.err.println("Sender：" + context);
        rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE,TopicRabbitConfig.TOPIC_C,context);
    }
}
