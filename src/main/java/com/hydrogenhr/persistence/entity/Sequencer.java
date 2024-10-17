package com.hydrogenhr.persistence.entity;

import jakarta.persistence.Column;

public class Sequencer extends BaseEntity {

    @Column(name = "sequence")
    private Integer sequence;

}
