package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.api.paymentmethods.GeneralPaymentMethodDetailsDTO;
import java.util.List;

/**
 * Obtiene objetos de prueba para test unitarios
 */
public class GeneralPaymentMethodDetailsDTOSample {

    public static List<GeneralPaymentMethodDetailsDTO> getGeneralPaymentMethodDetailsDTOList(){
        return List.of(getGeneralPaymentMethodDetailsDTO());
    }

    public static GeneralPaymentMethodDetailsDTO getGeneralPaymentMethodDetailsDTO() {
        return GeneralPaymentMethodDetailsDTO
            .builder()
            .id(1L)
            .name("name")
            .slug("slug")
            .image("image")
            .parameters(
                List.of(GeneralPaymentMethodDetailsDTO.TerminalPaymentMethodSettingDetailDTO
                    .builder()
                    .key("key")
                    .value("value")
                    .type("type")
                    .build())
            )
            .build();
    }
}
