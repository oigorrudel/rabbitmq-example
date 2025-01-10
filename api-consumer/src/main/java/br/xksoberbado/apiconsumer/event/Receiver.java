package br.xksoberbado.apiconsumer.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;

@Slf4j
@Component
public class Receiver {

    @RabbitListener(queues = "my-queue")
    public void receive(final Person person) {
        log.info("Received: {}", person);
    }

    public record Person(String name, LocalDate birthdate) implements Serializable {
    }
}
