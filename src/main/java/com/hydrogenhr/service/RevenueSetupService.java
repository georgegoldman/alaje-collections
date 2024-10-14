package com.hydrogenhr.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.hydrogenhr.persistence.entity.RevenueSetup;

public interface RevenueSetupService {
    List<RevenueSetup> getAllRevenueSetups();

    Optional<RevenueSetup> getRevenueSetupById(UUID id);

    RevenueSetup createRevenueSetup(RevenueSetup revenueSetup);

    RevenueSetup updateRevenueSetup(UUID id, RevenueSetup updatedRevenueSetup);

    void deleteRevenueSetup(UUID id);
}
