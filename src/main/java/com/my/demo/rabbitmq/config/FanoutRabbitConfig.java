package com.my.demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Li
 * @date 2018/12/8 22:33
 *
 * 广播模式
 * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
 */
@Configuration
public class FanoutRabbitConfig {

    public static final String FANOUT_A = "fanout.A";
    public static final String FANOUT_B = "fanout.B";
    public static final String FANOUT_C = "fanout.C";

    public static final String FANOUT_EXCHANGE = "fanoutExchange";

    @Bean
    public Queue AMessage() {
        return new Queue(FANOUT_A);
    }

    @Bean
    public Queue BMessage() {
        return new Queue(FANOUT_B);
    }

    @Bean
    public Queue CMessage() {
        return new Queue(FANOUT_C);
    }

    /**
     * Fanout 交换机
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
}
