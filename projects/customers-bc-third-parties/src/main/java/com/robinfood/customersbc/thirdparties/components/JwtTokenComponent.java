package com.robinfood.customersbc.thirdparties.components;

import com.robinfood.customersbc.thirdparties.dtos.SSOUserDTO;
import com.robinfood.customersbc.thirdparties.security.SessionUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.robinfood.customersbc.thirdparties.constants.JwtTokenConstants.CLAIM_COMPANY_ID_KEY;
import static com.robinfood.customersbc.thirdparties.constants.JwtTokenConstants.CLAIM_USER_KEY;
import static com.robinfood.customersbc.thirdparties.constants.JwtTokenConstants.JWT_TOKEN_VALIDITY;
import static com.robinfood.customersbc.thirdparties.constants.JwtTokenConstants.SESSION_USER_PASSWORD;

/**
 * Component for the utilization of jsonwebtoken for encode and decode tokens.
 */
@Component
@Slf4j
public class JwtTokenComponent implements Serializable {
    @Serial
    private static final long serialVersionUID = -1083651453409442621L;
    private final String secret;

    public JwtTokenComponent(@Value("${jwt-token-secret}") String secret) {
        this.secret = secret;
    }

    /**
     * Gets claim by key
     *
     * @param token token
     * @param key key
     * @return Object
     */
    public Object getClaimByKeyFromToken(String token, String key) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get(key);
    }

    public Object getAudienceFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.getAudience();
    }

    /**
     * Gets all claims from token
     * @param token token
     * @return Claims
     */
    public Claims getAllClaimsFromToken(String token) {
        return Jwts
            .parserBuilder()
            .deserializeJsonWith(
                new JacksonDeserializer(
                    Maps.of(CLAIM_USER_KEY, SSOUserDTO.class).build()
                )
            )
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * Gets the user details from token
     * @param token token jwt
     * @return SessionUser
     */
    public SessionUser getUserDetails(final String token){
        final SSOUserDTO ssoUser =
            Optional.ofNullable(getSSOUserFromToken(token)).orElseGet(SSOUserDTO::new);

        ssoUser.setCompanyId((Integer) getClaimByKeyFromToken(token, CLAIM_COMPANY_ID_KEY));

        final GrantedAuthority role = getGrantedAuthorityByAudience(token);

        return new SessionUser(
            String.valueOf(ssoUser.getUserId()),
            SESSION_USER_PASSWORD,
            ssoUser,
            Collections.singletonList(role)
        );
    }

    /**
     * Gets the SSOUserDTO from jwt token
     * @param token token jwt
     * @return SSOUserDTO
     */
    private SSOUserDTO getSSOUserFromToken(final String token) {
        return (SSOUserDTO) getClaimByKeyFromToken(
            token,
            CLAIM_USER_KEY,
            SSOUserDTO.class
        );
    }

    /**
     * Gets permission from audience
     *
     * @param token token jwt
     * @return GrantedAuthority
     */
    private GrantedAuthority getGrantedAuthorityByAudience(
        final String token
    ) {
        try {
            final String authority = (String) getAudienceFromToken(token);
            return new SimpleGrantedAuthority(authority);
        } catch (Exception e) {
            log.error("No value found in attribute {}", Claims.AUDIENCE);
            log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }

    public Object getClaimByKeyFromToken(
        final String token,
        final String key,
        final Class<?> clazz
    ) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get(key, clazz);
    }

    /**
     * Validate jwt token
     * @param token token
     * @return Boolean
     */
    public boolean validateToken(String token) {
        Claims claims = getAllClaimsFromToken(token);

        return validateTokenStructure(claims);
    }

    public boolean validateTokenStructure(final Claims claims) {
        boolean isValid = true;
        if (
            Objects.isNull(claims.get(Claims.AUDIENCE)) ||
            Objects.isNull(claims.get(Claims.EXPIRATION)) ||
            Objects.isNull(claims.get(Claims.ISSUED_AT))
        ) {
            log.error("Invalid JWT token {Claims:{}}", claims);
            isValid = false;
        }
        return isValid;
    }

    /**
     * Generate an jwt token
     * @param claims  claims
     * @param subject  subject
     * @return String
     */
    public String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts
            .builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(
                new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)
            )
            .signWith(getSecretKey())
            .compact();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
