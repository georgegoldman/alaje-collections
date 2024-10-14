package com.hydrogenhr.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hydrogenhr.persistence.entity.RevenueSetup;
import com.hydrogenhr.persistence.repository.RevenueSetupRepository;
import com.hydrogenhr.service.RevenueSetupService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevenueSetupServiceImpl implements RevenueSetupService {

    private final RevenueSetupRepository revenueSetupRepository;
    
    
    @Override
    public List<RevenueSetup> getAllRevenueSetups() {
        return revenueSetupRepository.findAll();
    }

    @Override
    public Optional<RevenueSetup> getRevenueSetupById(UUID id) {
        return revenueSetupRepository.findById(id);
    }

    @Override
    public RevenueSetup createRevenueSetup(RevenueSetup revenueSetup) {
        return revenueSetupRepository.save(revenueSetup);
    }

    @Override
    public RevenueSetup updateRevenueSetup(UUID id, RevenueSetup updatedRevenueSetup) {
        return revenueSetupRepository.findById(id).map(revenueSetup -> {
            revenueSetup.setRevenueCode(updatedRevenueSetup.getRevenueCode());
            return revenueSetupRepository.save(revenueSetup);
        }).orElseThrow(() -> new RuntimeException("RevenueSetup not found with id " + id));
    }

    @Override
    public void deleteRevenueSetup(UUID id) {
        revenueSetupRepository.deleteById(id);
    }

}
