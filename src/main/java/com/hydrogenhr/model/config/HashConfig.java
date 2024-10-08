package com.hydrogenhr.model.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 10:41 PM
 */
@ConfigurationProperties(prefix = "app.salt")
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HashConfig implements Serializable {

    private Integer strength;
    private String secret;
    private Integer length;
    private Integer iterations;
    private Integer cpuCost;
    private Integer memoryCost;
    private Integer parallelization;
    private Integer hashLength;
    private Integer memory;
}
