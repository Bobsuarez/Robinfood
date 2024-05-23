package com.robinfood.paymentmethodsbc.repositories.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.constants.RedisConstants;
import com.robinfood.paymentmethodsbc.model.TerminalPaymentMethodSetting;
import com.robinfood.paymentmethodsbc.repositories.RedisRepository;
import com.robinfood.paymentmethodsbc.repositories.TerminalPaymentMethodSettingsCachedRepository;
import com.robinfood.paymentmethodsbc.repositories.TerminalPaymentMethodSettingsRepository;
import com.robinfood.paymentmethodsbc.utils.JsonUtils;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class TerminalPaymentMethodSettingsCachedRepositoryImpl
    implements TerminalPaymentMethodSettingsCachedRepository {

    private final TerminalPaymentMethodSettingsRepository terminalPaymentMethodSettingsRepository;
    private final RedisRepository redisRepository;

    public TerminalPaymentMethodSettingsCachedRepositoryImpl(
        final TerminalPaymentMethodSettingsRepository terminalPaymentMethodSettingsRepository,
        final RedisRepository redisRepository
    ) {
        this.terminalPaymentMethodSettingsRepository = terminalPaymentMethodSettingsRepository;
        this.redisRepository = redisRepository;
    }

    @BasicLog
    @Override
    public List<TerminalPaymentMethodSetting> findByTerminalIdAndPaymentGatewayIdAndTerminalParameterVisible(
        final Long terminalId,
        final Long paymentGatewayId,
        final boolean parameterVisible
    ) {
        final String keyRedis = getKeyFindTerminalIdAndPaymentGatewayId(
            terminalId,
            paymentGatewayId,
            parameterVisible
        );

        List<TerminalPaymentMethodSetting> result = getSettingsInRedis(keyRedis);

        if (result == null || result.isEmpty()) {
            log.info("Redis data is not available, getting from database");

            result =
                terminalPaymentMethodSettingsRepository.findByTerminalIdAndPaymentGatewayIdAndTerminalParameterVisible(
                    terminalId,
                    paymentGatewayId,
                    parameterVisible
                );

            log.debug("Result of query on terminal_payment_method_settings with parameters: "
                .concat(String.format("terminalId: %d, paymentGatewayId: %d, parameterVisible: %s ",
                    terminalId,
                    paymentGatewayId,
                    parameterVisible)
                )
                .concat(String.format("number of results: %d", result.size()))
            );

            redisRepository.set(
                keyRedis,
                JsonUtils.convertToJson(result)
            );
        }
        return result;
    }

    @BasicLog
    @Override
    public Map<String, String> findAllSettingsTerminalPaymentMethod(
        final Long terminalId, final Long paymentGatewayId
    ) {
        final String keyRedis = getKeyFindAllSettingsTerminalPaymentMethod(
            terminalId,
            paymentGatewayId
        );

        List<TerminalPaymentMethodSetting> result = getSettingsInRedis(keyRedis);

        if (result == null || result.isEmpty()) {
            log.info("Redis data is not available {}, getting from database", keyRedis);

            result =
                terminalPaymentMethodSettingsRepository.findByTerminalIdAndPaymentGatewayId(
                    terminalId,
                    paymentGatewayId
                );

            log.debug("Result of query on terminal_payment_method_settings with parameters: "
                .concat(String.format("terminalId: %d, paymentGatewayId: %d ",
                    terminalId,
                    paymentGatewayId)
                )
                .concat(String.format("number of results: %d", result.size()))
            );

            redisRepository.set(
                keyRedis,
                JsonUtils.convertToJson(result)
            );
        }

        return result
            .stream()
            .collect(
                Collectors.toMap(
                    x -> x.getTerminalParameter().getKey(),
                    TerminalPaymentMethodSetting::getValue
                )
            );
    }

    @Override
    public void clearCache() {
        final int index = RedisConstants.PM_REDIS_TERMINAL_PAYMENT_METHOD_SETTINGS_KEY.indexOf('%');
        final String pattern = RedisConstants
            .PM_REDIS_TERMINAL_PAYMENT_METHOD_SETTINGS_KEY
            .substring(0, index)
            .concat("*");

        redisRepository
            .getKey(pattern)
            .forEach(redisRepository::deleteKey);
    }

    private List<TerminalPaymentMethodSetting> getSettingsInRedis(
        final String keyRedis
    ) {
        log.info("Querying TerminalPaymentMethodSetting in redis. key {}", keyRedis);

        return redisRepository
            .get(keyRedis)
            .map(this::jsonToTerminalPaymentMethodSetting)
            .orElse(Collections.emptyList());
    }

    /**
     * Convierte un JSON a una lista de objetos TerminalPaymentMethodSetting
     * @param json {@linkplain String} Json a convertir
     * @return {@linkplain List<TerminalPaymentMethodSetting>}
     */
    private List<TerminalPaymentMethodSetting> jsonToTerminalPaymentMethodSetting(
        final String json
    ) {
        try {
            log.info(
                "Converting json to {} object",
                TerminalPaymentMethodSetting.class.getSimpleName()
            );
            return List.of(
                JsonUtils.jsonToObject(
                    json,
                    TerminalPaymentMethodSetting[].class
                )
            );
        } catch (JsonProcessingException e) {
            log.error("JSON from redis cannot be converted to object", e);
        }
        return Collections.emptyList();
    }

    private static String getKeyFindTerminalIdAndPaymentGatewayId(
        final Long terminalId,
        final Long paymentGatewayId,
        final boolean parameterVisible
    ) {
        return String.format(
            RedisConstants.PM_REDIS_TERMINAL_PAYMENT_METHOD_SETTINGS_VISIBLE_KEY,
            terminalId,
            paymentGatewayId,
            parameterVisible
        );
    }

    private static String getKeyFindAllSettingsTerminalPaymentMethod(
        final Long terminalId,
        final Long paymentGatewayId
    ) {
        return String.format(
            RedisConstants.PM_REDIS_TERMINAL_PAYMENT_METHOD_SETTINGS_KEY,
            terminalId,
            paymentGatewayId
        );
    }
}
