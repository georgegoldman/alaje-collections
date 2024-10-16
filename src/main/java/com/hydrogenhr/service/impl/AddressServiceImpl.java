package com.hydrogenhr.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hydrogenhr.model.dto.AddressDTO;
import com.hydrogenhr.persistence.entity.Address;
import com.hydrogenhr.persistence.entity.User;
import com.hydrogenhr.persistence.repository.AddressRepository;
import com.hydrogenhr.service.AddressService;
import com.hydrogenhr.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository  addressRepository;
    private final UserService userService;

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address createAddress(AddressDTO addressDTO) {
        Optional<User> user  = userService.getUserById(addressDTO.getUserId());

        Address newAddress = Address.builder()
                .buildingName(addressDTO.getBuildingName())
                .streetNumber(addressDTO.getStreetNumber())
                .zipCode(addressDTO.getZipCode())
                .streetName(addressDTO.getStreetName())
                .landmark(addressDTO.getLandmark())
                .description(addressDTO.getDescription())
                .city(addressDTO.getCity())
                .lga(addressDTO.getLga())
                .state(addressDTO.getState())
                .country(addressDTO.getCountry())
                .user(user.get())  // Set the user object on the address
                .build();
        return addressRepository.save(newAddress);
    }
    

    @Override
    public Address updateAddress(Long id, AddressDTO updatedAddress) {
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
