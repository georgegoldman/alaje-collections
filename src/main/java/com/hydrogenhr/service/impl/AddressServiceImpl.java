package com.hydrogenhr.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hydrogenhr.persistence.entity.Address;
import com.hydrogenhr.persistence.repository.AddressRepository;
import com.hydrogenhr.service.AddressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository  addressRepository;

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
    

    @Override
    public Address updateAddress(Long id, Address updatedAddress) {
        return addressRepository.findById(id).map(address -> {
            address.setBuildingName(updatedAddress.getBuildingName());
            address.setStreetNumber(updatedAddress.getStreetNumber());
            address.setZipCode(updatedAddress.getZipCode());
            address.setStreetName(updatedAddress.getStreetName());
            address.setLandmark(updatedAddress.getLandmark());
            address.setDescription(updatedAddress.getDescription());
            address.setCity(updatedAddress.getCity());
            address.setLga(updatedAddress.getLga());
            address.setState(updatedAddress.getState());
            address.setCountry(updatedAddress.getCountry());
            return addressRepository.save(address);
        }).orElseThrow(() -> new RuntimeException("Address not found with id " + id));
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

}
