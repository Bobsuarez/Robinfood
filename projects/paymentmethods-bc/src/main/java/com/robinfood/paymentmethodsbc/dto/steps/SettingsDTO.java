package com.robinfood.paymentmethodsbc.dto.steps;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingsDTO {
    private Map<String, String> terminalConfig;

    private Map<String, String> gatewayConfig;

    private Map<String, String> orchConfig;
}
