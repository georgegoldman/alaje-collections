package com.hydrogenhr.resource;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydrogenhr.persistence.entity.Business;
import com.hydrogenhr.service.BusinessService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dev/business")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;

    // Get all businesses
    @GetMapping
    public List<Business> getAllBusinesses() {
        return businessService.getAllBusinesses();
    }

    // Get business by ID
    @GetMapping("/{id}")
    public ResponseEntity<Business> getBusinessById(@PathVariable Long id) {
        Optional<Business> business = businessService.getBusinessById(id);
        return business.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    // Create a new business
    @PostMapping
    public Business createBusiness(@Valid @RequestBody Business business) {
        return businessService.createBusiness(business);
    }

    // Update an existing business
    @PutMapping("/{id}")
    public ResponseEntity<Business> updateBusiness(@PathVariable Long id,@Valid @RequestBody Business updatedBusiness) {
        try {
            return ResponseEntity.ok(businessService.updateBusiness(id, updatedBusiness));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a business
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable Long id) {
        businessService.deleteBusiness(id);
        return ResponseEntity.noContent().build();
    }
    
}
