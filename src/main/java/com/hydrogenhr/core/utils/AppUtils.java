package com.hydrogenhr.core.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hydrogenhr.core.constants.AppConstants;
import com.hydrogenhr.core.exceptions.CustomException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.hydrogenhr.core.constants.AppConstants.DateFormatters.shortTime;
import static com.hydrogenhr.core.constants.AppConstants.DateFormatters.styleTime;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 10:50 PM
 */
@Slf4j
public class AppUtils {

    private AppUtils() {
    }

    private static ObjectMapper mapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Jdk8Module());
        return mapper;
    }

    @SneakyThrows
    public static <T> String toJson(T t) {
        return mapper().writeValueAsString(t);
    }

    @SneakyThrows
    public static String toJson(Type t) {
        return mapper().writeValueAsString(t);
    }

    @SneakyThrows
    public static <O, T> void writeValue(O out, T object) {
        switch (out) {
            case OutputStream o -> mapper().writeValue(o, object);
            case File f -> mapper().writeValue(f, object);
            case DataOutput d -> mapper().writeValue(d, object);
            case Writer w -> mapper().writeValue(w, object);
            case null, default -> throw new CustomException("Unsupported output type", HttpStatus.PRECONDITION_FAILED);
        }
    }

    @SneakyThrows
    public static <R, T> T fromJson(R resource, TypeReference<T> clazz) {
        return switch (resource) {
            case Resource r -> mapper().readValue(r.getInputStream(), clazz);
            case File f -> mapper().readValue(f, clazz);
            case Reader r -> mapper().readValue(r, clazz);
            case InputStream i -> mapper().readValue(i, clazz);
            case String s -> mapper().readValue(s, clazz);
            case byte[] b -> mapper().readValue(b, clazz);
            case URL d -> mapper().readValue(d, clazz);
            case Map o -> mapper().convertValue(o, clazz);
            case Object o -> mapper().convertValue(o, clazz);
        };
    }

    @SneakyThrows
    public static <R, T> T fromJson(R resource, JavaType clazz) {
        return switch (resource) {
            case Resource r -> mapper().readValue(r.getInputStream(), clazz);
            case File f -> mapper().readValue(f, clazz);
            case Reader r -> mapper().readValue(r, clazz);
            case InputStream i -> mapper().readValue(i, clazz);
            case String s -> mapper().readValue(s, clazz);
            case byte[] b -> mapper().readValue(b, clazz);
            case URL d -> mapper().readValue(d, clazz);
            case DataInput d -> mapper().readValue(d, clazz);
            case Map o -> mapper().convertValue(o, clazz);
            case Object o -> mapper().convertValue(o, clazz);
        };
    }

    @SneakyThrows
    public static <R, T> T fromJson(R resource, Class<T> clazz) {
        return switch (resource) {
            case Resource r -> mapper().readValue(r.getInputStream(), clazz);
            case File f -> mapper().readValue(f, clazz);
            case Reader r -> mapper().readValue(r, clazz);
            case InputStream i -> mapper().readValue(i, clazz);
            case String s -> mapper().readValue(s, clazz);
            case byte[] b -> mapper().readValue(b, clazz);
            case URL d -> mapper().readValue(d, clazz);
            case DataInput d -> mapper().readValue(d, clazz);
            case Map o -> mapper().convertValue(o, clazz);
            case Object o -> mapper().convertValue(o, clazz);
        };
    }

    public static Timestamp parseDateUtil(String dateStr) {
        if (dateStr == null) {
            throw new CustomException("Date cannot be empty, please enter correct date format", HttpStatus.BAD_REQUEST);
        }
        try {
            final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(dateFormat.parse(dateStr).getTime());
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            log.error("error occurred converting string to date, please check you date format >> {}", e.getMessage());
            throw new CustomException("Date format error, please check your date format. Example of valid date format 'yyyy-MM-dd'", HttpStatus.BAD_REQUEST);
        }
    }

    public static Timestamp parseTime(String time) {
        if (time == null) {
            throw new CustomException("Time cannot be empty, please enter correct time format 'HH:mm:ss'", HttpStatus.BAD_REQUEST);
        }
        try {
            return new Timestamp(AppConstants.DateFormatters.timeFormat.parse(time).getTime());
        } catch (ParseException ignore) {
            throw new CustomException("Time format error, please check your time format. \nExample of valid time format 'HH:mm:ss'", HttpStatus.BAD_REQUEST);
        }
    }

    public static String parseTime(Timestamp time) {
        if (time == null) {
            return null;
        }
        return AppConstants.DateFormatters.dateFormat.format(time);
    }

    public static String fromTimeStamp(Timestamp timestamp) {
        return AppConstants.DateFormatters.dateFormat.format(timestamp);
    }

    public static String toTitleCase(String data) {
        if (StringUtils.isBlank(data)) {
            return data;
        }
        return StringUtils.capitalize(data);
    }

    public static String styleTime(Timestamp timestamp) {
        return styleTime.format(timestamp);
    }

    public static String shortTime(Timestamp timestamp) {
        return shortTime.format(timestamp);
    }
}
