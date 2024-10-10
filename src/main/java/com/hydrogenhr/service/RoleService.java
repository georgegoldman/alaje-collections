package com.hydrogenhr.service;

import com.hydrogenhr.persistence.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);

    List<Role> getRoles();
}
