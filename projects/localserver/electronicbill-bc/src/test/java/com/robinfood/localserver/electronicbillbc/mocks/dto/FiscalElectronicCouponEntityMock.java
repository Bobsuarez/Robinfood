package com.robinfood.localserver.electronicbillbc.mocks.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localserver.commons.entities.electronicbill.FiscalElectronicCouponEntity;

public class FiscalElectronicCouponEntityMock {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public FiscalElectronicCouponEntity getDefaultFiscalElectronicCouponEntity() throws JsonProcessingException {

        String json="{\n" +
                "    \"infCFe\": {\n" +
                "      \"ide\": {\n" +
                "        \"port\": \"8080\",\n" +
                "        \"url\": \"http://localhost\"\n" +
                "      },\n" +
                "      \"emit\": {\n" +
                "        \"stveb\": \"61099008000141\",\n" +
                "        \"CNPJ\": \"61099008000141\",\n" +
                "        \"IE\": \"111111111111\"\n" +
                "      },\n" +
                "      \"det\": [\n" +
                "        {\n" +
                "          \"prod\": {\n" +
                "            \"CFOP\": \"5101\",\n" +
                "            \"cProd\": \"179\",\n" +
                "            \"indRegra\": \"A\",\n" +
                "            \"qCom\": \"1\",\n" +
                "            \"uCom\": \"UN\",\n" +
                "            \"vUnCom\": \"18900.0\",\n" +
                "            \"xProd\": \"Pizza pepperoni\"\n" +
                "          },\n" +
                "          \"imposto\": {\n" +
                "            \"COFINS\": {\n" +
                "              \"COFINSSN\": {\n" +
                "                \"CST\": \"49\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"COFINSSN\": {\n" +
                "              \"COFINSSNPRUEBA\": {\n" +
                "                \"CSTPRUEBA\": \"49PRUEBA\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"nitem\": \"1\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"total\": \"\",\n" +
                "      \"pgto\": {\n" +
                "        \"mp\": [\n" +
                "          {\n" +
                "            \"cPM\": \"01\",\n" +
                "            \"vMP\": \"17900\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"cPM\": \"03\",\n" +
                "            \"vMP\": \"2000\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"infAdic\": {\n" +
                "        \"infCpl\": \"\"\n" +
                "      }\n" +
                "    }\n" +
                "  }";

        return objectMapper.readValue(json,FiscalElectronicCouponEntity.class);
    }
}
