package com.robinfood.configurationsposbc.configs;

import com.robinfood.configurationsposbc.security.JwtAuthorizationFilter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfiguration extends WebSecurityConfigurerAdapter {

    private final Environment environment;

    private static final String[] AUTH_WHITE_LIST = {
            "/api-docs/**",
            "v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/actuator",
            "/actuator/health",
            "/actuator/info"
    };

    public WebConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(
                        securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ))
                .info(new Info()
                        .title("Welcome to Configurations Pos Bc Business Capability")
                        .version("1.0")
                        .description(
                                "The following APIs allow to carry out all the necessary validations"
                                        + " to consult configurations"
                        )
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITE_LIST).permitAll()
                .antMatchers("/v1/**").hasRole("NOT_YET_DEFINED")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        (
                                HttpServletRequest request,
                                HttpServletResponse response,
                                AuthenticationException authException
                        ) -> response.sendError(
                                HttpServletResponse.SC_UNAUTHORIZED,
                                authException.getLocalizedMessage()
                        )
                )
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(new JwtAuthorizationFilter(environment),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.GET, AUTH_WHITE_LIST);
    }
}
