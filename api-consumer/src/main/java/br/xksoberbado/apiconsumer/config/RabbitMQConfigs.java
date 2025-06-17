package br.xksoberbado.apiconsumer.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RabbitMQConfigs {

    private final LocalValidatorFactoryBean validator;

    @Bean
    RabbitListenerConfigurer rabbitListenerConfigurer() {
        return registrar -> registrar.setValidator(validator);
    }
}
