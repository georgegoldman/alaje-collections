package com.hydrogenhr.core.security.clientRegistration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.oidc.OidcClientRegistration;
import org.springframework.security.oauth2.server.authorization.oidc.converter.RegisteredClientOidcClientRegistrationConverter;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 10/1/24
 * Time: 2:52 PM
 */
public class CustomClientRegistrationConverter implements Converter<RegisteredClient, OidcClientRegistration> {

    private final List<String> customClientMetadata;
    private final RegisteredClientOidcClientRegistrationConverter delegate;

    public CustomClientRegistrationConverter(List<String> customClientMetadata) {
        this.customClientMetadata = customClientMetadata;
        this.delegate = new RegisteredClientOidcClientRegistrationConverter();
    }

    @Override
    public OidcClientRegistration convert(RegisteredClient registeredClient) {
        OidcClientRegistration clientRegistration = this.delegate.convert(registeredClient);
        Map<String, Object> claims = new HashMap<>(clientRegistration.getClaims());
        if (!CollectionUtils.isEmpty(this.customClientMetadata)) {
            ClientSettings clientSettings = registeredClient.getClientSettings();
            claims.putAll(this.customClientMetadata.stream()
                    .filter(metadata -> clientSettings.getSetting(metadata) != null)
                    .collect(Collectors.toMap(Function.identity(), clientSettings::getSetting)));
        }

        return OidcClientRegistration.withClaims(claims).build();
    }

}
