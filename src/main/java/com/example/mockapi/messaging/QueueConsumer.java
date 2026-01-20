package com.example.mockapi.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    private static final Logger log = LoggerFactory.getLogger(QueueConsumer.class);

    @JmsListener(destination = "demo.queue")
    public void receiveMessage(String message) {
        log.info("Mensaje recibido de la cola 'demo.queue': {}", message);
    }
}

