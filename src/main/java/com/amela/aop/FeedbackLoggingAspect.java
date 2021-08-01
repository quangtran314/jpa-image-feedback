package com.amela.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class FeedbackLoggingAspect {
    @AfterThrowing(value = "execution(public * com.amela.repository.FeedbackRepository.*(..))", throwing = "ex")  //point-cut expression
    public void log(JoinPoint joinPoint, Exception ex)
    {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println(String.format("[%s] [CMS] co loi xay ra: %s.%s%s: %s", new Date(), className, method, args, ex.getMessage()));
    }
}
