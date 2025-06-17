package br.xksoberbado.apiconsumer.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class TopicConfig {

    private static final String EXCHANGE_NAME = "ex.topic";
    private static final String QUEUE_NAME = "";

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue queue() {
        return QueueUtil.buildQueue(QUEUE_NAME, EXCHANGE_NAME);
    }

    @Bean
    Queue dlqQueue() {
        return QueueUtil.buildDlq(QUEUE_NAME);
    }
}
