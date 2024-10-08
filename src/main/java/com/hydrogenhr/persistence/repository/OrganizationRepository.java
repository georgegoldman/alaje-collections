package com.hydrogenhr.persistence.repository;

import com.hydrogenhr.persistence.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:43 PM
 */
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
   Optional<Organization> findByName(String name);
}
