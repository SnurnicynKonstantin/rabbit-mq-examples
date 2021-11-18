package com.rabbitmq.producer.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ProducerController {

    private final String MESSAGE = "Hi from future.";
    private final String EXCHANGE1 = "exchange1";
    private final String EXCHANGE2 = "exchange2";
    private final String EXCHANGE3 = "exchange3";
    private final String EXCHANGE4 = "exchange4";
    private final String EXCHANGE5 = "exchange5";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/direct1")
    public void sendDirectMessage1() {
        rabbitTemplate.convertAndSend(EXCHANGE4, "key1", MESSAGE);
    }

    @GetMapping("/direct2")
    public void sendDirectMessage2() {
        rabbitTemplate.convertAndSend(EXCHANGE4, "key2", MESSAGE);
    }

    @GetMapping("/fanout")
    public void sendFanoutMessage() {
        rabbitTemplate.setExchange(EXCHANGE1);
        rabbitTemplate.convertAndSend(MESSAGE);
    }

    @GetMapping("/topic1")
    public void sendTopicMessage1() {
        rabbitTemplate.convertAndSend(EXCHANGE3, "some.queue1.string",MESSAGE);
    }

    @GetMapping("/topic2")
    public void sendTopicMessage2() {
        rabbitTemplate.convertAndSend(EXCHANGE3,"some.queue2.string", MESSAGE);
    }

    @GetMapping("/header1")
    public void sendHeaderMessage1() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("way", "1");
        MessageConverter messageConverter = new SimpleMessageConverter();
        Message message = messageConverter.toMessage(MESSAGE, messageProperties);
        rabbitTemplate.send(EXCHANGE2, "", message);
    }

    @GetMapping("/header2")
    public void sendHeaderMessage2() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("way", "2");
        MessageConverter messageConverter = new SimpleMessageConverter();
        Message message = messageConverter.toMessage(MESSAGE, messageProperties);
        rabbitTemplate.send(EXCHANGE2, "", message);
    }
}
