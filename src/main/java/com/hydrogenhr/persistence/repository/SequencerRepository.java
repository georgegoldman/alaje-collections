package com.hydrogenhr.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydrogenhr.persistence.entity.Sequencer;

public interface SequencerRepository extends JpaRepository<Sequencer, Long> {

}
