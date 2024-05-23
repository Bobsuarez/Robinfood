package com.robinfood.localserver.electronicbillbc.mocks.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localserver.commons.dtos.orders.billing.TreasuryPaymentsDTO;
import java.util.List;

public class TreasuryPaymentsDTOMocks {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public TreasuryPaymentsDTO getDefaultTreasuryPaymentsDTOCashData() throws JsonProcessingException {

        return objectMapper.readValue("{\n" +
                "  \"id\": 116,\n" +
                "  \"name\": \"Dinheiro\",\n" +
                "  \"parameters\": [\n" +
                "    {\n" +
                "      \"name\": \"cMP\",\n" +
                "      \"value\": \"01\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"value\": 16.9\n" +
                "}", TreasuryPaymentsDTO.class);
    }
    public TreasuryPaymentsDTO getDefaultTreasuryPaymentsDTOCreditCardData() throws JsonProcessingException {

        return objectMapper.readValue("{\n" +
                "  \"id\": 2,\n" +
                "  \"name\": \"Cartão de Crédito\",\n" +
                "  \"parameters\": [\n" +
                "    {\n" +
                "      \"name\": \"cMP\",\n" +
                "      \"value\": \"03\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"value\": 10.0\n" +
                "}", TreasuryPaymentsDTO.class);
    }

    public TreasuryPaymentsDTO getDefaultTreasuryPaymentsDTOCashDataWithDiscount() throws JsonProcessingException {

        return objectMapper.readValue("{\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Dinheiro\",\n" +
                "      \"parameters\": [\n" +
                "        {\n" +
                "          \"name\": \"cMP\",\n" +
                "          \"value\": \"01\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"value\": 29.85\n" +
                "    }", TreasuryPaymentsDTO.class);
    }

    public List<TreasuryPaymentsDTO> getDefaultTwoOrMoreTreasuryPaymentsDTOWithDeduction() throws JsonProcessingException {

        String json = "[\n" +
                "    {\n" +
                "      \"id\": 148,\n" +
                "      \"name\": \"Dinheiro\",\n" +
                "      \"parameters\": [\n" +
                "        {\n" +
                "          \"name\": \"cMP\",\n" +
                "          \"value\": \"01\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"value\": 15.6\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 150,\n" +
                "      \"name\": \"Cartão de Crédito\",\n" +
                "      \"parameters\": [\n" +
                "        {\n" +
                "          \"name\": \"cMP\",\n" +
                "          \"value\": \"03\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"value\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 151,\n" +
                "      \"name\": \"Cartão de Débito\",\n" +
                "      \"parameters\": [\n" +
                "        {\n" +
                "          \"name\": \"cMP\",\n" +
                "          \"value\": \"04\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"value\": 10.3\n" +
                "    }\n" +
                "  ]";

        return objectMapper.readValue(json, new TypeReference<List<TreasuryPaymentsDTO>>() {});
    }
}
