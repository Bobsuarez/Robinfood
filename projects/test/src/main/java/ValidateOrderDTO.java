import lombok.extern.slf4j.Slf4j;
import org.example.constants.HttpStatusConstants;
import org.example.dtos.request.OrderDTO;
import org.example.dtos.request.ThirdPartyDTO;
import org.example.dtos.request.TransactionRequestDTO;
import org.example.exceptions.ApplicationException;
import org.example.exceptions.DataNotFoundException;
import org.example.mappers.ResponseMapper;
import org.example.util.LogsUtil;
import org.example.util.ObjectMapperSingleton;
import org.example.util.ValidatorFieldsUtil;

import java.util.Objects;

@Slf4j
public class ValidateOrderDTO {

    public static void main(String[] args) {

        String json = "{\"id\": 7623896, \"paid\": true, \"user\": {\"id\": 238752, \"email\": \"userdomi-co@muy" +
                ".co\", \"mobile\": \"9413131312\", \"lastName\": \"Domicilios\", \"firstName\": \"Usuario Integracion\", \"phoneCode\": \"57\"}, \"uuid\": \"6f4e0854-b59c-4838-9172-e69e6048ffbf\", \"total\": 19200.0, \"device\": {\"ip\": \"10.10.10.119\", \"platform\": 5, \"timezone\": \"America/Bogota\"}, \"flowId\": 6, \"orders\": [{\"id\": 12251065, \"uid\": \"n61l4fad7tsb\", \"uuid\": \"a331791b-5e40-4d26-adfd-171c1873dc3d\", \"brand\": {\"id\": 1, \"name\": \"MUY\"}, \"flags\": null, \"notes\": \"\", \"store\": {\"id\": 79, \"code\": \"05001\", \"name\": \"RobinFood plaza Astorga\", \"posId\": 363, \"postalCode\": \"50022\"}, \"total\": 19200.0, \"origin\": {\"id\": 7, \"name\": \"Rappi\"}, \"status\": null, \"coupons\": [], \"co2Total\": 0, \"services\": [], \"subtotal\": 17777.7778, \"discounts\": [{\"id\": null, \"sku\": \"\", \"value\": 0.0, \"typeId\": 3, \"productId\": 1953, \"isProductDiscount\": true, \"orderFinalProductId\": 1953, \"isConsumptionDiscount\": false}], \"foodcoins\": null, \"deductions\": [], \"thirdParty\": {\"email\": \"tom7479@gmail.com\", \"phone\": null, \"fullName\": \"Tomás Botero Castrillón\", \"documentType\": 0, \"documentNumber\": \"1128454615\"}, \"totalTaxes\": 1422.2222, \"orderNumber\": null, \"taxCriteria\": {\"store\": {\"id\": 79}, \"brand_id\": 1, \"country_id\": 1, \"location_id\": 2, \"platform_id\": 1}, \"criteriaInfo\": {\"store\": {\"id\": 79}, \"flow_id\": 6, \"brand_id\": 1, \"country_id\": 1, \"platform_id\": 1}, \"isMultiBrand\": false, \"finalProducts\": [{\"id\": 1953, \"sku\": \"12278208995117760881\", \"name\": \"Muy Personalizado +Bebida\", \"size\": {\"id\": 1, \"name\": \"MUY\"}, \"brand\": {\"id\": 1, \"name\": \"MUY\"}, \"image\": \"https://assets.robinfood.com/delivery/menu/products/ddfd1a7c-bbd9-4612-8677-883be78efd17.png\", \"price\": 18900.0, \"taxes\": [{\"taxId\": 1, \"dicTaxId\": 1, \"familyId\": 1, \"taxPrice\": 1422.2222, \"taxValue\": 8.0, \"articleId\": 2159, \"taxTypeId\": 1, \"taxTypeName\": \"Impoconsumo CO\", \"articleTypeId\": 1, \"familyTaxTypeId\": 1}], \"groups\": [{\"id\": 3, \"sku\": \"26257095296811385\", \"name\": \"Elige tu Base\", \"portions\": [{\"id\": 212, \"sku\": \"17680480075943772284\", \"free\": 1, \"name\": \"Arroz integral.\", \"price\": 0.0, \"unitId\": 1, \"product\": {\"id\": 89, \"name\": \"Arroz integral.\"}, \"discount\": 0, \"quantity\": 1, \"isIncluded\": false, \"unitNumber\": 127, \"replacementPortion\": null}]}, {\"id\": 2, \"sku\": \"26257095296811384\", \"name\": \"Elige Grano o Complemento\", \"portions\": [{\"id\": 1365, \"sku\": \"12277329449820619152\", \"free\": 1, \"name\": \"Lenteja\", \"price\": 0.0, \"unitId\": 1, \"product\": {\"id\": 102, \"name\": \"Lenteja\"}, \"discount\": 0, \"quantity\": 1, \"isIncluded\": false, \"unitNumber\": 102, \"replacementPortion\": null}]}, {\"id\": 76, \"sku\": \"12276489554839470173\", \"name\": \"Elige tu Proteína\", \"portions\": [{\"id\": 1376, \"sku\": \"12277329449820619168\", \"free\": 0, \"name\": \"Pollo a la plancha\", \"price\": 0.0, \"unitId\": 1, \"product\": {\"id\": 107, \"name\": \"Pollo a la plancha\"}, \"discount\": 0, \"quantity\": 1, \"isIncluded\": false, \"unitNumber\": 80, \"replacementPortion\": null}]}, {\"id\": 43, \"sku\": \"17680480075943772389\", \"name\": \"Elige tus Acompañamientos\", \"portions\": [{\"id\": 1208, \"sku\": \"12277226218234839041\", \"free\": 1, \"name\": \"Guacamole\", \"price\": 0.0, \"unitId\": 1, \"product\": {\"id\": 99, \"name\": \"Guacamole\"}, \"discount\": 0, \"quantity\": 1, \"isIncluded\": false, \"unitNumber\": 30, \"replacementPortion\": null}, {\"id\": 1381, \"sku\": \"12277329449820619173\", \"free\": 1, \"name\": \"Chorizo\", \"price\": 0.0, \"unitId\": 1, \"product\": {\"id\": 108, \"name\": \"Chorizo\"}, \"discount\": 0, \"quantity\": 1, \"isIncluded\": false, \"unitNumber\": 30, \"replacementPortion\": null}]}, {\"id\": 38, \"sku\": \"17680480075943772263\", \"name\": \"Elige tu salsa\", \"portions\": [{\"id\": 1330, \"sku\": \"12277329449820618930\", \"free\": 1, \"name\": \"Salsa muy\", \"price\": 0.0, \"unitId\": 1, \"product\": {\"id\": 2389, \"name\": \"Salsa muy\"}, \"discount\": 0, \"quantity\": 1, \"isIncluded\": false, \"unitNumber\": 20, \"replacementPortion\": null}]}, {\"id\": 398, \"sku\": \"12278459927759945763\", \"name\": \"Quieres cubiertos?\", \"portions\": [{\"id\": 1597, \"sku\": \"12278459927759945764\", \"free\": 0, \"name\": \"Con cubiertos\", \"price\": 300.0, \"unitId\": 9, \"product\": {\"id\": 3161, \"name\": \"Con cubiertos\"}, \"discount\": 0, \"quantity\": 1, \"isIncluded\": false, \"unitNumber\": 1, \"replacementPortion\": null}]}, {\"id\": 385, \"sku\": \"12278335076080549934\", \"name\": \"Elige tu bebida (Rappi y combos pecado natural)\", \"portions\": [{\"id\": 1400, \"sku\": \"12277544897157791799\", \"free\": 0, \"name\": \"Jugo natural MUY\", \"price\": 0.0, \"unitId\": 6, \"product\": {\"id\": 416, \"name\": \"Jugo natural MUY\"}, \"discount\": 0, \"quantity\": 1, \"isIncluded\": false, \"unitNumber\": 266, \"replacementPortion\": null}]}], \"article\": {\"id\": 2159, \"typeId\": 1, \"typeName\": \"ARTICLE\", \"menuHallProductId\": 21293}, \"category\": {\"id\": 2, \"name\": \"Personalizado\"}, \"co2Total\": 0, \"quantity\": 1, \"deduction\": [], \"discounts\": [{\"sku\": \"\", \"value\": 0.0, \"isCoupons\": false, \"isProductDiscount\": true}], \"totalPrice\": 19200.0, \"removedPortions\": []}], \"invoiceNumber\": \"49595\", \"posResolution\": {\"id\": 1594, \"name\": \"Caja Online - RobinFood plaza Astorga\", \"posId\": 363, \"prefix\": \"MDE6\", \"current\": 1, \"endDate\": \"2025-04-11\", \"storeId\": 79, \"endNumber\": \"49595\", \"dicStatusId\": 1, \"initialDate\": \"2024-04-11\", \"startNumber\": \"49571\", \"typeDocument\": \"Doc Aut DIAN\", \"cancelledInvoices\": \"0\", \"effectiveInvoices\": \"25\"}, \"totalDiscount\": 0.0, \"deliveryTypeId\": 4, \"hasConsumption\": false, \"paymentModelId\": 2, \"consumptionValue\": 0, \"discountsApplied\": null, \"couponCriteriaInfo\": {\"store\": {\"id\": 79}, \"flow_id\": 6, \"brand_id\": 1, \"country_id\": 1, \"platform_id\": 1}, \"billingResolutionId\": 363}], \"origin\": {\"id\": 7, \"name\": \"rappi\"}, \"company\": {\"id\": 1, \"currency\": \"COP\"}, \"coupons\": [], \"co2Total\": 0, \"delivery\": {\"code\": \"2238278840\", \"notes\": \"NOTES\", \"totalTip\": 1000, \"userName\": \"\", \"orderType\": 4, \"addressCity\": \"Address City\", \"arrivalDate\": {\"day\": 29, \"year\": 2024, \"month\": 5}, \"arrivalTime\": {\"hour\": 14, \"nano\": 0, \"minute\": 42, \"second\": 34}, \"integrationId\": \"2238278840\", \"paymentMethod\": \"cc\", \"totalDelivery\": 6400, \"totalDiscount\": 0, \"addressDescription\": \"Address Description\"}, \"storeInfo\": {\"id\": 79, \"city\": {\"id\": 2, \"code\": \"05001\", \"name\": \"Medellín\"}, \"name\": \"MUY Astorga\", \"email\": \"store1@robinfood.com\", \"phone\": \"3204252642\", \"state\": {\"id\": 2, \"name\": \"Meta\"}, \"address\": \"Carrera 43 C No. 7 D 33\", \"flowTax\": {}, \"location\": \"location\", \"timezone\": \"America/Bogota\", \"postalCode\": \"50022\", \"internalName\": \"MUY Astorga\"}, \"updateOrder\": false, \"paymentsPaid\": [], \"orderCreatedAt\": \"2024-05-29T19:55:47.145564\", \"paymentMethods\": [{\"id\": 8, \"value\": 19200.0, \"originId\": 7}], \"totalPaidPayments\": 0.0, \"pickupTimeConsumption\": false, \"performCouponResponseDTOS\": []}";
        TransactionRequestDTO nuevo = ObjectMapperSingleton.jsonToClass(json, TransactionRequestDTO.class);
        assignThirdParty(nuevo.getOrders().get(0));

    }

    private static void assignThirdParty(OrderDTO orderDTO) {

        try {

            if (Objects.isNull(orderDTO.getThirdParty())) {
                throw new DataNotFoundException(
                        ResponseMapper
                                .buildWithError(
                                        HttpStatusConstants.SC_NOT_FOUND.getCodeHttp(),
                                        "Error Vacio thrity",
                                        Boolean.TRUE),
                        "Error Vacio thrity");
            }

            log.info("objecto cliente {}" ,
                          ObjectMapperSingleton.objectToJson(orderDTO.getThirdParty())
            );

            ValidatorFieldsUtil.validateNullFields(orderDTO.getThirdParty(),
                                                   "documentNumber", "documentType", "email", "fullName");


        } catch (ApplicationException e) {

            log.error(
                    "Error validar cliente {}", e.getMessage()
            );

            orderDTO.setThirdParty(ThirdPartyDTO.builder()
                                           .documentNumber("2")
                                           .fullName("Consumidor final")
                                           .email("email.com")
                                           .documentType(Integer.parseInt("2"))
                                           .build());
        }
    }
}
