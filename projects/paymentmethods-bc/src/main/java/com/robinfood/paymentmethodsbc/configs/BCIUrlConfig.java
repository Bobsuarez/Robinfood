package com.robinfood.paymentmethodsbc.configs;

import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "bci.url")
public class BCIUrlConfig {
    private Map<String, String> component;

    public String getComponentUrl(String componentName) {
        return component.get(componentName);
    }
}
