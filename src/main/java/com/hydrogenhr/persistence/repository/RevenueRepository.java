package com.hydrogenhr.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydrogenhr.persistence.entity.Revenue;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {

}
