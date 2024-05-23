package com.robinfood.paymentmethodsbc.mappers;


import com.robinfood.paymentmethodsbc.dto.api.paymentmethods.GeneralPaymentMethodDetailsDTO;
import com.robinfood.paymentmethodsbc.model.GeneralPaymentMethod;
import com.robinfood.paymentmethodsbc.model.TerminalPaymentMethodSetting;
import java.util.List;

public final class PaymentMethodMapper {

    private PaymentMethodMapper() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static GeneralPaymentMethodDetailsDTO toGeneralPaymentMethodDetailsDTO(
        final GeneralPaymentMethod generalPaymentMethod,
        final List<GeneralPaymentMethodDetailsDTO.TerminalPaymentMethodSettingDetailDTO> settings
    ){
        return GeneralPaymentMethodDetailsDTO
            .builder()
            .id(generalPaymentMethod.getId())
            .name(generalPaymentMethod.getName())
            .slug(generalPaymentMethod.getSlugName())
            .image(generalPaymentMethod.getImageUrl())
            .parameters(settings)
            .build();
    }

    public static GeneralPaymentMethodDetailsDTO.TerminalPaymentMethodSettingDetailDTO
    toTerminalPaymentMethodSettingDetailDTO(
        final TerminalPaymentMethodSetting terminalPaymentMethodSetting
    ) {
        return GeneralPaymentMethodDetailsDTO.TerminalPaymentMethodSettingDetailDTO
            .builder()
            .key(terminalPaymentMethodSetting.getTerminalParameter().getKey())
            .value(terminalPaymentMethodSetting.getValue())
            .type(terminalPaymentMethodSetting.getTerminalParameter().getType())
            .build();
    }
}
