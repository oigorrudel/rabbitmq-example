package br.xksoberbado.apiconsumer.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.ContainerCustomizer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RabbitMQConfigs {

    private final LocalValidatorFactoryBean validator;

    @Value("${spring.application.name}")
    private String tag;

    @Bean
    RabbitListenerConfigurer rabbitListenerConfigurer() {
        return registrar -> registrar.setValidator(validator);
    }

    @Bean
    ContainerCustomizer<SimpleMessageListenerContainer> containerCustomizer() {
        return container -> container.setConsumerTagStrategy(queue -> tag);
    }
}
