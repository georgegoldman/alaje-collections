package com.hydrogenhr.core.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 10:33 PM
 */
public interface AppConstants {
    public interface DateFormatters {
        public static final DateTimeFormatter FORMATTER = ofPattern("dd::MM::yyyy");
        public SimpleDateFormat FORMATTER_ = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        public static final DateTimeFormatter DATE_TIME_FORMATTER = ofPattern("yyyy-MM-dd HH:mm");
        public static final DateTimeFormatter DATE_TIME_LONG_FORMATTER = ofPattern("yyyy-MM-dd HH:mm:ss");
        public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
        public static final String defaultDateTimeFormatter = "yyyy-MM-dd HH:mm:ss";
        public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        public static final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        public static final DateFormat styleTime = new SimpleDateFormat("MMM dd, HH:mm a");
        public static final DateFormat shortTime = new SimpleDateFormat("yyMMdd");
    }

    public interface Authentication {
        public static String PERMISSION = "PERMISSION_";
        public static String ROLE = "ROLE_";
        public static String USERNAME = "username";
        public static String PASSWORD = "password";
    }

    public interface Rsakey {
        public static String KEY_ID = """
                17155938286125604947128952399168114542956438901169836962534052576157584405540286554600129414058913249356
                76984578034923248133124064084759719817330643264396710859486604086764313904640080509130312684782644175203
                067513091599937783856988325950612947472055759403659341160421918968477950800326578114017694283567148623305
                0996607118659866979884615457038211052526933454970294872439939332584652539274884717595808162251861044365208
                1637654395564167680815057377019223445992901312235661866823897552496372816985453511762453873143594512996753
                16563020068770805622219159254548925438827754444298609064948860195518504632750871608547736987
                """;
    }

    public interface Suspicous {
        public static String PAGE_NOT_FOUND = "PageNotFound";
        public static String AUTHENTICATION_FAILED = "AuthenticationFailed";
        public static Integer MAX_ATTEMPT = 5;
    }

    public interface Oauth2Uri {
        public static String OAUTH2_AUTHORIZATION_ENDPOINT = "/oauth2/authorize";
        public static String OAUTH2_TOKEN_ENDPOINT = "/oauth2/token";
        public static String OAUTH2_INTROSPECT_ENDPOINT = "/oauth2/introspect";
        public static String OAUTH2_REVOKE_ENDPOINT = "/oauth2/revoke";
        public static String OAUTH2_JWK_SET_ENDPOINT = "/oauth2/jwks";
        public static String OAUTH2_USER_INFO = "/oauth2/userinfo";
        public static String OAUTH2_REGISTRATION_ENDPOINT = "/oauth2/connect/register";
        public static String OAUTH2_LOGOUT_ENDPOINT = "/oauth2/connect/logout";
        public static String LOGIN = "/login";
        public static String LOGOUT = "/logout";
        public static String ASSETS = "/assets/**";
        public static String STATIC = "/static/**";
        public static String API_DOC = "/v1/api-docs";
        public static String SWAGGER = "/swagger**";
    }

    public interface SecurityConstants {
        public String SECRET_KEY = "T1Boem9KOUFUa0p4OVhiWE9qTU91WmRzMjVtYURwMjZNdi1MZm1ZVzVBalBrNElIOGdOMFVOdGlEV01UY3dxMTVUeW5LcUNxck5qcU1DOGVZQ0lLZHc9PQ==";
        public String DEFUALT_KEY = "6YXvfUFfkZLqosDiYoEOwMYiG6M4Eb2sVdXozXW8tZZ90ZVA4B2BYzhf799hLmkdz4Q7Y2kbJSFLUfk9vYAyYXvMtDK4oDO4yhRh9xbAMAMrmnOE7HepUQ==";
        public byte[] SALT = {(byte) 0x21, (byte) 0x21, (byte) 0xF0, (byte) 0x55, (byte) 0xC3, (byte) 0x9F, (byte) 0x5A, (byte) 0x75};
        public int ITERATION_COUNT = 31;
        String APP_NAME = "CYBER_STARS_INFORMATION_TECHNOLOGY_BACKEND_API";
        String API_SECRET = "Y@WN447rub404*j@d35h0l@#";
        Long JWT_EXPIRES_IN = 3600000L; // 1 hr
        String BEARER = "Bearer ";
        String AUTHORIZATION = "Authorization";
        public static final String AES = "AES";
        public static String UTF_8 = "UTF-8";
        public static final String CUSTOM_PASSWORD = "custom_password";
        public static final String SECURITY_POLICY = "default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self'; connect-src 'self'; frame-src 'self'; object-src 'none'; media-src 'self'; frame-ancestors 'self'; form-action 'self'; base-uri 'self'; block-all-mixed-content; upgrade-insecure-requests; report-uri /csp-report";
        public static final String REMEMBER_ME = "rememberMe";
    }

    public interface RestMessage {
        String success = "Success";
        String failed = "Failed";
        String created = "Created";
    }
}
