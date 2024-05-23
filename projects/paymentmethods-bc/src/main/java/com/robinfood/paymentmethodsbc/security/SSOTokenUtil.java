package com.robinfood.paymentmethodsbc.security;

import com.robinfood.paymentmethodsbc.constants.ControllerPermissions;
import com.robinfood.paymentmethodsbc.dto.sso.SSOUserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.security.Keys;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static com.robinfood.paymentmethodsbc.constants.JwtTokenConstants.ALG_KEY;
import static com.robinfood.paymentmethodsbc.constants.JwtTokenConstants.AUDIENCE_KEY;
import static com.robinfood.paymentmethodsbc.constants.JwtTokenConstants.HS512;
import static com.robinfood.paymentmethodsbc.constants.JwtTokenConstants.ISSUER;
import static com.robinfood.paymentmethodsbc.constants.JwtTokenConstants.JWT;
import static com.robinfood.paymentmethodsbc.constants.JwtTokenConstants.JWT_TOKEN_VALIDITY;
import static com.robinfood.paymentmethodsbc.constants.JwtTokenConstants.MODULES_KEY;
import static com.robinfood.paymentmethodsbc.constants.JwtTokenConstants.PERMISSIONS_KEY;
import static com.robinfood.paymentmethodsbc.constants.JwtTokenConstants.PUBLIC_AUDIENCE;
import static com.robinfood.paymentmethodsbc.constants.JwtTokenConstants.SERVICE_AUDIENCE;
import static com.robinfood.paymentmethodsbc.constants.JwtTokenConstants.TYP_KEY;

/**
 * Clase componente para la utilizacion de la libreria jsonwebtoken que permite
 * codificar y decodificar el token enviado
 */
@Component
@Slf4j
public class SSOTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    private static final String CLAIM_USER = "user";
    private static final List<String> LIST_AUDIENCES = List.of(
        SERVICE_AUDIENCE,
        "internal",
        "user"
    );

    @Value("${auth.public.cache.timeout-hours:12}")
    private long publicTimeoutInHours;

    private final String secret;

    public SSOTokenUtil(@Value("${auth.jwt-key}") String secret) {
        this.secret = secret;
    }

    /**
     *
     *
     * @param token token jwt
     * @param key   nombre key del claim
     * @return Object
     */
    public Object getClaimByKeyFromToken(String token, String key) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get(key);
    }

    public Object getClaimByKeyFromToken(
        String token,
        String key,
        Class<?> clazz
    ) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get(key, clazz);
    }

    /**
     * @param token
     * @return String
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * @param token
     * @return Date
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    /**
     * @param token
     * @return Date
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public SSOUserDTO getSSOUserFromToken(String token) {
        return (SSOUserDTO) getClaimByKeyFromToken(
            token,
            CLAIM_USER,
            SSOUserDTO.class
        );
    }

    /**
     * @param token
     * @param claimsResolver
     * @return T
     */
    public <T> T getClaimFromToken(
        String token,
        Function<Claims, T> claimsResolver
    ) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * @param token
     * @return Claims
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts
            .parserBuilder()
            .deserializeJsonWith(
                new JacksonDeserializer(
                    Maps.of(CLAIM_USER, SSOUserDTO.class).build()
                )
            )
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * Método encargado de validar token considenrando estructura y contenido
     * @param token {@linkplain String} token jwt
     * @return true si el token es valido
     */
    public boolean validateToken(final String token) {
        final Claims claims = getAllClaimsFromToken(token);

        return validateTokenStructure(claims) && validateTokenClaims(claims);
    }

    /**
     * Metodo encargado de validar estructura del token
     *
     * @param claims {@linkplain Claims} claims de token
     * @return Boolean true en caso de ser válido
     */
    public boolean validateTokenStructure(final Claims claims) {
        boolean isValid = true;
        if (
            claims.get("sub") == null ||
            claims.get("aud") == null ||
            claims.get("exp") == null ||
            claims.get("iat") == null
        ) {
            log.error("Invalid JWT token {Claims:{}}", claims);
            isValid = false;
        }
        return isValid;
    }

    /**
     * Método encargado de validar que el contenido de los claims sea válido,
     * se consideran audiencias y datos de user
     * @param claims {@linkplain Claims} datos de claims en token
     * @return true si tiene audiencia válida,
     *         y es token de servicio o tiene datos de user válidos.
     */
    public boolean validateTokenClaims(final Claims claims) {
        final boolean isAudienceService = SERVICE_AUDIENCE.equals(
            claims.getAudience()
        );

        return (
            isValidAudience(claims) &&
            (isAudienceService || isValidUser(claims))
        );
    }

    /**
     * Método encargado de validar si el usuario contenido en token
     * es válido
     * @param claims {@linkplain Claims} datos de claims en token
     * @return true si el usuario es válido
     */
    private boolean isValidUser(final Claims claims) {
        final SSOUserDTO user = (SSOUserDTO) claims.get("user");
        if (user == null || user.getUserId() == 0) {
            log.info("User object from token is not valid: {}", user);
            return false;
        }
        log.info("Token user obtained successfully: {}", user);
        return true;
    }

    /**
     * Método encargado de validar si la audiencia
     * recibida en token es válida
     * @param claims {@linkplain Claims} claims de token
     * @return
     */
    private boolean isValidAudience(final Claims claims) {
        final String tokenAud = claims.getAudience();
        if (!LIST_AUDIENCES.contains(tokenAud)) {
            log.info("Audience {} is not valid", tokenAud);
            return false;
        }
        log.info("Audience {} is valid", tokenAud);
        return true;
    }

    /**
     * Método encargado de validar si un token recibido en request
     * es permitido
     * @param requestToken {@linkplain String} token recibido en header
     * @return
     */
    public boolean isJwtToken(final String requestToken) {
        return requestToken != null && requestToken.startsWith("Bearer ");
    }

    /**
     * Método encargado de validar si un token jwt
     * es de audiencia service
     * @param token {@linkplain String} token jwt
     * @return
     */
    public boolean isTokenService(final String token) {
        final String audience = (String) getClaimByKeyFromToken(
            token,
            AUDIENCE_KEY
        );
        return SERVICE_AUDIENCE.equals(audience);
    }

    /**
     * @param token
     * @return Boolean
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * @param claims
     * @param subject
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

    /**
     * @param token
     * @return Boolean
     */
    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token));
    }

    /**
     * @param token
     * @param userDetails
     * @return Boolean
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
            username.equals(userDetails.getUsername()) && !isTokenExpired(token)
        );
    }

    /**
     *
     *
     * @param token
     * @param userDetails
     * @return Boolean
     */
    public Boolean validateToken(String token, String userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails) && !isTokenExpired(token));
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String exampleToken(boolean addAllControllerPerms) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("aud", "internal");
        claims.put("iss", "web.v1");
        List<String> perms = Arrays.asList("invalid_perm");
        if (addAllControllerPerms) {
            try {
                perms = ControllerPermissions.getAllPermissions();
            } catch (IllegalAccessException e) {
                log.error(" ", e);
            }
        }
        claims.put("per", perms);
        claims.put(CLAIM_USER, SSOUserDTO.builder().userId(1L).build());
        return doGenerateToken(claims, "1");
    }

    public String exampleInvalidToken() {
        Map<String, Object> claims = new HashMap<>();
        List<String> perms = Arrays.asList("invalid_perm");
        claims.put("per", perms);
        return doGenerateToken(claims, "1");
    }

    public String getPublicJwtToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put(MODULES_KEY, List.of());
        claims.put(PERMISSIONS_KEY, List.of());
        Instant now = Instant.now();
        log.info(
            "getPublicJwtToken, timeout in hours: {} and expiration {}",
            publicTimeoutInHours,
            Date.from(now.plus(publicTimeoutInHours, ChronoUnit.HOURS))
        );

        return Jwts.builder()
            .setHeaderParam(ALG_KEY, HS512)
            .setHeaderParam(TYP_KEY, JWT)
            .setClaims(claims)
            .setIssuer(ISSUER)
            .setAudience(PUBLIC_AUDIENCE)
            .setId(UUID.randomUUID().toString())
            .setExpiration(Date.from(now.plus(publicTimeoutInHours, ChronoUnit.HOURS)))
            .setIssuedAt(Date.from(now))
            .setNotBefore(Date.from(Instant.EPOCH))
            .signWith(getSecretKey())
            .compact();
    }
}
