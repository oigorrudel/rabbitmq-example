package br.xksoberbado.apiproducer.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class ExampleController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public void send(@RequestBody final Person person) {
        log.info("Sending message...");

        rabbitTemplate.convertAndSend("my-exchange", "my.key", person);
    }

    public record Person(String name,
                         LocalDate birthdate,
                         Gender gender) implements Serializable {
        enum Gender {
            MALE, FEMALE
        }
    }
}
