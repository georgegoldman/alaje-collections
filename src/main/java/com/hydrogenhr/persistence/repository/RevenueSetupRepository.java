package com.hydrogenhr.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydrogenhr.persistence.entity.RevenueSetup;

public interface RevenueSetupRepository extends JpaRepository<RevenueSetup, UUID> {

}
