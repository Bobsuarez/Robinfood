package com.robinfood.configurationsposbc.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.configurationsposbc.dtos.UserDTO;
import com.robinfood.configurationsposbc.dtos.apiresponsebuilder.ApiErrorResponseDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.auth.message.AuthException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.robinfood.configurationsposbc.configs.constans.APIConstants.DEFAULT_LOCALE;
import static com.robinfood.configurationsposbc.configs.constans.APIConstants.DEFAULT_STRING_VALUE;
import static com.robinfood.configurationsposbc.configs.constans.APIConstants.UNAUTHORIZED_REQUEST;

/**
 * Class that verifies the JWT Token of every single request and checks if the user has the correct
 * permissions
 */
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final Environment environment;

    private final StringWriter sw = new StringWriter();

    public JwtAuthorizationFilter(@NotNull final Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) {
        try {
            if (!existJwtToken(request)) {
                denyRequest(response);
                return;
            }

            final String tokenPrefix = environment.getRequiredProperty("jwt-token-prefix");
            final String jwtToken = request
                    .getHeader(HttpHeaders.AUTHORIZATION)
                    .replace(tokenPrefix, DEFAULT_STRING_VALUE);
            final Claims claims = getTokenClaims(jwtToken);
            if (claims == null) {
                denyRequest(response);
                return;
            }

            final UsernamePasswordAuthenticationToken auth = authenticateUser(claims);
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            sendErrorResponse(response, e.getMessage(), sw.toString());
        }
    }

    /**
     * Sets the grant access to the current user
     *
     * @param claims the role, module and audience this user will have
     * @return the authenticated user with these properties
     */
    private UsernamePasswordAuthenticationToken authenticateUser(Claims claims) {
        Set<SimpleGrantedAuthority> grantedValues = getGrantedValues(claims);
        final UserDTO user = new UserDTO(claims.getSubject(), DEFAULT_STRING_VALUE, grantedValues);
        return new UsernamePasswordAuthenticationToken(user, DEFAULT_STRING_VALUE, grantedValues);
    }

    private String toJson(ApiErrorResponseDTO apiErrorResponseDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(apiErrorResponseDTO);
        } catch (JsonProcessingException e) {
            json = "{}";
        }
        return json;
    }

    /**
     * Method called when there is a problem authenticating the user and the request cannot be processed
     *
     * @param response the response that will be returned to the client
     */
    private void denyRequest(@NotNull final HttpServletResponse response) {
        try (PrintWriter writer = response.getWriter()) {
            ApiErrorResponseDTO<String> apiResponse = new ApiErrorResponseDTO<>();
            apiResponse.setStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setMessage(UNAUTHORIZED_REQUEST);
            apiResponse.setLocale(DEFAULT_LOCALE);
            apiResponse.setCode(HttpStatus.UNAUTHORIZED.value());
            final String backOfficeResponse = toJson(apiResponse);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            writer.write(backOfficeResponse);
            writer.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void sendErrorResponse(@NotNull final HttpServletResponse response, String msg, String stackTrace) {
        try (PrintWriter writer = response.getWriter()) {
            final ApiErrorResponseDTO<String> apiResponse = new ApiErrorResponseDTO<>();
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setMessage(String.format("%s: %s", msg, stackTrace));
            apiResponse.setLocale(DEFAULT_LOCALE);
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            final String backOfficeResponse = toJson(apiResponse);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            writer.write(backOfficeResponse);
            writer.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Checks if the jwt token exists in the request
     *
     * @param request the request made by the connecting client
     * @return true if the jwt token prefix exists, false otherwise
     */
    private boolean existJwtToken(@NotNull final HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String tokenPrefix = environment.getRequiredProperty("jwt-token-prefix");
        return !(authHeader == null || !authHeader.startsWith(tokenPrefix));
    }

    /**
     * Gets the granted values a user will have, such as the current role and the permissions
     *
     * @param claims contains the permissions of the current user
     * @return a set of authorities for the current user
     */
    private Set<SimpleGrantedAuthority> getGrantedValues(Claims claims) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        try {
            List<String> scopes = (List<String>) (claims.get("per"));
            if (scopes != null) {
                scopes.forEach(scope -> authorities.add(
                        new SimpleGrantedAuthority("ROLE_" + scope.toUpperCase(Locale.getDefault())))
                );
            } else {
                throw new AuthException("Scopes variable is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return authorities;
    }

    /**
     * Gets the authorization properties a JWT token must have
     *
     * @param jwtToken the current JWT token
     * @return returns the authorization claims
     */
    private Claims getTokenClaims(@NotNull final String jwtToken) {
        String tokenSecret = environment.getRequiredProperty("jwt.token.secret");

        String tokenAudValues = environment.getRequiredProperty("jwt-token-aud");

        List<String> tokenAudValidValues = Arrays
                .asList(tokenAudValues.split(","));
        Claims claims = Jwts
                .parser()
                .setSigningKey(tokenSecret.getBytes(StandardCharsets.ISO_8859_1))
                .parseClaimsJws(jwtToken)
                .getBody();
        String tokenAud = claims.getAudience();

        if (tokenAud == null || !tokenAudValidValues.contains(tokenAud)) {
            return null;
        }

        return claims;
    }
}
