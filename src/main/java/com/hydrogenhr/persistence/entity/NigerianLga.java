package com.hydrogenhr.persistence.entity;

import com.hydrogenhr.model.enums.ApprovalStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "NigerianLga")
@Table(name = "nigeria_lga")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class NigerianLga extends BaseEntity {

    @Column(name = "lga")
    private String lga;

    @Column(name = "approval_status")
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @JoinColumn(name = "nigeria_state")
    @ManyToOne(fetch= FetchType.EAGER)
    private NigerianState nigerianState;


}
