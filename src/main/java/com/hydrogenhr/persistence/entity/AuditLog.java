package com.hydrogenhr.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 10/1/24
 * Time: 1:03 PM
 */
@Entity(name = "AuditLog")
@Table(name = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class AuditLog extends BaseEntity {

    @Column(name = "request_id", length = 50, updatable = false)
    private String requestId;

    @Column(name = "initiator", length = 200, updatable = false)
    private String initiator;

    @Column(name = "method_name", length = 200, updatable = false)
    private String methodName;

    @Column(name = "method", length = 200, updatable = false)
    private String method;

    @Column(name = "url", length = 400, updatable = false)
    private String url;

    @Column(name = "ip_address", length = 16, updatable = false)
    private String ipAddress;

    @Column(name = "request_body", columnDefinition = "json", updatable = false)
    private String requestBody;

    @Column(name = "response_body", columnDefinition = "json", updatable = false)
    private String responseBody;

    @Column(name = "exceptions", length = 400, updatable = false)
    private String exceptions;

    @Column(name = "exception_type", length = 100, updatable = false)
    private String exceptionType;

    @Column(name = "timestamp", updatable = false)
    private Timestamp timestamp;
}
