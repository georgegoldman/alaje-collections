package com.hydrogenhr.core.aop;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 10/1/24
 * Time: 12:59 PM
 */
@Aspect
@Service
@Slf4j
public class ExecutionTimeAspect {
    @Around("@annotation(com.hydrogenhr.core.annotations.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();

        Object proceed = joinPoint.proceed();
        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime) / 1_000_000;

        log.info("Method {} executed in {} ms", joinPoint.getSignature(), executionTime);
        return proceed;
    }
}
