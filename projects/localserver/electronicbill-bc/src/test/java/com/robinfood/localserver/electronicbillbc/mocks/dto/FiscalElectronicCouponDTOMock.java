package com.robinfood.localserver.electronicbillbc.mocks.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localserver.commons.dtos.electronicbill.FiscalElectronicCouponDTO;

public class FiscalElectronicCouponDTOMock {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public FiscalElectronicCouponDTO getDefaultFiscalElectronicCouponDTO() throws JsonProcessingException {

        String json="{\n" +
                "  \"infCFe\": {\n" +
                "    \"ide\": {\n" +
                "      \"CNPJ\": \"35880842000160\",\n" +
                "      \"numeroCaixa\": \"002\",\n" +
                "      \"signAC\": \"GjnnWlqNi94RkP4oDpsY+yIg1xWhAAocOrCJ4wOdLB5E7NV/eJ+QgbI80QiOcgeCRhsjF4I2C8DPjeOd0fcIENHe8HjJZ3rB9gaH8vb4D1DgMbXeg4VFC1pJJpTm56kXH6bZogU55o1oyKNUJfIDSp1P3Ls1EExvkATNSsW88ecg+3krdnKmNw1FKDEHTQjqOqDEdRbToGP/2LQkdyOKdWFZhLSGYID9h+mGGazytYld0o+F6Oa/FFUo41DtKzq8HAQuiTgUPLbo09ngM5ggRIX8G4CD6NO+nExPs/NAjbS7LPpOeowO0ns5yngsdJDdiIXfE5421qgA4yhL3MPPpA==\",\n" +
                "      \"xNome\": \"xNome\"\n" +
                "    },\n" +
                "    \"emit\": {\n" +
                "      \"CNPJ\": \"35880842001050\",\n" +
                "      \"IE\": \"636491161112\",\n" +
                "      \"IM\": \"123552\",\n" +
                "      \"cRegTribISSQN\": \"3\",\n" +
                "      \"indRatISSQN\": \"N\"\n" +
                "    },\n" +
                "    \"det\": [\n" +
                "      {\n" +
                "        \"prod\": {\n" +
                "          \"CFOP\": \"5101\",\n" +
                "          \"NCM\": \"890890890\",\n" +
                "          \"cProd\": \"519\",\n" +
                "          \"indRegra\": \"A\",\n" +
                "          \"qCom\": \"1\",\n" +
                "          \"uCom\": \"UN\",\n" +
                "          \"vUnCom\": \"20.90\",\n" +
                "          \"xProd\": \"Crie sua salada\"\n" +
                "        },\n" +
                "        \"imposto\": {\n" +
                "          \"COFINS\": {\n" +
                "            \"COFINSAliq\": {\n" +
                "              \"CST\": \"01\",\n" +
                "              \"vBC\": \"20.90\",\n" +
                "              \"pCOFINS\": \"0.0760\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"ICMS\": {\n" +
                "            \"ICMS00\": {\n" +
                "              \"Orig\": \"0\",\n" +
                "              \"CST\": \"0\",\n" +
                "              \"pICMS\": \"8.40\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"PIS\": {\n" +
                "            \"PISAliq\": {\n" +
                "              \"CST\": \"01\",\n" +
                "              \"vBC\": \"20.90\",\n" +
                "              \"pPIS\": \"0.0165\"\n" +
                "            },\n" +
                "            \"PISNT\": {\n" +
                "              \"CST\": \"01\"\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"nItem\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"prod\": {\n" +
                "          \"CFOP\": \"5405\",\n" +
                "          \"NCM\": \"890890890\",\n" +
                "          \"cProd\": \"521\",\n" +
                "          \"indRegra\": \"A\",\n" +
                "          \"qCom\": \"1\",\n" +
                "          \"uCom\": \"UN\",\n" +
                "          \"vUnCom\": \"2.50\",\n" +
                "          \"xProd\": \"Coca Cola Regular 350ml\"\n" +
                "        },\n" +
                "        \"imposto\": {\n" +
                "          \"COFINS\": {\n" +
                "            \"COFINSAliq\": {\n" +
                "              \"CST\": \"06\",\n" +
                "              \"vBC\": \"0.00\",\n" +
                "              \"pCOFINS\": \"0.0000\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"ICMS\": {\n" +
                "            \"ICMS00\": {\n" +
                "              \"Orig\": \"0\",\n" +
                "              \"CST\": \"60\",\n" +
                "              \"pICMS\": \"0.00\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"PIS\": {\n" +
                "            \"PISAliq\": {\n" +
                "              \"CST\": \"06\",\n" +
                "              \"vBC\": \"0.00\",\n" +
                "              \"pPIS\": \"0.0000\"\n" +
                "            },\n" +
                "            \"PISNT\": {\n" +
                "              \"CST\": \"06\"\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"nItem\": \"2\"\n" +
                "      }\n" +
                "    ],\n" +
                " \"total\":null," +
                "    \"pgto\": {\n" +
                "      \"mp\": [\n" +
                "        {\n" +
                "          \"cPM\": \"01\",\n" +
                "          \"vMP\": \"16.90\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"cPM\": \"03\",\n" +
                "          \"vMP\": \"10.00\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"infAdic\": {\n" +
                "      \"infCpl\": \"\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        return objectMapper.readValue(json,FiscalElectronicCouponDTO.class);
    }

    public FiscalElectronicCouponDTO getDefaultFiscalElectronicCouponDTOWithDiscount() throws JsonProcessingException {

        String json="{\n" +
                "  \"infCFe\": {\n" +
                "    \"ide\": {\n" +
                "      \"CNPJ\": \"16716114000172\",\n" +
                "      \"numeroCaixa\": \"003\",\n" +
                "      \"signAC\": \"SGR-SAT SISTEMA DE GESTAO E RETAGUARDA DO SAT\",\n" +
                "      \"xNome\": \"xNome\"\n" +
                "    },\n" +
                "    \"emit\": {\n" +
                "      \"CNPJ\": \"61099008000141\",\n" +
                "      \"IE\": \"111111111111\",\n" +
                "      \"IM\": \"123123\",\n" +
                "      \"cRegTribISSQN\": \"3\",\n" +
                "      \"indRatISSQN\": \"N\"\n" +
                "    },\n" +
                "    \"det\": [\n" +
                "      {\n" +
                "        \"prod\": {\n" +
                "          \"cProd\": \"521\",\n" +
                "          \"NCM\": \"890890890\",\n" +
                "          \"indRegra\": \"A\",\n" +
                "          \"qCom\": \"2\",\n" +
                "          \"uCom\": \"UN\",\n" +
                "          \"CFOP\": \"5101\",\n" +
                "          \"vUnCom\": \"19.90\",\n" +
                "          \"vDesc\": \"9.95\",\n" +
                "          \"xProd\": \"Baião\"\n" +
                "        },\n" +
                "        \"imposto\": {\n" +
                "          \"COFINS\": {\n" +
                "            \"COFINSAliq\": {\n" +
                "              \"CST\": \"01\",\n" +
                "              \"vBC\": \"14.93\",\n" +
                "              \"pCOFINS\": \"0.0760\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"ICMS\": {\n" +
                "            \"ICMS00\": {\n" +
                "              \"Orig\": \"0\",\n" +
                "              \"CST\": \"00\",\n" +
                "              \"pICMS\": \"3.20\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"PIS\": {\n" +
                "            \"PISAliq\": {\n" +
                "              \"CST\": \"01\",\n" +
                "              \"vBC\": \"14.93\",\n" +
                "              \"pPIS\": \"0.0165\"\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"nItem\": \"1\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"total\": null,\n" +
                "    \"pgto\": {\n" +
                "      \"mp\": [\n" +
                "        {\n" +
                "          \"cMP\": \"01\",\n" +
                "          \"vMP\": \"29.85\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"infAdic\": {\n" +
                "      \"infCpl\": \"\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        return objectMapper.readValue(json,FiscalElectronicCouponDTO.class);
    }
}
