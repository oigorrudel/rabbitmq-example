package br.xksoberbado.apiconsumer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomMessageRecoverer implements MessageRecoverer {

    @Override
    public void recover(final Message message, final Throwable cause) {
        log.info("Here!");

        throw new AmqpRejectAndDontRequeueException("Rejecting message.", cause);
    }
}
