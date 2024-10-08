package com.hydrogenhr.persistence.repository;

import com.hydrogenhr.persistence.entity.SuspiciousActivity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:22 PM
 */
public interface SuspiciousActivityRepository extends JpaRepository<SuspiciousActivity, Long> {
}
