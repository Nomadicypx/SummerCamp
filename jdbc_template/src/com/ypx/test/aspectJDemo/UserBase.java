package com.ypx.test.aspectJDemo;

import org.springframework.stereotype.Component;

@Component
public class UserBase {
    public void update(){
//        int i = 10/0;
        System.out.println("update输出");
    }
}
