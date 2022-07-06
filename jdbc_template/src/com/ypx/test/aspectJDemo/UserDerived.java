package com.ypx.test.aspectJDemo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserDerived {
    @Before(value = "execution(* com.ypx.test.aspectJDemo.UserBase.update(..))")
    public void before(){
        System.out.println("事前通知");
    }
    @After(value = "execution(* com.ypx.test.aspectJDemo.UserBase.update(..))")
    public void after(){
        System.out.println("事后通知");
    }
    @AfterReturning(value = "execution(* com.ypx.test.aspectJDemo.UserBase.update(..))")
    public void ar(){
        System.out.println("after returning");
    }
    @AfterThrowing(value = "execution(* com.ypx.test.aspectJDemo.UserBase.update(..))")
    public void at(){
        System.out.println("after throwing");
    }
    @Around(value = "execution(* com.ypx.test.aspectJDemo.UserBase.update(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕前");
        proceedingJoinPoint.proceed();
        System.out.println("环绕后");
    }

}
