package com.robinfood.customersbc.thirdparties.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "authority")
public class AuthorityConfiguration implements Serializable {
    @Serial
    private static final long serialVersionUID = 5747229687714129496L;
    private List<Audience> audiences;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Audience implements Serializable {
        @Serial
        private static final long serialVersionUID = 1455658094611601958L;
        private String type;
    }
}
