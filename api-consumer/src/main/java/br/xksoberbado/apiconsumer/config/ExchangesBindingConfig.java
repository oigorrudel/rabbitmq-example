package br.xksoberbado.apiconsumer.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangesBindingConfig {

    private static final String EXCHANGE_A = "exchange-a";
    private static final String EXCHANGE_B = "exchange-b";
    private static final String QUEUE_NAME = "queue-b";

    @Bean
    Declarables exchanges() {
        return new Declarables(
            exchangeA(),
            exchangeB()
        );
    }

    @Bean
    Declarables exchangesBindingQueues() {
        return new Declarables(
            getQueue(),
            QueueUtil.buildDlq(QUEUE_NAME),
            otherQueue()
        );
    }

    @Bean
    Declarables bindings() {
        return new Declarables(
            BindingBuilder.bind(exchangeB()).to(exchangeA()).with("redirect"),
            BindingBuilder.bind(getQueue()).to(exchangeB()).with("redirect"),
            BindingBuilder.bind(otherQueue()).to(exchangeA()).with("redirect")
        );
    }

    private static Queue getQueue() {
        return QueueUtil.buildQueue(QUEUE_NAME);
    }

    private Queue otherQueue() {
        return QueueUtil.buildQueue("redirected");
    }

    private DirectExchange exchangeB() {
        return new DirectExchange(EXCHANGE_B);
    }

    private DirectExchange exchangeA() {
        return new DirectExchange(EXCHANGE_A);
    }
}
