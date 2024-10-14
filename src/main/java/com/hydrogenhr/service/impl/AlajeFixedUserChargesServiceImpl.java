package com.hydrogenhr.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hydrogenhr.persistence.entity.AlajeFixedUserCharges;
import com.hydrogenhr.persistence.repository.AlajeFixedUserChargesRepository;
import com.hydrogenhr.service.AlajeFixedUserChargesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlajeFixedUserChargesServiceImpl implements AlajeFixedUserChargesService {

    private final AlajeFixedUserChargesRepository alajeFixedUserChargesRepository;

    @Override
    public List<AlajeFixedUserCharges> getAllAlajeFixedUserCharges() {
        return alajeFixedUserChargesRepository.findAll();
    }

    @Override
    public Optional<AlajeFixedUserCharges> getAlajeFixedUserCharges(Long id) {
        return alajeFixedUserChargesRepository.findById(id);
    }

    @Override
    public AlajeFixedUserCharges creatAlajeFixedUserCharges(AlajeFixedUserCharges alajeFixedUserCharges) {
        return alajeFixedUserChargesRepository.save(alajeFixedUserCharges);
    }

}
