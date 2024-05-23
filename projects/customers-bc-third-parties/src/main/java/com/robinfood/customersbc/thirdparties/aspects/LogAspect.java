package com.robinfood.customersbc.thirdparties.aspects;

import com.robinfood.customersbc.thirdparties.constants.LogConstants;
import com.robinfood.customersbc.thirdparties.utils.JsonUtils;
import com.robinfood.customersbc.thirdparties.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @SuppressWarnings({"unchecked"})
    @Around("@annotation(com.robinfood.customersbc.thirdparties.annotations.BasicLog)")
    public Object basicLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        LocalDateTime startDate = Instant.ofEpochMilli(startTime).atZone(ZoneId.systemDefault()).toLocalDateTime();

        List<Object> argsList = LogUtils.transformProceedingJoinPointArgs(proceedingJoinPoint.getArgs());
        String argsJson = JsonUtils.convertToJson(argsList);

        log.info("Call to {}, with args: {}", proceedingJoinPoint.getSignature(), argsJson);

        Mono<Object> mono = (Mono<Object>) proceedingJoinPoint.proceed();

        return mono.doOnNext((Object result) -> {
            String resultJson = JsonUtils.convertToJson(result);
            long endTime = System.currentTimeMillis();
            LocalDateTime endDate = Instant.ofEpochMilli(endTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
            log.info(
                "Response from {}, (started at [{}], ended at [{}], total time {} seconds): {}",
                proceedingJoinPoint.getSignature(),
                startDate.toString(), endDate.toString(), ((endTime - startTime) / LogConstants.SECONDS_DIVISOR),
                resultJson
            );
        });
    }
}