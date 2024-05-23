package com.robinfood.localserver.electronicbillbc.mocks.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.EntityTaxDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.EntityTreasuryDepartmentDTO;

public class TreasuryEntitiesDTOMock {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public EntityTreasuryDepartmentDTO getDefaultEntityDTOIdeData() throws JsonProcessingException {

    return  objectMapper.readValue("    {\n" +
            "      \"name\": \"ide\",\n" +
            "      \"parameters\": [\n" +
            "        {\n" +
            "          \"name\": \"xNome\",\n" +
            "          \"value\": \"xNome\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"numeroCaixa\",\n" +
            "          \"value\": \"002\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"signAC\",\n" +
            "          \"value\": \"TEST\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"CNPJ\",\n" +
            "          \"value\": \"35880842000160\"\n" +
            "        }\n" +
            "      ]\n"+
            "    }", EntityTreasuryDepartmentDTO.class);
    }
    public EntityTreasuryDepartmentDTO getDefaultEntityDTOEmitData() throws JsonProcessingException {

        return  objectMapper.readValue("{\n" +
                "      \"name\": \"emit\",\n" +
                "      \"parameters\": [\n" +
                "        {\n" +
                "          \"name\": \"CNPJ\",\n" +
                "          \"value\": \"35880842001050\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"IE\",\n" +
                "          \"value\": \"636491161112\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"IM\",\n" +
                "          \"value\": \"123552\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"cRegTribISSQN\",\n" +
                "          \"value\": \"3\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"indRatISSQN\",\n" +
                "          \"value\": \"N\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },", EntityTreasuryDepartmentDTO.class);
    }

    public EntityTreasuryDepartmentDTO getDefaultEntityDTODestData() throws JsonProcessingException {

        return  objectMapper.readValue("    {\n" +
                "      \"name\": \"destinatario\",\n" +
                "      \"parameters\": [\n" +
                "        {\n" +
                "          \"name\": \"CPF\",\n" +
                "          \"value\": \"CPF\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },", EntityTreasuryDepartmentDTO.class);
    }

    public EntityTreasuryDepartmentDTO getDefaultEntityDTOIdeDataWhitDiscount() throws JsonProcessingException {

        return  objectMapper.readValue(" {\n" +
                "      \"name\": \"ide\",\n" +
                "      \"parameters\": [\n" +
                "        {\n" +
                "          \"name\": \"xNome\",\n" +
                "          \"value\": \"xNome\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"numeroCaixa\",\n" +
                "          \"value\": \"003\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"signAC\",\n" +
                "          \"value\": \"SGR-SAT SISTEMA DE GESTAO E RETAGUARDA DO SAT\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"CNPJ\",\n" +
                "          \"value\": \"16716114000172\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }", EntityTreasuryDepartmentDTO.class);
    }
    public EntityTreasuryDepartmentDTO getDefaultEntityDTOEmitDataWhitDiscount() throws JsonProcessingException {

        return  objectMapper.readValue("{\n" +
                "      \"name\": \"emit\",\n" +
                "      \"parameters\": [\n" +
                "        {\n" +
                "          \"name\": \"CNPJ\",\n" +
                "          \"value\": \"61099008000141\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"IE\",\n" +
                "          \"value\": \"111111111111\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"IM\",\n" +
                "          \"value\": \"123123\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"cRegTribISSQN\",\n" +
                "          \"value\": \"3\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"indRatISSQN\",\n" +
                "          \"value\": \"N\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },", EntityTreasuryDepartmentDTO.class);
    }

    public EntityTreasuryDepartmentDTO getDefaultEntityDTOConnectionDataWhitDiscount() throws JsonProcessingException {

        return  objectMapper.readValue("{\n" +
                "      \"name\": \"connection\",\n" +
                "      \"parameters\": [\n" +
                "        {\n" +
                "          \"name\": \"CPF\",\n" +
                "          \"value\": \"CPF\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },", EntityTreasuryDepartmentDTO.class);
    }
}
