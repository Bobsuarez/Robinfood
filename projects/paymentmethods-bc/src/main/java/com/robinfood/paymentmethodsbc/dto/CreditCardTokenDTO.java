package com.robinfood.paymentmethodsbc.dto;

import com.robinfood.paymentmethodsbc.enums.CompanyCreditCard;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CreditCardTokenDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String payerFirstName;
    private String payerLastName;
    private String payerId;
    private int payerIdentificationType;
    private String payerIdentification;
    private String payerEmail;
    private String creditCardNumber;
    private String creditCardExpirationYear;
    private String creditCardExpirationMonth;
    private String creditCardVerificationCode;
    private CompanyCreditCard companyCreditCard;
}
