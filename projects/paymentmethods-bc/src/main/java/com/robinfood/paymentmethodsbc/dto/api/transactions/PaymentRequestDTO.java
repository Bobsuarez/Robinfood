package com.robinfood.paymentmethodsbc.dto.api.transactions;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "PaymentRequestDTO")
public class PaymentRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long transactionId;

    private String transactionUuid;

    @NotNull
    private Long countryId;

    @Valid
    @NotNull
    private EntityDTO entity;

    @Valid
    @NotNull
    private PaymentMethodDTO paymentMethod;

    @Valid
    @NotNull
    private UserDTO user;

    @Valid
    @NotNull
    private PaymentDTO payment;

    @Valid
    @NotNull
    private OriginDTO origin;


    
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Setter
    @Getter
    public static class OriginDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        @NotNull
        private Long channelId;

        @NotNull
        private Long storeId;

        @NotNull
        private String ipAddress;

        private String userAgent;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Setter
    @Getter    
    public static class PaymentDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        @Min(value = 1)
        private BigDecimal subtotal;

        @Min(value = 0)
        private BigDecimal tax;

        @Min(value = 1)
        private BigDecimal total;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Setter
    @Getter
    public static class EntityDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        @NotNull
        private Long id;

        @NotNull
        private Long source;

        @NotEmpty
        private String reference;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Setter
    @Getter
    public static class UserDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        @NotNull
        private Long userId;

        @NotBlank
        private String firstName;

        @NotBlank
        private String lastName;

        @Default
        private String phoneCode = "";

        @NotNull
        private String phoneNumber;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PaymentMethodDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        @NotNull
        private Long id;

        private Long creditCardId;

        @Min(1)
        private Long installmentsNumber;

        private String terminalUuid;


        private List<PaymentReferencePaymentMethod> referencePaymentMethods;

        @Setter
        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class PaymentReferencePaymentMethod{

            @NotNull
            private Integer id;

        }
    }
}
