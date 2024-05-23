'use strict';

const mockEventSimple = {
    Records: [
        {
            messageId: "3cb9d4cb-840d-4f82-b1ef-54d50c76fdc7",
            receiptHandle: "AQEBEq5vBStApOTBYdfZLIrNTTQxg1oGGbRXEdJ6yggpxKLVC/ScCJaoonmDVAOoqKYPMUHj1fYSOzuLRRsPjoFC4qfFbVZiiu9ObqgE5ii4Shpla0qz1ucQu2+GA02RLMHcuNMlefo92JHC3/qfZ1Qw+j96Bl4OY6WYFU9X3WHPp4jvs2LXRAZKW5nOI5XlyiRsqJpUyGqbnrHDMTOdxCAHwkzW6HQsgTfYHkxbnSm1FXobN2LielQDevwS22Os4cR81+alW6RPeVFbvZ24cZJ1+Ml40Sc4pteeR1kWPmX7Sf8=",
            body: "{\n  \"brandId\": 8,\n  \"brandName\": \"Pecado Natural\",\n  \"co2Total\": 0,\n  \"currency\": \"BRL\",\n  \"coupons\": [],\n  \"id\": 3360031,\n  \"discount\": 0,\n  \"discounts\": [],\n  \"deliveryTypeId\": 1,\n  \"flowId\": 5,\n  \"invoice\": \"165\",\n  \"notes\": \"POSV2\",\n  \"originId\": 1,\n  \"originName\": \"POS\",\n  \"orderNumber\": \"7\",\n  \"orderIsIntegration\": false,\n  \"paymentMethods\": [\n    {\n      \"discount\": 0,\n      \"id\": 1,\n      \"originId\": 1,\n      \"subtotal\": 26.9,\n      \"tax\": 4.6426,\n      \"value\": 26.9\n    }\n  ],\n  \"posId\": 422,\n  \"printed\": false,\n  \"products\": [\n    {\n      \"articleId\": 927,\n      \"articleName\": \"\",\n      \"articleTypeId\": 1,\n      \"basePrice\": 20.9,\n      \"brandId\": 8,\n      \"brandName\": \"Pecado Natural\",\n      \"categoryId\": 35,\n      \"categoryName\": \"Personalizado\",\n      \"co2Total\": 0,\n      \"discounts\": [],\n      \"groups\": [\n        {\n          \"id\": 109,\n          \"name\": \"Proteína\",\n          \"portions\": [\n            {\n              \"addition\": false,\n              \"discount\": 0,\n              \"id\": 656,\n              \"name\": \"Almôndegas vegetarianas 140 g\",\n              \"parentId\": 1095,\n              \"price\": 0,\n              \"quantity\": 1,\n              \"sku\": \"12276489554839470385\",\n              \"units\": 1,\n              \"weight\": 140,\n              \"free\": 1\n            }\n          ],\n          \"removedPortions\": [],\n          \"sku\": \"12276489554839470385\"\n        },\n        {\n          \"id\": 228,\n          \"name\": \"Escolha o molho\",\n          \"portions\": [\n            {\n              \"addition\": false,\n              \"discount\": 0,\n              \"id\": 1021,\n              \"name\": \"Molho Caesar 70 ml\",\n              \"parentId\": 2281,\n              \"price\": 0,\n              \"quantity\": 1,\n              \"sku\": \"12276758091713741530\",\n              \"units\": 6,\n              \"weight\": 70,\n              \"free\": 1\n            }\n          ],\n          \"removedPortions\": [],\n          \"sku\": \"12276758091713741530\"\n        },\n        {\n          \"id\": 227,\n          \"name\": \"Escolha os complementos\",\n          \"portions\": [\n            {\n              \"addition\": false,\n              \"discount\": 0,\n              \"id\": 1014,\n              \"name\": \"Alho frito 2 g\",\n              \"parentId\": 2215,\n              \"price\": 0,\n              \"quantity\": 1,\n              \"sku\": \"12276758091713741523\",\n              \"units\": 1,\n              \"weight\": 2,\n              \"free\": 1\n            }\n          ],\n          \"removedPortions\": [],\n          \"sku\": \"12276758091713741523\"\n        },\n        {\n          \"id\": 230,\n          \"name\": \"Deseja talheres?\",\n          \"portions\": [\n            {\n              \"addition\": false,\n              \"discount\": 0,\n              \"id\": 1022,\n              \"name\": \"Sim, por favor, enviar talheres\",\n              \"parentId\": 1299,\n              \"price\": 0,\n              \"quantity\": 1,\n              \"sku\": \"12276758091713741539\",\n              \"units\": 9,\n              \"weight\": 1,\n              \"free\": 1\n            }\n          ],\n          \"removedPortions\": [],\n          \"sku\": \"12276758091713741539\"\n        },\n        {\n          \"id\": 226,\n          \"name\": \"Escolha os vegetais\",\n          \"portions\": [\n            {\n              \"addition\": false,\n              \"discount\": 0,\n              \"id\": 1008,\n              \"name\": \"Beterraba assada 40 g\",\n              \"parentId\": 2274,\n              \"price\": 0,\n              \"quantity\": 1,\n              \"sku\": \"12276758091713741517\",\n              \"units\": 1,\n              \"weight\": 40,\n              \"free\": 1\n            }\n          ],\n          \"removedPortions\": [],\n          \"sku\": \"12276758091713741517\"\n        },\n        {\n          \"id\": 107,\n          \"name\": \"Base\",\n          \"portions\": [\n            {\n              \"addition\": false,\n              \"discount\": 0,\n              \"id\": 999,\n              \"name\": \"Alface americana laminada 80 g\",\n              \"parentId\": 2285,\n              \"price\": 0,\n              \"quantity\": 1,\n              \"sku\": \"12276758091713741508\",\n              \"units\": 1,\n              \"weight\": 80,\n              \"free\": 1\n            }\n          ],\n          \"removedPortions\": [],\n          \"sku\": \"12276758091713741508\"\n        }\n      ],\n      \"id\": 1009,\n      \"image\": \"https://assets.robinfood.com/delivery/menu/products/e314a5d1-b1ea-4ce3-86c6-da99908a2a71.jpg\",\n      \"brandMenuId\": 13,\n      \"name\": \"Crie sua salada\",\n      \"quantity\": 1,\n      \"sizeId\": 21,\n      \"sizeName\": \"\",\n      \"sku\": \"9034800139121721348\",\n      \"taxes\": [\n        {\n          \"familyTypeId\": 3,\n          \"id\": 3549513,\n          \"price\": 0.3448,\n          \"taxTypeId\": 4,\n          \"taxTypeName\": \"PIS\",\n          \"value\": 1.65\n        },\n        {\n          \"familyTypeId\": 7,\n          \"id\": 3549514,\n          \"price\": 1.5884,\n          \"taxTypeId\": 5,\n          \"taxTypeName\": \"COFINS\",\n          \"value\": 7.6\n        },\n        {\n          \"familyTypeId\": 11,\n          \"id\": 3549515,\n          \"price\": 1.7556,\n          \"taxTypeId\": 3,\n          \"taxTypeName\": \"ICMS\",\n          \"value\": 8.4\n        }\n      ],\n      \"unitPrice\": 20.9\n    },\n    {\n      \"articleId\": 941,\n      \"articleName\": \"\",\n      \"articleTypeId\": 1,\n      \"basePrice\": 2.5,\n      \"brandId\": 13,\n      \"brandName\": \"Pecado Natural\",\n      \"categoryId\": 36,\n      \"categoryName\": \"Sugerido\",\n      \"co2Total\": 0,\n      \"discounts\": [],\n      \"groups\": [],\n      \"id\": 1024,\n      \"image\": \"https://assets.robinfood.com/delivery/menu/products/e314a5d1-b1ea-4ce3-86c6-da99908a2a71.jpg\",\n      \"brandMenuId\": 13,\n      \"name\": \"Coca Cola Regular 350ml\",\n      \"quantity\": 1,\n      \"sizeId\": 21,\n      \"sizeName\": \"\",\n      \"sku\": \"12276758091713741484\",\n      \"taxes\": [\n        {\n          \"familyTypeId\": 6,\n          \"id\": 3549516,\n          \"price\": 0,\n          \"taxTypeId\": 4,\n          \"taxTypeName\": \"PIS\",\n          \"value\": 0\n        },\n        {\n          \"familyTypeId\": 10,\n          \"id\": 3549517,\n          \"price\": 0,\n          \"taxTypeId\": 5,\n          \"taxTypeName\": \"COFINS\",\n          \"value\": 0\n        },\n        {\n          \"familyTypeId\": 15,\n          \"id\": 3549518,\n          \"price\": 0,\n          \"taxTypeId\": 3,\n          \"taxTypeName\": \"ICMS\",\n          \"value\": 0\n        }\n      ],\n      \"unitPrice\": 2.5\n    },\n    {\n      \"articleId\": 942,\n      \"articleName\": \"\",\n      \"articleTypeId\": 1,\n      \"basePrice\": 3.5,\n      \"brandId\": 13,\n      \"brandName\": \"Pecado Natural\",\n      \"categoryId\": 36,\n      \"categoryName\": \"Sugerido\",\n      \"co2Total\": 0,\n      \"discounts\": [],\n      \"groups\": [],\n      \"id\": 1025,\n      \"image\": \"https://assets.robinfood.com/delivery/menu/products/e314a5d1-b1ea-4ce3-86c6-da99908a2a71.jpg\",\n      \"brandMenuId\": 13,\n      \"name\": \"Coca Cola Regular 350ml\",\n      \"quantity\": 1,\n      \"sizeId\": 21,\n      \"sizeName\": \"\",\n      \"sku\": \"12276758091713741485\",\n      \"taxes\": [\n        {\n          \"familyTypeId\": 4,\n          \"id\": 3549519,\n          \"price\": 0.0578,\n          \"taxTypeId\": 4,\n          \"taxTypeName\": \"PIS\",\n          \"value\": 1.65\n        },\n        {\n          \"familyTypeId\": 8,\n          \"id\": 3549520,\n          \"price\": 0.266,\n          \"taxTypeId\": 5,\n          \"taxTypeName\": \"COFINS\",\n          \"value\": 7.6\n        },\n        {\n          \"familyTypeId\": 13,\n          \"id\": 3549521,\n          \"price\": 0.63,\n          \"taxTypeId\": 3,\n          \"taxTypeName\": \"ICMS\",\n          \"value\": 18\n        }\n      ],\n      \"unitPrice\": 3.5\n    }\n  ],\n  \"statusId\": 1,\n  \"statusName\": \"Pedido\",\n  \"storeId\": 96,\n  \"storeName\": \"MUY Dino Bueno\",\n  \"subtotal\": 26.9,\n  \"tax\": 4.6426,\n  \"total\": 26.9,\n  \"transactionId\": 620010,\n  \"uid\": \"1xw8yhkhhz350\",\n  \"user\": {\n    \"email\": \"jlozano@muy.com.co\",\n    \"firstName\": \"Jorge\",\n    \"id\": 435889,\n    \"lastName\": \"Test\",\n    \"loyaltyStatus\": 3,\n    \"mobile\": \"3831234561\"\n  },\n  \"users\": [\n    {\n      \"email\": \"jlozano@muy.com.co\",\n      \"firstName\": \"Jorge\",\n      \"id\": 435889,\n      \"lastName\": \"Test\",\n      \"loyaltyStatus\": 3,\n      \"mobile\": \"3831234561\"\n    }\n  ]\n}",
            attributes: {
                ApproximateReceiveCount: "1",
                SentTimestamp: "1651158491198",
                SequenceNumber: "18869440647456240128",
                MessageGroupId: "1234",
                SenderId: "AROA4AB3664KMSIGLUP72:jairo@robinfood.com",
                MessageDeduplicationId: "fgadfgadfgsd12347",
                ApproximateFirstReceiveTimestamp: "1651158491198"
            },
            messageAttributes: {},
            md5OfBody: "d2acad5d69b6995be506e2ed12e3b42b",
            eventSource: "aws:sqs",
            eventSourceARN: "arn:aws:sqs:us-east-2:824759482132:Rf_Co_OrderBilling_Queue.fifo",
            awsRegion: "us-east-2"
        }
    ]
}

const mockEventConnect = {
    requestContext: {
        routeKey: "$default",
        messageId: "GXLKJfX4FiACG1w=",
        eventType: "CONNECT",
        messageDirection: "IN",
        connectionId: "GXLKAfX1FiACG1w=",
        apiId: "3m4dnp0wy4",
        requestTimeEpoch: 1632812813588,
        // some fields omitted for brevity
    },
    body: "{\"storeId\":\"305\",\"message\":\"OthersMessages\"}",
    isBase64Encoded: false,
    queryStringParameters: {
        id: "16",
        connectionId: "RTAYTdcciYcCGNg=",
        storeId: "16",
        orders: [],
        status: "1"
    },
}

const mockEventOff = {
    requestContext: {
        routeKey: "$default",
        messageId: "GXLKJfX4FiACG1w=",
        eventType: "CONNECT",
        messageDirection: "IN",
        connectionId: "GXLKAfX1FiACG1w=",
        apiId: "3m4dnp0wy4",
        requestTimeEpoch: 1632812813588,
        // some fields omitted for brevity
    },
    body: "{\"storeId\":\"305\",\"message\":\"getOfflineOrders\"}",
    isBase64Encoded: false,
    queryStringParameters: {
        id: "16",
        connectionId: "RTAYTdcciYcCGNg=",
        storeId: "16",
        orders: [],
        status: "1"
    },
}

const mockResultDyanamo = {
    Items: [
        {
            connectionId: "RTAYTdcciYcCGNg=",
            storeId: "16",
            orders: [{
                brandId: 8,
                brandName: "Pecado Natural",
                co2Total: 0,
                currency: "BRL",
                coupons: [],
                id: 3360031,
                discount: 0,
                discounts: [],
                deliveryTypeId: 1,
                flowId: 5,
                invoice: "165",
                notes: "POSV2",
                originId: 1,
                originName: "POS",
                orderNumber: "7",
                orderIsIntegration: false,
                paymentMethods: [],
                posId: 422,
                printed: false,
                products: [],
                statusId: 1,
                statusName: "Pedido",
                storeId: 96,
                storeName: "MUY Dino Bueno",
                subtotal: 26.9,
                tax: 4.6426,
                total: 26.9,
                transactionId: 620010,
                uid: "1xw8yhkhhz350",
                user: {},
                users: [{}]
            }],
            status: "1"
        }
    ],
    Count: 1,
    ScannedCount: 1
}

const mockResultWithoutItemstDyanamo = {
    Items: []
}

const mockResultdyanamoInactivo = {
    Items: [
        {
            connectionId: "RTAYTdcciYcCGNg=",
            storeId: "16",
            orders: [{
                brandId: 8,
                brandName: "Pecado Natural",
                co2Total: 0,
                currency: "BRL",
                coupons: [],
                id: 3360031,
                discount: 0,
                discounts: [],
                deliveryTypeId: 1,
                flowId: 5,
                invoice: "165",
                notes: "POSV2",
                originId: 1,
                originName: "POS",
                orderNumber: "7",
                orderIsIntegration: false,
                paymentMethods: [],
                posId: 422,
                printed: false,
                products: [],
                statusId: 1,
                statusName: "Pedido",
                storeId: 96,
                storeName: "MUY Dino Bueno",
                subtotal: 26.9,
                tax: 4.6426,
                total: 26.9,
                transactionId: 620010,
                uid: "1xw8yhkhhz350",
                user: {},
                users: [{}]
            }],
            status: "0"
        }
    ],
    Count: 1,
    ScannedCount: 1
}
const mockConectionPayload = {
    id: "16",
    connectionId: "RTAYTdcciYcCGNg=",
    storeId: "16",
    orders: [],
    status: "1"
}

const mockResultDyanamoInit = {
    Items: [
        {
            connectionId: "RTAYTdcciYcCGNg=",
            storeId: "16",
            orders: [],
            status: "1"
        }
    ],
    Count: 1,
    ScannedCount: 1
}


module.exports = {
    mockEventSimple,
    mockEventConnect,
    mockEventOff,
    mockResultDyanamo,
    mockResultdyanamoInactivo,
    mockResultWithoutItemstDyanamo,
    mockConectionPayload,
    mockResultDyanamoInit
};