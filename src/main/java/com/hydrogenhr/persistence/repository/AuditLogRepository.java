package com.hydrogenhr.persistence.repository;

import com.hydrogenhr.persistence.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 10/1/24
 * Time: 1:03 PM
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
