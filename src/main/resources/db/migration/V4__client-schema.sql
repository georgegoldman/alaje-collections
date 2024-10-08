INSERT INTO client(id, authorization_grant_types, client_authentication_methods, client_id, client_id_issued_at,
                   client_name,
                   client_secret, client_secret_expires_at, client_settings, redirect_uris, scopes, token_settings)
VALUES (1, 'refresh_token,client_credentials,authorization_code',
        'client_secret_basic,client_secret_post',
        'auth-server', now(), 'abbc70f1-fb59-4b42-b1e4-c52fa0080bea',
        '{argon2}$argon2id$v=19$m=4096,t=4,p=4$kQmbD6PJPBruzi02ujisxDu4YHDgslpixAHLUodzP7Vol4voQJcSUzTjpPFRbVlUtL9p7UNSnpOO8YJylPOmur8Wd+6rR0qyxV3u0nFZ+fvEPKIaGZj++39BC7TV0rJ/2A8ChEPyevND8MfQEYdfsoFz9txcIIgrLvvbWD7Jtf2HNULIeeuKDMc0s4TKBxslToJKrd4I1fBh0y87g3FnIaFmc5hfU+5As9gEiZidzyOWbXLsuT1JhjuJg2CMeNj9lNf2HuQHTK08VSak9zDBcrVG6adS4wCZL3BZt2YdhzEq+csfcL7rBJeft3Kc76++mgEClHFNq7tjh5lW723UdnD5jaaUG6qe/SGjCnM6NKA2Dp/F6jfnpdmRGj7JzInjrkVYjGft1yzodB0dgsdWqC4wyWaltVlC94jt4xGk653W5lgzcIht/4rrHhcIUu5wlGzamSCAHRV//xEJIQoGFnOQyTx4lQjkprNtjO4P8/I4WppVZDP//xSzS4zNQyI6kbATg8mi8u/eGBiB/rAPimK0TUgUJXeAjgupEdn2NpiayE+rbjC3w/G164yqMFSj66MnDRqCR+671Ht1175c9KP4xydLfm+bqxzpPop6rD41u0dKln52DAnL0lXp+EYd3T/MItNSsQeJ3nXCNZ5Y0vqK20gE2hwAjnI2BLPUsUN6GfiAkhWBq3hITSps5dlevQH8o93Geonsp9axlnp5DvGYDJEeYxQv9bdbOhb+rvvvDkWjBqSsrNXvF2cwOvfgTRX7Mcovmt83DbPFvGvTan556lxsFbXbQEKlevX5mlEqNMzCkSKwcOY8MjvQBe9553P0kkNXImUnlRxp/MKEV6g6Z5rckSMrTPkifFPwgCaAZmaT1KfUJt5Gtl8sgNHgDgMq4GlzuMzqwvb3ct21DdMO9HYpQs1ue1U/RnUwvrCSkJhM2WBOFIE4bZDF6clr312l6ZxPLbYwDLIXqFlzT+3pISoaM4Vf2haBIiLuoI8ONYnU9WpDOkooy4oIq4DV2eVLMK/5vfyECT9xcK4fYm7vfGAKsyVNLhZpufn0P7VKCNb/0IrVX9Hi90swcsNyFhhPt0tWeRYVmsHeUtuQ9lFoHhvuWTy2/TogJH+37gZr7ZnDOVdnNQuVTZ+7GAE5NY56g9zcgpZIGX7ofGDkyjt/QmQaaLiFWAb11GcPZCEuUKR70/tqUqj6NS0kcG17ywVD5LE/X7OOXGYKsbAq8N9uAMphkVM0G7lUsD5/bo/rKl9HCEXDC5KGwbV2uMKrdM1Nbd17Bses06FfFUTIeC2XbhQcuI/LM39qv6C/IVaYJKPJkvfJYnTfHYqt1T9plITIOivaTx9S9GBrp8mnCw$REYuhjScYUZIXBl1BEYx1UrGEVXAax+UziBzAnJvlc8',
        null,
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":true,"settings.client.require-authorization-consent":true}',
        'http://127.0.0.1:8084/api/v1/login/oauth2/code/google,http://127.0.0.1:8084/api/v1/login/oauth2/code/vdms,https://oidcdebugger.com/debug,https://oauthdebugger.com/debug,https://getpostman.com/oauth2/callback',
        'read,openid,profile,client.read,client.create',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,
        "settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],
        "settings.token.access-token-time-to-live":["java.time.Duration",86400.000000000],
        "settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat",
        "value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],
        "settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

INSERT INTO client(id, authorization_grant_types, client_authentication_methods, client_id, client_id_issued_at,
                   client_name,
                   client_secret, client_secret_expires_at, client_settings, redirect_uris, scopes, token_settings)
VALUES (2, 'refresh_token,client_credentials,authorization_code',
        'client_secret_basic,client_secret_post',
        'vdms-server', now(), '88ee9893-1cc7-4e8c-8d07-b04791b78380',
        '{argon2}$argon2id$v=19$m=4096,t=4,p=4$kQmbD6PJPBruzi02ujisxDu4YHDgslpixAHLUodzP7Vol4voQJcSUzTjpPFRbVlUtL9p7UNSnpOO8YJylPOmur8Wd+6rR0qyxV3u0nFZ+fvEPKIaGZj++39BC7TV0rJ/2A8ChEPyevND8MfQEYdfsoFz9txcIIgrLvvbWD7Jtf2HNULIeeuKDMc0s4TKBxslToJKrd4I1fBh0y87g3FnIaFmc5hfU+5As9gEiZidzyOWbXLsuT1JhjuJg2CMeNj9lNf2HuQHTK08VSak9zDBcrVG6adS4wCZL3BZt2YdhzEq+csfcL7rBJeft3Kc76++mgEClHFNq7tjh5lW723UdnD5jaaUG6qe/SGjCnM6NKA2Dp/F6jfnpdmRGj7JzInjrkVYjGft1yzodB0dgsdWqC4wyWaltVlC94jt4xGk653W5lgzcIht/4rrHhcIUu5wlGzamSCAHRV//xEJIQoGFnOQyTx4lQjkprNtjO4P8/I4WppVZDP//xSzS4zNQyI6kbATg8mi8u/eGBiB/rAPimK0TUgUJXeAjgupEdn2NpiayE+rbjC3w/G164yqMFSj66MnDRqCR+671Ht1175c9KP4xydLfm+bqxzpPop6rD41u0dKln52DAnL0lXp+EYd3T/MItNSsQeJ3nXCNZ5Y0vqK20gE2hwAjnI2BLPUsUN6GfiAkhWBq3hITSps5dlevQH8o93Geonsp9axlnp5DvGYDJEeYxQv9bdbOhb+rvvvDkWjBqSsrNXvF2cwOvfgTRX7Mcovmt83DbPFvGvTan556lxsFbXbQEKlevX5mlEqNMzCkSKwcOY8MjvQBe9553P0kkNXImUnlRxp/MKEV6g6Z5rckSMrTPkifFPwgCaAZmaT1KfUJt5Gtl8sgNHgDgMq4GlzuMzqwvb3ct21DdMO9HYpQs1ue1U/RnUwvrCSkJhM2WBOFIE4bZDF6clr312l6ZxPLbYwDLIXqFlzT+3pISoaM4Vf2haBIiLuoI8ONYnU9WpDOkooy4oIq4DV2eVLMK/5vfyECT9xcK4fYm7vfGAKsyVNLhZpufn0P7VKCNb/0IrVX9Hi90swcsNyFhhPt0tWeRYVmsHeUtuQ9lFoHhvuWTy2/TogJH+37gZr7ZnDOVdnNQuVTZ+7GAE5NY56g9zcgpZIGX7ofGDkyjt/QmQaaLiFWAb11GcPZCEuUKR70/tqUqj6NS0kcG17ywVD5LE/X7OOXGYKsbAq8N9uAMphkVM0G7lUsD5/bo/rKl9HCEXDC5KGwbV2uMKrdM1Nbd17Bses06FfFUTIeC2XbhQcuI/LM39qv6C/IVaYJKPJkvfJYnTfHYqt1T9plITIOivaTx9S9GBrp8mnCw$REYuhjScYUZIXBl1BEYx1UrGEVXAax+UziBzAnJvlc8',
        null,
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":true,"settings.client.require-authorization-consent":true}',
        'http://127.0.0.1:8084/api/v1/login/oauth2/code/google,http://127.0.0.1:8084/api/v1/login/oauth2/code/vdms,https://oidcdebugger.com/debug,https://oauthdebugger.com/debug,https://getpostman.com/oauth2/callback',
        'read,openid,profile,client.read,client.create',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,
        "settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],
        "settings.token.access-token-time-to-live":["java.time.Duration",86400.000000000],
        "settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat",
        "value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],
        "settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

commit;
