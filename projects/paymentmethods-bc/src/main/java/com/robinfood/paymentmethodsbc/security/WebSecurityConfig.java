package com.robinfood.paymentmethodsbc.security;

import com.robinfood.paymentmethodsbc.enums.ResponseCode;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Clase de configuracion que define los parámetros de acceso a los
 * controlladores o paginas de la aplicacion
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    public static final String ENDPOINT_TRANSACTION_STATUS =
        "/api/v1/transactions/**/status";

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    private static final String[] AUTH_WHITE_LIST = {
        "/api-docs/**",
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/swagger-ui.html",
        "/actuator",
        "/actuator/health"
    };

    public WebSecurityConfig() {
        //WebSecurityConfig
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
        throws BaseException {
        try {
            auth
                .userDetailsService(jwtUserDetailsService)
                .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BaseException(e, ResponseCode.UNAUTHORIZED);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuracion CORS para Spring security
     * Si el HttpSecurity contiene filtros personalizados este filtro debera reemplazar las cabeceras
     * para que la el preflight (https://developer.mozilla.org/es/docs/Glossary/Preflight_request)
     * no ocasione error en los clientes de tipo javascript
     *
     * @return CorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(
            Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")
        );
        configuration.setAllowedHeaders(Arrays.asList("*"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Configuration
    @Order(1)
    public static class LambdaSecurityConfigurationAdapter
        extends WebSecurityConfigurerAdapter {
        @Autowired
        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

        @Autowired
        private LambdaJwtRequestFilter lambdaJwtRequestFilter;

        /**
         * Configuracion de acceso a las peticiones realizadas a el servicio. algunos
         * controlladores no requieren autenticacion por lo tanto deberan ser
         * congigurados con la instruccion
         * httpSecurity.csrf().disable().authorizeRequests().antMatchers(PATH_API).permitAll();
         *
         * @param httpSecurity
         * @throws Exception
         */
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                .antMatcher(ENDPOINT_TRANSACTION_STATUS)
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITE_LIST)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(
                    lambdaJwtRequestFilter,
                    UsernamePasswordAuthenticationFilter.class
                );
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers(HttpMethod.GET, AUTH_WHITE_LIST);
        }
    }

    @Configuration
    public static class SSOSecurityConfigurationAdapter
        extends WebSecurityConfigurerAdapter {
        @Autowired
        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

        @Autowired
        private SSORequestFilter ssoRequestFilter;

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITE_LIST)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(
                    ssoRequestFilter,
                    UsernamePasswordAuthenticationFilter.class
                );
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers(HttpMethod.GET, AUTH_WHITE_LIST);
        }
    }
}
