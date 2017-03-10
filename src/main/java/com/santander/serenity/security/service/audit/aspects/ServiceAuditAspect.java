package com.santander.serenity.security.service.audit.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Service Audit Aspect
 */
@Component
@Aspect
public class ServiceAuditAspect {

    private static final String RESULT_OK = "OK";
    private static final String RESULT_KO = "KO";
    private static Logger logger = LoggerFactory.getLogger(ServiceAuditAspect.class);

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date operationStartDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date operationEndDate;

    /**
     * Audit method for '' endpoint method
     *
     * @param pjp The {@link ProceedingJoinPoint} object that exposes the proceed(..) method
     * @throws Throwable
     */
    /*@Around("execution(public * com.santander.serenity.security.*.controller.ServiceController.methodName(..))")
    public void methodName(ProceedingJoinPoint pjp) throws Throwable {
        Object executeResult = executeAudit(pjp, ".");
    }*/

    /**
     * Audit generic method
     *
     * @param pjp The {@link ProceedingJoinPoint} object that exposes the proceed(..) method
     * @param message Description of audited operation
     * @return The operation result (PK generated or PK check)
     * @throws Throwable
     */
    private Object executeAudit(ProceedingJoinPoint pjp, String message) throws Throwable {

        String operationName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();

        this.operationStartDate = new Date(System.currentTimeMillis());

        logger.info("AUDIT -- {} begins: timestamp '{}' method name: '{}' params: '{}'", message, this.operationStartDate,
                operationName, args);

        try {
            Object operationResult = pjp.proceed();

            this.operationEndDate = new Date(System.currentTimeMillis());

            if (operationResult != null || pjp.getSignature().toString().contains("void")){
                logger.info("AUDIT -- {} ends: timestamp '{}' method name: '{}' result: '{}' params: '{}'", message, this.operationEndDate,
                        operationName, RESULT_OK, args);
            } else {
                logger.info("AUDIT -- {} ends: timestamp '{}' method name: '{}' result: '{}' params: '{}'", message, this.operationEndDate,
                        operationName, RESULT_KO, args);
            }


            return operationResult;

        } catch (Throwable th) {

            this.operationEndDate = new Date(System.currentTimeMillis());

            logger.info("AUDIT -- {} ends: timestamp '{}' method name: '{}' result: '{}' params: '{}' error: '{}'", message, this.operationEndDate,
                    operationName, RESULT_KO, args,th.getMessage());

            throw th;
        }
    }
}
