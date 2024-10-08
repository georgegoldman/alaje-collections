package com.hydrogenhr.core.aop;

import com.hydrogenhr.core.utils.AppUtils;
import com.hydrogenhr.persistence.entity.AuditLog;
import com.hydrogenhr.persistence.repository.AuditLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 10/1/24
 * Time: 12:58 PM
 */
@Aspect
@Service
public class AuditAspect {

    private final AuditLogRepository auditLogRepository;
    private final AsyncTaskExecutor taskExecutor;

    public AuditAspect(AuditLogRepository auditLogRepository, @Qualifier("taskExecutor") AsyncTaskExecutor taskExecutor) {
        this.auditLogRepository = auditLogRepository;
        this.taskExecutor = taskExecutor;
    }

    @Around("@annotation(com.hydrogenhr.core.annotations.Audit)")
    public Object logAudit(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String initiator = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "system";
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String requestBody = AppUtils.toJson(joinPoint.getArgs());
        String requestId = request.getRequestId();
        String exceptions = null;
        String exceptionType = null;

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            exceptions = e.getLocalizedMessage();
            exceptionType = e.getClass().getName();
        }

        String responseBody = AppUtils.toJson(result);

        AuditLog auditLog = new AuditLog();
        auditLog.setRequestId(requestId);
        auditLog.setInitiator(initiator);
        auditLog.setMethodName(methodName);
        auditLog.setMethod(method);
        auditLog.setUrl(url);
        auditLog.setIpAddress(ip);
        auditLog.setRequestBody(requestBody);
        auditLog.setResponseBody(responseBody);
        auditLog.setTimestamp(new Timestamp(System.currentTimeMillis()));
        auditLog.setExceptions(exceptions);
        auditLog.setExceptionType(exceptionType);
        taskExecutor.submit(() -> auditLogRepository.save(auditLog));
        return result;
    }
}
