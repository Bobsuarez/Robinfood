package com.robinfood.paymentmethodsbc.dto.api.creditcards;

import com.robinfood.paymentmethodsbc.utils.Utilities;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.robinfood.paymentmethodsbc.constants.AppConstants.CREDIT_CARD_LAST_NUMBER_SIZE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "CreateCreditCardRequestDTO")
public class CreateCreditCardRequestDTO implements Serializable {
    private static final long serialVersionUID = -2481758079096644944L;

    @Schema(hidden = true)
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long countryId;

    @NotEmpty
    private String number;

    @NotEmpty
    private String cardHolderFirstName;

    @NotEmpty
    private String cardHolderLastName;

    private int userIdentificationType;

    private String userIdentificationNumber;

    private String userEmail;

    private int isUpdated;

    @NotEmpty
    private String userAddress;

    @NotEmpty
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
            numberToShow = Utilities.maskCreditCardNumber(number, CREDIT_CARD_LAST_NUMBER_SIZE);
        }

        return new StringBuilder()
            .append(getClass().getSimpleName())
            .append("(id=").append(id)
            .append(", userId=").append(userId)
            .append(", countryId=").append(countryId)
            .append(", number=").append(numberToShow)
            .append(", cardHolderFirstName=").append(cardHolderFirstName)
            .append(", cardHolderLastName=").append(cardHolderLastName)
            .append(", userIdentificationNumber=").append(userIdentificationNumber)
            .append(", userAddress=").append(userAddress)
            .append(", userCity=").append(userCity)
            .append(", expirationYear=****")
            .append(", expirationMonth=**)")
            .toString();
    }
}
