package com.robinfood.taxes.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("@annotation(com.robinfood.taxes.annotations.BasicLog)")
    public Object basicLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Call to {}, with args {}", proceedingJoinPoint.getSignature(), proceedingJoinPoint.getArgs());
        Long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        log.info("Response from {} with args {} :{} - with time:{}",
            proceedingJoinPoint.getSignature(),
            proceedingJoinPoint.getArgs(),
            result,
            endTime - startTime
        );
        return result;
    }
}

