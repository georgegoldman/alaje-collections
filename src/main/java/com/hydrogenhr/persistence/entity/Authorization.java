package com.hydrogenhr.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:00 PM
 */
@Entity(name = "Authorization")
@Table(name = "authorizations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Authorization {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "registered_client_id", length = 200)
    private String registeredClientId;

    @Column(name = "principal_name", length = 200)
    private String principalName;

    @Column(name = "authorization_grant_type", length = 200)
    private String authorizationGrantType;

    @Column(name = "authorized_scopes", columnDefinition = "text")
    private String authorizedScopes;

    @Column(name = "attributes", columnDefinition = "text")
    private String attributes;

    @Column(name = "state", length = 500)
    private String state;

    @Column(name = "authorization_code_value", columnDefinition = "text")
    private String authorizationCodeValue;

    @Column(name = "authorization_code_issued_at")
    private Instant authorizationCodeIssuedAt;

    @Column(name = "authorization_code_expires_at")
    private Instant authorizationCodeExpiresAt;

    @Column(name = "authorization_code_metadata", columnDefinition = "text")
    private String authorizationCodeMetadata;

    @Column(name = "access_token_value", columnDefinition = "text")
    private String accessTokenValue;

    @Column(name = "access_token_issued_at")
    private Instant accessTokenIssuedAt;

    @Column(name = "access_token_expires_at")
    private Instant accessTokenExpiresAt;

    @Column(name = "access_token_metadata", columnDefinition = "text")
    private String accessTokenMetadata;

    @Column(name = "access_token_type", length = 200)
    private String accessTokenType;

    @Column(name = "access_token_scopes", columnDefinition = "text")
    private String accessTokenScopes;

    @Column(name = "refresh_token_value", columnDefinition = "text")
    private String refreshTokenValue;

    @Column(name = "refresh_token_issued_at")
    private Instant refreshTokenIssuedAt;

    @Column(name = "refresh_token_expires_at")
    private Instant refreshTokenExpiresAt;

    @Column(name = "refresh_token_metadata", columnDefinition = "text")
    private String refreshTokenMetadata;

    @Column(name = "oidc_id_token_value", columnDefinition = "text")
    private String oidcIdTokenValue;

    @Column(name = "oidc_id_token_issued_at")
    private Instant oidcIdTokenIssuedAt;

    @Column(name = "oidc_id_token_expires_at")
    private Instant oidcIdTokenExpiresAt;

    @Column(name = "oidc_id_token_metadata", columnDefinition = "text")
    private String oidcIdTokenMetadata;

    @Column(name = "oidc_id_token_claims", columnDefinition = "text")
    private String oidcIdTokenClaims;
}
