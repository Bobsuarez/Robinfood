package com.robinfood.paymentmethodsbc.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.utils.Utilities;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.robinfood.paymentmethodsbc.constants.AppConstants.CREDIT_CARD_LAST_NUMBER_SIZE;

@Slf4j
public class CreditCardSerializer extends StdSerializer<CreditCard> {
    private static final long serialVersionUID = -7141374478892312033L;

    public CreditCardSerializer() {
        this(null);
    }

    public CreditCardSerializer(Class<CreditCard> type) {
        super(type);
    }

    @Override
    public void serialize(
        CreditCard creditCard,
        JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider
    ) {
        try {
            jsonGenerator.writeStartObject();
            String numberToShow = Utilities.creditCardNumberIsGreaterThanFour(
                creditCard.getNumber(), CREDIT_CARD_LAST_NUMBER_SIZE
            );
            jsonGenerator.writeObjectField("id", creditCard.getId());
            jsonGenerator.writeObjectField("userId", creditCard.getUserId());
            jsonGenerator.writeObjectField("creditCardType", creditCard.getCreditCardType());
            jsonGenerator.writeObjectField("country", creditCard.getCountry());
            jsonGenerator.writeStringField("number", numberToShow);
            jsonGenerator.writeStringField("cardHolderName", creditCard.getCardHolderName());
            jsonGenerator.writeObjectField(
                "userIdentificationType", creditCard.getUserIdentificationType()
            );
            jsonGenerator.writeStringField(
                "userIdentificationNumber", creditCard.getUserIdentificationNumber()
            );
            jsonGenerator.writeStringField("userEmail", creditCard.getUserEmail());
            jsonGenerator.writeStringField("userAddress", creditCard.getUserAddress());
            jsonGenerator.writeStringField("userCity", creditCard.getUserCity());
            jsonGenerator.writeStringField("expirationYear", "****");
            jsonGenerator.writeStringField("expirationMonth", "**");
            jsonGenerator.writeStringField("cardVerificationCode", "***");
            jsonGenerator.writeEndObject();
        } catch(IOException ioe) {
            log.error(ioe.getMessage(), ioe);
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
