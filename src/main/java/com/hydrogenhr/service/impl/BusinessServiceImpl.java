package com.hydrogenhr.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hydrogenhr.persistence.entity.Business;
import com.hydrogenhr.persistence.repository.BusinessRepository;
import com.hydrogenhr.service.BusinessService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    private final BusinessRepository businessRepository;

    @Override
    public List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }

    @Override
    public Optional<Business> getBusinessById(Long id) {
        return businessRepository.findById(id);
    }

    @Override
    public Business createBusiness(Business business) {
        return businessRepository.save(business);
    }

    @Override
    public Business updateBusiness(Long id, Business updatedBusiness) {
        return businessRepository.findById(id).map(business -> {
            business.setBusinessType(updatedBusiness.getBusinessType());
            business.setBusinessDescription(updatedBusiness.getBusinessDescription());
            business.setUser(updatedBusiness.getUser());
            business.setAddress(updatedBusiness.getAddress());
            return businessRepository.save(business);
        }).orElseThrow(() -> new RuntimeException("Business not found with id " + id));
    }

    @Override
    public void deleteBusiness(Long id) {
        businessRepository.deleteById(id);
    }
    
}
