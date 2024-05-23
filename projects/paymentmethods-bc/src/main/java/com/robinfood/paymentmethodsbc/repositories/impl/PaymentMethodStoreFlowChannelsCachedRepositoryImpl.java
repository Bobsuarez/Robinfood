package com.robinfood.paymentmethodsbc.repositories.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.constants.RedisConstants;
import com.robinfood.paymentmethodsbc.model.PaymentMethodStoreFlowChannel;
import com.robinfood.paymentmethodsbc.repositories.PaymentMethodStoreFlowChannelsCachedRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentMethodStoreFlowChannelsRepository;
import com.robinfood.paymentmethodsbc.repositories.RedisRepository;
import com.robinfood.paymentmethodsbc.utils.JsonUtils;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class PaymentMethodStoreFlowChannelsCachedRepositoryImpl
    implements PaymentMethodStoreFlowChannelsCachedRepository {

    private final PaymentMethodStoreFlowChannelsRepository paymentMethodStoreFlowChannelsRepository;
    private final RedisRepository redisRepository;

    public PaymentMethodStoreFlowChannelsCachedRepositoryImpl(
        PaymentMethodStoreFlowChannelsRepository paymentMethodStoreFlowChannelsRepository,
        RedisRepository redisRepository
    ) {
        this.paymentMethodStoreFlowChannelsRepository = paymentMethodStoreFlowChannelsRepository;
        this.redisRepository = redisRepository;
    }

    @BasicLog
    @Override
    public List<PaymentMethodStoreFlowChannel> findByStoreIdAndChannelIdAndFlowIdAndStatus(
        final Long storeId,
        final Long channelId,
        final Long flowId,
        final boolean status
    ) {
        final String keyRedis = getKeyFindByStoreIdAndChannelIdAndFlowIdAndStatus(
            storeId,
            channelId,
            flowId,
            status
        );

        List<PaymentMethodStoreFlowChannel> result = getDataInRedis(keyRedis);

        if (Objects.isNull(result) || result.isEmpty()) {
            log.info("Redis data is not available, getting from database");

            result =
                paymentMethodStoreFlowChannelsRepository.findByStoreIdAndChannelIdAndFlowIdAndStatusOrderByPositionAsc(
                    storeId,
                    channelId,
                    flowId,
                    status
                );
            redisRepository.set(
                keyRedis,
                JsonUtils.convertToJson(result)
            );

        }
        return result;
    }

    @Override
    public void clearCache() {
        final int index = RedisConstants.PM_REDIS_PAYMENTMETHOD_STORE_FLOW_CHANNEL_KEY.indexOf('%');
        final String pattern = RedisConstants
            .PM_REDIS_PAYMENTMETHOD_STORE_FLOW_CHANNEL_KEY
            .substring(0, index)
            .concat("*");

        redisRepository
            .getKey(pattern)
            .forEach(redisRepository::deleteKey);
    }

    private List<PaymentMethodStoreFlowChannel> getDataInRedis(
        final String keyRedis
    ) {
        log.info("Querying PaymentMethodStoreFlowChannel in redis. key {}", keyRedis);

        return redisRepository
            .get(keyRedis)
            .map(this::jsonToPaymentMethodStoreFlowChannel)
            .orElse(Collections.emptyList());
    }

    /**
     * Convierte un JSON a una lista de objetos PaymentMethodStoreFlowChannel
     * @param json {@linkplain String} Json a convertir
     * @return {@linkplain List<PaymentMethodStoreFlowChannel>}
     */
    private List<PaymentMethodStoreFlowChannel> jsonToPaymentMethodStoreFlowChannel(
        final String json
    ) {
        try {
            log.info(
                "Converting json to {} object",
                PaymentMethodStoreFlowChannel.class.getSimpleName()
            );
            return List.of(
                JsonUtils.jsonToObject(
                    json,
                    PaymentMethodStoreFlowChannel[].class
                )
            );
        } catch (JsonProcessingException e) {
            log.error("JSON from redis cannot be converted to object", e);
        }
        return Collections.emptyList();
    }

    /**
     * Obtains the respective key of redis according to the applied filters.
     * @param storeId {@linkplain Long}
     * @param channelId {@linkplain Long}
     * @param flowId {@linkplain Long}
     * @param status {@linkplain boolean}
     * @return
     */
    private static String getKeyFindByStoreIdAndChannelIdAndFlowIdAndStatus(
        final Long storeId,
        final Long channelId,
        final Long flowId,
        final boolean status
    ) {
        return String.format(
            RedisConstants.PM_REDIS_PAYMENTMETHOD_STORE_FLOW_CHANNEL_KEY,
            storeId,
            channelId,
            flowId,
            status
        );
    }
}
