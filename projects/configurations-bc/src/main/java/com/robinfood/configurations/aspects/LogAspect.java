package com.robinfood.configurations.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static com.robinfood.configurations.utils.JsonUtils.convertToJson;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Aspect
@Component
@Slf4j
public class LogAspect {

    private static final double SECONDS_DIVISOR = 1000;

    @Around("@annotation(com.robinfood.configurations.annotations.BasicLog)")
    public Object basicLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String argsJson = convertToJson(proceedingJoinPoint.getArgs());
        log.info("Call to {}, with args: {}", proceedingJoinPoint.getSignature(), argsJson);
        long startTime = System.currentTimeMillis();
        LocalDateTime startDate = Instant.ofEpochMilli(startTime).atZone(ZoneId.systemDefault())
            .toLocalDateTime();
        Object result = proceedingJoinPoint.proceed();
        String resultJson = convertToJson(result);
        long endTime = System.currentTimeMillis();
        LocalDateTime endDate = Instant.ofEpochMilli(endTime).atZone(ZoneId.systemDefault())
            .toLocalDateTime();
        log.info("Response from {} with args [{}] (started at [{}], ended at [{}], "
                + "total time {} seconds): {}", proceedingJoinPoint.getSignature(), argsJson,
            startDate.toString(), endDate.toString(), (endTime - startTime) / SECONDS_DIVISOR,
            resultJson);

        return result;
    }
}
