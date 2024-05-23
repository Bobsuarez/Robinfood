package com.robinfood.ordereports_bc_muyapp.datamock.dto;

import com.robinfood.app.library.util.ObjectMapperSingleton;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;

public class ResponseOrderDetailDTOMock {

    public static ResponseOrderDetailDTO getDataDefault() {

        var dataJson = """
                {
                        "brands": [
                            {
                                "brandMenuId": 1,
                                "id": 1,
                                "image": "",
                                "name": "MUY"
                            }
                        ],
                        "flowId": 8,
                        "id": 5517853,
                        "orderNumber": "19884",
                        "origin": {
                            "companyId": 1,
                            "platformId": 9,
                            "store": {
                                "id": 27,
                                "image": "",
                                "name": "MUY 79"
                            }
                        },
                        "paid": true,
                        "payment": {
                            "co2Total": 0.0000,
                            "discount": 800.0,
                            "discounts": [
                                {
                                    "id": 2190187,
                                    "value": 800.0
                                }
                            ],
                            "methods": [
                                {
                                    "id": 8,
                                    "name": "integration",
                                    "value": 41900.0
                                }
                            ],
                            "subtotal": 39596.2963,
                            "tax": 3103.7037,
                            "total": 41900.0
                        },
                        "paymentModelId": 2,
                        "products": [
                            {
                                "brandId": 1,
                                "co2Total": 0.0000,
                                "groups": [],
                                "id": 9315137,
                                "image": "https://assets.robinfood.com/delivery/menu/products/8e3992dd-7933-4540-8fc9-a3e8ec79662c.jpg",
                                "name": "Promo MUYX3: Casero + Típico + Mexicano",
                                "price": 42700.0000,
                                "quantity": 1,
                                "value": 41900.0
                            }
                        ],
                        "services": [],
                        "status": {
                            "description": " - ",
                            "id": 1,
                            "name": "Pedido",
                            "trace": [
                                {
                                    "description": " - ",
                                    "id": 1,
                                    "name": "Pedido"
                                }
                            ]
                        },
                        "transactionId": 2375352,
                        "statusOrder": 1,
                        "uid": "d80e1lwxxvz8"
                    }
                """;

        return ObjectMapperSingleton.jsonToClass(dataJson, ResponseOrderDetailDTO.class);
    }
}
