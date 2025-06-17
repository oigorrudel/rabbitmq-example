package br.xksoberbado.apiconsumer.event;

import br.xksoberbado.apiconsumer.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FanoutExample {

    @RabbitListener(queues = "a")
    public void receiveA(@Payload final Person person) {
        log.info("Received a: {}", person);
    }

    @RabbitListener(queues = "b")
    public void receiveB(@Payload final Person person) {
        log.info("Received b: {}", person);
    }
}
