package com.robinfood.paymentmethodsbc.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.utils.Utilities;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.robinfood.paymentmethodsbc.constants.AppConstants.CREDIT_CARD_LAST_NUMBER_SIZE;

@Slf4j
public class CreateCreditCardRequestDTOSerializer extends StdSerializer<CreateCreditCardRequestDTO> {
    private static final long serialVersionUID = 6401343040267410990L;

    public CreateCreditCardRequestDTOSerializer() {
        this(null);
    }

    public CreateCreditCardRequestDTOSerializer(Class<CreateCreditCardRequestDTO> type) {
        super(type);
    }

    @Override
    public void serialize(
        CreateCreditCardRequestDTO createCreditCardRequestDTO,
        JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider
    ) {
        try {
            jsonGenerator.writeStartObject();
            String numberToShow = Utilities.creditCardNumberIsGreaterThanFour(
                createCreditCardRequestDTO.getNumber(), CREDIT_CARD_LAST_NUMBER_SIZE
            );
            jsonGenerator.writeObjectField("id", createCreditCardRequestDTO.getId());
            jsonGenerator.writeObjectField("userId", createCreditCardRequestDTO.getUserId());
            jsonGenerator.writeObjectField("countryId", createCreditCardRequestDTO.getCountryId());
            jsonGenerator.writeStringField("number", numberToShow);
            jsonGenerator.writeStringField(
                "cardHolderFirstName", createCreditCardRequestDTO.getCardHolderFirstName()
            );
            jsonGenerator.writeStringField(
                "cardHolderLastName", createCreditCardRequestDTO.getCardHolderLastName()
            );
            jsonGenerator.writeObjectField(
                "userIdentificationType", createCreditCardRequestDTO.getUserIdentificationType()
            );
            jsonGenerator.writeStringField(
                "userIdentificationNumber", createCreditCardRequestDTO.getUserIdentificationNumber()
            );
            jsonGenerator.writeStringField("userEmail", createCreditCardRequestDTO.getUserEmail());
            jsonGenerator.writeStringField("userAddress", createCreditCardRequestDTO.getUserAddress());
            jsonGenerator.writeStringField("userCity", createCreditCardRequestDTO.getUserCity());
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
