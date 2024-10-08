package com.hydrogenhr.core.security.clientRegistration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.oidc.OidcClientRegistration;
import org.springframework.security.oauth2.server.authorization.oidc.converter.OidcClientRegistrationRegisteredClientConverter;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 10/1/24
 * Time: 2:51 PM
 */
public class CustomRegisteredClientConverter implements Converter<OidcClientRegistration, RegisteredClient> {

    private final List<String> customClientMetadata;
    private final OidcClientRegistrationRegisteredClientConverter delegate;

    public CustomRegisteredClientConverter(List<String> customClientMetadata) {
        this.customClientMetadata = customClientMetadata;
        this.delegate = new OidcClientRegistrationRegisteredClientConverter();
    }

    @Override
    public RegisteredClient convert(OidcClientRegistration clientRegistration) {
        RegisteredClient registeredClient = this.delegate.convert(clientRegistration);
        ClientSettings.Builder clientSettingsBuilder = ClientSettings.withSettings(
                registeredClient.getClientSettings().getSettings());
        if (!CollectionUtils.isEmpty(this.customClientMetadata)) {
            clientRegistration.getClaims().forEach((claim, value) -> {
                if (this.customClientMetadata.contains(claim)) {
                    clientSettingsBuilder.setting(claim, value);
                }
            });
        }

        return RegisteredClient.from(registeredClient)
                .clientSettings(clientSettingsBuilder.build())
                .build();
    }
}
