package br.xksoberbado.apiproducer.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("v1/persons")
@RequiredArgsConstructor
public class ExampleController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public void send(@RequestHeader String exchange,
                     @RequestHeader(required = false) String rk,
                     @RequestHeader(required = false) String hhh,
                     @RequestBody final Person person) {
        log.info("Sending message...");

        Optional.ofNullable(hhh)
            .ifPresentOrElse(
                k -> sendWithHeaders(exchange, k, person),
                () -> send(exchange, rk, person));
    }

    @SneakyThrows
    private void sendWithHeaders(final String exchange,
                                 final String header,
                                 final Person person) {
        final MessagePostProcessor postProcessor = message -> {
            message.getMessageProperties().setHeader("hhh", header);

            return message;
        };

        rabbitTemplate.convertAndSend(exchange, "", person, postProcessor);
    }

    private void send(final String exchange,
                      final String rk,
                      final Person person) {
        rabbitTemplate.convertAndSend(exchange, rk, person);
    }

    public record Person(String name,
                         LocalDate birthdate,
                         Gender gender) {
        enum Gender {
            MALE, FEMALE
        }
    }
}
