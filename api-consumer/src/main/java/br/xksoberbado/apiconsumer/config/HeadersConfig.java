package br.xksoberbado.apiconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class HeadersConfig {

    private static final String EXCHANGE_NAME = "ex.headers";
    private static final String QUEUE1_NAME = "h1";
    private static final String QUEUE2_NAME = "h2";

    @Bean
    HeadersExchange headerExchange() {
        return new HeadersExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue queue111() {
        return QueueUtil.buildQueue(QUEUE1_NAME, EXCHANGE_NAME);
    }

    @Bean
    Queue dlqQueue111() {
        return QueueUtil.buildDlq(QUEUE1_NAME);
    }

    @Bean
    Queue queue222() {
        return QueueUtil.buildQueue(QUEUE2_NAME, EXCHANGE_NAME);
    }

    @Bean
    Queue dlqQueue222() {
        return QueueUtil.buildDlq(QUEUE2_NAME);
    }

    @Bean
    Binding binding111() {
        return BindingBuilder
            .bind(queue111())
            .to(headerExchange())
            .whereAll(Map.of("hhh", "111"))
            .match();
    }

    @Bean
    Binding binding222() {
        return BindingBuilder
            .bind(queue222())
            .to(headerExchange())
            .whereAll(Map.of("hhh", "222"))
            .match();
    }
}
