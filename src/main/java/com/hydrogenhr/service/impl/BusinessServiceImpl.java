package com.hydrogenhr.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hydrogenhr.model.dto.BusinessDTO;
import com.hydrogenhr.model.enums.BusinessType;
import com.hydrogenhr.persistence.entity.Address;
import com.hydrogenhr.persistence.entity.Business;
import com.hydrogenhr.persistence.entity.User;
import com.hydrogenhr.persistence.repository.BusinessRepository;
import com.hydrogenhr.service.AddressService;
import com.hydrogenhr.service.BusinessService;
import com.hydrogenhr.service.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    private final BusinessRepository businessRepository;
    private final UserService userService;
    private final AddressService addressService;

    @Override
    public List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }

    @Override
    public Optional<Business> getBusinessById(Long id) {
        return businessRepository.findById(id);
    }

    @Override
    public Business createBusiness(BusinessDTO businessDTO) {
        Optional<User> user = userService.getUserById(businessDTO.getUserId());

        // get business address  
        Optional<Address> address = addressService.getAddressById(businessDTO.getAddressId());

        BusinessType businessType;
        try{
            businessType = BusinessType.valueOf(businessDTO.getBusinessType().toUpperCase());
        }catch (IllegalArgumentException e){
            return null;
        }

        Business newBusiness = Business.builder()
        .user(user.get())
        .address(address.get())
        .businessDescription(businessDTO.getBusinessDescription())
        .businessType(businessType)
        .build();

        return businessRepository.save(newBusiness);
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
