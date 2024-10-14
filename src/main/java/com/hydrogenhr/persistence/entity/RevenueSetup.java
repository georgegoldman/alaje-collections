package com.hydrogenhr.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "RevenueSetup")
@Table(name = "RevenueSetup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RevenueSetup {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(name = "revenue_code")
    private String revenueCode;

}
