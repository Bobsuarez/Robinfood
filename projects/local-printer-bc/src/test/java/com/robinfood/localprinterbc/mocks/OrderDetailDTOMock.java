package com.robinfood.localprinterbc.mocks;

import com.google.gson.Gson;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;

public class OrderDetailDTOMock {

    public OrderDetailDTO suggestedProducts() {

        String json = "{\n" +
                "  \"buyer\": {},\n" +
                "  \"brandId\": 6,\n" +
                "  \"brandName\": \"Tremendo burrito\",\n" +
                "  \"co2Total\": 200,\n" +
                "  \"currency\": \"COP\",\n" +
                "  \"coupons\": [],\n" +
                "  \"id\": 5473548,\n" +
                "  \"discount\": 0,\n" +
                "  \"discounts\": [],\n" +
                "  \"deliveryTypeId\": 1,\n" +
                "  \"deduction\": 0,\n" +
                "  \"flowId\": 5,\n" +
                "  \"foodCoins\": 0,\n" +
                "  \"invoice\": \"27934\",\n" +
                "  \"notes\": \"POSV2\",\n" +
                "  \"originId\": 10,\n" +
                "  \"originName\": \"POS V2\",\n" +
                "  \"orderNumber\": \"5304\",\n" +
                "  \"orderUuid\": \"589dd29e-5ff0-4dc9-aba5-0e8a09acb6b9\",\n" +
                "  \"orderIntegrationId\": \"123456\",\n" +
                "  \"orderIntegrationCode\": \"12345\",\n" +
                "  \"orderIntegrationUser\": \"Integration\",\n" +
                "  \"orderIsIntegration\": false,\n" +
                "  \"operationDate\": \"2023-03-08\",\n" +
                "  \"paymentMethods\": [\n" +
                "    {\n" +
                "      \"discount\": 0,\n" +
                "      \"id\": 1,\n" +
                "      \"originId\": 10,\n" +
                "      \"subtotal\": 16390.9352,\n" +
                "      \"tax\": 1311.2748,\n" +
                "      \"value\": 17900\n" +
                "    }\n" +
                "  ],\n" +
                "  \"posId\": 164,\n" +
                "  \"printed\": false,\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"articleId\": 351,\n" +
                "      \"articleName\": \"\",\n" +
                "      \"articleTypeId\": 1,\n" +
                "      \"basePrice\": 17900,\n" +
                "      \"brandId\": 6,\n" +
                "      \"brandName\": \"Tremendo burrito\",\n" +
                "      \"categoryId\": 28,\n" +
                "      \"categoryName\": \"Bowls y Burritos\",\n" +
                "      \"co2Total\": 100,\n" +
                "      \"deduction\": 0,\n" +
                "      \"discount\": 0,\n" +
                "      \"discounts\": [],\n" +
                "      \"groups\": [\n" +
                "        {\n" +
                "          \"id\": 70,\n" +
                "          \"name\": \"¿Quieres retirar algo de tu Tremendo?\",\n" +
                "          \"portions\": [\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 503,\n" +
                "              \"name\": \"Frijol negro\",\n" +
                "              \"parentId\": 98,\n" +
                "              \"price\": 0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470114\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 110,\n" +
                "              \"free\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 504,\n" +
                "              \"name\": \"Maíz tierno.\",\n" +
                "              \"parentId\": 734,\n" +
                "              \"price\": 0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470115\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 25,\n" +
                "              \"free\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 505,\n" +
                "              \"name\": \"Pico de gallo\",\n" +
                "              \"parentId\": 100,\n" +
                "              \"price\": 0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470116\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 25,\n" +
                "              \"free\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 506,\n" +
                "              \"name\": \"Sour cream\",\n" +
                "              \"parentId\": 37,\n" +
                "              \"price\": 0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470117\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 30,\n" +
                "              \"free\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 507,\n" +
                "              \"name\": \"Guacamole.\",\n" +
                "              \"parentId\": 99,\n" +
                "              \"price\": 0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470118\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 50,\n" +
                "              \"free\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1117,\n" +
                "              \"name\": \"Arroz con cilantro.\",\n" +
                "              \"parentId\": 733,\n" +
                "              \"price\": 0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276758091713741748\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 120,\n" +
                "              \"free\": 1\n" +
                "            }\n" +
                "          ],\n" +
                "          \"removedPortions\": [],\n" +
                "          \"sku\": \"12276489554839470114\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"id\": 449,\n" +
                "      \"image\": \"https://assets.robinfood.com/delivery/menu/products/a9265e9f-ac93-4400-89ec-2b446c0568f8.png\",\n" +
                "      \"brandMenuId\": 4,\n" +
                "      \"name\": \"MUY COMBO MUY Fresco + coca cola 400 ml + Galleta\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"sizeId\": 27,\n" +
                "      \"sizeName\": \"MUY\",\n" +
                "      \"sku\": \"12276489554839470163\",\n" +
                "      \"taxes\": [\n" +
                "        {\n" +
                "          \"familyTypeId\": 1,\n" +
                "          \"id\": 9370464,\n" +
                "          \"price\": 1325.9259,\n" +
                "          \"taxTypeId\": 1,\n" +
                "          \"taxTypeName\": \"Impoconsumo CO\",\n" +
                "          \"value\": 8\n" +
                "        }\n" +
                "      ],\n" +
                "      \"unitPrice\": 17900,\n" +
                "      \"total\": 17900\n" +
                "    }\n" +
                "  ],\n" +
                "  \"statusId\": 1,\n" +
                "  \"statusName\": \"Pedido\",\n" +
                "  \"storeId\": 29,\n" +
                "  \"storeName\": \"RobinFood Plaza 83\",\n" +
                "  \"subtotal\": 16574.0741,\n" +
                "  \"tax\": 1325.9259,\n" +
                "  \"total\": 17900,\n" +
                "  \"transactionId\": 2346001,\n" +
                "  \"transactionUuid\": \"bec65775-46ce-4620-8e1a-2f76dc6d5aa9\",\n" +
                "  \"uid\": \"886rtaqv61w\",\n" +
                "  \"user\": {\n" +
                "    \"email\": \"muy83-c1@muy.com.co\",\n" +
                "    \"firstName\": \"Cajero 1\",\n" +
                "    \"id\": 55318,\n" +
                "    \"lastName\": \"Muy Calle 83\",\n" +
                "    \"loyaltyStatus\": null,\n" +
                "    \"mobile\": \"3831234561\"\n" +
                "  },\n" +
                "  \"users\": [\n" +
                "    {\n" +
                "      \"email\": \"muy83-c1@muy.com.co\",\n" +
                "      \"firstName\": \"Cajero 1\",\n" +
                "      \"id\": 55318,\n" +
                "      \"lastName\": \"Muy Calle 83\",\n" +
                "      \"loyaltyStatus\": null,\n" +
                "      \"mobile\": \"3831234561\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Gson gson = new Gson();
        return gson.fromJson(json, OrderDetailDTO.class);
    }

    public OrderDetailDTO drinksAndDesserts() {

        String json = "{\n" +
                "    \"orderBrandId\": \"w4ut5yibg2s-1\",\n" +
                "    \"kitchenTicketNumber\": \"1 / 1\",\n" +
                "    \"isDelivery\": false,\n" +
                "    \"buyer\": {},\n" +
                "    \"brandId\": 1,\n" +
                "    \"brandName\": \"MUY\",\n" +
                "    \"co2Total\": 0,\n" +
                "    \"id\": 5468295,\n" +
                "    \"currency\": \"COP\",\n" +
                "    \"flowId\": 2,\n" +
                "    \"coupons\": [],\n" +
                "    \"deduction\": 0,\n" +
                "    \"discount\": 0,\n" +
                "    \"discounts\": [],\n" +
                "    \"deliveryTypeId\": 1,\n" +
                "    \"invoice\": \"0000\",\n" +
                "    \"notes\": \"POSV2\",\n" +
                "    \"originId\": 4,\n" +
                "    \"originName\": \"Autogestión\",\n" +
                "    \"orderNumber\": \"0000\",\n" +
                "    \"orderIntegrationId\": \"123456\",\n" +
                "    \"orderIntegrationCode\": \"12345\",\n" +
                "    \"orderIntegrationUser\": \"Integration\",\n" +
                "    \"orderIsIntegration\": false,\n" +
                "    \"paymentMethods\": [\n" +
                "      {\n" +
                "        \"discount\": 0,\n" +
                "        \"id\": 1,\n" +
                "        \"originId\": 10,\n" +
                "        \"subtotal\": 11944.4444,\n" +
                "        \"tax\": 955.5556,\n" +
                "        \"value\": 12900\n" +
                "      }\n" +
                "    ],\n" +
                "    \"printed\": false,\n" +
                "    \"products\": [\n" +
                "      {\n" +
                "        \"articleId\": 1412,\n" +
                "        \"articleTypeId\": 1,\n" +
                "        \"basePrice\": 12900,\n" +
                "        \"brandId\": 1,\n" +
                "        \"brandName\": \"MUY\",\n" +
                "        \"categoryId\": 3,\n" +
                "        \"categoryName\": \"Sugerido\",\n" +
                "        \"co2Total\": 0,\n" +
                "        \"deduction\": 0,\n" +
                "        \"discount\": 0,\n" +
                "        \"discounts\": [],\n" +
                "        \"groups\": [\n" +
                "          {\n" +
                "            \"id\": 10,\n" +
                "            \"name\": \"Ingredientes\",\n" +
                "            \"portions\": [\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 1350,\n" +
                "                \"name\": \"Arroz blanco\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 1350,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"12277329449820618983\",\n" +
                "                \"units\": 1,\n" +
                "                \"weight\": 148\n" +
                "              },\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 1371,\n" +
                "                \"name\": \"Fríjol rojo\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 1371,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"12277329449820619163\",\n" +
                "                \"units\": 1,\n" +
                "                \"weight\": 107\n" +
                "              },\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 1380,\n" +
                "                \"name\": \"Chicharrón\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 1380,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"12277329449820619172\",\n" +
                "                \"units\": 1,\n" +
                "                \"weight\": 30\n" +
                "              },\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 1374,\n" +
                "                \"name\": \"Carne molida a la criolla\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 1374,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"12277329449820619166\",\n" +
                "                \"units\": 1,\n" +
                "                \"weight\": 78\n" +
                "              },\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 1381,\n" +
                "                \"name\": \"Chorizo\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 1381,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"12277329449820619173\",\n" +
                "                \"units\": 1,\n" +
                "                \"weight\": 30\n" +
                "              },\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 264,\n" +
                "                \"name\": \"Plátano Maduro\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 264,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"17680480075943772449\",\n" +
                "                \"units\": 1,\n" +
                "                \"weight\": 50\n" +
                "              },\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 11,\n" +
                "                \"name\": \"Hogao\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 11,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"26257095296811284\",\n" +
                "                \"units\": 1,\n" +
                "                \"weight\": 40\n" +
                "              },\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 58,\n" +
                "                \"name\": \"Jugo\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 58,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"26257095296811331\",\n" +
                "                \"units\": 6,\n" +
                "                \"weight\": 266\n" +
                "              }\n" +
                "            ],\n" +
                "            \"removedPortions\": [],\n" +
                "            \"sku\": \"12277329449820618983\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 16,\n" +
                "            \"name\": \"Bebidas\",\n" +
                "            \"portions\": [\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 58,\n" +
                "                \"name\": \"Jugo de prueba regla\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 58,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"26257095296811331\",\n" +
                "                \"units\": 6,\n" +
                "                \"weight\": 266\n" +
                "              }\n" +
                "            ],\n" +
                "            \"removedPortions\": [],\n" +
                "            \"sku\": \"12277329449820618983\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"id\": 1437,\n" +
                "        \"image\": \"https://assets.robinfood.com/delivery/menu/products/133a0208-f365-4f21-a09f-b31d3e741e71.png\",\n" +
                "        \"brandMenuId\": 1,\n" +
                "        \"name\": \"Paisa\",\n" +
                "        \"quantity\": 1,\n" +
                "        \"sizeId\": 1,\n" +
                "        \"sizeName\": \"MUY\",\n" +
                "        \"sku\": \"12277544897157791797\",\n" +
                "        \"taxes\": [\n" +
                "          {\n" +
                "            \"familyTypeId\": 1,\n" +
                "            \"id\": 9359209,\n" +
                "            \"price\": 955.5556,\n" +
                "            \"taxTypeId\": 1,\n" +
                "            \"taxTypeName\": \"Impoconsumo CO\",\n" +
                "            \"value\": 8\n" +
                "          }\n" +
                "        ],\n" +
                "        \"unitPrice\": 12900,\n" +
                "        \"total\": 12900\n" +
                "      }\n" +
                "    ],\n" +
                "    \"statusId\": 1,\n" +
                "    \"statusName\": \"Pedido\",\n" +
                "    \"storeId\": 27,\n" +
                "    \"storeName\": \"RobinFood Plaza 83\",\n" +
                "    \"subtotal\": 11944.4444,\n" +
                "    \"tax\": 955.5556,\n" +
                "    \"total\": 12900,\n" +
                "    \"transactionId\": 2341129,\n" +
                "    \"uid\": \"w4ut5yibg2s\",\n" +
                "    \"user\": {\n" +
                "      \"email\": \"jvega.soft@gmail.com\",\n" +
                "      \"firstName\": \"Julian Andres\",\n" +
                "      \"id\": 674563,\n" +
                "      \"lastName\": \"Vega Parra\",\n" +
                "      \"loyaltyStatus\": 1,\n" +
                "      \"mobile\": \"3186248429\"\n" +
                "    },\n" +
                "    \"users\": [\n" +
                "      {\n" +
                "        \"email\": \"jvega.soft@gmail.com\",\n" +
                "        \"firstName\": \"Julian Andres\",\n" +
                "        \"id\": 674563,\n" +
                "        \"lastName\": \"Vega Parra\",\n" +
                "        \"loyaltyStatus\": 1,\n" +
                "        \"mobile\": \"3186248429\"\n" +
                "      }\n" +
                "    ]\n" +
                "}";

        Gson gson = new Gson();
        return gson.fromJson(json, OrderDetailDTO.class);
    }

    public OrderDetailDTO drinksAndDessertsNotGroup() {

        String json = "{\n" +
                "    \"orderBrandId\": \"w4ut5yibg2s-1\",\n" +
                "    \"kitchenTicketNumber\": \"1 / 1\",\n" +
                "    \"isDelivery\": false,\n" +
                "    \"buyer\": {},\n" +
                "    \"brandId\": 1,\n" +
                "    \"brandName\": \"MUY\",\n" +
                "    \"co2Total\": 0,\n" +
                "    \"id\": 5468295,\n" +
                "    \"currency\": \"COP\",\n" +
                "    \"flowId\": 2,\n" +
                "    \"coupons\": [],\n" +
                "    \"deduction\": 0,\n" +
                "    \"discount\": 0,\n" +
                "    \"discounts\": [],\n" +
                "    \"deliveryTypeId\": 1,\n" +
                "    \"invoice\": \"0000\",\n" +
                "    \"notes\": \"POSV2\",\n" +
                "    \"originId\": 4,\n" +
                "    \"originName\": \"Autogestión\",\n" +
                "    \"orderNumber\": \"0000\",\n" +
                "    \"orderIntegrationId\": \"123456\",\n" +
                "    \"orderIntegrationCode\": \"12345\",\n" +
                "    \"orderIntegrationUser\": \"Integration\",\n" +
                "    \"orderIsIntegration\": false,\n" +
                "    \"paymentMethods\": [\n" +
                "      {\n" +
                "        \"discount\": 0,\n" +
                "        \"id\": 1,\n" +
                "        \"originId\": 10,\n" +
                "        \"subtotal\": 11944.4444,\n" +
                "        \"tax\": 955.5556,\n" +
                "        \"value\": 12900\n" +
                "      }\n" +
                "    ],\n" +
                "    \"printed\": false,\n" +
                "    \"products\": [\n" +
                "      {\n" +
                "        \"articleId\": 1412,\n" +
                "        \"articleTypeId\": 1,\n" +
                "        \"basePrice\": 12900,\n" +
                "        \"brandId\": 1,\n" +
                "        \"brandName\": \"MUY\",\n" +
                "        \"categoryId\": 3,\n" +
                "        \"categoryName\": \"Bebidas\",\n" +
                "        \"co2Total\": 0,\n" +
                "        \"deduction\": 0,\n" +
                "        \"discount\": 0,\n" +
                "        \"discounts\": [],\n" +
                "        \"groups\": [],\n" +
                "        \"id\": 1437,\n" +
                "        \"image\": \"https://assets.robinfood.com/delivery/menu/products/133a0208-f365-4f21-a09f-b31d3e741e71.png\",\n" +
                "        \"brandMenuId\": 1,\n" +
                "        \"name\": \"Paisa\",\n" +
                "        \"quantity\": 1,\n" +
                "        \"sizeId\": 1,\n" +
                "        \"sizeName\": \"MUY\",\n" +
                "        \"sku\": \"12277544897157791797\",\n" +
                "        \"taxes\": [\n" +
                "          {\n" +
                "            \"familyTypeId\": 1,\n" +
                "            \"id\": 9359209,\n" +
                "            \"price\": 955.5556,\n" +
                "            \"taxTypeId\": 1,\n" +
                "            \"taxTypeName\": \"Impoconsumo CO\",\n" +
                "            \"value\": 8\n" +
                "          }\n" +
                "        ],\n" +
                "        \"unitPrice\": 12900,\n" +
                "        \"total\": 12900\n" +
                "      }\n" +
                "    ],\n" +
                "    \"statusId\": 1,\n" +
                "    \"statusName\": \"Pedido\",\n" +
                "    \"storeId\": 27,\n" +
                "    \"storeName\": \"RobinFood Plaza 83\",\n" +
                "    \"subtotal\": 11944.4444,\n" +
                "    \"tax\": 955.5556,\n" +
                "    \"total\": 12900,\n" +
                "    \"transactionId\": 2341129,\n" +
                "    \"uid\": \"w4ut5yibg2s\",\n" +
                "    \"user\": {\n" +
                "      \"email\": \"jvega.soft@gmail.com\",\n" +
                "      \"firstName\": \"Julian Andres\",\n" +
                "      \"id\": 674563,\n" +
                "      \"lastName\": \"Vega Parra\",\n" +
                "      \"loyaltyStatus\": 1,\n" +
                "      \"mobile\": \"3186248429\"\n" +
                "    },\n" +
                "    \"users\": [\n" +
                "      {\n" +
                "        \"email\": \"jvega.soft@gmail.com\",\n" +
                "        \"firstName\": \"Julian Andres\",\n" +
                "        \"id\": 674563,\n" +
                "        \"lastName\": \"Vega Parra\",\n" +
                "        \"loyaltyStatus\": 1,\n" +
                "        \"mobile\": \"3186248429\"\n" +
                "      }\n" +
                "    ]\n" +
                "}";

        Gson gson = new Gson();
        return gson.fromJson(json, OrderDetailDTO.class);
    }

    public OrderDetailDTO drinksAndDessertsGroupContains() {

        String json = "{\n" +
                "    \"orderBrandId\": \"w4ut5yibg2s-1\",\n" +
                "    \"kitchenTicketNumber\": \"1 / 1\",\n" +
                "    \"isDelivery\": false,\n" +
                "    \"buyer\": {},\n" +
                "    \"brandId\": 1,\n" +
                "    \"brandName\": \"MUY\",\n" +
                "    \"co2Total\": 0,\n" +
                "    \"id\": 5468295,\n" +
                "    \"currency\": \"COP\",\n" +
                "    \"flowId\": 2,\n" +
                "    \"coupons\": [],\n" +
                "    \"deduction\": 0,\n" +
                "    \"discount\": 0,\n" +
                "    \"discounts\": [],\n" +
                "    \"deliveryTypeId\": 1,\n" +
                "    \"invoice\": \"0000\",\n" +
                "    \"notes\": \"POSV2\",\n" +
                "    \"originId\": 4,\n" +
                "    \"originName\": \"Autogestión\",\n" +
                "    \"orderNumber\": \"0000\",\n" +
                "    \"orderIntegrationId\": \"123456\",\n" +
                "    \"orderIntegrationCode\": \"12345\",\n" +
                "    \"orderIntegrationUser\": \"Integration\",\n" +
                "    \"orderIsIntegration\": false,\n" +
                "    \"paymentMethods\": [\n" +
                "      {\n" +
                "        \"discount\": 0,\n" +
                "        \"id\": 1,\n" +
                "        \"originId\": 10,\n" +
                "        \"subtotal\": 11944.4444,\n" +
                "        \"tax\": 955.5556,\n" +
                "        \"value\": 12900\n" +
                "      }\n" +
                "    ],\n" +
                "    \"printed\": false,\n" +
                "    \"products\": [\n" +
                "      {\n" +
                "        \"articleId\": 1412,\n" +
                "        \"articleTypeId\": 1,\n" +
                "        \"basePrice\": 12900,\n" +
                "        \"brandId\": 1,\n" +
                "        \"brandName\": \"MUY\",\n" +
                "        \"categoryId\": 3,\n" +
                "        \"categoryName\": \"Sugerido\",\n" +
                "        \"co2Total\": 0,\n" +
                "        \"deduction\": 0,\n" +
                "        \"discount\": 0,\n" +
                "        \"discounts\": [],\n" +
                "        \"groups\": [\n" +
                "          {\n" +
                "            \"id\": 16,\n" +
                "            \"name\": \"Ingredientes\",\n" +
                "            \"portions\": [\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 1350,\n" +
                "                \"name\": \"Arroz blanco\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 1350,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"12277329449820618983\",\n" +
                "                \"units\": 1,\n" +
                "                \"weight\": 148\n" +
                "              },\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 1371,\n" +
                "                \"name\": \"Fríjol rojo\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 1371,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"12277329449820619163\",\n" +
                "                \"units\": 1,\n" +
                "                \"weight\": 107\n" +
                "              },\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 1380,\n" +
                "                \"name\": \"Chicharrón\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 1380,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"12277329449820619172\",\n" +
                "                \"units\": 1,\n" +
                "                \"weight\": 30\n" +
                "              },\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 1374,\n" +
                "                \"name\": \"Carne molida a la criolla\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 1374,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"12277329449820619166\",\n" +
                "                \"units\": 1,\n" +
                "                \"weight\": 78\n" +
                "              },\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 1381,\n" +
                "                \"name\": \"Chorizo\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 1381,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"12277329449820619173\",\n" +
                "                \"units\": 1,\n" +
                "                \"weight\": 30\n" +
                "              },\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 264,\n" +
                "                \"name\": \"Plátano Maduro\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 264,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"17680480075943772449\",\n" +
                "                \"units\": 1,\n" +
                "                \"weight\": 50\n" +
                "              },\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 11,\n" +
                "                \"name\": \"Hogao\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 11,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"26257095296811284\",\n" +
                "                \"units\": 1,\n" +
                "                \"weight\": 40\n" +
                "              },\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 58,\n" +
                "                \"name\": \"Jugo\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 58,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"26257095296811331\",\n" +
                "                \"units\": 6,\n" +
                "                \"weight\": 266\n" +
                "              }\n" +
                "            ],\n" +
                "            \"removedPortions\": [],\n" +
                "            \"sku\": \"12277329449820618983\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 16,\n" +
                "            \"name\": \"Bebidas\",\n" +
                "            \"portions\": [\n" +
                "              {\n" +
                "                \"addition\": false,\n" +
                "                \"discount\": 0,\n" +
                "                \"id\": 58,\n" +
                "                \"name\": \"Jugo de prueba regla\",\n" +
                "                \"price\": 0,\n" +
                "                \"parentId\": 58,\n" +
                "                \"quantity\": 1,\n" +
                "                \"sku\": \"26257095296811331\",\n" +
                "                \"units\": 6,\n" +
                "                \"weight\": 266\n" +
                "              }\n" +
                "            ],\n" +
                "            \"removedPortions\": [],\n" +
                "            \"sku\": \"12277329449820618983\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"id\": 1437,\n" +
                "        \"image\": \"https://assets.robinfood.com/delivery/menu/products/133a0208-f365-4f21-a09f-b31d3e741e71.png\",\n" +
                "        \"brandMenuId\": 1,\n" +
                "        \"name\": \"Paisa\",\n" +
                "        \"quantity\": 1,\n" +
                "        \"sizeId\": 1,\n" +
                "        \"sizeName\": \"MUY\",\n" +
                "        \"sku\": \"12277544897157791797\",\n" +
                "        \"taxes\": [\n" +
                "          {\n" +
                "            \"familyTypeId\": 1,\n" +
                "            \"id\": 9359209,\n" +
                "            \"price\": 955.5556,\n" +
                "            \"taxTypeId\": 1,\n" +
                "            \"taxTypeName\": \"Impoconsumo CO\",\n" +
                "            \"value\": 8\n" +
                "          }\n" +
                "        ],\n" +
                "        \"unitPrice\": 12900,\n" +
                "        \"total\": 12900\n" +
                "      }\n" +
                "    ],\n" +
                "    \"statusId\": 1,\n" +
                "    \"statusName\": \"Pedido\",\n" +
                "    \"storeId\": 27,\n" +
                "    \"storeName\": \"RobinFood Plaza 83\",\n" +
                "    \"subtotal\": 11944.4444,\n" +
                "    \"tax\": 955.5556,\n" +
                "    \"total\": 12900,\n" +
                "    \"transactionId\": 2341129,\n" +
                "    \"uid\": \"w4ut5yibg2s\",\n" +
                "    \"user\": {\n" +
                "      \"email\": \"jvega.soft@gmail.com\",\n" +
                "      \"firstName\": \"Julian Andres\",\n" +
                "      \"id\": 674563,\n" +
                "      \"lastName\": \"Vega Parra\",\n" +
                "      \"loyaltyStatus\": 1,\n" +
                "      \"mobile\": \"3186248429\"\n" +
                "    },\n" +
                "    \"users\": [\n" +
                "      {\n" +
                "        \"email\": \"jvega.soft@gmail.com\",\n" +
                "        \"firstName\": \"Julian Andres\",\n" +
                "        \"id\": 674563,\n" +
                "        \"lastName\": \"Vega Parra\",\n" +
                "        \"loyaltyStatus\": 1,\n" +
                "        \"mobile\": \"3186248429\"\n" +
                "      }\n" +
                "    ]\n" +
                "}";

        Gson gson = new Gson();
        return gson.fromJson(json, OrderDetailDTO.class);
    }

    public OrderDetailDTO suggestedProductsWithDrinksAndGroup() {

        String json = "{\n" +
                "  \"buyer\": {},\n" +
                "  \"brandId\": 6,\n" +
                "  \"brandName\": \"Tremendo burrito\",\n" +
                "  \"co2Total\": 200,\n" +
                "  \"currency\": \"COP\",\n" +
                "  \"coupons\": [],\n" +
                "  \"id\": 5473548,\n" +
                "  \"discount\": 0,\n" +
                "  \"discounts\": [],\n" +
                "  \"deliveryTypeId\": 1,\n" +
                "  \"deduction\": 0,\n" +
                "  \"flowId\": 5,\n" +
                "  \"foodCoins\": 0,\n" +
                "  \"invoice\": \"27934\",\n" +
                "  \"notes\": \"POSV2\",\n" +
                "  \"originId\": 10,\n" +
                "  \"originName\": \"POS V2\",\n" +
                "  \"orderNumber\": \"5304\",\n" +
                "  \"orderUuid\": \"589dd29e-5ff0-4dc9-aba5-0e8a09acb6b9\",\n" +
                "  \"orderIntegrationId\": \"123456\",\n" +
                "  \"orderIntegrationCode\": \"12345\",\n" +
                "  \"orderIntegrationUser\": \"Integration\",\n" +
                "  \"orderIsIntegration\": false,\n" +
                "  \"operationDate\": \"2023-03-08\",\n" +
                "  \"paymentMethods\": [\n" +
                "    {\n" +
                "      \"discount\": 0,\n" +
                "      \"id\": 1,\n" +
                "      \"originId\": 10,\n" +
                "      \"subtotal\": 16390.9352,\n" +
                "      \"tax\": 1311.2748,\n" +
                "      \"value\": 17900\n" +
                "    }\n" +
                "  ],\n" +
                "  \"posId\": 164,\n" +
                "  \"printed\": false,\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"articleId\": 351,\n" +
                "      \"articleName\": \"\",\n" +
                "      \"articleTypeId\": 1,\n" +
                "      \"basePrice\": 17900,\n" +
                "      \"brandId\": 6,\n" +
                "      \"brandName\": \"Tremendo burrito\",\n" +
                "      \"categoryId\": 28,\n" +
                "      \"categoryName\": \"Bebidas\",\n" +
                "      \"co2Total\": 100,\n" +
                "      \"deduction\": 0,\n" +
                "      \"discount\": 0,\n" +
                "      \"discounts\": [],\n" +
                "      \"groups\": [\n" +
                "        {\n" +
                "          \"id\": 70,\n" +
                "          \"name\": \"¿Quieres retirar algo de tu Tremendo?\",\n" +
                "          \"portions\": [\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 503,\n" +
                "              \"name\": \"Frijol negro\",\n" +
                "              \"parentId\": 98,\n" +
                "              \"price\": 0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470114\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 110,\n" +
                "              \"free\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 504,\n" +
                "              \"name\": \"Maíz tierno.\",\n" +
                "              \"parentId\": 734,\n" +
                "              \"price\": 0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470115\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 25,\n" +
                "              \"free\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 505,\n" +
                "              \"name\": \"Pico de gallo\",\n" +
                "              \"parentId\": 100,\n" +
                "              \"price\": 0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470116\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 25,\n" +
                "              \"free\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 506,\n" +
                "              \"name\": \"Sour cream\",\n" +
                "              \"parentId\": 37,\n" +
                "              \"price\": 0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470117\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 30,\n" +
                "              \"free\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 507,\n" +
                "              \"name\": \"Guacamole.\",\n" +
                "              \"parentId\": 99,\n" +
                "              \"price\": 0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276489554839470118\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 50,\n" +
                "              \"free\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1117,\n" +
                "              \"name\": \"Arroz con cilantro.\",\n" +
                "              \"parentId\": 733,\n" +
                "              \"price\": 0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"sku\": \"12276758091713741748\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 120,\n" +
                "              \"free\": 1\n" +
                "            }\n" +
                "          ],\n" +
                "          \"removedPortions\": [],\n" +
                "          \"sku\": \"12276489554839470114\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"id\": 449,\n" +
                "      \"image\": \"https://assets.robinfood.com/delivery/menu/products/a9265e9f-ac93-4400-89ec-2b446c0568f8.png\",\n" +
                "      \"brandMenuId\": 4,\n" +
                "      \"name\": \"MUY COMBO MUY Fresco + coca cola 400 ml + Galleta\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"sizeId\": 27,\n" +
                "      \"sizeName\": \"MUY\",\n" +
                "      \"sku\": \"12276489554839470163\",\n" +
                "      \"taxes\": [\n" +
                "        {\n" +
                "          \"familyTypeId\": 1,\n" +
                "          \"id\": 9370464,\n" +
                "          \"price\": 1325.9259,\n" +
                "          \"taxTypeId\": 1,\n" +
                "          \"taxTypeName\": \"Impoconsumo CO\",\n" +
                "          \"value\": 8\n" +
                "        }\n" +
                "      ],\n" +
                "      \"unitPrice\": 17900,\n" +
                "      \"total\": 17900\n" +
                "    }\n" +
                "  ],\n" +
                "  \"statusId\": 1,\n" +
                "  \"statusName\": \"Pedido\",\n" +
                "  \"storeId\": 29,\n" +
                "  \"storeName\": \"RobinFood Plaza 83\",\n" +
                "  \"subtotal\": 16574.0741,\n" +
                "  \"tax\": 1325.9259,\n" +
                "  \"total\": 17900,\n" +
                "  \"transactionId\": 2346001,\n" +
                "  \"transactionUuid\": \"bec65775-46ce-4620-8e1a-2f76dc6d5aa9\",\n" +
                "  \"uid\": \"886rtaqv61w\",\n" +
                "  \"user\": {\n" +
                "    \"email\": \"muy83-c1@muy.com.co\",\n" +
                "    \"firstName\": \"Cajero 1\",\n" +
                "    \"id\": 55318,\n" +
                "    \"lastName\": \"Muy Calle 83\",\n" +
                "    \"loyaltyStatus\": null,\n" +
                "    \"mobile\": \"3831234561\"\n" +
                "  },\n" +
                "  \"users\": [\n" +
                "    {\n" +
                "      \"email\": \"muy83-c1@muy.com.co\",\n" +
                "      \"firstName\": \"Cajero 1\",\n" +
                "      \"id\": 55318,\n" +
                "      \"lastName\": \"Muy Calle 83\",\n" +
                "      \"loyaltyStatus\": null,\n" +
                "      \"mobile\": \"3831234561\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Gson gson = new Gson();
        return gson.fromJson(json, OrderDetailDTO.class);
    }

}
