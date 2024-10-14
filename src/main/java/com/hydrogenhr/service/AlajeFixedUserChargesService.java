package com.hydrogenhr.service;

import java.util.*;

import com.hydrogenhr.persistence.entity.AlajeFixedUserCharges;

public interface AlajeFixedUserChargesService {

    List<AlajeFixedUserCharges> getAllAlajeFixedUserCharges();

    Optional<AlajeFixedUserCharges> getAlajeFixedUserCharges(Long id);

    AlajeFixedUserCharges creatAlajeFixedUserCharges(AlajeFixedUserCharges alajeFixedUserCharges);

}
