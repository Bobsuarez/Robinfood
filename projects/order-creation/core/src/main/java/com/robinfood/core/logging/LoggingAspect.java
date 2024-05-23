package com.robinfood.core.logging;

import com.robinfood.core.exceptions.AbstractGenericAppException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private static final String CUSTOM_NAME_LOGGING = "Aspect logging";

    @Pointcut(value = "within(com.robinfood.app.usecases..*)")
    public void pointCutUseCases() {
        //method that intercepts the use cases
    }

    @Pointcut("within(com.robinfood.app.controllers..*) ")
    public void pointcutPost() {
        //method that intercepts the controller with the POST method
    }

    @Pointcut("within(com.robinfood.app.controllers..*) " +
            "&& @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void pointcutGet() {
        //method that intercepts the controller with the GET method
    }

    @SneakyThrows
    @Around("pointCutUseCases()")
    public Object logAroundUseCases(ProceedingJoinPoint pjp) {
        try {
            final String method = pjp.getSignature().getName();
            final String className = pjp.getTarget().getClass().getName();
            log.info("{}: {}.{}() Request", CUSTOM_NAME_LOGGING, className, method);
            Object object = pjp.proceed();
            log.info("{}: {}.{}() Response", CUSTOM_NAME_LOGGING, className, method);
            return object;
        } catch (IllegalArgumentException exception) {
            log.error("Illegal argument " + objectToJson(pjp.getArgs()) + "in " + pjp.getSignature().getName());
            throw exception;
        }

    }

    @AfterThrowing(pointcut = "within(com.robinfood.app.usecases..*)", throwing = "e")
    public void appLoggerException(JoinPoint jp, Throwable e) {
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().toString();
        String origin = methodName.concat(".").concat(className);
        String exceptionType = e.getClass().getCanonicalName();
        String loggingTarget;
        if (e instanceof AbstractGenericAppException) {
            loggingTarget = e.getMessage();
        } else {
            loggingTarget = ExceptionUtils.getStackTrace(e);
        }
        log.error("{}: Error in: {}(), Type: {}, Cause: {}", CUSTOM_NAME_LOGGING, origin, exceptionType, loggingTarget);
    }

    @SneakyThrows
    @Around("pointcutPost()")
    public Object logControllerPost(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        return getObject(pjp, signature, getValuePostAnnotation(signature), getValueClassAnnotation(pjp));
    }

    @SneakyThrows
    @Around("pointcutGet()")
    public Object logControllerGet(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        return getObject(pjp, signature, getValueGetAnnotation(signature), getValueClassAnnotation(pjp));
    }

    public String[] getValuePostAnnotation(MethodSignature signature) {
        PostMapping mappingMethod = signature.getMethod().getAnnotation(PostMapping.class);
        return mappingMethod.value();
    }

    public String[] getValueGetAnnotation(MethodSignature signature) {
        GetMapping mappingMethod = signature.getMethod().getAnnotation(GetMapping.class);
        return mappingMethod.value();
    }

    public String[] getValueClassAnnotation(ProceedingJoinPoint pjp) {
        RequestMapping mappingClass = pjp.getTarget().getClass().getAnnotation(RequestMapping.class);
        return mappingClass.value();
    }

    @NotNull
    @SneakyThrows
    private Object getObject(
            ProceedingJoinPoint pjp, MethodSignature signature,
            String[] value, String[] mappingClassValue
    ) {

        Object req = pjp.getArgs();
        try {
            final StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            Object obj = pjp.proceed();
            ResponseEntity<?> res = (ResponseEntity<?>) obj;
            stopWatch.stop();

            final String path;

            if (value.length > 0) {
                path = mappingClassValue[0] + value[0];
            } else {
                path = mappingClassValue[0] + "";
            }

            log.info("{}", path);
            log.info("{}", objectToJson(req));
            log.info("{}", res.getStatusCode());
            log.info("{}", objectToJson(res.getBody()));
            log.info("\"{}\" executed in {} ms", signature.getName(), stopWatch.getTotalTimeMillis());
            return res;

        } catch (IllegalArgumentException e) {
            log.error("Illegal argument " + objectToJson(pjp.getArgs()));
            throw e;
        }
    }
}
