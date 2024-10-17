package com.hydrogenhr.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "NigerianState")
@Table(name = "nigeria_state")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class NigerianState extends BaseEntity {
    
    @Column(name = "state")
    private String state;

    @Column(name = "alias")
    private String alias;
}
