package com.carara.crudify.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.carara.crudify.service.GenericService.*(..)) || execution(* com.carara.crudify.controller.GenericController.*(..))")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        logger.info("Entered: {}.{} with arguments: {}", className, methodName, args);

        Object result = joinPoint.proceed();

        logger.info("Exited: {}.{} with result: {}", className, methodName, result);

        return result;
    }
}


