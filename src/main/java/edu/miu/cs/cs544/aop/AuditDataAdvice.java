package edu.miu.cs.cs544.aop;

import edu.miu.cs.cs544.domain.AuditableEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AuditDataAdvice {

    @Around("execution(* edu.miu.cs.cs544.repository.*.save(..))")
    public void setAuditDataRound(ProceedingJoinPoint jp) throws Throwable {

        System.out.println("Advice Audit work...............");

        var obj = jp.getArgs();
        if((AuditableEntity.class).isAssignableFrom(jp.getArgs()[0].getClass())){
            ((AuditableEntity)jp.getArgs()[0]).getAuditData().setCreatedBy("1");
            ((AuditableEntity)jp.getArgs()[0]).getAuditData().setUpdatedBy("1");

        }
        jp.proceed(obj);
    }
}
