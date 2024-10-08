package com.hydrogenhr.core.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 10:59 PM
 */
@Service
@RequiredArgsConstructor
public class TranslatorUtil {

    private static ReloadableResourceBundleMessageSource messageSource;

    public static String toLocale(String msgCode, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, args, locale);
    }

    public static String toLocale(String msgCode) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, null, locale);
    }

    @Autowired
    public void setMessageSource(ReloadableResourceBundleMessageSource resourceBundleMessageSource) {
        messageSource = resourceBundleMessageSource;
    }
}
