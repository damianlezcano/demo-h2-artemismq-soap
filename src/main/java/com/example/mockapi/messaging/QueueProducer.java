package com.example.mockapi.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer {

    private static final Logger log = LoggerFactory.getLogger(QueueProducer.class);
    private static final String DESTINATION_QUEUE = "demo.queue";

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String message) {
        log.info("Enviando mensaje a la cola '{}': {}", DESTINATION_QUEUE, message);
        jmsTemplate.convertAndSend(DESTINATION_QUEUE, message);
    }
}
