package br.xksoberbado.apiconsumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DirectConfig {

    private static final String EXCHANGE_NAME = "ex.direct";
    private static final String QUEUE_NAME = "a.b.c";

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    Declarables directQueues() {
        return new Declarables(
            List.of(
                queue(),
                QueueUtil.buildDlq(QUEUE_NAME)
            )
        );
    }

    private Queue queue() {
        return QueueUtil.buildQueue(QUEUE_NAME);
    }

    @Bean
    Binding directBinding() {
        return BindingBuilder
            .bind(queue())
            .to(directExchange())
            .with(QUEUE_NAME);
    }
}
