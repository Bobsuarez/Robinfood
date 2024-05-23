package com.robinfood.taxes.security;

import static com.robinfood.taxes.constants.GeneralConstants.AUDIENCE_SERVICE;
import static com.robinfood.taxes.constants.GeneralConstants.BEARER;
import static com.robinfood.taxes.constants.GeneralConstants.USER_SERVICE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.taxes.constants.GeneralConstants;
import com.robinfood.taxes.constants.PermissionsConstants;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final Environment environment;
    private final ObjectMapper objectMapper;

    public JwtAuthorizationFilter(@NotNull final Environment environment,
        ObjectMapper objectMapper) {
        this.environment = environment;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {

        if (!existJwtToken(request)) {
            log.trace("Token does not exist.");
            denyRequest(response);
            return;
        }

        log.info("Starting authentication process...");

        log.trace("Getting JWT token from request.");
        String tokenPrefix = BEARER;
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION)
            .substring(tokenPrefix.length());
        log.trace("JWT token: {}", jwtToken);

        try {
            log.trace("Getting claims from JWT");
            Claims claims = getClaims(jwtToken);
            log.trace("Claims obtained successfully");

            if (!isValidClaims(claims)) {
                denyRequest(response);
                return;
            }

            log.trace("Token is valid.");
            log.trace("Authenticate User");
            UsernamePasswordAuthenticationToken auth = authenticateUser(claims);
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
            log.trace("Authenticated user successfully");

        } catch (JwtException e) {
            log.error(e.getMessage(), e);
            denyRequest(response);
            return;
        }

        filterChain.doFilter(request, response);
        log.info("Authentication process ended successfully.");

    }

    private Claims getClaims(String token) {
        String tokenSecret = environment.getRequiredProperty("jwt.token.secret");
        return Jwts.parser().setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
            .parseClaimsJws(token).getBody();
    }

    private boolean isValidAudience(Claims claims) {

        log.trace("Getting system audiences.");
        String tokenAudValues = environment.getRequiredProperty("jwt.token.aud");
        List<String> tokenAudValidValues = Arrays.asList(tokenAudValues.split(","));
        log.trace("Audiences obtained successfully: {}", tokenAudValues);

        log.trace("Getting token audience.");
        String tokenAud = claims.getAudience();
        log.trace("Token audience obtained successfully: {}", tokenAud);

        log.trace("Validating audience");
        if (tokenAud == null || !tokenAudValidValues.contains(tokenAud)) {
            log.trace("Audience no valid");
            return false;
        }
        log.trace("Valid audience");
        return true;
    }

    private boolean isValidUser(Claims claims) {
        log.trace("Getting token user.");
        Map<String, Object> user = claims.get("user", Map.class);
        if (user == null || user.get("user_id") == null) {
            log.trace("User objet no valid");
            return false;
        }
        log.trace("Token user obtained successfully: {}", user);
        return true;
    }

    private boolean isValidClaims(Claims claims) {

        if (!claims.getAudience().equals(AUDIENCE_SERVICE)) {
            if (!isValidUser(claims)) {
                return false;
            }
        }
        return isValidAudience(claims);
    }

    private boolean existJwtToken(@NotNull final HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        return !(authHeader == null || !authHeader.startsWith(BEARER));
    }

    public UsernamePasswordAuthenticationToken authenticateUser(Claims claims) {
        Set<SimpleGrantedAuthority> grantedValues = getGrantedValues(claims);
        Map<String, Object> tokenUser = claims.get("user", Map.class);
        String userId = USER_SERVICE;
        if (tokenUser != null) {
            userId = tokenUser.get("user_id").toString();
        }
        User user = new User(userId, "", "", grantedValues);
        return new UsernamePasswordAuthenticationToken(user, "", grantedValues);
    }

    private static Set<SimpleGrantedAuthority> getGrantedValues(Claims claims) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(
                "ROLE_" + PermissionsConstants.SERVICE
            )
        );

        if (!claims.getAudience().equals(AUDIENCE_SERVICE)) {
            authorities.clear();
            log.trace("Getting token permissions.");
            List<String> scopes = claims.get("per", ArrayList.class);
            log.trace("Token permissions obtained successfully: {}", scopes);
            if (scopes != null) {
                scopes.forEach(
                    x -> authorities.add(
                        new SimpleGrantedAuthority("ROLE_" + x.toUpperCase(Locale.getDefault()))
                    )
                );
            }
        }

        return authorities;
    }

    private void denyRequest(@NotNull final HttpServletResponse response) {
        try (PrintWriter writer = response.getWriter()) {
            ApiResponseDTO<Object> apiResponse = new ApiResponseDTO<>();
            apiResponse.setMessage(GeneralConstants.UNAUTHORIZED_REQUEST);
            apiResponse.setCode(HttpStatus.UNAUTHORIZED.value());
            String dataResponse = objectMapper.writeValueAsString(apiResponse);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            writer.write(dataResponse);
            writer.flush();
        } catch (IOException e) {
            log.trace("Error while writing response to deny request.");
            log.error(e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}

