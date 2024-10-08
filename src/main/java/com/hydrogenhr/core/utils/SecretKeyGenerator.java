package com.hydrogenhr.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/30/24
 * Time: 3:29 PM
 */
@Slf4j
public class SecretKeyGenerator {

    public static String generateSecretKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();
            return SecurityUtils.twoXEncryptStringData(Base64.encodeBase64String(secretKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            log.error("error occurred generating secret key:: {}", e.getMessage());
        }
        return null;
    }
}
