package br.xksoberbado.apiconsumer.event;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;

@Slf4j
@Component
public class Receiver {

    private Integer count = 1;

    @RabbitListener(queues = "my-queue")
    public void receive(@Payload @Valid final Person person) {
        log.info("Received: {}", person);

        if (person.gender() == Person.Gender.FEMALE) {
            throw new RuntimeException("Erro simulado.");
        }
    }

    @RabbitListener(queues = "my-count-queue")
    public void count(@Payload @Valid final Person person) {
        log.info("Received: {}, Count: {}", person, count++);
    }

    public record Person(String name,
                         LocalDate birthdate,
                         @NotNull
                         Gender gender) implements Serializable {

        enum Gender {
            MALE, FEMALE
        }
    }
}
