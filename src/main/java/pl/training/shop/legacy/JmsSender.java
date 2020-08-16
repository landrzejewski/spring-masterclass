package pl.training.shop.legacy;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@RequiredArgsConstructor
public class JmsSender {

    private final JmsTemplate jmsTemplate;
    private final Queue queue;

    public void send(String text) {
        jmsTemplate.send(queue, session -> session.createTextMessage(text));
    }

}
