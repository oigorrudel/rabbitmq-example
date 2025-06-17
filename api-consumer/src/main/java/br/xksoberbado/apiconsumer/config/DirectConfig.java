package br.xksoberbado.apiconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    private static final String EXCHANGE_NAME = "ex.direct";
    private static final String QUEUE_NAME = "a.b.c";

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue queue() {
        return QueueUtil.buildQueue(QUEUE_NAME, EXCHANGE_NAME);
    }

    @Bean
    Queue dlqQueue() {
        return QueueUtil.buildDlq(QUEUE_NAME);
    }

    @Bean
    Binding directBinding() {
        return BindingBuilder
            .bind(queue())
            .to(directExchange())
            .with(QUEUE_NAME);
    }
}
