package br.xksoberbado.apiproducer.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;

@Slf4j
@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class ExampleController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public void send() {
        log.info("Sending message...");

        rabbitTemplate.convertAndSend(
            "my-exchange", "my.key", new Person("Igor", LocalDate.of(1993, Month.APRIL, 1))
        );
    }

    public record Person(String name, LocalDate birthdate) implements Serializable {
    }
}
