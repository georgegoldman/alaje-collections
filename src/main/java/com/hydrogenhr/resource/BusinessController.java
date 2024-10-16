package com.hydrogenhr.resource;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydrogenhr.model.dto.BusinessDTO;
import com.hydrogenhr.model.enums.BusinessType;
import com.hydrogenhr.persistence.entity.Address;
import com.hydrogenhr.persistence.entity.Business;
import com.hydrogenhr.persistence.entity.User;
import com.hydrogenhr.service.BusinessService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dev/business")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;
    private final UserController userController;
    private final AddressController addressController;

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
    public ResponseEntity<Business> createBusiness(@Valid @RequestBody BusinessDTO businessDTO) {
        Business business = businessService.createBusiness(businessDTO);
        return new ResponseEntity<>(business, HttpStatus.CREATED);
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
