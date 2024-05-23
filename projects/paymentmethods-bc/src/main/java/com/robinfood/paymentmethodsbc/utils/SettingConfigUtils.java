package com.robinfood.paymentmethodsbc.utils;

import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SettingConfigUtils {

    enum SettingType {
        TERMINAL_CONFIG,
        GATEWAY_CONFIG,
        ORCH_CONFIG
    }

    public static String get(
        SettingType settingType,
        SettingsDTO settingsDTO,
        String paramName,
        String paramDefaulValue
    ) {
        String value = paramDefaulValue;
        switch (settingType) {
            case TERMINAL_CONFIG:
                value =
                    getTerminalConfig(settingsDTO, paramName, paramDefaulValue);
                break;
            case GATEWAY_CONFIG:
                value =
                    getGatewayConfig(settingsDTO, paramName, paramDefaulValue);
                break;
            case ORCH_CONFIG:
                value = getOrchConfig(settingsDTO, paramName, paramDefaulValue);
                break;
            default:
                break;
        }
        log.info(
            "Getting Config {} by param {} = {}",
            settingType,
            paramName,
            value
        );

        return value;
    }

    private static String getTerminalConfig(
        SettingsDTO settingsDTO,
        String paramName,
        String paramDefaulValue
    ) {
        String value = Optional
            .ofNullable(settingsDTO)
            .orElse(
                SettingsDTO
                    .builder()
                    .terminalConfig(Map.of(paramName, paramDefaulValue))
                    .build()
            )
            .getTerminalConfig()
            .get(paramName);

        return value;
    }

    private static String getGatewayConfig(
        SettingsDTO settingsDTO,
        String paramName,
        String paramDefaulValue
    ) {
        String value = Optional
            .ofNullable(settingsDTO)
            .orElse(
                SettingsDTO
                    .builder()
                    .gatewayConfig(Map.of(paramName, paramDefaulValue))
                    .build()
            )
            .getGatewayConfig()
            .get(paramName);

        return value;
    }

    private static String getOrchConfig(
        SettingsDTO settingsDTO,
        String paramName,
        String paramDefaulValue
    ) {
        String value = Optional
            .ofNullable(settingsDTO)
            .orElse(
                SettingsDTO
                    .builder()
                    .orchConfig(Map.of(paramName, paramDefaulValue))
                    .build()
            )
            .getOrchConfig()
            .get(paramName);

        return value;
    }
}
