package com.hydrogenhr.resource;


import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydrogenhr.model.dto.AddressDTO;
import com.hydrogenhr.persistence.entity.Address;
import com.hydrogenhr.persistence.entity.User;
import com.hydrogenhr.service.AddressService;

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
    public Address createAddress(@Valid @RequestBody AddressDTO addressDTO) {

        return addressService.createAddress(addressDTO);
    }

    // Update an existing address
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO updatedAddress) {
        try {
            return ResponseEntity.ok(addressService.updateAddress(id, updatedAddress));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    
}
