package com.robinfood.localserver.electronicbillbc.mocks.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localserver.commons.dtos.orders.billing.OrderBillingDTO;

public class OrderBillingDTOMock {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public OrderBillingDTO getDefaultOrderBillingDTO() throws JsonProcessingException {

        String json = "{\n" +
                "  \"brandId\": 8,\n" +
                "  \"brandName\": \"Pecado Natural\",\n" +
                "  \"co2Total\": 0,\n" +
                "  \"currency\": \"BRL\",\n" +
                "  \"coupons\": [\n" +
                "    \n" +
                "  ],\n" +
                "  \"id\": 3360031,\n" +
                "  \"deduction\": 0,\n" +
                "  \"discount\": 0,\n" +
                "  \"discounts\": [\n" +
                "    \n" +
                "  ],\n" +
                "  \"deliveryTypeId\": 1,\n" +
                "  \"flowId\": 5,\n" +
                "  \"invoice\": \"165\",\n" +
                "  \"notes\": \"POSV2\",\n" +
                "  \"originId\": 1,\n" +
                "  \"originName\": \"POS\",\n" +
                "  \"orderNumber\": \"7\",\n" +
                "  \"orderIsIntegration\": false,\n" +
                "  \"paymentMethods\": [\n" +
                "    {\n" +
                "      \"discount\": 0.0,\n" +
                "      \"id\": 1,\n" +
                "      \"originId\": 1,\n" +
                "      \"subtotal\": 26.9,\n" +
                "      \"tax\": 4.6426,\n" +
                "      \"value\": 26.9\n" +
                "    }\n" +
                "  ],\n" +
                "  \"printed\": false,\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"articleId\": 927,\n" +
                "      \"articleName\": null,\n" +
                "      \"articleTypeId\": 1,\n" +
                "      \"basePrice\": 20.9,\n" +
                "      \"brandId\": 8,\n" +
                "      \"brandName\": \"Pecado Natural\",\n" +
                "      \"categoryId\": 35,\n" +
                "      \"categoryName\": \"Personalizado\",\n" +
                "      \"co2Total\": 0,\n" +
                "      \"deduction\": 0,\n" +
                "      \"discount\": 0,\n" +
                "      \"discounts\": [\n" +
                "        \n" +
                "      ],\n" +
                "      \"groups\": [\n" +
                "        {\n" +
                "          \"id\": 109,\n" +
                "          \"name\": \"Proteína\",\n" +
                "          \"portions\": [\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 656,\n" +
                "              \"name\": \"Almôndegas vegetarianas 140 g\",\n" +
                "              \"parentId\": 1095,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470385\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 140.0\n" +
                "            }\n" +
                "          ],\n" +
                "          \"removedPortions\": [\n" +
                "            \n" +
                "          ],\n" +
                "          \"sku\": \"12276489554839470385\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 228,\n" +
                "          \"name\": \"Escolha o molho\",\n" +
                "          \"portions\": [\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1021,\n" +
                "              \"name\": \"Molho Caesar 70 ml\",\n" +
                "              \"parentId\": 2281,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276758091713741530\",\n" +
                "              \"units\": 6,\n" +
                "              \"weight\": 70.0\n" +
                "            }\n" +
                "          ],\n" +
                "          \"removedPortions\": [\n" +
                "            \n" +
                "          ],\n" +
                "          \"sku\": \"12276758091713741530\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 227,\n" +
                "          \"name\": \"Escolha os complementos\",\n" +
                "          \"portions\": [\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1014,\n" +
                "              \"name\": \"Alho frito 2 g\",\n" +
                "              \"parentId\": 2215,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276758091713741523\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 2.0\n" +
                "            }\n" +
                "          ],\n" +
                "          \"removedPortions\": [\n" +
                "            \n" +
                "          ],\n" +
                "          \"sku\": \"12276758091713741523\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 230,\n" +
                "          \"name\": \"Deseja talheres?\",\n" +
                "          \"portions\": [\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1022,\n" +
                "              \"name\": \"Sim, por favor, enviar talheres\",\n" +
                "              \"parentId\": 1299,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276758091713741539\",\n" +
                "              \"units\": 9,\n" +
                "              \"weight\": 1.0\n" +
                "            }\n" +
                "          ],\n" +
                "          \"removedPortions\": [\n" +
                "            \n" +
                "          ],\n" +
                "          \"sku\": \"12276758091713741539\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 226,\n" +
                "          \"name\": \"Escolha os vegetais\",\n" +
                "          \"portions\": [\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1008,\n" +
                "              \"name\": \"Beterraba assada 40 g\",\n" +
                "              \"parentId\": 2274,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276758091713741517\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 40.0\n" +
                "            }\n" +
                "          ],\n" +
                "          \"removedPortions\": [\n" +
                "            \n" +
                "          ],\n" +
                "          \"sku\": \"12276758091713741517\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 107,\n" +
                "          \"name\": \"Base\",\n" +
                "          \"portions\": [\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 999,\n" +
                "              \"name\": \"Alface americana laminada 80 g\",\n" +
                "              \"parentId\": 2285,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276758091713741508\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 80.0\n" +
                "            }\n" +
                "          ],\n" +
                "          \"removedPortions\": [\n" +
                "            \n" +
                "          ],\n" +
                "          \"sku\": \"12276758091713741508\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"id\": 519,\n" +
                "      \"image\": \"https://assets.robinfood.com/delivery/menu/products/e314a5d1-b1ea-4ce3-86c6-da99908a2a71.jpg\",\n" +
                "      \"brandMenuId\": 13,\n" +
                "      \"menuHallProductId\": null,\n" +
                "      \"name\": \"Crie sua salada\",\n" +
                "      \"ncm\": \"890890890\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"sizeId\": 21,\n" +
                "      \"sizeName\": \"\",\n" +
                "      \"sku\": \"9034800139121721348\",\n" +
                "      \"treasuryCategory\": \n" +
                "        {\n" +
                "          \"name\": \"CFOP\",\n" +
                "          \"parameters\": [\n" +
                "            {\n" +
                "              \"name\": \"code\",\n" +
                "              \"value\": \"5101\"\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "      \"taxes\": [\n" +
                "        {\n" +
                "          \"familyTypeId\": 3,\n" +
                "          \"id\": 3549513,\n" +
                "          \"price\": 0.3448,\n" +
                "          \"taxTypeName\": \"PIS\",\n" +
                "          \"taxTypeId\": 4,\n" +
                "          \"treasuryTaxes\": [\n" +
                "            {\n" +
                "              \"name\": \"PISAliq\",\n" +
                "              \"parameters\": [\n" +
                "                {\n" +
                "                  \"name\": \"CST\",\n" +
                "                  \"value\": \"01\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"pPIS\",\n" +
                "                  \"value\": \"0.0165\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"vBC\",\n" +
                "                  \"value\": \"20.90\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"name\": \"PISNT\",\n" +
                "              \"parameters\": [\n" +
                "                {\n" +
                "                  \"name\": \"CST\",\n" +
                "                  \"value\": \"01\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ],\n" +
                "          \"value\": 1.65\n" +
                "        },\n" +
                "        {\n" +
                "          \"familyTypeId\": 7,\n" +
                "          \"id\": 3549514,\n" +
                "          \"price\": 1.5884,\n" +
                "          \"taxTypeName\": \"COFINS\",\n" +
                "          \"taxTypeId\": 5,\n" +
                "          \"treasuryTaxes\": [\n" +
                "            {\n" +
                "              \"name\": \"COFINSAliq\",\n" +
                "              \"parameters\": [\n" +
                "                {\n" +
                "                  \"name\": \"CST\",\n" +
                "                  \"value\": \"01\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"vBC\",\n" +
                "                  \"value\": \"20.90\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"pCOFINS\",\n" +
                "                  \"value\": \"0.0760\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ],\n" +
                "          \"value\": 7.6\n" +
                "        },\n" +
                "        {\n" +
                "          \"familyTypeId\": 11,\n" +
                "          \"id\": 3549515,\n" +
                "          \"price\": 1.7556,\n" +
                "          \"taxTypeName\": \"ICMS\",\n" +
                "          \"taxTypeId\": 3,\n" +
                "          \"treasuryTaxes\": [\n" +
                "            {\n" +
                "              \"name\": \"ICMS00\",\n" +
                "              \"parameters\": [\n" +
                "                {\n" +
                "                  \"name\": \"Orig\",\n" +
                "                  \"value\": \"0\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"CST\",\n" +
                "                  \"value\": \"0\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"pICMS\",\n" +
                "                  \"value\": \"8.40\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ],\n" +
                "          \"value\": 8.4\n" +
                "        }\n" +
                "      ],\n" +
                "      \"unitPrice\": 20.9,\n" +
                "      \"total\": 20.9\n" +
                "    },\n" +
                "    {\n" +
                "      \"articleId\": 941,\n" +
                "      \"articleName\": null,\n" +
                "      \"articleTypeId\": 1,\n" +
                "      \"basePrice\": 2.5,\n" +
                "      \"brandId\": 13,\n" +
                "      \"brandName\": \"Pecado Natural\",\n" +
                "      \"categoryId\": 36,\n" +
                "      \"categoryName\": \"Sugerido\",\n" +
                "      \"co2Total\": 0,\n" +
                "      \"deduction\": 0,\n" +
                "      \"discount\": 0,\n" +
                "      \"discounts\": [\n" +
                "        \n" +
                "      ],\n" +
                "      \"groups\": [\n" +
                "        \n" +
                "      ],\n" +
                "      \"id\": 521,\n" +
                "      \"image\": \"https://assets.robinfood.com/delivery/menu/products/e314a5d1-b1ea-4ce3-86c6-da99908a2a71.jpg\",\n" +
                "      \"brandMenuId\": 13,\n" +
                "      \"menuHallProductId\": null,\n" +
                "      \"name\": \"Coca Cola Regular 350ml\",\n" +
                "      \"ncm\": \"890890890\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"sizeId\": 21,\n" +
                "      \"sizeName\": \"\",\n" +
                "      \"sku\": \"12276758091713741484\",\n" +
                "      \"treasuryCategory\": \n" +
                "        {\n" +
                "          \"name\": \"CFOP\",\n" +
                "          \"parameters\": [\n" +
                "            {\n" +
                "              \"name\": \"code\",\n" +
                "              \"value\": \"5405\"\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "      \"taxes\": [\n" +
                "        {\n" +
                "          \"familyTypeId\": 6,\n" +
                "          \"id\": 3549516,\n" +
                "          \"price\": 0.0,\n" +
                "          \"taxTypeName\": \"PIS\",\n" +
                "          \"taxTypeId\": 4,\n" +
                "          \"treasuryTaxes\": [\n" +
                "            {\n" +
                "              \"name\": \"PISAliq\",\n" +
                "              \"parameters\": [\n" +
                "                {\n" +
                "                  \"name\": \"CST\",\n" +
                "                  \"value\": \"06\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"pPIS\",\n" +
                "                  \"value\": \"0.0000\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"vBC\",\n" +
                "                  \"value\": \"0.00\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"name\": \"PISNT\",\n" +
                "              \"parameters\": [\n" +
                "                {\n" +
                "                  \"name\": \"CST\",\n" +
                "                  \"value\": \"06\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ],\n" +
                "          \"value\": 0.0\n" +
                "        },\n" +
                "        {\n" +
                "          \"familyTypeId\": 10,\n" +
                "          \"id\": 3549517,\n" +
                "          \"price\": 0.0,\n" +
                "          \"taxTypeName\": \"COFINS\",\n" +
                "          \"taxTypeId\": 5,\n" +
                "          \"treasuryTaxes\": [\n" +
                "            {\n" +
                "              \"name\": \"COFINSAliq\",\n" +
                "              \"parameters\": [\n" +
                "                {\n" +
                "                  \"name\": \"CST\",\n" +
                "                  \"value\": \"06\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"vBC\",\n" +
                "                  \"value\": \"0.00\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"pCOFINS\",\n" +
                "                  \"value\": \"0.0000\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ],\n" +
                "          \"value\": 0.0\n" +
                "        },\n" +
                "        {\n" +
                "          \"familyTypeId\": 15,\n" +
                "          \"id\": 3549518,\n" +
                "          \"price\": 0.0,\n" +
                "          \"taxTypeName\": \"ICMS\",\n" +
                "          \"taxTypeId\": 3,\n" +
                "          \"treasuryTaxes\": [\n" +
                "            {\n" +
                "              \"name\": \"ICMS00\",\n" +
                "              \"parameters\": [\n" +
                "                {\n" +
                "                  \"name\": \"Orig\",\n" +
                "                  \"value\": \"0\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"CST\",\n" +
                "                  \"value\": \"60\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"pICMS\",\n" +
                "                  \"value\": \"0.00\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ],\n" +
                "          \"value\": 0.0\n" +
                "        }\n" +
                "      ],\n" +
                "      \"unitPrice\": 2.5,\n" +
                "      \"total\": 2.5\n" +
                "    }\n" +
                "  ],\n" +
                "  \"statusId\": 1,\n" +
                "  \"statusName\": \"Pedido\",\n" +
                "  \"storeId\": 253,\n" +
                "  \"storeName\": \"MUY Dino Bueno\",\n" +
                "  \"subtotal\": 26.9,\n" +
                "  \"tax\": 4.6426,\n" +
                "  \"total\": 26.9,\n" +
                "  \"transactionId\": 620010,\n" +
                "  \"treasuryEntities\": [\n" +
                "    {\n" +
                "      \"name\": \"destinatario\",\n" +
                "      \"parameters\": [\n" +
                "        {\n" +
                "          \"name\": \"CPF\",\n" +
                "          \"value\": \"CPF\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
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
                "    },\n" +
                "    {\n" +
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
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "\"treasuryPayments\": [\n" +
                "      {\n" +
                "        \"id\": 116,\n" +
                "        \"name\": \"Dinheiro\",\n" +
                "        \"parameters\": [\n" +
                "          {\n" +
                "            \"name\": \"cMP\",\n" +
                "            \"value\": \"01\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"value\": 16.9\n" +
                "      },\n" +
                "      { \n" +
                "        \"id\": 2, \n" +
                "        \"name\": \"Cartão de Crédito\", \n" +
                "        \"parameters\": [ \n" +
                "          { \n" +
                "            \"name\": \"cMP\", \n" +
                "            \"value\": \"03\" \n" +
                "          } \n" +
                "        ], \n" +
                "        \"value\": 10.0\n" +
                "      }\n" +
                "    ]," +
                "  \"uid\": \"1xw8yhkhhz350\",\n" +
                "  \"user\": {\n" +
                "    \"email\": \"jlozano@muy.com.co\",\n" +
                "    \"firstName\": \"Jorge\",\n" +
                "    \"id\": 435889,\n" +
                "    \"lastName\": \"Test\",\n" +
                "    \"loyaltyStatus\": 3,\n" +
                "    \"mobile\": \"3831234561\"\n" +
                "  },\n" +
                "  \"users\": [\n" +
                "    {\n" +
                "      \"email\": \"jlozano@muy.com.co\",\n" +
                "      \"firstName\": \"Jorge\",\n" +
                "      \"id\": 435889,\n" +
                "      \"lastName\": \"Test\",\n" +
                "      \"loyaltyStatus\": 3,\n" +
                "      \"mobile\": \"3831234561\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        return objectMapper.readValue(json, OrderBillingDTO.class);
    }

    public OrderBillingDTO getDefaultOrderBillingDTOWithDiscount() throws JsonProcessingException {

        String json = "{\n" +
                "  \"brandId\": 1,\n" +
                "  \"brandName\": \"MUY\",\n" +
                "  \"co2Total\": 0,\n" +
                "  \"currency\": \"BRL\",\n" +
                "  \"coupons\": [\n" +
                "    \n" +
                "  ],\n" +
                "  \"id\": 3394406,\n" +
                "  \"deduction\": 0,\n" +
                "  \"discount\": 9.95,\n" +
                "  \"discounts\": [\n" +
                "    {\n" +
                "      \"id\": 995517,\n" +
                "      \"typeId\": 2,\n" +
                "      \"value\": 9.95\n" +
                "    }\n" +
                "  ],\n" +
                "  \"deliveryTypeId\": 1,\n" +
                "  \"flowId\": 5,\n" +
                "  \"invoice\": \"819\",\n" +
                "  \"notes\": \"POSV2\",\n" +
                "  \"originId\": 10,\n" +
                "  \"originName\": \"POS V2\",\n" +
                "  \"orderNumber\": \"819\",\n" +
                "  \"orderIsIntegration\": false,\n" +
                "  \"paymentMethods\": [\n" +
                "    {\n" +
                "      \"discount\": 9.95,\n" +
                "      \"id\": 1,\n" +
                "      \"originId\": 10,\n" +
                "      \"subtotal\": 39.8,\n" +
                "      \"tax\": 3.7163,\n" +
                "      \"value\": 29.85\n" +
                "    }\n" +
                "  ],\n" +
                "  \"printed\": false,\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"articleId\": 436,\n" +
                "      \"articleName\": null,\n" +
                "      \"articleTypeId\": 1,\n" +
                "      \"basePrice\": 19.9,\n" +
                "      \"brandId\": 1,\n" +
                "      \"brandName\": \"MUY\",\n" +
                "      \"categoryId\": 36,\n" +
                "      \"categoryName\": \"Sugerido\",\n" +
                "      \"co2Total\": 0,\n" +
                "      \"deduction\": 0,\n" +
                "      \"discount\": 9.95,\n" +
                "      \"discounts\": [\n" +
                "        \n" +
                "      ],\n" +
                "      \"groups\": [\n" +
                "        {\n" +
                "          \"id\": 101,\n" +
                "          \"name\": \"Ingrediente\",\n" +
                "          \"portions\": [\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 648,\n" +
                "              \"name\": \"Baião de dois\",\n" +
                "              \"parentId\": 1075,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470377\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 370.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 649,\n" +
                "              \"name\": \"Couve fresca\",\n" +
                "              \"parentId\": 1099,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470378\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 30.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 642,\n" +
                "              \"name\": \"Vinagrete\",\n" +
                "              \"parentId\": 1078,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470371\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 30.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 650,\n" +
                "              \"name\": \"Pimenta biquinho\",\n" +
                "              \"parentId\": 938,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470379\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 10.0\n" +
                "            }\n" +
                "          ],\n" +
                "          \"removedPortions\": [\n" +
                "            \n" +
                "          ],\n" +
                "          \"sku\": \"12276489554839470377\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"id\": 521,\n" +
                "      \"image\": \"https://assets.robinfood.com/delivery/menu/products/318d7148-99f9-454a-a7e0-53d3a9e1e30c.jpg\",\n" +
                "      \"brandMenuId\": 12,\n" +
                "      \"menuHallProductId\": null,\n" +
                "      \"name\": \"Baião\",\n" +
                "      \"quantity\": 2,\n" +
                "      \"sizeId\": 19,\n" +
                "      \"sizeName\": \"MUY\",\n" +
                "      \"sku\": \"12276489554839470468\",\n" +
                "      \"treasuryCategory\": \n" +
                "        {\n" +
                "          \"name\": \"CFOP\",\n" +
                "          \"parameters\": [\n" +
                "            {\n" +
                "              \"name\": \"code\",\n" +
                "              \"value\": \"5101\"\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "      \"taxes\": [\n" +
                "        {\n" +
                "          \"familyTypeId\": 3,\n" +
                "          \"id\": 3685603,\n" +
                "          \"price\": 0.4925,\n" +
                "          \"taxTypeName\": \"PIS\",\n" +
                "          \"taxTypeId\": 4,\n" +
                "          \"treasuryTaxes\": [\n" +
                "            {\n" +
                "              \"name\": \"PISAliq\",\n" +
                "              \"parameters\": [\n" +
                "                {\n" +
                "                  \"name\": \"CST\",\n" +
                "                  \"value\": \"01\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"pPIS\",\n" +
                "                  \"value\": \"0.0165\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"vBC\",\n" +
                "                  \"value\": \"14.93\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ],\n" +
                "          \"value\": 1.65\n" +
                "        },\n" +
                "        {\n" +
                "          \"familyTypeId\": 7,\n" +
                "          \"id\": 3685604,\n" +
                "          \"price\": 2.2686,\n" +
                "          \"taxTypeName\": \"COFINS\",\n" +
                "          \"taxTypeId\": 5,\n" +
                "          \"treasuryTaxes\": [\n" +
                "            {\n" +
                "              \"name\": \"COFINSAliq\",\n" +
                "              \"parameters\": [\n" +
                "                {\n" +
                "                  \"name\": \"CST\",\n" +
                "                  \"value\": \"01\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"vBC\",\n" +
                "                  \"value\": \"14.93\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"pCOFINS\",\n" +
                "                  \"value\": \"0.0760\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ],\n" +
                "          \"value\": 7.6\n" +
                "        },\n" +
                "        {\n" +
                "          \"familyTypeId\": 11,\n" +
                "          \"id\": 3685605,\n" +
                "          \"price\": 0.9552,\n" +
                "          \"taxTypeName\": \"ICMS\",\n" +
                "          \"taxTypeId\": 3,\n" +
                "          \"treasuryTaxes\": [\n" +
                "            {\n" +
                "              \"name\": \"ICMS00\",\n" +
                "              \"parameters\": [\n" +
                "                {\n" +
                "                  \"name\": \"Orig\",\n" +
                "                  \"value\": \"0\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"CST\",\n" +
                "                  \"value\": \"00\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"pICMS\",\n" +
                "                  \"value\": \"3.20\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ],\n" +
                "          \"value\": 3.2\n" +
                "        }\n" +
                "      ],\n" +
                "      \"unitPrice\": 19.9,\n" +
                "      \"total\": 29.85\n" +
                "    }\n" +
                "  ],\n" +
                "  \"statusId\": 1,\n" +
                "  \"statusName\": \"Pedido\",\n" +
                "  \"storeId\": 96,\n" +
                "  \"storeName\": \"MUY Guararapes\",\n" +
                "  \"subtotal\": 39.8,\n" +
                "  \"tax\": 3.7163,\n" +
                "  \"total\": 29.85,\n" +
                "  \"transactionId\": 653043,\n" +
                "  \"treasuryEntities\": [\n" +
                "    {\n" +
                "      \"name\": \"connection\",\n" +
                "      \"parameters\": [\n" +
                "        {\n" +
                "          \"name\": \"CPF\",\n" +
                "          \"value\": \"CPF\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
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
                "    },\n" +
                "    {\n" +
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
                "    }\n" +
                "  ],\n" +
                "  \"treasuryPayments\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Dinheiro\",\n" +
                "      \"parameters\": [\n" +
                "        {\n" +
                "          \"name\": \"cMP\",\n" +
                "          \"value\": \"01\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"value\": 29.85\n" +
                "    }\n" +
                "  ],\n" +
                "  \"uid\": \"ml44rngrge12\",\n" +
                "  \"user\": {\n" +
                "    \"email\": \"rfguararapes-c1@robinfood.com\",\n" +
                "    \"firstName\": \"Cajero 1\",\n" +
                "    \"id\": 471024,\n" +
                "    \"lastName\": \"MUY Guararapes\",\n" +
                "    \"loyaltyStatus\": 0,\n" +
                "    \"mobile\": \"90001453746\"\n" +
                "  },\n" +
                "  \"users\": [\n" +
                "    {\n" +
                "      \"email\": \"rfguararapes-c1@robinfood.com\",\n" +
                "      \"firstName\": \"Cajero 1\",\n" +
                "      \"id\": 471024,\n" +
                "      \"lastName\": \"MUY Guararapes\",\n" +
                "      \"loyaltyStatus\": 0,\n" +
                "      \"mobile\": \"90001453746\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        return objectMapper.readValue(json, OrderBillingDTO.class);
    }
}
