package com.hydrogenhr.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydrogenhr.persistence.entity.RevenueSetup;
import com.hydrogenhr.service.RevenueSetupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dev/revenue-setup")
@RequiredArgsConstructor
public class RevenueSetupController {

    private final RevenueSetupService revenueSetupService;

    // Get all revenue setups
    @GetMapping
    public List<RevenueSetup> getAllRevenueSetups() {
        return revenueSetupService.getAllRevenueSetups();
    }

    // Get revenue setup by ID
    @GetMapping("/{id}")
    public ResponseEntity<RevenueSetup> getRevenueSetupById(@PathVariable UUID id) {
        Optional<RevenueSetup> revenueSetup = revenueSetupService.getRevenueSetupById(id);
        return revenueSetup.map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
    }

    // Create a new revenue setup
    @PostMapping
    public RevenueSetup createRevenueSetup(@RequestBody RevenueSetup revenueSetup) {
        return revenueSetupService.createRevenueSetup(revenueSetup);
    }

    // Update an existing revenue setup
    @PutMapping("/{id}")
    public ResponseEntity<RevenueSetup> updateRevenueSetup(@PathVariable UUID id, @RequestBody RevenueSetup updatedRevenueSetup) {
        try {
            return ResponseEntity.ok(revenueSetupService.updateRevenueSetup(id, updatedRevenueSetup));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a revenue setup
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRevenueSetup(@PathVariable UUID id) {
        revenueSetupService.deleteRevenueSetup(id);
        return ResponseEntity.noContent().build();
    }

}
