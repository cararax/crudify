package com.carara.crudify.aspect;

import com.carara.crudify.annotation.CrudOperations;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CrudOperationsAspect {

    @Around("execution(* com.carara.crudify.annotation..*.*(..)) && @within(crudOperations)")
    public Object checkCRUDOptions(ProceedingJoinPoint joinPoint, CrudOperations crudOperations) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();

        if ("create".equals(methodName) && !crudOperations.create() ||
                "read".equals(methodName) && !crudOperations.read() ||
                "readById".equals(methodName) && !crudOperations.readById() ||
                "update".equals(methodName) && !crudOperations.update() ||
                "delete".equals(methodName) && !crudOperations.delete()) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        return joinPoint.proceed();
    }
}
