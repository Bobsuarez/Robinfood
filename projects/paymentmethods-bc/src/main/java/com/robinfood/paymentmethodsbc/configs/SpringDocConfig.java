package com.robinfood.paymentmethodsbc.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
            .addServersItem(new Server().url("/"))
            .addSecurityItem(
                new SecurityRequirement().addList(securitySchemeName)
            )
            .components(
                new Components()
                .addSecuritySchemes(
                        securitySchemeName,
                        new SecurityScheme()
                            .name(securitySchemeName)
                            .type(Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                    )
            )
            .info(
                new Info()
                    .title("Payment Methods Business Capability")
                    .description(
                        "API para la gestión de servicios de Payment Methods"
                    )
                    .version("v0.0.1")
                    .license(
                        new License()
                            .name("Robinfood")
                            .url("https://www.robinfood.com/")
                    )
            );
    }
}
