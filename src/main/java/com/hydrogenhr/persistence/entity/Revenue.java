package com.hydrogenhr.persistence.entity;

import com.hydrogenhr.model.enums.ApprovalStatus;
import com.hydrogenhr.model.enums.RevenueSource;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "Revenue")
@Table(name = "revenue")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Revenue extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "approval_status")
    private ApprovalStatus approvalStatus;

    @Column(name = "revenue_source")
    @Enumerated(EnumType.STRING)
    private RevenueSource revenueSource;

    @Column(name = "revenue_unique_code")
    private String revenueUniqueCode;

    @JoinColumn(name = "nigerian_lga")
    @ManyToMany(fetch = FetchType.EAGER)
    private NigerianLga nigerianLga;


}
