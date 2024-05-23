package com.robinfood.localserver.electronicbillbc.mocks.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localserver.commons.dtos.electronicbill.DetDTO;
import java.util.List;

public class DetDTOMock {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public DetDTO getDefaultDetDTO() throws JsonProcessingException {

        String json = "{\n" +
                "                    \"prod\": {\n" +
                "                        \"cProd\": \"179\",\n" +
                "                        \"NCM\": \"890890890\",\n" +
                "                        \"indRegra\": \"A\",\n" +
                "                        \"qCom\": \"1\",\n" +
                "                        \"uCom\": \"UN\",\n" +
                "                        \"CFOP\": \"5101\",\n" +
                "                        \"vUnCom\": \"11500.0\",\n" +
                "                        \"xProd\": \"Pizza pepperoni\"\n" +
                "                    },\n" +
                "                    \"imposto\": {\n" +
                "                        \"COFINS\": {\n" +
                "                            \"COFINSSN\": {\n" +
                "                                \"CST\": \"49\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"nItem\": \"1\"\n" +
                "                }";

        return objectMapper.readValue(json, DetDTO.class);
    }

    public List<DetDTO> getDefaultFinalDetDTOList() throws JsonProcessingException {

        String json = "[\n" +
                "  {\n" +
                "    \"prod\": {\n" +
                "      \"CFOP\": \"5101\",\n" +
                "      \"NCM\": \"890890890\",\n" +
                "      \"cProd\": \"519\",\n" +
                "      \"indRegra\": \"A\",\n" +
                "      \"qCom\": \"1\",\n" +
                "      \"uCom\": \"UN\",\n" +
                "      \"vUnCom\": \"20.90\",\n" +
                "      \"xProd\": \"Crie sua salada\"\n" +
                "    },\n" +
                "    \"imposto\": {\n" +
                "      \"COFINS\": {\n" +
                "        \"COFINSAliq\": {\n" +
                "          \"CST\": \"01\",\n" +
                "          \"vBC\": \"20.90\",\n" +
                "          \"pCOFINS\": \"0.0760\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"ICMS\": {\n" +
                "        \"ICMS00\": {\n" +
                "          \"Orig\": \"0\",\n" +
                "          \"CST\": \"0\",\n" +
                "          \"pICMS\": \"8.40\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"PIS\": {\n" +
                "        \"PISAliq\": {\n" +
                "          \"CST\": \"01\",\n" +
                "          \"vBC\": \"20.90\",\n" +
                "          \"pPIS\": \"0.0165\"\n" +
                "        },\n" +
                "        \"PISNT\": {\n" +
                "          \"CST\": \"01\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"nItem\": \"1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"prod\": {\n" +
                "      \"CFOP\": \"5405\",\n" +
                "      \"NCM\": \"890890890\",\n" +
                "      \"cProd\": \"521\",\n" +
                "      \"indRegra\": \"A\",\n" +
                "      \"qCom\": \"1\",\n" +
                "      \"uCom\": \"UN\",\n" +
                "      \"vUnCom\": \"2.50\",\n" +
                "      \"xProd\": \"Coca Cola Regular 350ml\"\n" +
                "    },\n" +
                "    \"imposto\": {\n" +
                "      \"COFINS\": {\n" +
                "        \"COFINSAliq\": {\n" +
                "          \"CST\": \"06\",\n" +
                "          \"vBC\": \"0.00\",\n" +
                "          \"pCOFINS\": \"0.0000\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"ICMS\": {\n" +
                "        \"ICMS00\": {\n" +
                "          \"Orig\": \"0\",\n" +
                "          \"CST\": \"60\",\n" +
                "          \"pICMS\": \"0.00\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"PIS\": {\n" +
                "        \"PISAliq\": {\n" +
                "          \"CST\": \"06\",\n" +
                "          \"vBC\": \"0.00\",\n" +
                "          \"pPIS\": \"0.0000\"\n" +
                "        },\n" +
                "        \"PISNT\": {\n" +
                "          \"CST\": \"06\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"nItem\": \"2\"\n" +
                "  }\n" +
                "]";

        return objectMapper.readValue(json, new TypeReference<List<DetDTO>>() {});
    }


    public List<DetDTO> getDefaultFinalDetDTOListWithDiscounts() throws JsonProcessingException {

        String json = "[\n" +
                "      {\n" +
                "        \"prod\": {\n" +
                "          \"cProd\": \"521\",\n" +
                "          \"NCM\": \"890890890\",\n" +
                "          \"indRegra\": \"A\",\n" +
                "          \"qCom\": \"2\",\n" +
                "          \"uCom\": \"UN\",\n" +
                "          \"vDesc\": \"9.95\",\n" +
                "          \"CFOP\": \"5101\",\n" +
                "          \"vUnCom\": \"19.90\",\n" +
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
                "    ]";

        return objectMapper.readValue(json, new TypeReference<List<DetDTO>>() {});
    }

    public List<DetDTO> getDefaultFinalDetDTOListResponseWithDiscounts() throws JsonProcessingException {

        String json = "[\n" +
                "  {\n" +
                "    \"prod\": {\n" +
                "      \"CFOP\": \"5101\",\n" +
                "      \"NCM\": \"890890890\",\n" +
                "      \"cProd\": \"519\",\n" +
                "      \"indRegra\": \"A\",\n" +
                "      \"qCom\": \"1\",\n" +
                "      \"uCom\": \"UN\",\n" +
                "      \"vDesc\": \"1.00\",\n" +
                "      \"vUnCom\": \"20.90\",\n" +
                "      \"xProd\": \"Crie sua salada\"\n" +
                "    },\n" +
                "    \"imposto\": {\n" +
                "      \"COFINS\": {\n" +
                "        \"COFINSAliq\": {\n" +
                "          \"CST\": \"01\",\n" +
                "          \"vBC\": \"20.90\",\n" +
                "          \"pCOFINS\": \"0.0760\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"ICMS\": {\n" +
                "        \"ICMS00\": {\n" +
                "          \"Orig\": \"0\",\n" +
                "          \"CST\": \"0\",\n" +
                "          \"pICMS\": \"8.40\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"PIS\": {\n" +
                "        \"PISAliq\": {\n" +
                "          \"CST\": \"01\",\n" +
                "          \"vBC\": \"20.90\",\n" +
                "          \"pPIS\": \"0.0165\"\n" +
                "        },\n" +
                "        \"PISNT\": {\n" +
                "          \"CST\": \"01\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"nItem\": \"1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"prod\": {\n" +
                "      \"CFOP\": \"5405\",\n" +
                "      \"NCM\": \"890890890\",\n" +
                "      \"cProd\": \"521\",\n" +
                "      \"indRegra\": \"A\",\n" +
                "      \"qCom\": \"1\",\n" +
                "      \"uCom\": \"UN\",\n" +
                "      \"vUnCom\": \"2.50\",\n" +
                "      \"xProd\": \"Coca Cola Regular 350ml\"\n" +
                "    },\n" +
                "    \"imposto\": {\n" +
                "      \"COFINS\": {\n" +
                "        \"COFINSAliq\": {\n" +
                "          \"CST\": \"06\",\n" +
                "          \"vBC\": \"0.00\",\n" +
                "          \"pCOFINS\": \"0.0000\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"ICMS\": {\n" +
                "        \"ICMS00\": {\n" +
                "          \"Orig\": \"0\",\n" +
                "          \"CST\": \"60\",\n" +
                "          \"pICMS\": \"0.00\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"PIS\": {\n" +
                "        \"PISAliq\": {\n" +
                "          \"CST\": \"06\",\n" +
                "          \"vBC\": \"0.00\",\n" +
                "          \"pPIS\": \"0.0000\"\n" +
                "        },\n" +
                "        \"PISNT\": {\n" +
                "          \"CST\": \"06\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"nItem\": \"2\"\n" +
                "  }\n" +
                "]";

        return objectMapper.readValue(json, new TypeReference<List<DetDTO>>() {});

    }

    public List<DetDTO> getDefaultFinalDetDTOListResponseWithDiscountsRounding() throws JsonProcessingException {

        String json = "[\n" +
                "      {\n" +
                "        \"prod\": {\n" +
                "          \"cProd\": \"1376\",\n" +
                "          \"NCM\": \"890890890\",\n" +
                "          \"indRegra\": \"A\",\n" +
                "          \"qCom\": \"1\",\n" +
                "          \"vDesc\": \"2.00\",\n" +
                "          \"uCom\": \"UN\",\n" +
                "          \"CFOP\": \"5101\",\n" +
                "          \"vUnCom\": \"20.90\",\n" +
                "          \"xProd\": \"Feij?\"\n" +
                "        },\n" +
                "        \"imposto\": {\n" +
                "          \"COFINS\": {\n" +
                "            \"COFINSAliq\": {\n" +
                "              \"CST\": \"01\",\n" +
                "              \"vBC\": \"18.91\",\n" +
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
                "              \"vBC\": \"18.91\",\n" +
                "              \"pPIS\": \"0.0165\"\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"nItem\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"prod\": {\n" +
                "          \"NCM\": \"890890890\",\n" +
                "          \"cProd\": \"1376\",\n" +
                "          \"indRegra\": \"A\",\n" +
                "          \"qCom\": \"1\",\n" +
                "          \"vDesc\": \"1.99\",\n" +
                "          \"uCom\": \"UN\",\n" +
                "          \"CFOP\": \"5101\",\n" +
                "          \"vUnCom\": \"20.90\",\n" +
                "          \"xProd\": \"Muy Bai?o\"\n" +
                "        },\n" +
                "        \"imposto\": {\n" +
                "          \"COFINS\": {\n" +
                "            \"COFINSAliq\": {\n" +
                "              \"CST\": \"01\",\n" +
                "              \"vBC\": \"18.91\",\n" +
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
                "              \"vBC\": \"18.91\",\n" +
                "              \"pPIS\": \"0.0165\"\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"nItem\": \"2\"\n" +
                "      }\n" +
                "    ]";

        return objectMapper.readValue(json, new TypeReference<List<DetDTO>>() {});

    }
}
