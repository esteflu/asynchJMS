package com.lundberg.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Starter {

    public static void main(String... args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");


    }
}
