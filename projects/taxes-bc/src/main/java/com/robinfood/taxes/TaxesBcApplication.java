package com.robinfood.taxes;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaxesBcApplication {

    @Value("${swagger.server.url}")
    private String serverUrl;

    public TaxesBcApplication() {
        // this constructor is empty because it is a configuration class
    }

    public static void main(String[] args) {
        SpringApplication.run(TaxesBcApplication.class);
    }

    @Bean
    public OpenAPI customOpenAPI() {

        final String SECURITY_SCHEME_NAME = "bearerAuth";

        Server server = new Server();
        server.setDescription("Server URL");
        server.setUrl(serverUrl);

        return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
            .components(new Components().addSecuritySchemes(SECURITY_SCHEME_NAME,
                new SecurityScheme().name(SECURITY_SCHEME_NAME)
                    .type(Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")))
            .servers(Collections.singletonList(server))
            .info(new Info()
                .title("Taxes Business Capability")
                .version("1.0")
                .description("Allows to manage taxes entities.")
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}

