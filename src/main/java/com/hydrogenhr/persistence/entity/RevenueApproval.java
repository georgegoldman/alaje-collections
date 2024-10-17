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

@Entity(name = "RevenueApproval")
@Table(name = "revenue")
public class RevenueApproval extends BaseEntity {

    @Column(name="comment")
    private String comment;

    @Column(name = "approvalStatus")
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "revenue_fk")
    private Revenue revenue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk")
    private User user;

}
