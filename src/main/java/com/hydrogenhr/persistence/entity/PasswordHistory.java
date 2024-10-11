package com.hydrogenhr.persistence.entity;

import jakarta.persistence.*;

@Entity(name = "PasswordHistory")
@Table(name = "password_history")
public class PasswordHistory extends BaseEntity {

    @Column(name = "passwordHash")
    private String passwordHash;

    @JoinColumn(name = "user_fk")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
}
