package com.robinfood.paymentmethodsbc.dto.api.paymentmethods;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "PaymentMethodDetailsDTO")
public class PaymentMethodDetailsDTO {
    private Long id;

    private Long originId;

    private String name;

    private String slugName;

    private String image;
}
