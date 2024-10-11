package com.hydrogenhr.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "UserDeviceParameter")
@Table(name = "user_device_parameter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserDeviceParameter extends BaseEntity {

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "advertising_id")
    private String advertisingId;

    @Column(name = "device_manufacturer")
    private String deviceManufacturer;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name ="device_name")
    private String deviceName;

    @Column(name = "device_osv")
    private String deviceOSV;

    @Column(name = "build_version")
    private String buildVersion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk")
    private User user;

    @Column(name = "ssaid")
    private String ssaid;

    @Column(name = "channel")
    private String channel;
}
