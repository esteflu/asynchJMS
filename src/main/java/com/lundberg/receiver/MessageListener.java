package com.lundberg.receiver;


import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MessageListener implements SessionAwareMessageListener<TextMessage>{

    @Override
    public void onMessage(TextMessage textMessage, Session session) throws JMSException {
        //TODO

    }
}
