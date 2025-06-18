package br.xksoberbado.apiconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    private static final String EXCHANGE_NAME = "ex.fanout";
    private static final String QUEUE1_NAME = "a";
    private static final String QUEUE2_NAME = "b";

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue queue1() {
        return QueueUtil.buildQueue(QUEUE1_NAME);
    }

    @Bean
    Queue dlqQueue1() {
        return QueueUtil.buildDlq(QUEUE1_NAME);
    }

    @Bean
    Queue queue2() {
        return QueueUtil.buildQueue(QUEUE2_NAME);
    }

    @Bean
    Queue dlqQueue2() {
        return QueueUtil.buildDlq(QUEUE2_NAME);
    }

    @Bean
    Binding binding1() {
        return BindingBuilder
            .bind(queue1())
            .to(fanoutExchange());
    }

    @Bean
    Binding binding2() {
        return BindingBuilder
            .bind(queue2())
            .to(fanoutExchange());
    }
}
