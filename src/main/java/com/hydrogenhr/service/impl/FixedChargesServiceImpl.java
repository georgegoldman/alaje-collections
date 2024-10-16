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

    private final FixedChargesRepository fixedChargesRepository;

    @Override
    public List<FixedCharges> getAllAlajeFixedUserCharges() {
        return fixedChargesRepository.findAll();
    }

    @Override
    public Optional<FixedCharges> getAlajeFixedUserCharges(Long id) {
        return fixedChargesRepository.findById(id);
    }

    @Override
    public FixedCharges creatAlajeFixedUserCharges(FixedCharges fixedCharges) {
        
        return fixedChargesRepository.save(fixedCharges);
    }

    @Override
    public void deletAlajeFixedUserCharges(Long id) {
        fixedChargesRepository.deleteById(id);
    }

}
