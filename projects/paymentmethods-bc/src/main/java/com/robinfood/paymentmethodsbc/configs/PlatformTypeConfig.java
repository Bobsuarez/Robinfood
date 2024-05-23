package com.robinfood.paymentmethodsbc.configs;

import com.robinfood.paymentmethodsbc.enums.PlatformType;
import java.util.Map;
import java.util.Objects;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "platform")
public class PlatformTypeConfig {

    private Map<String, Long> type;

    public PlatformType getPlatformById(final Long platformId){

        return type.entrySet()
            .stream()
            .filter(entry -> Objects.equals(entry.getValue(), platformId))
            .map(Map.Entry::getKey)
            .map(PlatformType::getPlatformByName)
            .findFirst()
            .orElse(null);
    }
    
}
