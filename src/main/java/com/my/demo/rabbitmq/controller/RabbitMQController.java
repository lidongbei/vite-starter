package com.my.demo.rabbitmq.controller;

import com.my.demo.rabbitmq.sender.TestExchangeSend;
import com.my.demo.rabbitmq.sender.TestTopicSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Li
 * @date 2018/12/8 22:02
 */
@RestController
public class RabbitMQController {


    @Autowired
    private TestExchangeSend testExchangeSend;

    @Autowired
    private TestTopicSend testTopicSend;


    @RequestMapping(value = "/sendExchange", method = RequestMethod.GET)
    public String sendExchange() {
        System.err.println("准备发送消息。。。");
        testExchangeSend.send();
        return "success";
    }

    @RequestMapping(value = "/sendTopicAll", method = RequestMethod.GET)
    public String sendTopicAll() {
        System.err.println("准备发送消息。。。");
        testTopicSend.send();
        return "success";
    }

    @RequestMapping(value = "/sendTopicA", method = RequestMethod.GET)
    public String sendTopicA() {
        System.err.println("准备发送消息。。。");
        testTopicSend.sendA();
        return "success";
    }

    @RequestMapping(value = "/sendTopicB", method = RequestMethod.GET)
    public String sendTopicB() {
        System.err.println("准备发送消息。。。");
        testTopicSend.sendB();
        return "success";
    }

    @RequestMapping(value = "/sendTopicC", method = RequestMethod.GET)
    public String sendTopicC() {
        System.err.println("准备发送消息。。。");
        testTopicSend.sendC();
        return "success";
    }
}