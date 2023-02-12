package com.test.superhero.superhero.config.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@within(LogExecutionTime) || @annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = null;

        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {

            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("{} executed in {} ms", joinPoint.getSignature().toShortString(), endTime - startTime);
            return result;
        }
    }
}
