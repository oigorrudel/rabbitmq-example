package br.xksoberbado.apiconsumer.event;

import br.xksoberbado.apiconsumer.domain.Person;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExchangesBindingExample {

    @RabbitListener(queues = "queue-b")
    public void receive(@Payload @Valid final Person person) {
        log.info("Received queue-b: {}", person);
    }

    @RabbitListener(queues = "redirected")
    public void receive2(@Payload @Valid final Person person) {
        log.info("Received redirected: {}", person);
    }
}
