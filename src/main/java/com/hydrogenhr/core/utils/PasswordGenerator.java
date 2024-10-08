package com.hydrogenhr.core.utils;

import java.security.SecureRandom;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 10:57 PM
 */
public class PasswordGenerator {

    private static final String alphaCap = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
    private static final String numeric = "0123456789";
    private static final String specialCharacter = "!@#$%^&*_=+-/";
    private static SecureRandom random = new SecureRandom();

    private static String generateStrongPassword(int len, String dic) {
        String result = "";
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(dic.length());
            result += dic.charAt(index);
        }
        return result;
    }

    private static String generatePassword(int len, String dic) {
        String result = "";
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(dic.length());
            result += dic.charAt(index);
        }
        return result;
    }

    public static String generateStrongPassword() {
        return generateStrongPassword(14, alphaCap + alpha + numeric + specialCharacter);
    }

    public static String generatePassword() {
        return generatePassword(10, alphaCap + alpha + numeric);
    }
}
