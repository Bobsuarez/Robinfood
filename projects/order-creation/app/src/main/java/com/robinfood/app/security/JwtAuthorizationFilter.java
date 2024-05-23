package com.robinfood.app.security;

import com.robinfood.core.dtos.UserDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.exceptions.AuthException;
import com.robinfood.core.extensions.ObjectExtensions;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Objects;

import static com.robinfood.core.constants.APIConstants.DEFAULT_LOCALE;
import static com.robinfood.core.constants.GlobalConstants.AUDIENCE_INTERNAL_JWT;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;

/**
 * Class that verifies the JWT Token of every single request and checks if the user has the correct
 * permissions
 */
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final Environment environment;

    public JwtAuthorizationFilter(@NotNull final Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
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
            log.error(e.getLocalizedMessage(), e);
            denyRequest(response);
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
        final UserDTO user = new UserDTO(DEFAULT_STRING_VALUE, grantedValues, claims.getSubject());
        return new UsernamePasswordAuthenticationToken(user, DEFAULT_STRING_VALUE, grantedValues);
    }

    /**
     * Method called when there is a problem authenticating the user and the request cannot be processed
     *
     * @param response the response that will be returned to the client
     */
    private void denyRequest(@NotNull final HttpServletResponse response) {
        try (PrintWriter writer = response.getWriter()) {
            final ApiResponseDTO<String> apiResponse = new ApiResponseDTO<>(null);
            apiResponse.setStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setLocale(DEFAULT_LOCALE);
            apiResponse.setCode(HttpStatus.UNAUTHORIZED.value());
            final String backOfficeResponse = ObjectExtensions.toJson(apiResponse);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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
        final Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        try {
            List<String> scopes = claims.get("per", ArrayList.class);
            if (scopes != null) {
                scopes.forEach(scope -> authorities.add(
                        new SimpleGrantedAuthority(scope.toLowerCase(Locale.getDefault())))
                );
            } else {
                throw new AuthException("Scopes variable is null");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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

        final String tokenSecret = environment.getRequiredProperty("jwt.token.secret");

        final String tokenAudValues = environment.getRequiredProperty("jwt-token-aud");

        final List<String> tokenAudValidValues = Arrays
                .asList(tokenAudValues.split(","));

        final Claims claims = Jwts
                .parser()
                .setSigningKey(tokenSecret.getBytes(StandardCharsets.ISO_8859_1))
                .parseClaimsJws(jwtToken)
                .getBody();

        String tokenAud = claims.getAudience();

        if (Objects.isNull(tokenAud) || !tokenAudValidValues.contains(tokenAud)) {
            return null;
        }

        if(tokenAud.equals(AUDIENCE_INTERNAL_JWT)){

            final List<String> tokenMods = claims.get("mod", ArrayList.class);

            final String tokenModValidValue = environment.getRequiredProperty("jwt-token-mod");

            if (Objects.isNull(tokenMods) || !tokenMods.contains(tokenModValidValue)) {
                return null;
            }

        }

        return claims;

    }
}
