package com.robinfood.app.di

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import kotlin.Throws
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.config.http.SessionCreationPolicy
import com.robinfood.app.security.JwtAuthorizationFilter
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.core.AuthenticationException
import java.lang.Exception

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
open class WebConfiguration(private val environment: Environment) : WebSecurityConfigurerAdapter() {

    @Bean
    open fun customOpenAPI(): OpenAPI {
        val securitySchemeName = "bearerAuth"
        return OpenAPI()
            .addSecurityItem(SecurityRequirement().addList(securitySchemeName))
            .components(
                Components().addSecuritySchemes(
                    securitySchemeName,
                    SecurityScheme()
                        .name(securitySchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
            )
            .info(
                Info()
                    .title("POS v2")
                    .version("1.0")
                    .description(
                        "The following API's allow to connect " +
                                "to all data related to the new RobinFood's POS"
                    )
            )
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers(*AUTH_WHITE_LIST).permitAll()
            .antMatchers("/v1/**").hasRole("NOT_YET_DEFINED")
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint { _: HttpServletRequest?, response: HttpServletResponse, authException: AuthenticationException ->
                response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    authException.localizedMessage
                )
            }
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterAfter(
                JwtAuthorizationFilter(environment),
                UsernamePasswordAuthenticationFilter::class.java
            )
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(HttpMethod.GET, *AUTH_WHITE_LIST)
    }

    companion object {
        private val AUTH_WHITE_LIST = arrayOf(
            "/api-docs/**",
            "v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/actuator",
            "/actuator/**"
        )
    }
}