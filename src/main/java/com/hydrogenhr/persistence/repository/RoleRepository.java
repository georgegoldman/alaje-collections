package com.hydrogenhr.persistence.repository;

import com.hydrogenhr.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:21 PM
 */
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);
}
