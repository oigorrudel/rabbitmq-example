package br.xksoberbado.apiconsumer.event;

import br.xksoberbado.apiconsumer.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TopicExample {

    @RabbitListener(queues = "a.1.b")
    public void receiveA1(@Payload final Person person) {
        log.info("Received a1b: {}", person);
    }

    @RabbitListener(queues = "a.2.b")
    public void receiveA2(@Payload final Person person) {
        log.info("Received a2b: {}", person);
    }
}
