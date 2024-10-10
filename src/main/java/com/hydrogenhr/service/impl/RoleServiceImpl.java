package com.hydrogenhr.service.impl;

import com.hydrogenhr.persistence.entity.Role;
import com.hydrogenhr.persistence.repository.RoleRepository;
import com.hydrogenhr.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
