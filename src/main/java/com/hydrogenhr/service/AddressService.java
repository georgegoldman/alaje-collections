package com.hydrogenhr.service;

import java.util.*;

import com.hydrogenhr.persistence.entity.Address;

public interface AddressService {
    List<Address> getAllAddresses();

    Optional<Address> getAddressById(Long id);

    Address createAddress(Address address);

    Address updateAddress(Long id, Address updatedAddress);

    void deleteAddress(Long id);
}
