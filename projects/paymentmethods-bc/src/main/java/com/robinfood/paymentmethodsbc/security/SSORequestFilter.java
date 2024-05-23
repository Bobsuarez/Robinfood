package com.robinfood.paymentmethodsbc.security;

import static com.robinfood.paymentmethodsbc.constants.AppConstants.SPRING_SECURITY_PASSWORD;

import com.robinfood.paymentmethodsbc.constants.ControllerPermissions;
import com.robinfood.paymentmethodsbc.dto.sso.SSOUserDTO;

import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Clase componente que se utiliza como filtro middleware para la validacion y
 * autenticacion por medio de l JWT
 * https://muytech.atlassian.net/wiki/spaces/TECH/pages/1290108953/JWT+Middleware+for+SSO+Backend
 *
 */
@Component
@Slf4j
public class SSORequestFilter extends OncePerRequestFilter {

    private final SSOTokenUtil ssoTokenUtil;

    private static final String SERVICE_USER_ID_DEFAULT = "0";
    private static final String CLAIM_AUDIENCIA = "aud";
    private static final String CLAIM_PERMISOS = "per";
    private static final int SUBSTRING_TOKEN = 7;

    public SSORequestFilter(SSOTokenUtil ssoTokenUtil) {
        this.ssoTokenUtil = ssoTokenUtil;
    }

    /**
     * @param request
     * @param response
     * @param chain
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

        final String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (ssoTokenUtil.isJwtToken(requestTokenHeader)) {
            final String jwtToken = requestTokenHeader.substring(
                SUBSTRING_TOKEN
            );
            try {
                if (SecurityContextHolder.getContext().getAuthentication() == null
                    && ssoTokenUtil.validateToken(jwtToken)
                ) {

                    final UsernamePasswordAuthenticationToken authenticateUser = authenticateUser(jwtToken);
                    authenticateUser.setDetails(
                        new WebAuthenticationDetailsSource()
                        .buildDetails(request)
                    );
                    SecurityContextHolder
                        .getContext()
                        .setAuthentication(authenticateUser);
                }
            } catch (IllegalArgumentException | ExpiredJwtException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * Método encargado de autenticar usuario en aplicación
     * @param jwtToken {@linkplain String} token jwt
     * @return objeto de usuario logeado
     */
    private UsernamePasswordAuthenticationToken authenticateUser(
        final String jwtToken
    ) {
        final SSOUserDTO ssoUser = ssoTokenUtil.getSSOUserFromToken(
            jwtToken
        );

        String userId = SERVICE_USER_ID_DEFAULT;

        if(ssoUser != null) {
            userId = String.valueOf(ssoUser.getUserId());
        }

        final GrantedAuthority role = getGrantedAuthorityByTokenAndClaim(
            jwtToken,
            CLAIM_AUDIENCIA
        );

        final UserDetails userDetails = new SessionUser(
            userId,
            SPRING_SECURITY_PASSWORD,
            ssoUser,
            Collections.singletonList(role)
        );

        return new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            getGrantedAuthorityListByToken(jwtToken, CLAIM_PERMISOS)
        );
    }

    /**
     * asigna los permiso que vienen en el claim per a el Authority de spring boot
     *
     * @since 11022021
     * @param token token jwt
     * @return List GrantedAuthority
     */
    public List<GrantedAuthority> getGrantedAuthorityListByToken(
        String token,
        String claim
    ) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        try {
            if(ssoTokenUtil.isTokenService(token)) {
                grantedAuthorityList.add(new SimpleGrantedAuthority(ControllerPermissions.PERM_SERVICE));
            } else {
                getGrantedAuthorityByAudience(token, claim, grantedAuthorityList);
            }
        } catch (Exception e) {
            log.error(
                "Attibute 'per' was not found",
                e
            );
        }

        return grantedAuthorityList;
    }

    /**
     * Obtiene permisos de token de usuario cuando audience es diferente a service
     * @param token {@linkplain String} token jwt
     * @param claim {@linkplain String} claim de busqueda
     * @param grantedAuthorityList {@linkplain List<GrantedAuthority>} lista de permisos
     */
    private void getGrantedAuthorityByAudience(
        final String token,
        final String claim,
        final List<GrantedAuthority> grantedAuthorityList
    ) {
        @SuppressWarnings("unchecked")
        final List<String> authorities = (List<String>) ssoTokenUtil.getClaimByKeyFromToken(
            token,
            claim
        );
        for (String authority : authorities) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(authority));
        }
    }

    /**
     * asigna los permiso que vienen en el claim seteado a el Authority de spring
     * boot
     *
     * @since 11022021
     * @param token token jwt
     * @return List GrantedAuthority
     */
    public GrantedAuthority getGrantedAuthorityByTokenAndClaim(
        String token,
        String claim
    ) {
        GrantedAuthority grantedAuthority = null;
        try {
            String authority = (String) ssoTokenUtil.getClaimByKeyFromToken(
                token,
                claim
            );
            grantedAuthority = new SimpleGrantedAuthority(authority);
        } catch (Exception e) {
            log.warn(
                "No se encontro lista de permisos en el atributo {} ",
                claim
            );
            log.error(e.getLocalizedMessage(), e);
        }

        return grantedAuthority;
    }
}
