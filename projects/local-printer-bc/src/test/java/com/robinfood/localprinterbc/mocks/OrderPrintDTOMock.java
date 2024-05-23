package com.robinfood.localprinterbc.mocks;

import com.google.gson.Gson;
import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;

public class OrderPrintDTOMock {

    public OrderPrintDTO suggestedProducts() {

        String json = "{\n" +
                "        \"buyer\": {\n" +
                "            \"identifier\": null\n" +
                "        },\n" +
                "        \"brandId\": 6,\n" +
                "        \"brandName\": \"Tremendo burrito\",\n" +
                "        \"co2Total\": 200,\n" +
                "        \"currency\": \"COP\",\n" +
                "        \"coupons\": [],\n" +
                "        \"id\": 5473548,\n" +
                "        \"deduction\": 0,\n" +
                "        \"discount\": 0,\n" +
                "        \"discounts\": [],\n" +
                "        \"deliveryTypeId\": 1,\n" +
                "        \"drinksAndDesserts\": {\n" +
                "            \"title\": \"Bebidas y postres\",\n" +
                "            \"items\": []\n" +
                "        },\n" +
                "        \"flowId\": 5,\n" +
                "        \"invoice\": \"27934\",\n" +
                "        \"notes\": \"POSV2\",\n" +
                "        \"originId\": 10,\n" +
                "        \"originName\": \"POS V2\",\n" +
                "        \"orderNumber\": \"5304\",\n" +
                "        \"orderIsIntegration\": false,\n" +
                "        \"orderUuid\": \"589dd29e-5ff0-4dc9-aba5-0e8a09acb6b9\",\n" +
                "        \"paymentMethods\": [\n" +
                "            {\n" +
                "                \"discount\": 0.0,\n" +
                "                \"id\": 1,\n" +
                "                \"originId\": 10,\n" +
                "                \"subtotal\": 16390.9352,\n" +
                "                \"tax\": 1311.2748,\n" +
                "                \"value\": 17900\n" +
                "            }\n" +
                "        ],\n" +
                "        \"printed\": false,\n" +
                "        \"suggestedProducts\": [\n" +
                "            {\n" +
                "                \"articleId\": 351,\n" +
                "                \"articleName\": \"\",\n" +
                "                \"articleTypeId\": 1,\n" +
                "                \"basePrice\": 17900.0,\n" +
                "                \"brandId\": 6,\n" +
                "                \"brandName\": \"Tremendo burrito\",\n" +
                "                \"categoryId\": 28,\n" +
                "                \"categoryName\": \"Bowls y Burritos\",\n" +
                "                \"co2Total\": 100,\n" +
                "                \"deduction\": 0,\n" +
                "                \"discount\": 0,\n" +
                "                \"discounts\": [],\n" +
                "                \"groups\": [\n" +
                "                    {\n" +
                "                        \"id\": 70,\n" +
                "                        \"name\": \"¿Quieres retirar algo de tu Tremendo?\",\n" +
                "                        \"portions\": [\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 503,\n" +
                "                                \"name\": \"Frijol negro\",\n" +
                "                                \"parentId\": 98,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470114\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 110.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 504,\n" +
                "                                \"name\": \"Maíz tierno.\",\n" +
                "                                \"parentId\": 734,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470115\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 25.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 505,\n" +
                "                                \"name\": \"Pico de gallo\",\n" +
                "                                \"parentId\": 100,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470116\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 25.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 506,\n" +
                "                                \"name\": \"Sour cream\",\n" +
                "                                \"parentId\": 37,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470117\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 30.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 507,\n" +
                "                                \"name\": \"Guacamole.\",\n" +
                "                                \"parentId\": 99,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470118\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 50.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 1117,\n" +
                "                                \"name\": \"Arroz con cilantro.\",\n" +
                "                                \"parentId\": 733,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276758091713741748\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 120.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"removedPortions\": [],\n" +
                "                        \"sku\": \"12276489554839470114\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"id\": 449,\n" +
                "                \"image\": \"https://assets.robinfood.com/delivery/menu/products/a9265e9f-ac93-4400-89ec-2b446c0568f8.png\",\n" +
                "                \"brandMenuId\": 4,\n" +
                "                \"name\": \"MUY COMBO MUY Fresco + coca cola 400 ml + Galleta\",\n" +
                "                \"quantity\": 1,\n" +
                "                \"sizeId\": 27,\n" +
                "                \"sizeName\": \"MUY\",\n" +
                "                \"sku\": \"12276489554839470163\",\n" +
                "                \"taxes\": [\n" +
                "                    {\n" +
                "                        \"familyTypeId\": 1,\n" +
                "                        \"id\": 9370464,\n" +
                "                        \"price\": 1325.9259,\n" +
                "                        \"taxTypeName\": \"Impoconsumo CO\",\n" +
                "                        \"taxTypeId\": 1,\n" +
                "                        \"value\": 8.0\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"unitPrice\": 17900.0,\n" +
                "                \"total\": 17900.0,\n" +
                "                \"toAdd\": {\n" +
                "                    \"title\": \"Adicionar\",\n" +
                "                    \"items\": []\n" +
                "                },\n" +
                "                \"toChange\": {\n" +
                "                    \"title\": \"Cambiar\",\n" +
                "                    \"items\": []\n" +
                "                },\n" +
                "                \"toInclude\": {\n" +
                "                    \"title\": \"Ingredientes\",\n" +
                "                    \"items\": [\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 503,\n" +
                "                            \"name\": \"Frijol negro\",\n" +
                "                            \"parentId\": 98,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470114\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 110.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 504,\n" +
                "                            \"name\": \"Maíz tierno.\",\n" +
                "                            \"parentId\": 734,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470115\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 25.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 505,\n" +
                "                            \"name\": \"Pico de gallo\",\n" +
                "                            \"parentId\": 100,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470116\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 25.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 506,\n" +
                "                            \"name\": \"Sour cream\",\n" +
                "                            \"parentId\": 37,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470117\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 30.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 507,\n" +
                "                            \"name\": \"Guacamole.\",\n" +
                "                            \"parentId\": 99,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470118\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 50.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 1117,\n" +
                "                            \"name\": \"Arroz con cilantro.\",\n" +
                "                            \"parentId\": 733,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276758091713741748\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 120.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"toRemove\": {\n" +
                "                    \"title\": \"Quitar\",\n" +
                "                    \"items\": []\n" +
                "                }\n" +
                "            }\n" +
                "        ],\n" +
                "        \"statusId\": 1,\n" +
                "        \"statusName\": \"Pedido\",\n" +
                "        \"storeId\": 29,\n" +
                "        \"storeName\": \"RobinFood Plaza 83\",\n" +
                "        \"subtotal\": 16574.0741,\n" +
                "        \"tax\": 1325.9259,\n" +
                "        \"toGo\": {\n" +
                "            \"title\": \"** Para llevar **\",\n" +
                "            \"toGo\": false\n" +
                "        },\n" +
                "        \"total\": 17900,\n" +
                "        \"transactionId\": 2346001,\n" +
                "        \"uid\": \"886rtaqv61w\",\n" +
                "        \"userAndIdOrder\": {\n" +
                "            \"id\": \"(5304)\",\n" +
                "            \"user\": \"Cajero 1 Muy Calle 83\"\n" +
                "        },\n" +
                "        \"user\": {\n" +
                "            \"email\": \"muy83-c1@muy.com.co\",\n" +
                "            \"firstName\": \"Cajero 1\",\n" +
                "            \"id\": 55318,\n" +
                "            \"lastName\": \"Muy Calle 83\",\n" +
                "            \"loyaltyStatus\": null,\n" +
                "            \"mobile\": \"3831234561\"\n" +
                "        },\n" +
                "        \"users\": [\n" +
                "            {\n" +
                "                \"email\": \"muy83-c1@muy.com.co\",\n" +
                "                \"firstName\": \"Cajero 1\",\n" +
                "                \"id\": 55318,\n" +
                "                \"lastName\": \"Muy Calle 83\",\n" +
                "                \"loyaltyStatus\": null,\n" +
                "                \"mobile\": \"3831234561\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }";

        Gson gson = new Gson();
        return gson.fromJson(json, OrderPrintDTO.class);
    }

    public OrderPrintDTO toIncludeToRemoveToChange() {

        String json = "{\n" +
                "  \"buyer\": {},\n" +
                "  \"brandId\": 1,\n" +
                "  \"brandName\": \"MUY\",\n" +
                "  \"co2Total\": 50,\n" +
                "  \"currency\": \"COP\",\n" +
                "  \"coupons\": [],\n" +
                "  \"id\": 5474145,\n" +
                "  \"deduction\": 0,\n" +
                "  \"discount\": 4563.4,\n" +
                "  \"discounts\": [\n" +
                "    {\n" +
                "      \"id\": 2177023,\n" +
                "      \"typeId\": 1,\n" +
                "      \"value\": 4563.4\n" +
                "    }\n" +
                "  ],\n" +
                "  \"deliveryTypeId\": 1,\n" +
                "  \"drinksAndDesserts\": {\n" +
                "    \"title\": \"Bebidas y postres\",\n" +
                "    \"items\": []\n" +
                "  },\n" +
                "  \"flowId\": 1,\n" +
                "  \"invoice\": \"143035\",\n" +
                "  \"notes\": \"POSV2\",\n" +
                "  \"originId\": 2,\n" +
                "  \"originName\": \"RFApp\",\n" +
                "  \"orderNumber\": \"1878\",\n" +
                "  \"orderIsIntegration\": false,\n" +
                "  \"orderUuid\": \"f087edbf-a882-4d1a-80c4-a4ace2581f10\",\n" +
                "  \"paymentMethods\": [\n" +
                "    {\n" +
                "      \"discount\": 4563.5945,\n" +
                "      \"id\": 1,\n" +
                "      \"originId\": 10,\n" +
                "      \"subtotal\": 13208.9629,\n" +
                "      \"tax\": 691.6295,\n" +
                "      \"value\": 9387\n" +
                "    }\n" +
                "  ],\n" +
                "  \"printed\": false,\n" +
                "  \"suggestedProducts\": [\n" +
                "    {\n" +
                "      \"articleId\": 1412,\n" +
                "      \"articleName\": \"\",\n" +
                "      \"articleTypeId\": 1,\n" +
                "      \"basePrice\": 13900.0,\n" +
                "      \"brandId\": 1,\n" +
                "      \"brandName\": \"MUY\",\n" +
                "      \"categoryId\": 3,\n" +
                "      \"categoryName\": \"PLATOS PRINCIPALES\",\n" +
                "      \"co2Total\": 50,\n" +
                "      \"deduction\": 0,\n" +
                "      \"discount\": 4563.4,\n" +
                "      \"discounts\": [],\n" +
                "      \"groups\": [\n" +
                "        {\n" +
                "          \"id\": 10,\n" +
                "          \"name\": \"Ingrediente..\",\n" +
                "          \"portions\": [\n" +
                "            {\n" +
                "              \"addition\": true,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1398,\n" +
                "              \"name\": \"Arroz frito\",\n" +
                "              \"parentId\": 88,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"12277329449820618983\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 148.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1350,\n" +
                "              \"name\": \"Arroz blanco\",\n" +
                "              \"parentId\": 88,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"12277329449820618983\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 148.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1371,\n" +
                "              \"name\": \"Fríjol rojo\",\n" +
                "              \"parentId\": 97,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"12277329449820619163\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 107.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1380,\n" +
                "              \"name\": \"Chicharrón\",\n" +
                "              \"parentId\": 110,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"12277329449820619172\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 30.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1381,\n" +
                "              \"name\": \"Chorizo\",\n" +
                "              \"parentId\": 108,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"12277329449820619173\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 30.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 264,\n" +
                "              \"name\": \"Plátano Maduro\",\n" +
                "              \"parentId\": 104,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"17680480075943772449\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 50.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 58,\n" +
                "              \"name\": \"Jugo\",\n" +
                "              \"parentId\": 416,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"26257095296811331\",\n" +
                "              \"units\": 6,\n" +
                "              \"weight\": 266.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"changedPortion\": {\n" +
                "                \"id\": 33,\n" +
                "                \"name\": \"Salsa MUY\",\n" +
                "                \"parentId\": 103,\n" +
                "                \"sku\": \"26257095296811306\",\n" +
                "                \"unitId\": 1,\n" +
                "                \"unitNumber\": 15.0\n" +
                "              },\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 11,\n" +
                "              \"name\": \"Hogao\",\n" +
                "              \"parentId\": 2318,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"26257095296811306\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 15.0,\n" +
                "              \"symbol\": \"(c)\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"removedPortions\": [\n" +
                "            {\n" +
                "              \"id\": 1374,\n" +
                "              \"name\": \"Carne molida a la criolla\",\n" +
                "              \"symbol\": \"(-)\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"sku\": \"12277329449820618983\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"id\": 1437,\n" +
                "      \"image\": \"https://assets.robinfood.com/delivery/menu/products/133a0208-f365-4f21-a09f-b31d3e741e71.png\",\n" +
                "      \"brandMenuId\": 1,\n" +
                "      \"name\": \"MUY Paisa\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"sizeId\": 1,\n" +
                "      \"sizeName\": \"MUY\",\n" +
                "      \"sku\": \"12277544897157791797\",\n" +
                "      \"taxes\": [\n" +
                "        {\n" +
                "          \"familyTypeId\": 1,\n" +
                "          \"id\": 9371793,\n" +
                "          \"price\": 691.6,\n" +
                "          \"taxTypeName\": \"Impoconsumo CO\",\n" +
                "          \"taxTypeId\": 1,\n" +
                "          \"value\": 8.0\n" +
                "        }\n" +
                "      ],\n" +
                "      \"unitPrice\": 13900.0,\n" +
                "      \"total\": 9336.6,\n" +
                "      \"toAdd\": {\n" +
                "        \"hasItem\": false,\n" +
                "        \"title\": \"Adicionar\",\n" +
                "        \"items\": []\n" +
                "      },\n" +
                "      \"toChange\": {\n" +
                "        \"hasItem\": true,\n" +
                "        \"title\": \"Cambiar\",\n" +
                "        \"items\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"changedPortion\": {\n" +
                "              \"id\": 33,\n" +
                "              \"name\": \"Salsa MUY\",\n" +
                "              \"parentId\": 103,\n" +
                "              \"sku\": \"26257095296811306\",\n" +
                "              \"unitId\": 1,\n" +
                "              \"unitNumber\": 15.0\n" +
                "            },\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 11,\n" +
                "            \"name\": \"Hogao\",\n" +
                "            \"parentId\": 2318,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"quantityFree\": 1,\n" +
                "            \"sku\": \"26257095296811306\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 15.0,\n" +
                "            \"symbol\": \"(c)\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"toInclude\": {\n" +
                "        \"hasItem\": true,\n" +
                "        \"title\": \"Ingredientes\",\n" +
                "        \"items\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1350,\n" +
                "            \"name\": \"Arroz blanco\",\n" +
                "            \"parentId\": 88,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"quantityFree\": 1,\n" +
                "            \"sku\": \"12277329449820618983\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 148.0\n" +
                "          },\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1371,\n" +
                "            \"name\": \"Fríjol rojo\",\n" +
                "            \"parentId\": 97,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"quantityFree\": 1,\n" +
                "            \"sku\": \"12277329449820619163\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 107.0\n" +
                "          },\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1380,\n" +
                "            \"name\": \"Chicharrón\",\n" +
                "            \"parentId\": 110,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"quantityFree\": 1,\n" +
                "            \"sku\": \"12277329449820619172\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 30.0\n" +
                "          },\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1381,\n" +
                "            \"name\": \"Chorizo\",\n" +
                "            \"parentId\": 108,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"quantityFree\": 1,\n" +
                "            \"sku\": \"12277329449820619173\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 30.0\n" +
                "          },\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 264,\n" +
                "            \"name\": \"Plátano Maduro\",\n" +
                "            \"parentId\": 104,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"quantityFree\": 1,\n" +
                "            \"sku\": \"17680480075943772449\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 50.0\n" +
                "          },\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 58,\n" +
                "            \"name\": \"Jugo\",\n" +
                "            \"parentId\": 416,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"quantityFree\": 1,\n" +
                "            \"sku\": \"26257095296811331\",\n" +
                "            \"units\": 6,\n" +
                "            \"weight\": 266.0\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"toRemove\": {\n" +
                "        \"hasItem\": true,\n" +
                "        \"title\": \"Quitar\",\n" +
                "        \"items\": [\n" +
                "          {\n" +
                "            \"id\": 1374,\n" +
                "            \"name\": \"Carne molida a la criolla\",\n" +
                "            \"symbol\": \"(-)\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"statusId\": 1,\n" +
                "  \"statusName\": \"Pedido\",\n" +
                "  \"storeId\": 2,\n" +
                "  \"storeName\": \"MUY 79\",\n" +
                "  \"subtotal\": 13208.4,\n" +
                "  \"tax\": 691.6,\n" +
                "  \"toGo\": {\n" +
                "    \"title\": \"** Para llevar **\",\n" +
                "    \"toGo\": false\n" +
                "  },\n" +
                "  \"total\": 9336.6,\n" +
                "  \"transactionId\": 2346462,\n" +
                "  \"uid\": \"2b1ake3ff25d\",\n" +
                "  \"userAndIdOrder\": {\n" +
                "    \"id\": \"(1878)\",\n" +
                "    \"user\": \"Daniel Bedoya\"\n" +
                "  },\n" +
                "  \"user\": {\n" +
                "    \"email\": \"dbedoya-3147050365@example.muy.co\",\n" +
                "    \"firstName\": \"Daniel\",\n" +
                "    \"id\": 1083202,\n" +
                "    \"lastName\": \"Bedoya\",\n" +
                "    \"mobile\": \"3147050365\"\n" +
                "  },\n" +
                "  \"users\": [\n" +
                "    {\n" +
                "      \"email\": \"dbedoya-3147050365@example.muy.co\",\n" +
                "      \"firstName\": \"Daniel\",\n" +
                "      \"id\": 1083202,\n" +
                "      \"lastName\": \"Bedoya\",\n" +
                "      \"mobile\": \"3147050365\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Gson gson = new Gson();
        return gson.fromJson(json, OrderPrintDTO.class);
    }

    public OrderPrintDTO suggestedProductsGroupContains() {
        String json = "{\n" +
                "        \"buyer\": {\n" +
                "            \"identifier\": null\n" +
                "        },\n" +
                "        \"brandId\": 6,\n" +
                "        \"brandName\": \"Tremendo burrito\",\n" +
                "        \"co2Total\": 200,\n" +
                "        \"currency\": \"COP\",\n" +
                "        \"coupons\": [],\n" +
                "        \"id\": 5473548,\n" +
                "        \"deduction\": 0,\n" +
                "        \"discount\": 0,\n" +
                "        \"discounts\": [],\n" +
                "        \"deliveryTypeId\": 1,\n" +
                "        \"drinksAndDesserts\": {\n" +
                "            \"title\": \"Bebidas y postres\",\n" +
                "            \"items\": []\n" +
                "        },\n" +
                "        \"flowId\": 5,\n" +
                "        \"invoice\": \"27934\",\n" +
                "        \"notes\": \"POSV2\",\n" +
                "        \"originId\": 10,\n" +
                "        \"originName\": \"POS V2\",\n" +
                "        \"orderNumber\": \"5304\",\n" +
                "        \"orderIsIntegration\": false,\n" +
                "        \"orderUuid\": \"589dd29e-5ff0-4dc9-aba5-0e8a09acb6b9\",\n" +
                "        \"paymentMethods\": [\n" +
                "            {\n" +
                "                \"discount\": 0.0,\n" +
                "                \"id\": 1,\n" +
                "                \"originId\": 10,\n" +
                "                \"subtotal\": 16390.9352,\n" +
                "                \"tax\": 1311.2748,\n" +
                "                \"value\": 17900\n" +
                "            }\n" +
                "        ],\n" +
                "        \"printed\": false,\n" +
                "        \"suggestedProducts\": [\n" +
                "            {\n" +
                "                \"articleId\": 351,\n" +
                "                \"articleName\": \"\",\n" +
                "                \"articleTypeId\": 1,\n" +
                "                \"basePrice\": 17900.0,\n" +
                "                \"brandId\": 6,\n" +
                "                \"brandName\": \"Tremendo burrito\",\n" +
                "                \"categoryId\": 28,\n" +
                "                \"categoryName\": \"Bowls y Burritos\",\n" +
                "                \"co2Total\": 100,\n" +
                "                \"deduction\": 0,\n" +
                "                \"discount\": 0,\n" +
                "                \"discounts\": [],\n" +
                "                \"groups\": [\n" +
                "                    {\n" +
                "                        \"id\": 16,\n" +
                "                        \"name\": \"¿Quieres retirar algo de tu Tremendo?\",\n" +
                "                        \"portions\": [\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 503,\n" +
                "                                \"name\": \"Frijol negro\",\n" +
                "                                \"parentId\": 98,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470114\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 110.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 504,\n" +
                "                                \"name\": \"Maíz tierno.\",\n" +
                "                                \"parentId\": 734,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470115\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 25.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 505,\n" +
                "                                \"name\": \"Pico de gallo\",\n" +
                "                                \"parentId\": 100,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470116\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 25.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 506,\n" +
                "                                \"name\": \"Sour cream\",\n" +
                "                                \"parentId\": 37,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470117\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 30.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 507,\n" +
                "                                \"name\": \"Guacamole.\",\n" +
                "                                \"parentId\": 99,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470118\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 50.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 1117,\n" +
                "                                \"name\": \"Arroz con cilantro.\",\n" +
                "                                \"parentId\": 733,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276758091713741748\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 120.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"removedPortions\": [],\n" +
                "                        \"sku\": \"12276489554839470114\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"id\": 449,\n" +
                "                \"image\": \"https://assets.robinfood.com/delivery/menu/products/a9265e9f-ac93-4400-89ec-2b446c0568f8.png\",\n" +
                "                \"brandMenuId\": 4,\n" +
                "                \"name\": \"MUY COMBO MUY Fresco + coca cola 400 ml + Galleta\",\n" +
                "                \"quantity\": 1,\n" +
                "                \"sizeId\": 27,\n" +
                "                \"sizeName\": \"MUY\",\n" +
                "                \"sku\": \"12276489554839470163\",\n" +
                "                \"taxes\": [\n" +
                "                    {\n" +
                "                        \"familyTypeId\": 1,\n" +
                "                        \"id\": 9370464,\n" +
                "                        \"price\": 1325.9259,\n" +
                "                        \"taxTypeName\": \"Impoconsumo CO\",\n" +
                "                        \"taxTypeId\": 1,\n" +
                "                        \"value\": 8.0\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"unitPrice\": 17900.0,\n" +
                "                \"total\": 17900.0,\n" +
                "                \"toAdd\": {\n" +
                "                    \"title\": \"Adicionar\",\n" +
                "                    \"items\": []\n" +
                "                },\n" +
                "                \"toChange\": {\n" +
                "                    \"title\": \"Cambiar\",\n" +
                "                    \"items\": []\n" +
                "                },\n" +
                "                \"toInclude\": {\n" +
                "                    \"title\": \"Ingredientes\",\n" +
                "                    \"items\": [\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 503,\n" +
                "                            \"name\": \"Frijol negro\",\n" +
                "                            \"parentId\": 98,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470114\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 110.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 504,\n" +
                "                            \"name\": \"Maíz tierno.\",\n" +
                "                            \"parentId\": 734,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470115\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 25.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 505,\n" +
                "                            \"name\": \"Pico de gallo\",\n" +
                "                            \"parentId\": 100,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470116\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 25.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 506,\n" +
                "                            \"name\": \"Sour cream\",\n" +
                "                            \"parentId\": 37,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470117\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 30.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 507,\n" +
                "                            \"name\": \"Guacamole.\",\n" +
                "                            \"parentId\": 99,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470118\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 50.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 1117,\n" +
                "                            \"name\": \"Arroz con cilantro.\",\n" +
                "                            \"parentId\": 733,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276758091713741748\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 120.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"toRemove\": {\n" +
                "                    \"title\": \"Quitar\",\n" +
                "                    \"items\": []\n" +
                "                }\n" +
                "            }\n" +
                "        ],\n" +
                "        \"statusId\": 1,\n" +
                "        \"statusName\": \"Pedido\",\n" +
                "        \"storeId\": 29,\n" +
                "        \"storeName\": \"RobinFood Plaza 83\",\n" +
                "        \"subtotal\": 16574.0741,\n" +
                "        \"tax\": 1325.9259,\n" +
                "        \"toGo\": {\n" +
                "            \"title\": \"** Para llevar **\",\n" +
                "            \"toGo\": false\n" +
                "        },\n" +
                "        \"total\": 17900,\n" +
                "        \"transactionId\": 2346001,\n" +
                "        \"uid\": \"886rtaqv61w\",\n" +
                "        \"userAndIdOrder\": {\n" +
                "            \"id\": \"(5304)\",\n" +
                "            \"user\": \"Cajero 1 Muy Calle 83\"\n" +
                "        },\n" +
                "        \"user\": {\n" +
                "            \"email\": \"muy83-c1@muy.com.co\",\n" +
                "            \"firstName\": \"Cajero 1\",\n" +
                "            \"id\": 55318,\n" +
                "            \"lastName\": \"Muy Calle 83\",\n" +
                "            \"loyaltyStatus\": null,\n" +
                "            \"mobile\": \"3831234561\"\n" +
                "        },\n" +
                "        \"users\": [\n" +
                "            {\n" +
                "                \"email\": \"muy83-c1@muy.com.co\",\n" +
                "                \"firstName\": \"Cajero 1\",\n" +
                "                \"id\": 55318,\n" +
                "                \"lastName\": \"Muy Calle 83\",\n" +
                "                \"loyaltyStatus\": null,\n" +
                "                \"mobile\": \"3831234561\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }";

        Gson gson = new Gson();
        return gson.fromJson(json, OrderPrintDTO.class);
    }

    public OrderPrintDTO suggestedProductsNotGroups() {
        String json = "{\n" +
                "        \"buyer\": {\n" +
                "            \"identifier\": null\n" +
                "        },\n" +
                "        \"brandId\": 6,\n" +
                "        \"brandName\": \"Tremendo burrito\",\n" +
                "        \"co2Total\": 200,\n" +
                "        \"currency\": \"COP\",\n" +
                "        \"coupons\": [],\n" +
                "        \"id\": 5473548,\n" +
                "        \"deduction\": 0,\n" +
                "        \"discount\": 0,\n" +
                "        \"discounts\": [],\n" +
                "        \"deliveryTypeId\": 1,\n" +
                "        \"drinksAndDesserts\": {\n" +
                "            \"title\": \"Bebidas y postres\",\n" +
                "            \"items\": []\n" +
                "        },\n" +
                "        \"flowId\": 5,\n" +
                "        \"invoice\": \"27934\",\n" +
                "        \"notes\": \"POSV2\",\n" +
                "        \"originId\": 10,\n" +
                "        \"originName\": \"POS V2\",\n" +
                "        \"orderNumber\": \"5304\",\n" +
                "        \"orderIsIntegration\": false,\n" +
                "        \"orderUuid\": \"589dd29e-5ff0-4dc9-aba5-0e8a09acb6b9\",\n" +
                "        \"paymentMethods\": [\n" +
                "            {\n" +
                "                \"discount\": 0.0,\n" +
                "                \"id\": 1,\n" +
                "                \"originId\": 10,\n" +
                "                \"subtotal\": 16390.9352,\n" +
                "                \"tax\": 1311.2748,\n" +
                "                \"value\": 17900\n" +
                "            }\n" +
                "        ],\n" +
                "        \"printed\": false,\n" +
                "        \"suggestedProducts\": [\n" +
                "            {\n" +
                "                \"articleId\": 351,\n" +
                "                \"articleName\": \"\",\n" +
                "                \"articleTypeId\": 1,\n" +
                "                \"basePrice\": 17900.0,\n" +
                "                \"brandId\": 6,\n" +
                "                \"brandName\": \"Tremendo burrito\",\n" +
                "                \"categoryId\": 28,\n" +
                "                \"categoryName\": \"Bebidas\",\n" +
                "                \"co2Total\": 100,\n" +
                "                \"deduction\": 0,\n" +
                "                \"discount\": 0,\n" +
                "                \"discounts\": [],\n" +
                "                \"groups\": [\n" +
                "                ],\n" +
                "                \"id\": 449,\n" +
                "                \"image\": \"https://assets.robinfood.com/delivery/menu/products/a9265e9f-ac93-4400-89ec-2b446c0568f8.png\",\n" +
                "                \"brandMenuId\": 4,\n" +
                "                \"name\": \"MUY COMBO MUY Fresco + coca cola 400 ml + Galleta\",\n" +
                "                \"quantity\": 1,\n" +
                "                \"sizeId\": 27,\n" +
                "                \"sizeName\": \"MUY\",\n" +
                "                \"sku\": \"12276489554839470163\",\n" +
                "                \"taxes\": [\n" +
                "                    {\n" +
                "                        \"familyTypeId\": 1,\n" +
                "                        \"id\": 9370464,\n" +
                "                        \"price\": 1325.9259,\n" +
                "                        \"taxTypeName\": \"Impoconsumo CO\",\n" +
                "                        \"taxTypeId\": 1,\n" +
                "                        \"value\": 8.0\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"unitPrice\": 17900.0,\n" +
                "                \"total\": 17900.0,\n" +
                "                \"toAdd\": {\n" +
                "                    \"title\": \"Adicionar\",\n" +
                "                    \"items\": []\n" +
                "                },\n" +
                "                \"toChange\": {\n" +
                "                    \"title\": \"Cambiar\",\n" +
                "                    \"items\": []\n" +
                "                },\n" +
                "                \"toInclude\": {\n" +
                "                    \"title\": \"Ingredientes\",\n" +
                "                    \"items\": [\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 503,\n" +
                "                            \"name\": \"Frijol negro\",\n" +
                "                            \"parentId\": 98,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470114\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 110.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 504,\n" +
                "                            \"name\": \"Maíz tierno.\",\n" +
                "                            \"parentId\": 734,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470115\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 25.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 505,\n" +
                "                            \"name\": \"Pico de gallo\",\n" +
                "                            \"parentId\": 100,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470116\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 25.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 506,\n" +
                "                            \"name\": \"Sour cream\",\n" +
                "                            \"parentId\": 37,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470117\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 30.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 507,\n" +
                "                            \"name\": \"Guacamole.\",\n" +
                "                            \"parentId\": 99,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470118\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 50.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 1117,\n" +
                "                            \"name\": \"Arroz con cilantro.\",\n" +
                "                            \"parentId\": 733,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276758091713741748\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 120.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"toRemove\": {\n" +
                "                    \"title\": \"Quitar\",\n" +
                "                    \"items\": []\n" +
                "                }\n" +
                "            }\n" +
                "        ],\n" +
                "        \"statusId\": 1,\n" +
                "        \"statusName\": \"Pedido\",\n" +
                "        \"storeId\": 29,\n" +
                "        \"storeName\": \"RobinFood Plaza 83\",\n" +
                "        \"subtotal\": 16574.0741,\n" +
                "        \"tax\": 1325.9259,\n" +
                "        \"toGo\": {\n" +
                "            \"title\": \"** Para llevar **\",\n" +
                "            \"toGo\": false\n" +
                "        },\n" +
                "        \"total\": 17900,\n" +
                "        \"transactionId\": 2346001,\n" +
                "        \"uid\": \"886rtaqv61w\",\n" +
                "        \"userAndIdOrder\": {\n" +
                "            \"id\": \"(5304)\",\n" +
                "            \"user\": \"Cajero 1 Muy Calle 83\"\n" +
                "        },\n" +
                "        \"user\": {\n" +
                "            \"email\": \"muy83-c1@muy.com.co\",\n" +
                "            \"firstName\": \"Cajero 1\",\n" +
                "            \"id\": 55318,\n" +
                "            \"lastName\": \"Muy Calle 83\",\n" +
                "            \"loyaltyStatus\": null,\n" +
                "            \"mobile\": \"3831234561\"\n" +
                "        },\n" +
                "        \"users\": [\n" +
                "            {\n" +
                "                \"email\": \"muy83-c1@muy.com.co\",\n" +
                "                \"firstName\": \"Cajero 1\",\n" +
                "                \"id\": 55318,\n" +
                "                \"lastName\": \"Muy Calle 83\",\n" +
                "                \"loyaltyStatus\": null,\n" +
                "                \"mobile\": \"3831234561\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }";

        Gson gson = new Gson();
        return gson.fromJson(json, OrderPrintDTO.class);
    }


    public OrderPrintDTO suggestedDrinksWithGroup() {

        String json = "{\n" +
                "        \"buyer\": {\n" +
                "            \"identifier\": null\n" +
                "        },\n" +
                "        \"brandId\": 6,\n" +
                "        \"brandName\": \"Tremendo burrito\",\n" +
                "        \"co2Total\": 200,\n" +
                "        \"currency\": \"COP\",\n" +
                "        \"coupons\": [],\n" +
                "        \"id\": 5473548,\n" +
                "        \"deduction\": 0,\n" +
                "        \"discount\": 0,\n" +
                "        \"discounts\": [],\n" +
                "        \"deliveryTypeId\": 1,\n" +
                "        \"drinksAndDesserts\": {\n" +
                "            \"title\": \"Bebidas y postres\",\n" +
                "            \"items\": []\n" +
                "        },\n" +
                "        \"flowId\": 5,\n" +
                "        \"invoice\": \"27934\",\n" +
                "        \"notes\": \"POSV2\",\n" +
                "        \"originId\": 10,\n" +
                "        \"originName\": \"POS V2\",\n" +
                "        \"orderNumber\": \"5304\",\n" +
                "        \"orderIsIntegration\": false,\n" +
                "        \"orderUuid\": \"589dd29e-5ff0-4dc9-aba5-0e8a09acb6b9\",\n" +
                "        \"paymentMethods\": [\n" +
                "            {\n" +
                "                \"discount\": 0.0,\n" +
                "                \"id\": 1,\n" +
                "                \"originId\": 10,\n" +
                "                \"subtotal\": 16390.9352,\n" +
                "                \"tax\": 1311.2748,\n" +
                "                \"value\": 17900\n" +
                "            }\n" +
                "        ],\n" +
                "        \"printed\": false,\n" +
                "        \"suggestedProducts\": [\n" +
                "            {\n" +
                "                \"articleId\": 351,\n" +
                "                \"articleName\": \"\",\n" +
                "                \"articleTypeId\": 1,\n" +
                "                \"basePrice\": 17900.0,\n" +
                "                \"brandId\": 6,\n" +
                "                \"brandName\": \"Tremendo burrito\",\n" +
                "                \"categoryId\": 28,\n" +
                "                \"categoryName\": \"Bebidas\",\n" +
                "                \"co2Total\": 100,\n" +
                "                \"deduction\": 0,\n" +
                "                \"discount\": 0,\n" +
                "                \"discounts\": [],\n" +
                "                \"groups\": [\n" +
                "                    {\n" +
                "                        \"id\": 70,\n" +
                "                        \"name\": \"¿Quieres retirar algo de tu Tremendo?\",\n" +
                "                        \"portions\": [\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 503,\n" +
                "                                \"name\": \"Frijol negro\",\n" +
                "                                \"parentId\": 98,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470114\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 110.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 504,\n" +
                "                                \"name\": \"Maíz tierno.\",\n" +
                "                                \"parentId\": 734,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470115\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 25.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 505,\n" +
                "                                \"name\": \"Pico de gallo\",\n" +
                "                                \"parentId\": 100,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470116\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 25.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 506,\n" +
                "                                \"name\": \"Sour cream\",\n" +
                "                                \"parentId\": 37,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470117\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 30.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 507,\n" +
                "                                \"name\": \"Guacamole.\",\n" +
                "                                \"parentId\": 99,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276489554839470118\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 50.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"addition\": false,\n" +
                "                                \"discount\": 0,\n" +
                "                                \"id\": 1117,\n" +
                "                                \"name\": \"Arroz con cilantro.\",\n" +
                "                                \"parentId\": 733,\n" +
                "                                \"price\": 0.0,\n" +
                "                                \"quantity\": 1,\n" +
                "                                \"sku\": \"12276758091713741748\",\n" +
                "                                \"units\": 1,\n" +
                "                                \"weight\": 120.0,\n" +
                "                                \"symbol\": \"(-)\",\n" +
                "                                \"free\": 1\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"removedPortions\": [],\n" +
                "                        \"sku\": \"12276489554839470114\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"id\": 449,\n" +
                "                \"image\": \"https://assets.robinfood.com/delivery/menu/products/a9265e9f-ac93-4400-89ec-2b446c0568f8.png\",\n" +
                "                \"brandMenuId\": 4,\n" +
                "                \"name\": \"MUY COMBO MUY Fresco + coca cola 400 ml + Galleta\",\n" +
                "                \"quantity\": 1,\n" +
                "                \"sizeId\": 27,\n" +
                "                \"sizeName\": \"MUY\",\n" +
                "                \"sku\": \"12276489554839470163\",\n" +
                "                \"taxes\": [\n" +
                "                    {\n" +
                "                        \"familyTypeId\": 1,\n" +
                "                        \"id\": 9370464,\n" +
                "                        \"price\": 1325.9259,\n" +
                "                        \"taxTypeName\": \"Impoconsumo CO\",\n" +
                "                        \"taxTypeId\": 1,\n" +
                "                        \"value\": 8.0\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"unitPrice\": 17900.0,\n" +
                "                \"total\": 17900.0,\n" +
                "                \"toAdd\": {\n" +
                "                    \"title\": \"Adicionar\",\n" +
                "                    \"items\": []\n" +
                "                },\n" +
                "                \"toChange\": {\n" +
                "                    \"title\": \"Cambiar\",\n" +
                "                    \"items\": []\n" +
                "                },\n" +
                "                \"toInclude\": {\n" +
                "                    \"title\": \"Ingredientes\",\n" +
                "                    \"items\": [\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 503,\n" +
                "                            \"name\": \"Frijol negro\",\n" +
                "                            \"parentId\": 98,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470114\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 110.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 504,\n" +
                "                            \"name\": \"Maíz tierno.\",\n" +
                "                            \"parentId\": 734,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470115\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 25.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 505,\n" +
                "                            \"name\": \"Pico de gallo\",\n" +
                "                            \"parentId\": 100,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470116\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 25.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 506,\n" +
                "                            \"name\": \"Sour cream\",\n" +
                "                            \"parentId\": 37,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470117\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 30.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 507,\n" +
                "                            \"name\": \"Guacamole.\",\n" +
                "                            \"parentId\": 99,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276489554839470118\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 50.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"addition\": false,\n" +
                "                            \"discount\": 0,\n" +
                "                            \"id\": 1117,\n" +
                "                            \"name\": \"Arroz con cilantro.\",\n" +
                "                            \"parentId\": 733,\n" +
                "                            \"price\": 0.0,\n" +
                "                            \"quantity\": 1,\n" +
                "                            \"sku\": \"12276758091713741748\",\n" +
                "                            \"units\": 1,\n" +
                "                            \"weight\": 120.0,\n" +
                "                            \"symbol\": \"(-)\",\n" +
                "                            \"free\": 1\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"toRemove\": {\n" +
                "                    \"title\": \"Quitar\",\n" +
                "                    \"items\": []\n" +
                "                }\n" +
                "            }\n" +
                "        ],\n" +
                "        \"statusId\": 1,\n" +
                "        \"statusName\": \"Pedido\",\n" +
                "        \"storeId\": 29,\n" +
                "        \"storeName\": \"RobinFood Plaza 83\",\n" +
                "        \"subtotal\": 16574.0741,\n" +
                "        \"tax\": 1325.9259,\n" +
                "        \"toGo\": {\n" +
                "            \"title\": \"** Para llevar **\",\n" +
                "            \"toGo\": false\n" +
                "        },\n" +
                "        \"total\": 17900,\n" +
                "        \"transactionId\": 2346001,\n" +
                "        \"uid\": \"886rtaqv61w\",\n" +
                "        \"userAndIdOrder\": {\n" +
                "            \"id\": \"(5304)\",\n" +
                "            \"user\": \"Cajero 1 Muy Calle 83\"\n" +
                "        },\n" +
                "        \"user\": {\n" +
                "            \"email\": \"muy83-c1@muy.com.co\",\n" +
                "            \"firstName\": \"Cajero 1\",\n" +
                "            \"id\": 55318,\n" +
                "            \"lastName\": \"Muy Calle 83\",\n" +
                "            \"loyaltyStatus\": null,\n" +
                "            \"mobile\": \"3831234561\"\n" +
                "        },\n" +
                "        \"users\": [\n" +
                "            {\n" +
                "                \"email\": \"muy83-c1@muy.com.co\",\n" +
                "                \"firstName\": \"Cajero 1\",\n" +
                "                \"id\": 55318,\n" +
                "                \"lastName\": \"Muy Calle 83\",\n" +
                "                \"loyaltyStatus\": null,\n" +
                "                \"mobile\": \"3831234561\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }";

        Gson gson = new Gson();
        return gson.fromJson(json, OrderPrintDTO.class);
    }

    public OrderPrintDTO toIncludeToRemoveToChange1() {

        String json = "{\n" +
                "  \"buyer\": {},\n" +
                "  \"brandId\": 1,\n" +
                "  \"brandName\": \"MUY\",\n" +
                "  \"co2Total\": 50,\n" +
                "  \"currency\": \"COP\",\n" +
                "  \"coupons\": [],\n" +
                "  \"id\": 5474145,\n" +
                "  \"deduction\": 0,\n" +
                "  \"discount\": 4563.4,\n" +
                "  \"discounts\": [\n" +
                "    {\n" +
                "      \"id\": 2177023,\n" +
                "      \"typeId\": 1,\n" +
                "      \"value\": 4563.4\n" +
                "    }\n" +
                "  ],\n" +
                "  \"deliveryTypeId\": 1,\n" +
                "  \"drinksAndDesserts\": {\n" +
                "    \"title\": \"Bebidas y postres\",\n" +
                "    \"items\": []\n" +
                "  },\n" +
                "  \"flowId\": 1,\n" +
                "  \"invoice\": \"143035\",\n" +
                "  \"notes\": \"POSV2\",\n" +
                "  \"originId\": 2,\n" +
                "  \"originName\": \"RFApp\",\n" +
                "  \"orderNumber\": \"1878\",\n" +
                "  \"orderIsIntegration\": false,\n" +
                "  \"orderUuid\": \"f087edbf-a882-4d1a-80c4-a4ace2581f10\",\n" +
                "  \"paymentMethods\": [\n" +
                "    {\n" +
                "      \"discount\": 4563.5945,\n" +
                "      \"id\": 1,\n" +
                "      \"originId\": 10,\n" +
                "      \"subtotal\": 13208.9629,\n" +
                "      \"tax\": 691.6295,\n" +
                "      \"value\": 9387\n" +
                "    }\n" +
                "  ],\n" +
                "  \"printed\": false,\n" +
                "  \"suggestedProducts\": [\n" +
                "    {\n" +
                "      \"articleId\": 1412,\n" +
                "      \"articleName\": \"\",\n" +
                "      \"articleTypeId\": 1,\n" +
                "      \"basePrice\": 13900.0,\n" +
                "      \"brandId\": 1,\n" +
                "      \"brandName\": \"MUY\",\n" +
                "      \"categoryId\": 3,\n" +
                "      \"categoryName\": \"PLATOS PRINCIPALES\",\n" +
                "      \"co2Total\": 50,\n" +
                "      \"deduction\": 0,\n" +
                "      \"discount\": 4563.4,\n" +
                "      \"discounts\": [],\n" +
                "      \"groups\": [\n" +
                "        {\n" +
                "          \"id\": 10,\n" +
                "          \"name\": \"Ingrediente..\",\n" +
                "          \"portions\": [\n" +
                "            {\n" +
                "              \"addition\": true,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1398,\n" +
                "              \"name\": \"Arroz frito\",\n" +
                "              \"parentId\": 88,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"12277329449820618983\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 148.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1350,\n" +
                "              \"name\": \"Arroz blanco\",\n" +
                "              \"parentId\": 88,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"12277329449820618983\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 148.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1371,\n" +
                "              \"name\": \"Fríjol rojo\",\n" +
                "              \"parentId\": 97,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"12277329449820619163\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 107.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1380,\n" +
                "              \"name\": \"Chicharrón\",\n" +
                "              \"parentId\": 110,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"12277329449820619172\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 30.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 1381,\n" +
                "              \"name\": \"Chorizo\",\n" +
                "              \"parentId\": 108,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"12277329449820619173\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 30.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 264,\n" +
                "              \"name\": \"Plátano Maduro\",\n" +
                "              \"parentId\": 104,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"17680480075943772449\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 50.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 58,\n" +
                "              \"name\": \"Jugo\",\n" +
                "              \"parentId\": 416,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"26257095296811331\",\n" +
                "              \"units\": 6,\n" +
                "              \"weight\": 266.0\n" +
                "            },\n" +
                "            {\n" +
                "              \"addition\": false,\n" +
                "              \"changedPortion\": {\n" +
                "                \"id\": 33,\n" +
                "                \"name\": \"Salsa MUY\",\n" +
                "                \"parentId\": 103,\n" +
                "                \"sku\": \"26257095296811306\",\n" +
                "                \"unitId\": 1,\n" +
                "                \"unitNumber\": 15.0\n" +
                "              },\n" +
                "              \"discount\": 0,\n" +
                "              \"id\": 11,\n" +
                "              \"name\": \"Hogao\",\n" +
                "              \"parentId\": 2318,\n" +
                "              \"price\": 0.0,\n" +
                "              \"quantity\": 1,\n" +
                "              \"quantityFree\": 1,\n" +
                "              \"sku\": \"26257095296811306\",\n" +
                "              \"units\": 1,\n" +
                "              \"weight\": 15.0,\n" +
                "              \"symbol\": \"(c)\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"removedPortions\": [\n" +
                "            {\n" +
                "              \"id\": 1374,\n" +
                "              \"name\": \"Carne molida a la criolla\",\n" +
                "              \"symbol\": \"(-)\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"sku\": \"12277329449820618983\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"id\": 1437,\n" +
                "      \"image\": \"https://assets.robinfood.com/delivery/menu/products/133a0208-f365-4f21-a09f-b31d3e741e71.png\",\n" +
                "      \"brandMenuId\": 1,\n" +
                "      \"name\": \"MUY Paisa\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"sizeId\": 1,\n" +
                "      \"sizeName\": \"MUY\",\n" +
                "      \"sku\": \"12277544897157791797\",\n" +
                "      \"taxes\": [\n" +
                "        {\n" +
                "          \"familyTypeId\": 1,\n" +
                "          \"id\": 9371793,\n" +
                "          \"price\": 691.6,\n" +
                "          \"taxTypeName\": \"Impoconsumo CO\",\n" +
                "          \"taxTypeId\": 1,\n" +
                "          \"value\": 8.0\n" +
                "        }\n" +
                "      ],\n" +
                "      \"unitPrice\": 13900.0,\n" +
                "      \"total\": 9336.6,\n" +
                "      \"toAdd\": {\n" +
                "        \"hasItem\": false,\n" +
                "        \"title\": \"Adicionar\",\n" +
                "        \"items\": []\n" +
                "      },\n" +
                "      \"toChange\": {\n" +
                "        \"hasItem\": true,\n" +
                "        \"title\": \"Cambiar\",\n" +
                "        \"items\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"changedPortion\": {\n" +
                "              \"id\": 33,\n" +
                "              \"name\": \"Salsa MUY\",\n" +
                "              \"parentId\": 103,\n" +
                "              \"sku\": \"26257095296811306\",\n" +
                "              \"unitId\": 1,\n" +
                "              \"unitNumber\": 15.0\n" +
                "            },\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 11,\n" +
                "            \"name\": \"Hogao\",\n" +
                "            \"parentId\": 2318,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"quantityFree\": 1,\n" +
                "            \"sku\": \"26257095296811306\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 15.0,\n" +
                "            \"symbol\": \"(c)\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"toInclude\": {\n" +
                "        \"hasItem\": true,\n" +
                "        \"title\": \"Ingredientes\",\n" +
                "        \"items\": [\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1350,\n" +
                "            \"name\": \"Arroz blanco\",\n" +
                "            \"parentId\": 88,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"quantityFree\": 1,\n" +
                "            \"sku\": \"12277329449820618983\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 148.0\n" +
                "          },\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1371,\n" +
                "            \"name\": \"Fríjol rojo\",\n" +
                "            \"parentId\": 97,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"quantityFree\": 1,\n" +
                "            \"sku\": \"12277329449820619163\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 107.0\n" +
                "          },\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1380,\n" +
                "            \"name\": \"Chicharrón\",\n" +
                "            \"parentId\": 110,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"quantityFree\": 1,\n" +
                "            \"sku\": \"12277329449820619172\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 30.0\n" +
                "          },\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 1381,\n" +
                "            \"name\": \"Chorizo\",\n" +
                "            \"parentId\": 108,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"quantityFree\": 1,\n" +
                "            \"sku\": \"12277329449820619173\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 30.0\n" +
                "          },\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 264,\n" +
                "            \"name\": \"Plátano Maduro\",\n" +
                "            \"parentId\": 104,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"quantityFree\": 1,\n" +
                "            \"sku\": \"17680480075943772449\",\n" +
                "            \"units\": 1,\n" +
                "            \"weight\": 50.0\n" +
                "          },\n" +
                "          {\n" +
                "            \"addition\": false,\n" +
                "            \"discount\": 0,\n" +
                "            \"id\": 58,\n" +
                "            \"name\": \"Jugo\",\n" +
                "            \"parentId\": 416,\n" +
                "            \"price\": 0.0,\n" +
                "            \"quantity\": 1,\n" +
                "            \"quantityFree\": 1,\n" +
                "            \"sku\": \"26257095296811331\",\n" +
                "            \"units\": 6,\n" +
                "            \"weight\": 266.0\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"statusId\": 1,\n" +
                "  \"statusName\": \"Pedido\",\n" +
                "  \"storeId\": 2,\n" +
                "  \"storeName\": \"MUY 79\",\n" +
                "  \"subtotal\": 13208.4,\n" +
                "  \"tax\": 691.6,\n" +
                "  \"toGo\": {\n" +
                "    \"title\": \"** Para llevar **\",\n" +
                "    \"toGo\": false\n" +
                "  },\n" +
                "  \"total\": 9336.6,\n" +
                "  \"transactionId\": 2346462,\n" +
                "  \"uid\": \"2b1ake3ff25d\",\n" +
                "  \"userAndIdOrder\": {\n" +
                "    \"id\": \"(1878)\",\n" +
                "    \"user\": \"Daniel Bedoya\"\n" +
                "  },\n" +
                "  \"user\": {\n" +
                "    \"email\": \"dbedoya-3147050365@example.muy.co\",\n" +
                "    \"firstName\": \"Daniel\",\n" +
                "    \"id\": 1083202,\n" +
                "    \"lastName\": \"Bedoya\",\n" +
                "    \"mobile\": \"3147050365\"\n" +
                "  },\n" +
                "  \"users\": [\n" +
                "    {\n" +
                "      \"email\": \"dbedoya-3147050365@example.muy.co\",\n" +
                "      \"firstName\": \"Daniel\",\n" +
                "      \"id\": 1083202,\n" +
                "      \"lastName\": \"Bedoya\",\n" +
                "      \"mobile\": \"3147050365\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Gson gson = new Gson();
        return gson.fromJson(json, OrderPrintDTO.class);
    }

}
