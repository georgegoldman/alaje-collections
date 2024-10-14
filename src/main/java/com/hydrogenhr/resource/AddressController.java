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

import com.hydrogenhr.persistence.entity.Address;
import com.hydrogenhr.service.AddressService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dev/address")
@RequiredArgsConstructor
public class AddressController {
    
    private final AddressService addressService;

    // Get all addresses
    @GetMapping
    List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // Get address by ID
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Optional<Address> address = addressService.getAddressById(id);
        return address.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // Create a new address
    @PostMapping
    public Address createAddress(@Valid @RequestBody Address address) {
        return addressService.createAddress(address);
    }

    // Update an existing address
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @Valid @RequestBody Address updatedAddress) {
        try {
            return ResponseEntity.ok(addressService.updateAddress(id, updatedAddress));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

     // Delete an address
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
