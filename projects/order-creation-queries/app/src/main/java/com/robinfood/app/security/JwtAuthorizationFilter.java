package com.robinfood.app.security;

import static com.robinfood.core.constants.APIConstants.DEFAULT_LOCALE;
import static com.robinfood.core.constants.APIConstants.UNAUTHORIZED_MESSAGE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.UserDTO;
import com.robinfood.core.exceptions.AuthException;
import com.robinfood.core.extensions.ObjectExtensions;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

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
            @org.jetbrains.annotations.NotNull HttpServletRequest request,
            @org.jetbrains.annotations.NotNull HttpServletResponse response,
            @org.jetbrains.annotations.NotNull FilterChain filterChain
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
            log.error("Do Filter Internal failed ", e);
            denyRequest(response);
        }
    }

    /**
     * Sets the grant access to the current user
     * @param claims the role, module and audience this user will have
     * @return the authenticated user with these properties
     */
    private UsernamePasswordAuthenticationToken authenticateUser(Claims claims) {
        Set<SimpleGrantedAuthority> grantedValues = getGrantedValues(claims);
        final UserDTO user = new UserDTO(claims.getSubject(), DEFAULT_STRING_VALUE, grantedValues);
        return new UsernamePasswordAuthenticationToken(user, DEFAULT_STRING_VALUE, grantedValues);
    }

    /**
     * Method called when there is a problem authenticating the user and the request cannot be processed
     * @param response the response that will be returned to the client
     */
    private void denyRequest(@NotNull final HttpServletResponse response) {
        try (PrintWriter writer = response.getWriter()) {
            final ApiResponseDTO<String> apiResponse = new ApiResponseDTO<>(null);
            apiResponse.setMessage(UNAUTHORIZED_MESSAGE);
            apiResponse.setLocale(DEFAULT_LOCALE);
            final String backOfficeResponse = ObjectExtensions.toJson(apiResponse);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            writer.write(backOfficeResponse);
            writer.flush();
        } catch (IOException e) {
            log.error("Deny request failed ", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Checks if the jwt token exists in the request
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
     * @param claims contains the permissions of the current user
     * @return a set of authorities for the current user
     */
    private Set<SimpleGrantedAuthority> getGrantedValues(Claims claims) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        try {
            List<String> scopes = ObjectExtensions.tryCast(claims.get("per"));
            if (scopes != null) {
                scopes.forEach(scope -> authorities.add(
                        new SimpleGrantedAuthority("ROLE_" + scope.toUpperCase(Locale.getDefault())))
                );
            } else {
                throw new AuthException("Scopes variable is null");
            }
        } catch (Exception e) {
            log.error("Unexpected exception", e);
        }

        return authorities;
    }

    /**
     * Gets the authorization properties a JWT token must have
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

        final List<String> tokenMods = ObjectExtensions.tryCast(claims.get("mod"));

        final String tokenModValidValue = environment.getRequiredProperty("jwt-token-mod");
        if (tokenMods == null || !tokenMods.contains(tokenModValidValue)) {
            return null;
        }
        return claims;
    }
}
