package com.robinfood.paymentmethodsbc.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Clase componente para la utilizacion de la libreria jsonwebtoken que permite
 * codificar y decodificar el token enviado
 */
@Component
@Slf4j
public class LambdaTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000L;

    private final String secretKeyToken;

    public LambdaTokenUtil(
            @Value("${auth.lambda.jwt.key}") String secretKeyToken
    ) {
        this.secretKeyToken = secretKeyToken;
    }

    /**
     * Valida que un token de servicio cumpla con la estructura esperada
     * y que se encuentre firmado correctamente
     * @param token {@linkplain String} token a validar
     * @return boolean true en caso de ser un token valido
     */
    public boolean validateToken(final String token) {
        boolean isValid = true;
        final Claims claims = getAllClaimsFromToken(token);
        if (claims.get("aud") == null
                || claims.get("exp") == null
                || claims.get("iat") == null
        ) {
            log.error("JWT Token invalido {Claims:{}}", claims);
            isValid = false;
        }
        return isValid;
    }

    /**
     * Obtiene claims de token de servicio
     * @param token {@linkplain String} token
     * @return Claims {@linkplain Claims}
     */
    private Claims getAllClaimsFromToken(final String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSecretKeyToken())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getSecretKeyToken() {
        return Keys.hmacShaKeyFor(secretKeyToken.getBytes(StandardCharsets.UTF_8));
    }


    /**
     * Construye un token de servicio de acuerdo a la configuración enviada
     * @param claims {@linkplain Map<String, Object>}
     * @return {@linkplain String} token de servicio
     */
    public String doGenerateToken(
            final Map<String, Object> claims
    ) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(
                        new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)
                )
                .signWith(getSecretKeyToken())
                .compact();
    }
}
