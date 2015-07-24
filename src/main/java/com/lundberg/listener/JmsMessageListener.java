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
        System.out.println("Incoming message: "+ message.getText());

        // Acknowledge incoming message
        session.createProducer(message.getJMSReplyTo())
                .send(getReplyMessage("ACK for: " + message.getText()));
    }

    private ActiveMQTextMessage getReplyMessage(String replyMessage) throws MessageNotWriteableException {
        ActiveMQTextMessage textMessage = new ActiveMQTextMessage();
        textMessage.setText(replyMessage);
        return textMessage;
    }
}
