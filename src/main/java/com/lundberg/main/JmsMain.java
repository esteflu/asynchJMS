package com.lundberg.main;

import com.lundberg.sender.JmsMessageSender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmsMain {

    public static void main(String... args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");

        JmsMessageSender jmsMessageSender = (JmsMessageSender) context.getBean("jmsMessageSender");
        jmsMessageSender.send("Sender to Receiverlistener");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ((ClassPathXmlApplicationContext) context).close();
    }
}
