package com.hydrogenhr.service;

import java.util.List;
import java.util.Optional;

import com.hydrogenhr.persistence.entity.Organization;

public interface OrganizationService {

    List<Organization> getAllOrganizations();

    Optional<Organization> getOrganizationById(Long id);

    Organization createOrganization(Organization organization);

    Organization updateOrganization(Long id, Organization updatedOrganization);

    void deleteOrganization(Long id);

}
