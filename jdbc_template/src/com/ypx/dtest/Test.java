package com.ypx.dtest;

import com.ypx.entity.T_User;
import com.ypx.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    @org.junit.Test
    public void testJDBCTemplateAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        BookService bs = context.getBean("bookService", BookService.class);
        System.out.println(bs);
        T_User tu = context.getBean("t_User", T_User.class);//注意Component注解id的命名，如果不写的话是t_User
        tu.setUserid("1");
        tu.setUsername("java");
        tu.setUserstatus("now");
        bs.addUser(tu);
    }
}
