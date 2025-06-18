package br.xksoberbado.apiconsumer.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QueueUtil {

    private static final String DLQ_SUFFIX = ".dlq";

    static Queue buildQueue(final String queueName) {
        return QueueBuilder
            .durable(queueName)
            .deadLetterExchange("")
            .deadLetterRoutingKey(queueName + DLQ_SUFFIX)
            .build();
    }

    static Queue buildDlq(final String queueName) {
        return QueueBuilder
            .durable(queueName + DLQ_SUFFIX)
            .build();
    }
}
