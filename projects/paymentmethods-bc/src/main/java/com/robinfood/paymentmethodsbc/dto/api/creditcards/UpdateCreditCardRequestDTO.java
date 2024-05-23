package com.robinfood.paymentmethodsbc.dto.api.creditcards;

import com.robinfood.paymentmethodsbc.utils.Utilities;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "UpdateCreditCardRequestDTO")
public class UpdateCreditCardRequestDTO {
    @Schema(hidden = true)
    private Long id;

    private Long userId;

    @NotNull
    private Long countryId;

    @NotEmpty
    private String number;

    @NotEmpty
    private String cardHolderFirstName;

    @NotEmpty
    private String cardHolderLastName;

    @NotNull
    private Integer userIdentificationType;

    @NotEmpty
    private String userIdentificationNumber;

    @NotEmpty
    private String userEmail;

    private int isUpdated;

    private String userAddress;

    private String userCity;

    @NotEmpty
    private String expirationYear;

    @NotEmpty
    private String expirationMonth;

    @NotEmpty
    private String cardVerificationCode;

    @Schema(hidden = true)
    private boolean decrypted;

    @Override
    public String toString() {
        String numberToShow = number;
        if (decrypted) {
            numberToShow = Utilities.maskCreditCardNumber(number, 4);
        }

        return new StringBuilder()
            .append(getClass().getSimpleName())
            .append("(")
            .append("id=")
            .append(id)
            .append(", userId=")
            .append(userId)
            .append(", countryId=")
            .append(countryId)
            .append(", number=")
            .append(numberToShow)
            .append(", cardHolderFirstName=")
            .append(cardHolderFirstName)
            .append(", cardHolderLastName=")
            .append(cardHolderLastName)
            .append(", userIdentificationNumber=")
            .append(userIdentificationNumber)
            .append(", userAddress=")
            .append(userAddress)
            .append(", userCity=")
            .append(userCity)
            .append(", expirationYear=****")
            .append(", expirationMonth=**")
            .append(")")
            .toString();
    }
}
