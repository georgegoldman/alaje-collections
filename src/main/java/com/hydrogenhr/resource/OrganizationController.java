package com.hydrogenhr.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydrogenhr.persistence.entity.Organization;
import com.hydrogenhr.service.OrganizationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dev/organization")
@Tag(name = "organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    // Get all organizations
    @GetMapping
    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }
}
