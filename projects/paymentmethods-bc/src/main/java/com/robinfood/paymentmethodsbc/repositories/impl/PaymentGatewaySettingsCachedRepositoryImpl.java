package com.robinfood.paymentmethodsbc.repositories.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.constants.RedisConstants;
import com.robinfood.paymentmethodsbc.model.CountryPaymentGateway;
import com.robinfood.paymentmethodsbc.model.CountryPaymentGatewaySetting;
import com.robinfood.paymentmethodsbc.repositories.CountryPaymentGatewaySettingsRepository;
import com.robinfood.paymentmethodsbc.repositories.CountryPaymentGatewaysRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewaySettingsCachedRepository;
import com.robinfood.paymentmethodsbc.repositories.RedisRepository;
import com.robinfood.paymentmethodsbc.utils.JsonUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class PaymentGatewaySettingsCachedRepositoryImpl
    implements PaymentGatewaySettingsCachedRepository {
    private final CountryPaymentGatewaySettingsRepository paymentGatewaySettingsRepository;
    private final CountryPaymentGatewaysRepository countryPaymentGatewaysRepository;
    private final RedisRepository redisRepository;

    public PaymentGatewaySettingsCachedRepositoryImpl(
        CountryPaymentGatewaySettingsRepository paymentGatewaySettingsRepository,
        CountryPaymentGatewaysRepository countryPaymentGatewaysRepository,
        RedisRepository redisRepository
    ) {
        this.paymentGatewaySettingsRepository =
            paymentGatewaySettingsRepository;
        this.countryPaymentGatewaysRepository =
            countryPaymentGatewaysRepository;
        this.redisRepository = redisRepository;
    }

    public List<CountryPaymentGatewaySetting> findByCountryIdAndPaymentGatewayId(
        final boolean skipRedis,
        final Long countryId,
        final Long paymentGatewayId
    ) {
        final String keyRedis = getKeyFindCountryAndGatewayId(
            countryId,
            paymentGatewayId
        );

        List<CountryPaymentGatewaySetting> resultRedis = null;
        if (!skipRedis) {
            resultRedis = getSettingsInRedis(keyRedis);
        }

        if (resultRedis == null || resultRedis.isEmpty()) {
            if (!skipRedis) {
                log.info("Redis data is not available, getting from database");
            }

            return paymentGatewaySettingsRepository
                .findByCountryIdAndPaymentGatewayId(countryId, paymentGatewayId)
                .map(
                    (List<CountryPaymentGatewaySetting> settings) -> {
                        redisRepository.set(
                            keyRedis,
                            JsonUtils.convertToJson(settings)
                        );
                        return settings;
                    }
                )
                .orElse(Collections.emptyList());
        }

        return resultRedis;
    }

    /**
     * Metodo encargado de consultar configuraciones en redis
     * @param keyRedis {@linkplain String} key a consultar
     * @return {@linkplain List<CountryPaymentGatewaySetting>} resultado obtenido
     */
    private List<CountryPaymentGatewaySetting> getSettingsInRedis(
        final String keyRedis
    ) {
        return redisRepository
            .get(keyRedis)
            .map(this::jsonToCountryPaymentGateways)
            .orElse(Collections.emptyList());
    }

    /**
     * Convierte un JSON a una lista de objetos CountryPaymentGatewaySetting
     * @param json {@linkplain String} Json a convertir
     * @return {@linkplain List<CountryPaymentGatewaySetting>}
     */
    private List<CountryPaymentGatewaySetting> jsonToCountryPaymentGateways(
        final String json
    ) {
        try {
            log.info(
                "Converting json to {} object",
                CountryPaymentGatewaySetting.class.getSimpleName()
            );
            return List.of(
                JsonUtils.jsonToObject(
                    json,
                    CountryPaymentGatewaySetting[].class
                )
            );
        } catch (JsonProcessingException e) {
            log.error("JSON from redis cannot be converted to object", e);
        }
        return Collections.emptyList();
    }

    /**
     * Obtiene la key respectiva del payment gateway para un pais particular
     *
     * @param countryId id del pais a consultar
     * @param paymentGatewayId del payment gateway
     * @return {@linkplain String} key respectiva a configuración
     */
    private static String getKeyFindCountryAndGatewayId(
        final Long countryId,
        final Long paymentGatewayId
    ) {
        return RedisConstants
            .PM_REDIS_GATEWAY_SETTINGS_KEY.concat(String.valueOf(countryId))
            .concat("-")
            .concat(String.valueOf(paymentGatewayId));
    }

    @BasicLog
    @Override
    public Map<String, String> getSettingsPaymentGateway(
        boolean forceUpdate,
        Long countryId,
        Long paymentGatewayId
    ) {
        List<CountryPaymentGatewaySetting> settings = findByCountryIdAndPaymentGatewayId(
            forceUpdate,
            countryId,
            paymentGatewayId
        );

        return settings
            .stream()
            .collect(
                Collectors.toMap(
                    CountryPaymentGatewaySetting::getName,
                    CountryPaymentGatewaySetting::getValue
                )
            );
    }

    @BasicLog
    @Override
    public Map<String, String> getSettingsOrchestratorPaymentGateway(
        final Long countryId,
        final Long orchestratorId
    ) {
        if (orchestratorId != null) {
            return getSettingsPaymentGateway(false, countryId, orchestratorId);
        }
        return new HashMap<>();
    }

    @BasicLog
    @Override
    public Map<Long, Map<String, String>> getPaymentGatewaySettingsByCountryForTokenization(
        Long countryId
    ) {
        Map<Long, Map<String, String>> settings = new HashMap<>();

        final List<CountryPaymentGateway> listPaymentGateway =
            countryPaymentGatewaysRepository.findByCountryIdAndCreditCardTokenizationEnabled(
                countryId,
                true
            );

        for (CountryPaymentGateway countryPaymentGateway : listPaymentGateway) {
            Map<String, String> map = findByCountryIdAndPaymentGatewayId(
                    false,
                    countryId,
                    countryPaymentGateway.getPaymentGateway().getId()
                )
                .stream()
                .collect(
                    Collectors.toMap(
                        CountryPaymentGatewaySetting::getName,
                        CountryPaymentGatewaySetting::getValue
                    )
                );
            settings.put(
                countryPaymentGateway.getPaymentGateway().getId(),
                map
            );
        }

        return settings;
    }
}
