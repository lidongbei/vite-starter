package com.my.demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Li
 * @date 2018/12/8 23:00
 *
 * 策略模式
 * topic 是RabbitMQ中最灵活的一种方式，可以根据routing_key自由的绑定不同的队列
 */
@Configuration
public class TopicRabbitConfig {

    public static final String TOPIC_ALL = "topic.#";

    public static final String TOPIC_A = "topic.a";
    public static final String TOPIC_B = "topic.b";
    public static final String TOPIC_C = "topic.c";

    public static final String TOPIC_EXCHANGE = "topicExchange";

    @Bean
    public Queue queueTopicA() {
        return new Queue(TOPIC_A);
    }

    @Bean
    public Queue queueTopicB() {
        return new Queue(TOPIC_B);
    }

    @Bean
    public Queue queueTopicC() {
        return new Queue(TOPIC_C);
    }

    @Bean
    public Queue queueTopicAll() {
        return new Queue(TOPIC_ALL);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeMessageA(Queue queueTopicA, TopicExchange exchange) {
        return BindingBuilder.bind(queueTopicA).to(exchange).with(TOPIC_A);
    }

    @Bean
    Binding bindingExchangeMessageB(Queue queueTopicB, TopicExchange exchange) {
        return BindingBuilder.bind(queueTopicB).to(exchange).with(TOPIC_B);
    }

    @Bean
    Binding bindingExchangeMessageC(Queue queueTopicC, TopicExchange exchange) {
        return BindingBuilder.bind(queueTopicC).to(exchange).with(TOPIC_C);
    }

    @Bean
    Binding bindingExchangeMessageAll(Queue queueTopicAll, TopicExchange exchange) {
        return BindingBuilder.bind(queueTopicAll).to(exchange).with(TOPIC_ALL);
    }

}
