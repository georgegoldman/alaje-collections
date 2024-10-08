package com.hydrogenhr.core.utils;

import jakarta.persistence.AttributeConverter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 10/1/24
 * Time: 1:07 PM
 */
public class MapConverterUtils implements AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> stringObjectMap) {
        return AppUtils.toJson(stringObjectMap);
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String s) {
        return AppUtils.fromJson(s, Map.class);
    }
}
