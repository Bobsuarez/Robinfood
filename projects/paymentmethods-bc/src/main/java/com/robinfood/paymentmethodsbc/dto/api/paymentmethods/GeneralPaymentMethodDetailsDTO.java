package com.robinfood.paymentmethodsbc.dto.api.paymentmethods;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class GeneralPaymentMethodDetailsDTO {

    private Long id;
    private String name;
    private String slug;
    private String image;
    private List<TerminalPaymentMethodSettingDetailDTO> parameters;

    @Value
    @Builder
    public static class TerminalPaymentMethodSettingDetailDTO {

        private String key;
        private String value;
        private String type;

    }
}
