package com.robinfood.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class ConfigurationsBCApplication {

    @Value("${swagger.server.url}")
    private String serverUrl;

    private static final String BEARER_AUTH = "bearerAuth";

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationsBCApplication.class);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setDescription("Server URL");
        server.setUrl(serverUrl);
        return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList(BEARER_AUTH))
            .components(new Components().addSecuritySchemes(BEARER_AUTH,
                new SecurityScheme().name(BEARER_AUTH)
                    .type(Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")))
            .servers(Collections.singletonList(server))
            .info(new Info()
                .title("Configurations Business Capability")
                .version("1.0")
                .description("Administrative services for Configurations")
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
