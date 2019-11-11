package cn.pasteme.backend.interceptor;

import cn.pasteme.common.annotation.RequestLogging;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;

/**
 * @author Ryan Lee
 * @version 1.0.0
 */
/*
@Configuration
@EnableAspectJAutoProxy
@Aspect
@Order(2)
public class BackendServiceLoggingInterceptor {
    @Around("@annotation(logging)")
    public Object exceptionInterceptor(ProceedingJoinPoint joinPoint, RequestLogging logging) throws Throwable {
        return new cn.pasteme.common.service.ServiceLoggingInterceptor().invoke(joinPoint, logging);
    }
}
 */
