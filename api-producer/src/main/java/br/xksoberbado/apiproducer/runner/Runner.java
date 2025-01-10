package br.xksoberbado.apiproducer.runner;

import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    public Runner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    @SneakyThrows
    public void run(final String... args) {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend("my-exchange", "my.key", "Hello from Santa Maria!");

//        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
