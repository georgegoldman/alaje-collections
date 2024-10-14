package com.hydrogenhr.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hydrogenhr.persistence.entity.Organization;
import com.hydrogenhr.persistence.repository.OrganizationRepository;
import com.hydrogenhr.service.OrganizationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    @Override
    public Optional<Organization> getOrganizationById(Long id) {
        return organizationRepository.findById(id);
    }

    @Override
    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Organization updateOrganization(Long id, Organization updatedOrganization) {
        return organizationRepository.findById(id).map(organization -> {
            organization.setName(updatedOrganization.getName());
            organization.setRcNumber(updatedOrganization.getRcNumber());
            organization.setEmail(updatedOrganization.getEmail());
            organization.setTelephone(updatedOrganization.getTelephone());
            return organizationRepository.save(organization);
        }).orElseThrow(() -> new RuntimeException("Organization not found with id " + id));
    }

    @Override
    public void deleteOrganization(Long id) {
        organizationRepository.deleteById(id);
    }

    

}
