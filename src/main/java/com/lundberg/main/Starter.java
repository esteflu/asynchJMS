package com.lundberg.main;


import com.lundberg.listener.JmsMessageListener;
import com.lundberg.sender.JmsMessageSender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Starter {

    public static void main(String... args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");

        JmsMessageSender jmsMessageSender = (JmsMessageSender) context.getBean("jmsMessageSender");
        jmsMessageSender.send("Sending a message to: " + JmsMessageListener.class.getName());

        // sleep for 1 second
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ((ClassPathXmlApplicationContext) context).close();
    }
}
