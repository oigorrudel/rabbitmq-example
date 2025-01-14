package br.xksoberbado.apiconsumer.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RabbitMQConfigs {

    private static final String EXCHANGE_NAME = "my-exchange";
    public static final String DL_EXCHANGE_NAME = "";

    private static final String QUEUE_NAME = "my-queue";
    private static final String DL_QUEUE_NAME = "my-queue.dlq";

    private final LocalValidatorFactoryBean validator;

    @Bean
    Queue queue() {
        return QueueBuilder
            .durable(QUEUE_NAME)
            .deadLetterExchange(DL_EXCHANGE_NAME)
            .deadLetterRoutingKey(DL_QUEUE_NAME)
            .build();
    }

    @Bean
    Queue dlqQueue() {
        return QueueBuilder
            .durable(DL_QUEUE_NAME)
            .build();
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(final Queue queue,
                    final TopicExchange exchange) {
        return BindingBuilder.bind(queue)
            .to(exchange)
            .with("my.#");
    }

    @Bean
    RabbitListenerConfigurer rabbitListenerConfigurer() {
        return registrar -> registrar.setValidator(validator);
    }
}
