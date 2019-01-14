package com.iamitshri.katas.aspect;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StopWatch;
import com.iamitshri.katas.annotation.LoggerAnnotation;
import lombok.extern.slf4j.Slf4j;


@Aspect
@Slf4j
@Component
public class LoggingAspect {

    ReflectionUtils reflect;

    @Pointcut("@annotation(edu.wgu.ema.katas.annotation.LoggerAnnotation)")
    protected void methodsWithLogAnnotation() {}

    // @Before("methodsWithLogAnnotation()")
    public void beforeMethod(JoinPoint joinpoint) {
        log.debug("**calling annotated method**");
        log.debug("{} {}", joinpoint.getTarget(), joinpoint.getSignature());
    }

    @Around("methodsWithLogAnnotation()")
    public boolean measureTime(ProceedingJoinPoint pcp) throws Throwable {
        log.info("$$Around Aspect$$");
     
        MethodSignature signature = (MethodSignature) pcp.getSignature();
        Method method = signature.getMethod();
        LoggerAnnotation myAnnotation = method.getAnnotation(LoggerAnnotation.class);
        log.debug("Value on Annotation:{}", myAnnotation.value());
        log.info("{}", pcp.getSourceLocation());
        
        StopWatch sw = new StopWatch();
        sw.start();
        boolean result =  (boolean) pcp.proceed();
        sw.stop();
        log.debug("Time spent: {}",sw.getTotalTimeMillis());
        return result;
    }
}
