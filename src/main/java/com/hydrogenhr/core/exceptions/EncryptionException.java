package com.hydrogenhr.core.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 10:47 PM
 */
@Getter
@NoArgsConstructor
public class EncryptionException extends RuntimeException {

    private String message;

    public EncryptionException(String message) {
        super(message);
        this.message = message;
    }
}
