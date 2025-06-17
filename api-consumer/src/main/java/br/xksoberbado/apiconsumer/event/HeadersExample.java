package br.xksoberbado.apiconsumer.event;

import br.xksoberbado.apiconsumer.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeadersExample {

    @RabbitListener(queues = "h1")
    public void receiveH1(@Payload final Person person) {
        log.info("Received h1: {}", person);
    }

    @RabbitListener(queues = "h2")
    public void receiveH2(@Payload final Person person) {
        log.info("Received h2: {}", person);
    }
}
