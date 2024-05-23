package com.robinfood.paymentmethodsbc.aspects;

import static com.robinfood.paymentmethodsbc.constants.AppConstants.SECONDS_DIVISOR;
import static com.robinfood.paymentmethodsbc.utils.JsonUtils.convertToJson;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("@annotation(com.robinfood.paymentmethodsbc.annotations.BasicLog)")
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
        log.info(
            "Response from {} (started at [{}], ended at [{}], "
            + "total time {} seconds): {}", proceedingJoinPoint.getSignature(),
            startDate.toString(), endDate.toString(), (endTime - startTime) / SECONDS_DIVISOR,
            resultJson
        );

        return result;
    }
}
