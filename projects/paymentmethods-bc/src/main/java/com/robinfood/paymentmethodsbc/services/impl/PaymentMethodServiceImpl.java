package com.robinfood.paymentmethodsbc.services.impl;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.dto.api.paymentmethods.PaymentMethodDetailsDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.model.PaymentMethod;
import com.robinfood.paymentmethodsbc.model.PaymentMethodChannel;
import com.robinfood.paymentmethodsbc.model.PaymentMethodChannelStore;
import com.robinfood.paymentmethodsbc.repositories.PaymentMethodChannelStoresRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentMethodChannelsRepository;
import com.robinfood.paymentmethodsbc.services.PaymentMethodService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    private PaymentMethodChannelStoresRepository pmChannelStoresRepository;
    private PaymentMethodChannelsRepository pmChannelsRepository;

    public PaymentMethodServiceImpl(
        PaymentMethodChannelStoresRepository pmChannelStoresRepository,
        PaymentMethodChannelsRepository pmChannelsRepository
    ) {
        this.pmChannelStoresRepository = pmChannelStoresRepository;
        this.pmChannelsRepository = pmChannelsRepository;
    }

    @BasicLog
    @Override
    public List<PaymentMethodDetailsDTO> getPaymentMethodsByStoreAndChannelAndOrigin(
        Long storeId,
        Long channelId,
        Long originId
    )
        throws BaseException {
        return pmChannelStoresRepository
            .findByStoreIdAndChannelIdAndOriginIdAndStatusAndDeletedAt(
                storeId,
                channelId,
                originId,
                PaymentMethodChannelStore.STATUS_ENABLED,
                null
            )
            .stream()
            .map(this::getPaymentMethodDetailsDTO)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    private PaymentMethodDetailsDTO getPaymentMethodDetailsDTO(
        PaymentMethodChannelStore item
    ) {
        PaymentMethod paymentMethod = item.getPaymentMethod();
        PaymentMethodChannel pmChannel = pmChannelsRepository
            .findByPaymentMethodIdAndChannelIdAndOriginIdAndStatusAndDeletedAt(
                paymentMethod.getId(),
                item.getChannelId(),
                item.getOriginId(),
                PaymentMethodChannel.STATUS_ENABLED,
                null
            )
            .orElse(null);

        if (pmChannel == null) {
            return null;
        }

        String image = paymentMethod.getImageUrl();
        if (
            pmChannel.getImageUrl() != null &&
            !pmChannel.getImageUrl().isBlank()
        ) {
            image = pmChannel.getImageUrl();
        }

        return PaymentMethodDetailsDTO
            .builder()
            .id(paymentMethod.getId())
            .image(image)
            .name(paymentMethod.getName())
            .slugName(paymentMethod.getSlugName())
            .originId(pmChannel.getOriginId())
            .build();
    }
}
