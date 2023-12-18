package edu.miu.cs.cs544.aop;

import edu.miu.cs.cs544.config.AuthHelper;
import edu.miu.cs.cs544.config.CustomHttpContext;
import edu.miu.cs.cs544.domain.AuditData;
import edu.miu.cs.cs544.domain.AuditableEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AuditDataAdvice {

    @Autowired
    CustomHttpContext context;
    @Autowired
    AuthHelper authHelper;

    @Around("execution(* edu.miu.cs.cs544.repository.*.save(..))")
    public void setAuditDataRound(ProceedingJoinPoint jp) throws Throwable {

        System.out.println("Advice Audit work...............");

        var obj = jp.getArgs();
        String userId = "1";
        try {
            //if request receive
            userId = authHelper.getUserId().toString();
        } catch (Exception ex) {

        }

        if ((AuditableEntity.class).isAssignableFrom(jp.getArgs()[0].getClass())) {
            var auditData = ((AuditableEntity) jp.getArgs()[0]).getAuditData();
            if (auditData == null) {
                ((AuditableEntity) jp.getArgs()[0]).setAuditData(new AuditData());
                auditData = ((AuditableEntity) jp.getArgs()[0]).getAuditData();
            }
            auditData.setCreatedBy(userId);
            auditData.setUpdatedBy(userId);

        }
        jp.proceed(obj);
    }
}
