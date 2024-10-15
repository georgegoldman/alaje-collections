package com.hydrogenhr.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hydrogenhr.persistence.entity.FixedCharges;
import com.hydrogenhr.persistence.repository.FixedChargesRepository;
import com.hydrogenhr.service.FixedChargesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FixedChargesServiceImpl implements FixedChargesService {

    private final FixedChargesRepository alajeFixedUserChargesRepository;

    @Override
    public List<FixedCharges> getAllAlajeFixedUserCharges() {
        return alajeFixedUserChargesRepository.findAll();
    }

    @Override
    public Optional<FixedCharges> getAlajeFixedUserCharges(Long id) {
        return alajeFixedUserChargesRepository.findById(id);
    }

    @Override
    public FixedCharges creatAlajeFixedUserCharges(FixedCharges alajeFixedUserCharges) {
        return alajeFixedUserChargesRepository.save(alajeFixedUserCharges);
    }

    @Override
    public void deletAlajeFixedUserCharges(Long id) {
         alajeFixedUserChargesRepository.deleteById(id);
    }

}
