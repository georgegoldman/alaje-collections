package com.hydrogenhr.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydrogenhr.persistence.entity.AlajeFixedUserCharges;
import com.hydrogenhr.service.AlajeFixedUserChargesService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.*;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dev/alaje-fix-charges-controller")
@RequiredArgsConstructor
public class AlajeFixedUserChargesController {

    private final AlajeFixedUserChargesService alajeFixedUserChargesService;

    @GetMapping
    public List<AlajeFixedUserCharges> getAlajeFixedUserCharges(){
        return alajeFixedUserChargesService.getAllAlajeFixedUserCharges();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlajeFixedUserCharges> getAlageFixedUserCharges(@PathVariable Long id){
        Optional<AlajeFixedUserCharges> alajeFixedUserCharges = alajeFixedUserChargesService.getAlajeFixedUserCharges(id);

        return alajeFixedUserCharges.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    public AlajeFixedUserCharges createAlajeFixedUserCharges(@RequestBody AlajeFixedUserCharges alajeFixedUserCharges){
        return alajeFixedUserChargesService.creatAlajeFixedUserCharges(alajeFixedUserCharges);
    }
}
