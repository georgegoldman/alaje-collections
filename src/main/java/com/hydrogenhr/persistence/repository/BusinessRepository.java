package com.hydrogenhr.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydrogenhr.persistence.entity.Business;

public interface BusinessRepository extends JpaRepository<Business, Long>{

}
