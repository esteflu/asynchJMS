package com.lundberg.sender;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Message;

@Service
public class JmsMessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String text) {
        jmsTemplate.send(session -> {
            Message message = session.createTextMessage(text);
            message.setJMSReplyTo(new ActiveMQQueue("Recv2Send"));
            return message;
        });
    }
}
