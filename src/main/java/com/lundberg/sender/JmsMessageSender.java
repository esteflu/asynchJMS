package com.lundberg.sender;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Service
public class JmsMessageSender implements SessionAwareMessageListener<TextMessage> {

    private static final String ACK_QUEUE = "ackQueue";

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String text) {
        jmsTemplate.send(session -> {
            Message message = session.createTextMessage(text);
            message.setJMSReplyTo(new ActiveMQQueue(ACK_QUEUE));
            return message;
        });
    }

    @Override
    public void onMessage(TextMessage textMessage, Session session) throws JMSException {
        //Consume ack
        System.out.println(textMessage.getText());
    }
}
