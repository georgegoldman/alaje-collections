package com.hydrogenhr.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 10:46 PM
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppResponse<T> implements Serializable {

    private T data;
    private String message;
    private int status;
    private Object error;

}
