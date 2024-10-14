package com.hydrogenhr.service;

import java.util.List;
import java.util.Optional;

import com.hydrogenhr.persistence.entity.Business;

public interface BusinessService {

    List<Business> getAllBusinesses();

    Optional<Business> getBusinessById(Long id);

    Business createBusiness(Business business);

    Business updateBusiness(Long id, Business updatedBusiness);

    void deleteBusiness(Long id);

}
