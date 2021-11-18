package com.rabbitmq.listener.service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class ListenerService {

    @RabbitListener(queues = "queue1")
    public void processMessageToQueue1(String message) {
        System.out.println("Message from QUEUE1: " + message);
    }

    @RabbitListener(queues = "queue2")
    public void processMessageToQueue2(String message) {
        System.out.println("Message from QUEUE2: " + message);
    }

    @RabbitListener(queues = "queue3")
    public void processMessageToQueue3(String message) {
        System.out.println("Message from QUEUE3: " + message);
    }
}
