package br.xksoberbado.apiconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    private static final String EXCHANGE_NAME = "ex.topic";
    private static final String QUEUE1_NAME = "a.1.b";
    private static final String QUEUE2_NAME = "a.2.b";

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue queueA1() {
        return QueueUtil.buildQueue(QUEUE1_NAME);
    }

    @Bean
    Queue dlqQueueA1() {
        return QueueUtil.buildDlq(QUEUE1_NAME);
    }

    @Bean
    Queue queueA2() {
        return QueueUtil.buildQueue(QUEUE2_NAME);
    }

    @Bean
    Queue dlqQueueA2() {
        return QueueUtil.buildDlq(QUEUE2_NAME);
    }

    @Bean
    Binding bindingA1() {
        return BindingBuilder
            .bind(queueA1())
            .to(topicExchange())
            .with("a.#.b");
    }

    @Bean
    Binding bindingA2() {
        return BindingBuilder
            .bind(queueA2())
            .to(topicExchange())
            .with("a.#.b");
    }
}
