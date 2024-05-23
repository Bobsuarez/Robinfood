package com.robinfood.paymentmethodsbc.enums;

import java.util.Optional;

import lombok.Getter;

/**
 * Enum con el listado de codigos de respuesta y respectivo mensaje
 */
@Getter
public enum CompanyCreditCard {

    VISA("VISA", "^4[0-9]{12}(?:[0-9]{3})?$"),
    MASTER_CARD("MASTERCARD", "^5[1-5][0-9]{14}$"),
    AMERICAN_EXPRESS("AMEX", "^3[47][0-9]{13}$"),
    DISCOVER("DISCOVER", "^6(?:011|5[0-9]{2})[0-9]{12}$");

    private String name;
    private String regex;

    CompanyCreditCard(String name, String regex) {
        this.name = name;
        this.regex = regex;
    }

    /**
     * Obtiene el enumerado correspondiente de acuerdo al número de tarjeta
     * @param numberCreditCard
     * @return
     */
    public static CompanyCreditCard getCreditCardCompanyForNumber(String numberCreditCard){

        numberCreditCard  = Optional.ofNullable(numberCreditCard).orElse("");

        for (CompanyCreditCard companyCreditCard : CompanyCreditCard.values()) {
            if (numberCreditCard.matches(companyCreditCard.getRegex())) {
                return companyCreditCard;
            }
        }
        return null;
    }
}
