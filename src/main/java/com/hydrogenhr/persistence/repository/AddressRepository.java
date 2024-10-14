package com.hydrogenhr.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydrogenhr.persistence.entity.Address;


public interface AddressRepository extends JpaRepository<Address, Long> {

}
