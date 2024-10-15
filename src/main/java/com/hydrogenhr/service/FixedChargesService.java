package com.hydrogenhr.service;

import java.util.*;

import com.hydrogenhr.persistence.entity.FixedCharges;

public interface FixedChargesService {

    List<FixedCharges> getAllAlajeFixedUserCharges();

    Optional<FixedCharges> getAlajeFixedUserCharges(Long id);

    FixedCharges creatAlajeFixedUserCharges(FixedCharges alajeFixedUserCharges);

    void deletAlajeFixedUserCharges(Long id);

}
