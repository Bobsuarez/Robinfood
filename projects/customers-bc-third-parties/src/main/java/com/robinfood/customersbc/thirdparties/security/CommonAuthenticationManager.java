package com.robinfood.customersbc.thirdparties.security;

import com.robinfood.customersbc.thirdparties.components.JwtTokenComponent;
import com.robinfood.customersbc.thirdparties.security.AuthorityConfiguration.Audience;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Locale;

import static com.robinfood.customersbc.thirdparties.enums.GrantedAuthorityEnum.ROLE;
import static com.robinfood.customersbc.thirdparties.enums.ResponseCode.UNAUTHORIZED;

@Component
public class CommonAuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtTokenComponent jwtTokenComponent;
    private final AuthorityConfiguration authorityConfiguration;

    public CommonAuthenticationManager(
        JwtTokenComponent jwtTokenComponent, AuthorityConfiguration authorityConfiguration
    ) {
        this.jwtTokenComponent = jwtTokenComponent;
        this.authorityConfiguration = authorityConfiguration;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        return Mono.just(jwtTokenComponent.validateToken(authToken))
            .filter((Boolean valid) -> valid)
            .switchIfEmpty(Mono.error(new AuthenticationCredentialsNotFoundException(UNAUTHORIZED.getMessage())))
            .map((Boolean valid) -> jwtTokenComponent.getAllClaimsFromToken(authToken))
            .onErrorResume(e -> Mono.error(new AuthenticationCredentialsNotFoundException(UNAUTHORIZED.getMessage())))
            .filter((Claims claims) -> authorityConfiguration.getAudiences().stream().map(Audience::getType)
                .toList().contains(claims.getAudience())
            )
            .switchIfEmpty(Mono.error(new AuthenticationCredentialsNotFoundException(UNAUTHORIZED.getMessage())))
            .flatMap((Claims claims) -> {
                Authentication auth = new UsernamePasswordAuthenticationToken(
                    jwtTokenComponent.getUserDetails(authToken), null,
                    List.of(new SimpleGrantedAuthority(
                        ROLE.getName().concat(claims.getAudience().toUpperCase(Locale.getDefault())))
                    )
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
                return Mono.just(auth);
            })
            .onErrorResume(Mono::error);
    }
}
