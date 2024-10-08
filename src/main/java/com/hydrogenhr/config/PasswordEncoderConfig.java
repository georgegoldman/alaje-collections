package com.hydrogenhr.config;

import com.hydrogenhr.model.config.HashConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 10:41 PM
 */
@Configuration
@RequiredArgsConstructor
public class PasswordEncoderConfig {

    private final HashConfig config;

    @Bean
    public PasswordEncoder passwordEncoder() {
        String encodingId = "argon2";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y, config.getStrength(), new SecureRandom()));
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder(config.getSecret(), config.getLength(), config.getIterations(), Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256));
        encoders.put("scrypt", new SCryptPasswordEncoder(config.getCpuCost(), config.getMemoryCost(), config.getParallelization(), config.getHashLength(), config.getLength()));
        encoders.put("argon2", new Argon2PasswordEncoder(config.getLength(), config.getHashLength(), config.getParallelization(), config.getMemory(), config.getIterations()));

        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder(encodingId, encoders);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new Argon2PasswordEncoder(config.getLength(), config.getHashLength(), config.getParallelization(), config.getMemory(), config.getIterations()));
        return delegatingPasswordEncoder;
    }
}
