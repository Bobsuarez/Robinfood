package com.robinfood.paymentmethodsbc.utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.regex.Pattern;
import com.robinfood.paymentmethodsbc.components.BaseApplicationContextAware;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.security.CipherUtility;

public final class CreditCardUtils {
    
    private static final Pattern VALID_CVV = Pattern.compile(
        "^[\\d]{3,4}$"
    );
    private static final Pattern VALID_YEAR = Pattern.compile("^([\\d]{4})$");
    private static final Pattern VALID_MONTH = Pattern.compile(
        "^([0]?[1-9]|[1][0-2])$"
    );


    private CreditCardUtils(){

    }


    public static void validCvv(String verificationCode) throws PaymentStepException {

        if (!VALID_CVV.matcher(verificationCode).matches()) {
            throw new PaymentStepException(
                StepType.CREATE_CREDIT_CARD,
                "Verification code must be a 3 or 4 digit number"
            );
        }
    }


    public static void validExpirationDate(String month,String year) throws PaymentStepException{

        if (
            !VALID_MONTH
                .matcher(month)
                .matches()
        ) {
            throw new PaymentStepException(
                StepType.CREATE_CREDIT_CARD,
                String.format(
                    "The expiration month must be a number between 1 and 12 , actual %s",
                    month
                )
            );
        }

        if (
            !VALID_YEAR
                .matcher(year)
                .matches()
        ) {
            throw new PaymentStepException(
                StepType.CREATE_CREDIT_CARD,
                "The expiration year must be a number a value of 4 numeric characters"
            );
        }

        int diffMonths = YearMonth
            .from(
                LocalDate.of(
                    Integer.valueOf(year),
                    Integer.valueOf(month),
                    1
                )
            )
            .compareTo(YearMonth.now());

        if (diffMonths < 0) {
            throw new PaymentStepException(
                StepType.CREATE_CREDIT_CARD,
                "The credit card is expired"
            );
        }
    }


    public static String decryptTextWithPrivateKey(String content){

        final CipherUtility cipherUtility = BaseApplicationContextAware
            .getBean(CipherUtility.class);

        return cipherUtility.decryptTextWithPrivateKey(content);
    }


}
