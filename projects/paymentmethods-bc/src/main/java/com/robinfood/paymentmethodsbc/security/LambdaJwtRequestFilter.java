package com.robinfood.paymentmethodsbc.security;

import com.robinfood.paymentmethodsbc.constants.AppConstants;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Clase componente que se utiliza como filtro middleware para la validacion y
 * autenticacion por medio de JWT para token de servicio
 * https://muytech.atlassian.net/wiki/spaces/TECH/pages/1290108953/JWT+Middleware+for+SSO+Backend
 *
 */
@Component
public class LambdaJwtRequestFilter extends OncePerRequestFilter {

    private static final int SUBSTRING_TOKEN = 7;
    private final LambdaTokenUtil lambdaTokenUtil;

    public LambdaJwtRequestFilter(LambdaTokenUtil lambdaTokenUtil) {
        this.lambdaTokenUtil = lambdaTokenUtil;
    }

    /**
     * @param request {@linkplain HttpServletRequest}
     * @param response {@linkplain HttpServletResponse}
     * @param chain {@linkplain FilterChain}
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    )
        throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        if (
            requestTokenHeader != null &&
            requestTokenHeader.startsWith("Bearer ")
        ) {

            final String jwtToken = requestTokenHeader.substring(
                    SUBSTRING_TOKEN
            );

            if (SecurityContextHolder.getContext().getAuthentication() == null
                    && lambdaTokenUtil.validateToken(jwtToken)
            ) {

                final UserDetails userDetails = new User(
                        "internal",
                        AppConstants.SPRING_SECURITY_PASSWORD,
                        Collections.emptyList()
                );

                final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    requestTokenHeader,
                    Collections.emptyList()
                );

                usernamePasswordAuthenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder
                    .getContext()
                    .setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        chain.doFilter(request, response);
    }
}
