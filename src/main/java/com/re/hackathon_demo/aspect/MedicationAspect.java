package com.re.hackathon_demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order
public class MedicationAspect {

    @Before("execution(* com.re.hackathon_demo.service.*.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("Bắt đầu thực thi  hàm " + joinPoint.getSignature().getName());
    }

}
