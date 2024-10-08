package com.hydrogenhr.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:15 PM
 */
@Entity(name = "SuspiciousActivity")
@Table(name = "suspicious_activities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class SuspiciousActivity extends BaseEntity {
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_type_fk")
    private ActivityType type;

    @Column(name = "occurred_at", nullable = false)
    private Timestamp occurredAt;

    @Column(length = 100, nullable = false)
    private String page;

    @Column(length = 7)
    private String method;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk")
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "organisation_fk")
    private Organization organization;

    @Column(length = 100, nullable = false)
    private String ip;

    @Column(name = "referer_page", nullable = false)
    private String refererPage;

    @Column(name = "user_agent", nullable = false)
    private String userAgent;

    @Column(name = "username")
    private String username;
}
