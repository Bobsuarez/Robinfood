package com.robinfood.localserver.electronicbillbc.mocks.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localserver.commons.dtos.orders.billing.OrderBillingProductDTO;

import java.util.List;

public class OrderBillingProductDTOMock {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public OrderBillingProductDTO getDefaultOrderBillingProductDTO() throws JsonProcessingException {

        String json = "{\n" +
                "    \"articleId\": 267,\n" +
                "    \"articleName\": \"ARTICLE\",\n" +
                "    \"articleTypeId\": 1,\n" +
                "    \"basePrice\": 18900,\n" +
                "    \"brandId\": 3,\n" +
                "    \"brandName\": \"Pixi\",\n" +
                "    \"categoryId\": 22,\n" +
                "    \"categoryName\": \"Pizzas medianas\",\n" +
                "    \"co2Total\": 0,\n" +
                "    \"deduction\": 0,\n" +
                "    \"discount\": 0,\n" +
                "    \"discounts\": [\n" +
                "      \n" +
                "    ],\n" +
                "    \"groups\": [\n" +
                "      \n" +
                "    ],\n" +
                "    \"id\": 179,\n" +
                "    \"image\": \"https://assets.robinfood.com/delivery/menu/products/7fee68bf-41ff-4e98-a870-da3973d344d7.jpg\",\n" +
                "    \"brandMenuId\": 3,\n" +
                "    \"menuHallProductId\": 47,\n" +
                "    \"name\": \"Pizza pepperoni\",\n" +
                "    \"ncm\": \"890890890\",\n" +
                "    \"quantity\": 1,\n" +
                "    \"sizeId\": 13,\n" +
                "    \"sizeName\": \"Mediana\",\n" +
                "    \"sku\": \"17680692681858089293\",\n" +
                "    \"taxes\": [\n" +
                "      {\n" +
                "        \"familyTypeId\": 1,\n" +
                "        \"taxTypeId\": 5,\n" +
                "        \"taxTypeName\": \"COFINS\",\n" +
                "        \"id\": 3509892,\n" +
                "        \"price\": 1400,\n" +
                "        \"value\": 8,\n" +
                "        \"treasuryTaxes\": [\n" +
                "          {\n" +
                "            \"name\": \"COFINSSN\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"49\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ],\n" +
                "    \"treasuryCategory\":\n" +
                "      {\n" +
                "        \"name\": \"CFOP\",\n" +
                "        \"parameters\": [\n" +
                "          {\n" +
                "            \"name\": \"code\",\n" +
                "            \"value\": \"5101\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "    \"unitPrice\": 18900,\n" +
                "    \"total\": 11500\n" +
                "  }";

        return objectMapper.readValue(json, OrderBillingProductDTO.class);
    }

    public List<OrderBillingProductDTO> getDefaultOrderBillingProductDTOList() throws JsonProcessingException {

        String json = "[\n" +
                "  {\n" +
                "    \"articleId\": 927,\n" +
                "    \"articleName\": null,\n" +
                "    \"articleTypeId\": 1,\n" +
                "    \"basePrice\": 20.9,\n" +
                "    \"brandId\": 8,\n" +
                "    \"brandName\": \"Pecado Natural\",\n" +
                "    \"categoryId\": 35,\n" +
                "    \"categoryName\": \"Personalizado\",\n" +
                "    \"co2Total\": 0,\n" +
                "    \"deduction\": 0,\n" +
                "    \"discount\": 0,\n" +
                "    \"discounts\": [\n" +
                "      \n" +
                "    ],\n" +
                "    \"groups\": [\n" +
                "      {\n" +
                "        \"id\": 109,\n" +
                "        \"name\": \"Proteína\",\n" +
                "        \"portions\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 656,\n" +
                "            \"name\": \"Almôndegas vegetarianas 140 g\",\n" +
                "            \"parentId\": 1095,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"sku\": \"12276489554839470385\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 140.0\n" +
                "          }\n" +
                "        ],\n" +
                "        \"removedPortions\": [\n" +
                "          \n" +
                "        ],\n" +
                "        \"sku\": \"12276489554839470385\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 228,\n" +
                "        \"name\": \"Escolha o molho\",\n" +
                "        \"portions\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1021,\n" +
                "            \"name\": \"Molho Caesar 70 ml\",\n" +
                "            \"parentId\": 2281,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"sku\": \"12276758091713741530\",\n" +
                "            \"units\": 6,\n" +
                "            \"weight\": 70.0\n" +
                "          }\n" +
                "        ],\n" +
                "        \"removedPortions\": [\n" +
                "          \n" +
                "        ],\n" +
                "        \"sku\": \"12276758091713741530\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 227,\n" +
                "        \"name\": \"Escolha os complementos\",\n" +
                "        \"portions\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1014,\n" +
                "            \"name\": \"Alho frito 2 g\",\n" +
                "            \"parentId\": 2215,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"sku\": \"12276758091713741523\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 2.0\n" +
                "          }\n" +
                "        ],\n" +
                "        \"removedPortions\": [\n" +
                "          \n" +
                "        ],\n" +
                "        \"sku\": \"12276758091713741523\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 230,\n" +
                "        \"name\": \"Deseja talheres?\",\n" +
                "        \"portions\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1022,\n" +
                "            \"name\": \"Sim, por favor, enviar talheres\",\n" +
                "            \"parentId\": 1299,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"sku\": \"12276758091713741539\",\n" +
                "            \"units\": 9,\n" +
                "            \"weight\": 1.0\n" +
                "          }\n" +
                "        ],\n" +
                "        \"removedPortions\": [\n" +
                "          \n" +
                "        ],\n" +
                "        \"sku\": \"12276758091713741539\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 226,\n" +
                "        \"name\": \"Escolha os vegetais\",\n" +
                "        \"portions\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1008,\n" +
                "            \"name\": \"Beterraba assada 40 g\",\n" +
                "            \"parentId\": 2274,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"sku\": \"12276758091713741517\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 40.0\n" +
                "          }\n" +
                "        ],\n" +
                "        \"removedPortions\": [\n" +
                "          \n" +
                "        ],\n" +
                "        \"sku\": \"12276758091713741517\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 107,\n" +
                "        \"name\": \"Base\",\n" +
                "        \"portions\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 999,\n" +
                "            \"name\": \"Alface americana laminada 80 g\",\n" +
                "            \"parentId\": 2285,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"sku\": \"12276758091713741508\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 80.0\n" +
                "          }\n" +
                "        ],\n" +
                "        \"removedPortions\": [\n" +
                "          \n" +
                "        ],\n" +
                "        \"sku\": \"12276758091713741508\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"id\": 519,\n" +
                "    \"image\": \"https://assets.robinfood.com/delivery/menu/products/e314a5d1-b1ea-4ce3-86c6-da99908a2a71.jpg\",\n" +
                "    \"brandMenuId\": 13,\n" +
                "    \"menuHallProductId\": null,\n" +
                "    \"name\": \"Crie sua salada\",\n" +
                "    \"ncm\": \"890890890\",\n" +
                "    \"quantity\": 1,\n" +
                "    \"sizeId\": 21,\n" +
                "    \"sizeName\": \"\",\n" +
                "    \"sku\": \"9034800139121721348\",\n" +
                "    \"treasuryCategory\":\n" +
                "      {\n" +
                "        \"name\": \"CFOP\",\n" +
                "        \"parameters\": [\n" +
                "          {\n" +
                "            \"name\": \"code\",\n" +
                "            \"value\": \"5101\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "    \"taxes\": [\n" +
                "      {\n" +
                "        \"familyTypeId\": 3,\n" +
                "        \"id\": 3549513,\n" +
                "        \"price\": 0.3448,\n" +
                "        \"taxTypeName\": \"PIS\",\n" +
                "        \"taxTypeId\": 4,\n" +
                "        \"treasuryTaxes\": [\n" +
                "          {\n" +
                "            \"name\": \"PISAliq\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"01\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"pPIS\",\n" +
                "                \"value\": \"0.0165\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"vBC\",\n" +
                "                \"value\": \"20.90\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"PISNT\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"01\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ],\n" +
                "        \"value\": 1.65\n" +
                "      },\n" +
                "      {\n" +
                "        \"familyTypeId\": 7,\n" +
                "        \"id\": 3549514,\n" +
                "        \"price\": 1.5884,\n" +
                "        \"taxTypeName\": \"COFINS\",\n" +
                "        \"taxTypeId\": 5,\n" +
                "        \"treasuryTaxes\": [\n" +
                "          {\n" +
                "            \"name\": \"COFINSAliq\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"01\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"vBC\",\n" +
                "                \"value\": \"20.90\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"pCOFINS\",\n" +
                "                \"value\": \"0.0760\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ],\n" +
                "        \"value\": 7.6\n" +
                "      },\n" +
                "      {\n" +
                "        \"familyTypeId\": 11,\n" +
                "        \"id\": 3549515,\n" +
                "        \"price\": 1.7556,\n" +
                "        \"taxTypeName\": \"ICMS\",\n" +
                "        \"taxTypeId\": 3,\n" +
                "        \"treasuryTaxes\": [\n" +
                "          {\n" +
                "            \"name\": \"ICMS00\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"Orig\",\n" +
                "                \"value\": \"0\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"0\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"pICMS\",\n" +
                "                \"value\": \"8.40\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ],\n" +
                "        \"value\": 8.4\n" +
                "      }\n" +
                "    ],\n" +
                "    \"unitPrice\": 20.9,\n" +
                "    \"total\": 20.9\n" +
                "  },\n" +
                "  {\n" +
                "    \"articleId\": 941,\n" +
                "    \"articleName\": null,\n" +
                "    \"articleTypeId\": 1,\n" +
                "    \"basePrice\": 2.5,\n" +
                "    \"brandId\": 13,\n" +
                "    \"brandName\": \"Pecado Natural\",\n" +
                "    \"categoryId\": 36,\n" +
                "    \"categoryName\": \"Sugerido\",\n" +
                "    \"co2Total\": 0,\n" +
                "    \"deduction\": 0,\n" +
                "    \"discount\": 0,\n" +
                "    \"discounts\": [\n" +
                "      \n" +
                "    ],\n" +
                "    \"groups\": [\n" +
                "      \n" +
                "    ],\n" +
                "    \"id\": 521,\n" +
                "    \"image\": \"https://assets.robinfood.com/delivery/menu/products/e314a5d1-b1ea-4ce3-86c6-da99908a2a71.jpg\",\n" +
                "    \"brandMenuId\": 13,\n" +
                "    \"menuHallProductId\": null,\n" +
                "    \"name\": \"Coca Cola Regular 350ml\",\n" +
                "    \"ncm\": \"890890890\",\n" +
                "    \"quantity\": 1,\n" +
                "    \"sizeId\": 21,\n" +
                "    \"sizeName\": \"\",\n" +
                "    \"sku\": \"12276758091713741484\",\n" +
                "    \"treasuryCategory\":\n" +
                "      {\n" +
                "        \"name\": \"CFOP\",\n" +
                "        \"parameters\": [\n" +
                "          {\n" +
                "            \"name\": \"code\",\n" +
                "            \"value\": \"5405\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "    \"taxes\": [\n" +
                "      {\n" +
                "        \"familyTypeId\": 6,\n" +
                "        \"id\": 3549516,\n" +
                "        \"price\": 0.0,\n" +
                "        \"taxTypeName\": \"PIS\",\n" +
                "        \"taxTypeId\": 4,\n" +
                "        \"treasuryTaxes\": [\n" +
                "          {\n" +
                "            \"name\": \"PISAliq\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"06\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"pPIS\",\n" +
                "                \"value\": \"0.0000\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"vBC\",\n" +
                "                \"value\": \"0.00\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"PISNT\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"06\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ],\n" +
                "        \"value\": 0.0\n" +
                "      },\n" +
                "      {\n" +
                "        \"familyTypeId\": 10,\n" +
                "        \"id\": 3549517,\n" +
                "        \"price\": 0.0,\n" +
                "        \"taxTypeName\": \"COFINS\",\n" +
                "        \"taxTypeId\": 5,\n" +
                "        \"treasuryTaxes\": [\n" +
                "          {\n" +
                "            \"name\": \"COFINSAliq\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"06\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"vBC\",\n" +
                "                \"value\": \"0.00\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"pCOFINS\",\n" +
                "                \"value\": \"0.0000\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ],\n" +
                "        \"value\": 0.0\n" +
                "      },\n" +
                "      {\n" +
                "        \"familyTypeId\": 15,\n" +
                "        \"id\": 3549518,\n" +
                "        \"price\": 0.0,\n" +
                "        \"taxTypeName\": \"ICMS\",\n" +
                "        \"taxTypeId\": 3,\n" +
                "        \"treasuryTaxes\": [\n" +
                "          {\n" +
                "            \"name\": \"ICMS00\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"Orig\",\n" +
                "                \"value\": \"0\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"60\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"pICMS\",\n" +
                "                \"value\": \"0.00\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ],\n" +
                "        \"value\": 0.0\n" +
                "      }\n" +
                "    ],\n" +
                "    \"unitPrice\": 2.5,\n" +
                "    \"total\": 2.5\n" +
                "  }\n" +
                "]";

        return objectMapper.readValue(json, new TypeReference<List<OrderBillingProductDTO>>() {});
    }

    public List<OrderBillingProductDTO> getDefaultOrderBillingProductDTOWithDiscountsList()
            throws JsonProcessingException {

        String json = "[\n" +
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
                "  ]";

        return objectMapper.readValue(json, new TypeReference<List<OrderBillingProductDTO>>() {});
    }



    public List<OrderBillingProductDTO> getDefaultOrderBillingProductDTORequestList() throws JsonProcessingException {

        String json = "[\n" +
                "  {\n" +
                "    \"articleId\": 927,\n" +
                "    \"articleName\": null,\n" +
                "    \"articleTypeId\": 1,\n" +
                "    \"basePrice\": 20.9,\n" +
                "    \"brandId\": 8,\n" +
                "    \"brandName\": \"Pecado Natural\",\n" +
                "    \"categoryId\": 35,\n" +
                "    \"categoryName\": \"Personalizado\",\n" +
                "    \"co2Total\": 0,\n" +
                "    \"deduction\": 0,\n" +
                "    \"discount\": 1,\n" +
                "      \"discounts\": [\n" +
                "        {\n" +
                "      \"id\": 1,\n" +
                "      \"typeId\": 1,\n" +
                "      \"value\": 1\n" +
                "            }\n" +
                "      ],\n" +
                "    \"groups\": [\n" +
                "      {\n" +
                "        \"id\": 109,\n" +
                "        \"name\": \"Proteína\",\n" +
                "        \"portions\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 656,\n" +
                "            \"name\": \"Almôndegas vegetarianas 140 g\",\n" +
                "            \"parentId\": 1095,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"sku\": \"12276489554839470385\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 140.0\n" +
                "          }\n" +
                "        ],\n" +
                "        \"removedPortions\": [\n" +
                "          \n" +
                "        ],\n" +
                "        \"sku\": \"12276489554839470385\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 228,\n" +
                "        \"name\": \"Escolha o molho\",\n" +
                "        \"portions\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1021,\n" +
                "            \"name\": \"Molho Caesar 70 ml\",\n" +
                "            \"parentId\": 2281,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"sku\": \"12276758091713741530\",\n" +
                "            \"units\": 6,\n" +
                "            \"weight\": 70.0\n" +
                "          }\n" +
                "        ],\n" +
                "        \"removedPortions\": [\n" +
                "          \n" +
                "        ],\n" +
                "        \"sku\": \"12276758091713741530\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 227,\n" +
                "        \"name\": \"Escolha os complementos\",\n" +
                "        \"portions\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1014,\n" +
                "            \"name\": \"Alho frito 2 g\",\n" +
                "            \"parentId\": 2215,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"sku\": \"12276758091713741523\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 2.0\n" +
                "          }\n" +
                "        ],\n" +
                "        \"removedPortions\": [\n" +
                "          \n" +
                "        ],\n" +
                "        \"sku\": \"12276758091713741523\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 230,\n" +
                "        \"name\": \"Deseja talheres?\",\n" +
                "        \"portions\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1022,\n" +
                "            \"name\": \"Sim, por favor, enviar talheres\",\n" +
                "            \"parentId\": 1299,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"sku\": \"12276758091713741539\",\n" +
                "            \"units\": 9,\n" +
                "            \"weight\": 1.0\n" +
                "          }\n" +
                "        ],\n" +
                "        \"removedPortions\": [\n" +
                "          \n" +
                "        ],\n" +
                "        \"sku\": \"12276758091713741539\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 226,\n" +
                "        \"name\": \"Escolha os vegetais\",\n" +
                "        \"portions\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1008,\n" +
                "            \"name\": \"Beterraba assada 40 g\",\n" +
                "            \"parentId\": 2274,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"sku\": \"12276758091713741517\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 40.0\n" +
                "          }\n" +
                "        ],\n" +
                "        \"removedPortions\": [\n" +
                "          \n" +
                "        ],\n" +
                "        \"sku\": \"12276758091713741517\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 107,\n" +
                "        \"name\": \"Base\",\n" +
                "        \"portions\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 999,\n" +
                "            \"name\": \"Alface americana laminada 80 g\",\n" +
                "            \"parentId\": 2285,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"sku\": \"12276758091713741508\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 80.0\n" +
                "          }\n" +
                "        ],\n" +
                "        \"removedPortions\": [\n" +
                "          \n" +
                "        ],\n" +
                "        \"sku\": \"12276758091713741508\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"id\": 519,\n" +
                "    \"image\": \"https://assets.robinfood.com/delivery/menu/products/e314a5d1-b1ea-4ce3-86c6-da99908a2a71.jpg\",\n" +
                "    \"brandMenuId\": 13,\n" +
                "    \"menuHallProductId\": null,\n" +
                "    \"name\": \"Crie sua salada\",\n" +
                "    \"ncm\": \"890890890\",\n" +
                "    \"quantity\": 1,\n" +
                "    \"sizeId\": 21,\n" +
                "    \"sizeName\": \"\",\n" +
                "    \"sku\": \"9034800139121721348\",\n" +
                "    \"treasuryCategory\": \n" +
                "      {\n" +
                "        \"name\": \"CFOP\",\n" +
                "        \"parameters\": [\n" +
                "          {\n" +
                "            \"name\": \"code\",\n" +
                "            \"value\": \"5101\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "    \"taxes\": [\n" +
                "      {\n" +
                "        \"familyTypeId\": 3,\n" +
                "        \"id\": 3549513,\n" +
                "        \"price\": 0.3448,\n" +
                "        \"taxTypeName\": \"PIS\",\n" +
                "        \"taxTypeId\": 4,\n" +
                "        \"treasuryTaxes\": [\n" +
                "          {\n" +
                "            \"name\": \"PISAliq\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"01\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"pPIS\",\n" +
                "                \"value\": \"0.0165\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"vBC\",\n" +
                "                \"value\": \"20.90\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"PISNT\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"01\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ],\n" +
                "        \"value\": 1.65\n" +
                "      },\n" +
                "      {\n" +
                "        \"familyTypeId\": 7,\n" +
                "        \"id\": 3549514,\n" +
                "        \"price\": 1.5884,\n" +
                "        \"taxTypeName\": \"COFINS\",\n" +
                "        \"taxTypeId\": 5,\n" +
                "        \"treasuryTaxes\": [\n" +
                "          {\n" +
                "            \"name\": \"COFINSAliq\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"01\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"vBC\",\n" +
                "                \"value\": \"20.90\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"pCOFINS\",\n" +
                "                \"value\": \"0.0760\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ],\n" +
                "        \"value\": 7.6\n" +
                "      },\n" +
                "      {\n" +
                "        \"familyTypeId\": 11,\n" +
                "        \"id\": 3549515,\n" +
                "        \"price\": 1.7556,\n" +
                "        \"taxTypeName\": \"ICMS\",\n" +
                "        \"taxTypeId\": 3,\n" +
                "        \"treasuryTaxes\": [\n" +
                "          {\n" +
                "            \"name\": \"ICMS00\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"Orig\",\n" +
                "                \"value\": \"0\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"0\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"pICMS\",\n" +
                "                \"value\": \"8.40\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ],\n" +
                "        \"value\": 8.4\n" +
                "      }\n" +
                "    ],\n" +
                "    \"unitPrice\": 20.9,\n" +
                "    \"total\": 20.9\n" +
                "  },\n" +
                "  {\n" +
                "    \"articleId\": 941,\n" +
                "    \"articleName\": null,\n" +
                "    \"articleTypeId\": 1,\n" +
                "    \"basePrice\": 2.5,\n" +
                "    \"brandId\": 13,\n" +
                "    \"brandName\": \"Pecado Natural\",\n" +
                "    \"categoryId\": 36,\n" +
                "    \"categoryName\": \"Sugerido\",\n" +
                "    \"co2Total\": 0,\n" +
                "    \"deduction\": 0,\n" +
                "    \"discount\": 0,\n" +
                "    \"discounts\": [\n" +
                "      \n" +
                "    ],\n" +
                "    \"groups\": [\n" +
                "      \n" +
                "    ],\n" +
                "    \"id\": 521,\n" +
                "    \"image\": \"https://assets.robinfood.com/delivery/menu/products/e314a5d1-b1ea-4ce3-86c6-da99908a2a71.jpg\",\n" +
                "    \"brandMenuId\": 13,\n" +
                "    \"menuHallProductId\": null,\n" +
                "    \"name\": \"Coca Cola Regular 350ml\",\n" +
                "    \"ncm\": \"890890890\",\n" +
                "    \"quantity\": 1,\n" +
                "    \"sizeId\": 21,\n" +
                "    \"sizeName\": \"\",\n" +
                "    \"sku\": \"12276758091713741484\",\n" +
                "    \"treasuryCategory\": \n" +
                "      {\n" +
                "        \"name\": \"CFOP\",\n" +
                "        \"parameters\": [\n" +
                "          {\n" +
                "            \"name\": \"code\",\n" +
                "            \"value\": \"5405\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "    \"taxes\": [\n" +
                "      {\n" +
                "        \"familyTypeId\": 6,\n" +
                "        \"id\": 3549516,\n" +
                "        \"price\": 0.0,\n" +
                "        \"taxTypeName\": \"PIS\",\n" +
                "        \"taxTypeId\": 4,\n" +
                "        \"treasuryTaxes\": [\n" +
                "          {\n" +
                "            \"name\": \"PISAliq\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"06\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"pPIS\",\n" +
                "                \"value\": \"0.0000\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"vBC\",\n" +
                "                \"value\": \"0.00\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"PISNT\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"06\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ],\n" +
                "        \"value\": 0.0\n" +
                "      },\n" +
                "      {\n" +
                "        \"familyTypeId\": 10,\n" +
                "        \"id\": 3549517,\n" +
                "        \"price\": 0.0,\n" +
                "        \"taxTypeName\": \"COFINS\",\n" +
                "        \"taxTypeId\": 5,\n" +
                "        \"treasuryTaxes\": [\n" +
                "          {\n" +
                "            \"name\": \"COFINSAliq\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"06\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"vBC\",\n" +
                "                \"value\": \"0.00\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"pCOFINS\",\n" +
                "                \"value\": \"0.0000\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ],\n" +
                "        \"value\": 0.0\n" +
                "      },\n" +
                "      {\n" +
                "        \"familyTypeId\": 15,\n" +
                "        \"id\": 3549518,\n" +
                "        \"price\": 0.0,\n" +
                "        \"taxTypeName\": \"ICMS\",\n" +
                "        \"taxTypeId\": 3,\n" +
                "        \"treasuryTaxes\": [\n" +
                "          {\n" +
                "            \"name\": \"ICMS00\",\n" +
                "            \"parameters\": [\n" +
                "              {\n" +
                "                \"name\": \"Orig\",\n" +
                "                \"value\": \"0\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"CST\",\n" +
                "                \"value\": \"60\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"pICMS\",\n" +
                "                \"value\": \"0.00\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ],\n" +
                "        \"value\": 0.0\n" +
                "      }\n" +
                "    ],\n" +
                "    \"unitPrice\": 2.5,\n" +
                "    \"total\": 2.5\n" +
                "  }\n" +
                "]";

        return objectMapper.readValue(json, new TypeReference<List<OrderBillingProductDTO>>() {});
    }


    public List<OrderBillingProductDTO> getDefaultOrderBillingProductDTOWithDiscounts()
            throws JsonProcessingException {

        String json = "[\n" +
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
                "      \"discount\": 1,\n" +
                "      \"discounts\": [\n" +
                "        {\n" +
                "      \"id\": 1,\n" +
                "      \"typeId\": 1,\n" +
                "      \"value\": 1\n" +
                "            }\n" +
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
                "      \"treasuryCategory\":\n" +
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
                "      \"total\": 28.85\n" +
                "    }\n" +
                "  ]";

        return objectMapper.readValue(json, new TypeReference<List<OrderBillingProductDTO>>() {});
    }

    public List<OrderBillingProductDTO> getDefaultOrderBillingProductDTOWithDiscountsRounding()
            throws JsonProcessingException {

        String json = "[\n" +
                "    {\n" +
                "      \"articleId\": 1370,\n" +
                "      \"articleName\": null,\n" +
                "      \"articleTypeId\": 1,\n" +
                "      \"basePrice\": 20.9,\n" +
                "      \"brandId\": 1,\n" +
                "      \"brandName\": \"MUY\",\n" +
                "      \"categoryId\": 36,\n" +
                "      \"categoryName\": \"Sugerido\",\n" +
                "      \"co2Total\": 0,\n" +
                "      \"deduction\": 1.995,\n" +
                "      \"discount\": 0,\n" +
                "      \"discounts\": [\n" +
                "        {\n" +
                "          \"id\": 2125460,\n" +
                "          \"typeId\": 3,\n" +
                "          \"value\": 0\n" +
                "        }\n" +
                "      ],\n" +
                "      \"groups\": [\n" +
                "        {\n" +
                "          \"id\": 338,\n" +
                "          \"name\": \"?Quer tirar algo?\",\n" +
                "          \"portions\": [\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 641,\n" +
                "              \"name\": \"Feij?o preto com lingui?a, carne seca e bacon\",\n" +
                "              \"parentId\": 1076,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470370\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 170.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 640,\n" +
                "              \"name\": \"Pernil de porco desfiado\",\n" +
                "              \"parentId\": 1090,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470369\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 50.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 636,\n" +
                "              \"name\": \"Arroz branco cozido\",\n" +
                "              \"parentId\": 1073,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470365\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 130.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 638,\n" +
                "              \"name\": \"Couve refogada\",\n" +
                "              \"parentId\": 1087,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470367\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 40.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 639,\n" +
                "              \"name\": \"Farofa de alho\",\n" +
                "              \"parentId\": 1088,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470368\",\n" +
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
                "            }\n" +
                "          ],\n" +
                "          \"removedPortions\": [\n" +
                "            \n" +
                "          ],\n" +
                "          \"sku\": \"12276489554839470370\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"id\": 1376,\n" +
                "      \"image\": \"https://assets.robinfood.com/delivery/menu/products/df043ce2-a2ba-4f5e-b02d-7add19f0dde7.jpg\",\n" +
                "      \"brandMenuId\": 12,\n" +
                "      \"menuHallProductId\": null,\n" +
                "      \"name\": \"Feij?\",\n" +
                "      \"ncm\": \"890890890\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"sizeId\": 19,\n" +
                "      \"sizeName\": \"MUY\",\n" +
                "      \"sku\": \"12277544897157791753\",\n" +
                "      \"treasuryCategory\":\n" +
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
                "          \"id\": 9135958,\n" +
                "          \"price\": 0.3119,\n" +
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
                "                  \"value\": \"18.91\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ],\n" +
                "          \"value\": 1.65\n" +
                "        },\n" +
                "        {\n" +
                "          \"familyTypeId\": 7,\n" +
                "          \"id\": 9135959,\n" +
                "          \"price\": 1.4368,\n" +
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
                "                  \"value\": \"18.91\"\n" +
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
                "          \"id\": 9135960,\n" +
                "          \"price\": 0.605,\n" +
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
                "      \"unitPrice\": 20.9,\n" +
                "      \"total\": 20.9\n" +
                "    },\n" +
                "    {\n" +
                "      \"articleId\": 1254,\n" +
                "      \"articleName\": null,\n" +
                "      \"articleTypeId\": 1,\n" +
                "      \"basePrice\": 20.9,\n" +
                "      \"brandId\": 1,\n" +
                "      \"brandName\": \"MUY\",\n" +
                "      \"categoryId\": 36,\n" +
                "      \"categoryName\": \"Sugerido\",\n" +
                "      \"co2Total\": 0,\n" +
                "      \"deduction\": 1.995,\n" +
                "      \"discount\": 0,\n" +
                "      \"discounts\": [\n" +
                "        {\n" +
                "          \"id\": 2125461,\n" +
                "          \"typeId\": 3,\n" +
                "          \"value\": 0\n" +
                "        }\n" +
                "      ],\n" +
                "      \"groups\": [\n" +
                "        \n" +
                "      ],\n" +
                "      \"id\": 1376,\n" +
                "      \"image\": \"https://assets.robinfood.com/delivery/menu/products/311a0734-3f1b-4884-b407-967403bf503e.jpg\",\n" +
                "      \"brandMenuId\": 12,\n" +
                "      \"menuHallProductId\": null,\n" +
                "      \"name\": \"Muy Bai?o\",\n" +
                "      \"ncm\": \"890890890\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"sizeId\": 19,\n" +
                "      \"sizeName\": \"MUY\",\n" +
                "      \"sku\": \"12277329449820619016\",\n" +
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
                "          \"id\": 9135961,\n" +
                "          \"price\": 0.3119,\n" +
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
                "                  \"value\": \"18.91\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ],\n" +
                "          \"value\": 1.65\n" +
                "        },\n" +
                "        {\n" +
                "          \"familyTypeId\": 7,\n" +
                "          \"id\": 9135962,\n" +
                "          \"price\": 1.4368,\n" +
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
                "                  \"value\": \"18.91\"\n" +
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
                "          \"id\": 9135963,\n" +
                "          \"price\": 0.605,\n" +
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
                "      \"unitPrice\": 20.9,\n" +
                "      \"total\": 20.9\n" +
                "    }\n" +
                "  ]";

        return objectMapper.readValue(json, new TypeReference<List<OrderBillingProductDTO>>() {});
    }
}

