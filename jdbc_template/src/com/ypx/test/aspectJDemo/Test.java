package com.ypx.test.aspectJDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    @org.junit.Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        UserBase userBase = context.getBean("userBase",UserBase.class);
        userBase.update();
    }
}
