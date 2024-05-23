package com.robinfood.paymentmethodsbc.dto.api.creditcards;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "CreditCardDetailDTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardDetailDTO {
    private Long id;

    private Long userId;

    private CreditCardTypeDTO creditCardType;

    private CountryDTO country;

    private String number;

    private String cardHolderName;

    private int userIdentificationType;

    private String userIdentificationNumber;

    private String userEmail;

    private Boolean isUpdated;

    private String userAddress;

    private String userCity;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "CountryDTO")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CountryDTO {
        private Long id;

        private String uuid;

        private String name;

        public CountryDTO(Long id) {
            this.id = id;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "CreditCardTypeDTO")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CreditCardTypeDTO {
        private Long id;

        private String name;

        private String code;

        public CreditCardTypeDTO(Long id) {
            this.id = id;
        }
    }
}
