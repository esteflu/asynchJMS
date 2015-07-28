package com.lundberg.listener;


import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class JmsMessageListener implements SessionAwareMessageListener<TextMessage>{

    @Override
    public void onMessage(TextMessage message, Session session) throws JMSException {
        // Incoming message
        System.out.println("Incoming content: "+ message.getText());
        System.out.println("Incoming Message Id: "+ message.getJMSMessageID());

        // Acknowledge incoming message
        session.createProducer(message.getJMSReplyTo())
                .send(getReplyMessage(message));
    }

    private ActiveMQTextMessage getReplyMessage(TextMessage replyMessage) throws JMSException {
        ActiveMQTextMessage textMessage = new ActiveMQTextMessage();
        textMessage.setText("Ack: " + replyMessage.getText());
        textMessage.setCorrelationId(replyMessage.getJMSMessageID());
        return textMessage;
    }
}
