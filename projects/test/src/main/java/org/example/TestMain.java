package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMain {

    private final static String JSON_DATA_1 =
            "[\"Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9" +
                    ".eyJuYW1lIjoib3JkZXItY3JlYXRpb24iLCJhdWQiOiJzZXJ2aWNlIiwic3ViIjoib3JkZXItY3JlYXRpb24udjEiLCJwZXIiOltdLCJqdGkiOiJhMjYxY2FjMS00YWMxLTQyM2UtOGRjNy0xNWM1NWE3OGFjMjMiLCJpc3MiOiJiYWNrb2ZmaWNlLnYxIiwiaWF0IjoxNjg3Mjk5OTcwLCJleHAiOjE2ODczMDA1NzB9.jkpgXCMem9Jgnt0lrjwXZNAYiuWppgynDhRwyLazNB4YQk-S2qsluZD_8zs5lAA5qR5bWiL_Uz6-4x2teYCroA\",{\"address\":null,\"channel\":null,\"company\":{\"currency\":\"COP\",\"id\":1},\"coupons\":[],\"delivery\":null,\"device\":{\"ip\":\"10.0.2.16\",\"platform\":4,\"timezone\":\"America/Bogota\",\"version\":\"4\"},\"deductions\":null,\"flowId\":1,\"id\":null,\"orderCreatedAt\":null,\"orders\":[{\"billingResolutionId\":1,\"brand\":{\"id\":1,\"name\":\"MUY\"},\"criteriaInfo\":{\"countryId\":\"1\"},\"coupons\":[],\"couponCriteriaInfo\":{\"countryId\":\"1\"},\"consumptionValue\":0,\"deliveryTypeId\":1,\"deductions\":[],\"discounts\":[],\"discountsApplied\":null,\"finalProducts\":[{\"article\":{\"id\":1412,\"menuHallProductId\":5974,\"typeId\":1,\"typeName\":\"ARTICLE\"},\"brand\":{\"id\":1,\"name\":\"MUY\"},\"category\":null,\"discounts\":[],\"deduction\":[],\"groups\":[{\"id\":10,\"name\":\"Ingredientes\",\"portions\":[{\"discount\":0,\"free\":1,\"id\":1350,\"isIncluded\":true,\"name\":\"Arroz blanco\",\"price\":0,\"product\":{\"id\":1350,\"name\":\"Arroz blanco\"},\"quantity\":1,\"replacementPortion\":null,\"sku\":\"12277329449820618983\",\"unitId\":1,\"unitNumber\":148},{\"discount\":0,\"free\":1,\"id\":1371,\"isIncluded\":true,\"name\":\"Fr�jol rojo\",\"price\":0,\"product\":{\"id\":1371,\"name\":\"Fr�jol rojo\"},\"quantity\":1,\"replacementPortion\":null,\"sku\":\"12277329449820619163\",\"unitId\":1,\"unitNumber\":107},{\"discount\":0,\"free\":1,\"id\":1380,\"isIncluded\":true,\"name\":\"Chicharr�n\",\"price\":0,\"product\":{\"id\":1380,\"name\":\"Chicharr�n\"},\"quantity\":1,\"replacementPortion\":null,\"sku\":\"12277329449820619172\",\"unitId\":1,\"unitNumber\":30},{\"discount\":0,\"free\":1,\"id\":1374,\"isIncluded\":true,\"name\":\"Carne molida a la criolla\",\"price\":0,\"product\":{\"id\":1374,\"name\":\"Carne molida a la criolla\"},\"quantity\":1,\"replacementPortion\":null,\"sku\":\"12277329449820619166\",\"unitId\":1,\"unitNumber\":78},{\"discount\":0,\"free\":1,\"id\":1381,\"isIncluded\":true,\"name\":\"Chorizo\",\"price\":0,\"product\":{\"id\":1381,\"name\":\"Chorizo\"},\"quantity\":1,\"replacementPortion\":null,\"sku\":\"12277329449820619173\",\"unitId\":1,\"unitNumber\":30},{\"discount\":0,\"free\":1,\"id\":264,\"isIncluded\":true,\"name\":\"Pl�tano Maduro\",\"price\":0,\"product\":{\"id\":264,\"name\":\"Pl�tano Maduro\"},\"quantity\":1,\"replacementPortion\":null,\"sku\":\"17680480075943772449\",\"unitId\":1,\"unitNumber\":50},{\"discount\":0,\"free\":1,\"id\":11,\"isIncluded\":true,\"name\":\"Hogao\",\"price\":0,\"product\":{\"id\":11,\"name\":\"Hogao\"},\"quantity\":1,\"replacementPortion\":null,\"sku\":\"26257095296811284\",\"unitId\":1,\"unitNumber\":40},{\"discount\":0,\"free\":1,\"id\":58,\"isIncluded\":true,\"name\":\"Jugo\",\"price\":0,\"product\":{\"id\":58,\"name\":\"Jugo\"},\"quantity\":1,\"replacementPortion\":null,\"sku\":\"26257095296811331\",\"unitId\":6,\"unitNumber\":266}],\"sku\":\"26257095296811392\"},{\"id\":11,\"name\":\"Adiciones\",\"portions\":[],\"sku\":\"26257095296811393\"},{\"id\":14,\"name\":\"Salsas\",\"portions\":[],\"sku\":\"26257095296811598\"}],\"id\":1437,\"image\":\"https://assets.robinfood.com/delivery/menu/products/133a0208-f365-4f21-a09f-b31d3e741e71.png\",\"name\":\"MUY Paisa\",\"price\":12900,\"quantity\":1,\"removedPortions\":[],\"size\":{\"id\":1,\"name\":\"\"},\"sku\":\"12277544897157791797\",\"taxes\":[],\"totalPrice\":12900,\"co2Total\":150}],\"flags\":null,\"foodcoins\":null,\"id\":null,\"invoiceNumber\":null,\"isMultiBrand\":false,\"hasConsumption\":false,\"notes\":\"POSV2\",\"orderNumber\":null,\"origin\":{\"id\":4,\"name\":\"Autogesti�n 2.0\"},\"paymentModelId\":1,\"services\":[],\"subtotal\":null,\"status\":null,\"store\":{\"id\":27,\"name\":\"RobinFood Plaza 83\",\"posId\":null},\"taxCriteria\":{},\"total\":12900.0000,\"totalTaxes\":null,\"totalDiscount\":null,\"co2Total\":150,\"uid\":null,\"uuid\":null}],\"origin\":{\"id\":4,\"name\":\"Autogesti�n 2.0\"},\"paid\":false,\"paymentMethods\":[],\"paymentsPaid\":[],\"pickupTimeConsumption\":false,\"total\":12900.0000,\"totalPaidPayments\":0.0000,\"updateOrder\":false,\"user\":{\"id\":674563,\"firstName\":\"Julian Andres\",\"lastName\":\"Vega Parra\",\"phoneCode\":\"null\",\"mobile\":\"3186248429\",\"email\":\"jvega.soft@gmail.com\"},\"co2Total\":150,\"performCouponResponseDTOS\":[],\"subtotalOnlyWithDiscount\":null,\"storeInfo\":null,\"uuid\":null,\"buyer\":null}]";


    private final static String JSON_DATA =
            "{\"address\":null,\"channel\":null,\"company\":{\"currency\":\"COP\",\"id\":1},\"coupons\":[]," +
                    "\"delivery\":null,\"device\":{\"ip\":\"10.0.2.16\",\"platform\":4," +
                    "\"timezone\":\"America/Bogota\",\"version\":\"4\"},\"deductions\":null,\"flowId\":1,\"id\":null," +
                    "\"orderCreatedAt\":null,\"orders\":[{\"billingResolutionId\":1,\"brand\":{\"id\":1," +
                    "\"name\":\"MUY\"},\"criteriaInfo\":{\"countryId\":\"1\"},\"coupons\":[]," +
                    "\"couponCriteriaInfo\":{\"countryId\":\"1\"},\"consumptionValue\":0,\"deliveryTypeId\":1," +
                    "\"deductions\":[],\"discounts\":[],\"discountsApplied\":null," +
                    "\"finalProducts\":[{\"article\":{\"id\":1412,\"menuHallProductId\":5974,\"typeId\":1," +
                    "\"typeName\":\"ARTICLE\"},\"brand\":{\"id\":1,\"name\":\"MUY\"},\"category\":null," +
                    "\"discounts\":[],\"deduction\":[],\"groups\":[{\"id\":10,\"name\":\"Ingredientes\"," +
                    "\"portions\":[{\"discount\":0,\"free\":1,\"id\":1350,\"isIncluded\":true,\"name\":\"Arroz " +
                    "blanco\",\"price\":0,\"product\":{\"id\":1350,\"name\":\"Arroz blanco\"},\"quantity\":1," +
                    "\"replacementPortion\":null,\"sku\":\"12277329449820618983\",\"unitId\":1,\"unitNumber\":148}," +
                    "{\"discount\":0,\"free\":1,\"id\":1371,\"isIncluded\":true,\"name\":\"Fr�jol rojo\",\"price\":0," +
                    "\"product\":{\"id\":1371,\"name\":\"Fr�jol rojo\"},\"quantity\":1,\"replacementPortion\":null," +
                    "\"sku\":\"12277329449820619163\",\"unitId\":1,\"unitNumber\":107},{\"discount\":0,\"free\":1," +
                    "\"id\":1380,\"isIncluded\":true,\"name\":\"Chicharr�n\",\"price\":0,\"product\":{\"id\":1380," +
                    "\"name\":\"Chicharr�n\"},\"quantity\":1,\"replacementPortion\":null," +
                    "\"sku\":\"12277329449820619172\",\"unitId\":1,\"unitNumber\":30},{\"discount\":0,\"free\":1," +
                    "\"id\":1374,\"isIncluded\":true,\"name\":\"Carne molida a la criolla\",\"price\":0," +
                    "\"product\":{\"id\":1374,\"name\":\"Carne molida a la criolla\"},\"quantity\":1," +
                    "\"replacementPortion\":null,\"sku\":\"12277329449820619166\",\"unitId\":1,\"unitNumber\":78}," +
                    "{\"discount\":0,\"free\":1,\"id\":1381,\"isIncluded\":true,\"name\":\"Chorizo\",\"price\":0," +
                    "\"product\":{\"id\":1381,\"name\":\"Chorizo\"},\"quantity\":1,\"replacementPortion\":null," +
                    "\"sku\":\"12277329449820619173\",\"unitId\":1,\"unitNumber\":30},{\"discount\":0,\"free\":1," +
                    "\"id\":264,\"isIncluded\":true,\"name\":\"Pl�tano Maduro\",\"price\":0,\"product\":{\"id\":264," +
                    "\"name\":\"Pl�tano Maduro\"},\"quantity\":1,\"replacementPortion\":null," +
                    "\"sku\":\"17680480075943772449\",\"unitId\":1,\"unitNumber\":50},{\"discount\":0,\"free\":1," +
                    "\"id\":11,\"isIncluded\":true,\"name\":\"Hogao\",\"price\":0,\"product\":{\"id\":11," +
                    "\"name\":\"Hogao\"},\"quantity\":1,\"replacementPortion\":null,\"sku\":\"26257095296811284\"," +
                    "\"unitId\":1,\"unitNumber\":40},{\"discount\":0,\"free\":1,\"id\":58,\"isIncluded\":true," +
                    "\"name\":\"Jugo\",\"price\":0,\"product\":{\"id\":58,\"name\":\"Jugo\"},\"quantity\":1," +
                    "\"replacementPortion\":null,\"sku\":\"26257095296811331\",\"unitId\":6,\"unitNumber\":266}]," +
                    "\"sku\":\"26257095296811392\"},{\"id\":11,\"name\":\"Adiciones\",\"portions\":[]," +
                    "\"sku\":\"26257095296811393\"},{\"id\":14,\"name\":\"Salsas\",\"portions\":[]," +
                    "\"sku\":\"26257095296811598\"}],\"id\":1437,\"image\":\"https://assets.robinfood" +
                    ".com/delivery/menu/products/133a0208-f365-4f21-a09f-b31d3e741e71.png\",\"name\":\"MUY Paisa\"," +
                    "\"price\":12900,\"quantity\":1,\"removedPortions\":[],\"size\":{\"id\":1,\"name\":\"\"}," +
                    "\"sku\":\"12277544897157791797\",\"taxes\":[],\"totalPrice\":12900,\"co2Total\":150}]," +
                    "\"flags\":null,\"foodcoins\":null,\"id\":null,\"invoiceNumber\":null,\"isMultiBrand\":false," +
                    "\"hasConsumption\":false,\"notes\":\"POSV2\",\"orderNumber\":null,\"origin\":{\"id\":4," +
                    "\"name\":\"Autogesti�n 2.0\"},\"paymentModelId\":1,\"services\":[],\"subtotal\":null," +
                    "\"status\":null,\"store\":{\"id\":27,\"name\":\"RobinFood Plaza 83\",\"posId\":null}," +
                    "\"taxCriteria\":{\"flow_id\":null,\"location_id\":1},\"total\":12900.0000,\"totalTaxes\":null," +
                    "\"totalDiscount\":null,\"co2Total\":150,\"uid\":null," +
                    "\"uuid\":\"b9d5c0d0-143f-4899-994f-a4210b134af4\"}],\"origin\":{\"id\":4,\"name\":\"Autogesti�n " +
                    "2.0\"},\"paid\":false,\"paymentMethods\":[],\"paymentsPaid\":[],\"pickupTimeConsumption\":false," +
                    "\"total\":12900.0000,\"totalPaidPayments\":0.0000,\"updateOrder\":false,\"user\":{\"id\":674563," +
                    "\"firstName\":\"Julian Andres\",\"lastName\":\"Vega Parra\",\"phoneCode\":\"null\"," +
                    "\"mobile\":\"3186248429\",\"email\":\"jvega.soft@gmail.com\"},\"co2Total\":150," +
                    "\"performCouponResponseDTOS\":[],\"subtotalOnlyWithDiscount\":null,\"storeInfo\":{\"id\":27," +
                    "\"name\":\"MUY 83\",\"location\":\"location\",\"phone\":\"3166921358\"," +
                    "\"email\":\"store1@robinfood.com\",\"address\":\"Calle 83 #14a-28 Local 2\"," +
                    "\"internalName\":\"CR MUY BOG 83\",\"identification\":null,\"timezone\":\"America/Bogota\"," +
                    "\"city\":{\"id\":1,\"name\":\"Bogota\"},\"state\":{\"id\":1,\"name\":\"Cundinamarca\"}," +
                    "\"flowTax\":{\"value\":null},\"country\":null}," +
                    "\"uuid\":\"3ebfa2dc-2318-41be-91a1-9bd1a0c0859e\",\"buyer\":null}";
    private static String nuevo = "{\n  \"brand_id\": 1,\n  \"country_id\": 1,\n  \"flow_id\": 1,\n  \"payment\": {\n" +
            "    \"discount\": 0,\n    \"subtotal\": 12900.0000,\n    \"tax\": 0,\n    \"tax_type\": 1,\n    " +
            "\"total\": 12900.0000\n  },\n  \"platform_id\": 4,\n  \"products\": [\n    {\n      \"brand_id\": 1,\n  " +
            "    \"discount\": 0,\n      \"groups\": [\n        {\n          \"id\": 10,\n          \"portions\": [\n" +
            "            {\n              \"free\": 1,\n              \"id\": 1350,\n              \"included\": " +
            "true,\n              \"name\": \"Arroz blanco\",\n              \"price\": 0,\n              " +
            "\"unit_number\": 148,\n              \"quantity\": 1,\n              \"sku\": \"12277329449820618983\"\n" +
            "            },\n            {\n              \"free\": 1,\n              \"id\": 1371,\n              " +
            "\"included\": true,\n              \"name\": \"Fr�jol rojo\",\n              \"price\": 0,\n            " +
            "  \"unit_number\": 107,\n              \"quantity\": 1,\n              \"sku\": " +
            "\"12277329449820619163\"\n            },\n            {\n              \"free\": 1,\n              " +
            "\"id\": 1380,\n              \"included\": true,\n              \"name\": \"Chicharr�n\",\n             " +
            " \"price\": 0,\n              \"unit_number\": 30,\n              \"quantity\": 1,\n              " +
            "\"sku\": \"12277329449820619172\"\n            },\n            {\n              \"free\": 1,\n          " +
            "    \"id\": 1374,\n              \"included\": true,\n              \"name\": \"Carne molida a la " +
            "criolla\",\n              \"price\": 0,\n              \"unit_number\": 78,\n              \"quantity\":" +
            " 1,\n              \"sku\": \"12277329449820619166\"\n            },\n            {\n              " +
            "\"free\": 1,\n              \"id\": 1381,\n              \"included\": true,\n              \"name\": " +
            "\"Chorizo\",\n              \"price\": 0,\n              \"unit_number\": 30,\n              " +
            "\"quantity\": 1,\n              \"sku\": \"12277329449820619173\"\n            },\n            {\n      " +
            "        \"free\": 1,\n              \"id\": 264,\n              \"included\": true,\n              " +
            "\"name\": \"Pl�tano Maduro\",\n              \"price\": 0,\n              \"unit_number\": 50,\n        " +
            "      \"quantity\": 1,\n              \"sku\": \"17680480075943772449\"\n            },\n            {\n" +
            "              \"free\": 1,\n              \"id\": 11,\n              \"included\": true,\n              " +
            "\"name\": \"Hogao\",\n              \"price\": 0,\n              \"unit_number\": 40,\n              " +
            "\"quantity\": 1,\n              \"sku\": \"26257095296811284\"\n            },\n            {\n         " +
            "     \"free\": 1,\n              \"id\": 58,\n              \"included\": true,\n              \"name\":" +
            " \"Jugo\",\n              \"price\": 0,\n              \"unit_number\": 266,\n              " +
            "\"quantity\": 1,\n              \"sku\": \"26257095296811331\"\n            }\n          ],\n          " +
            "\"sku\": \"26257095296811392\"\n        }\n      ],\n      \"id\": 5974,\n      \"article_id\": 1412,\n " +
            "     \"price\": 12900,\n      \"quantity\": 1,\n      \"sku\": \"12277544897157791797\",\n      " +
            "\"subtotal\": 12900,\n      \"tax\": 0,\n      \"tax_type\": 1,\n      \"type\": \"ARTICLE\"\n    }\n  " +
            "],\n  \"store_id\": 27,\n  \"uuid\": \"117478df-e927-4104-81e4-61f7f5882607\"\n}";


    public static void main(String[] args) throws JSONException, JsonProcessingException {

        String texto = "La casa es de color rojo y el auto es de color azul. Los colores son geniales.";

        String patron = "[\\\\|\\\\n]\\s*"; // Patrón para buscar la palabra "color" seguida de una palabra en
        // minúsculas

        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(JSON_DATA);

        System.out.println(matcher);

        while (matcher.find()) {
            String coincidencia = matcher.group();
            System.out.println("Coincidencia encontrada: " + coincidencia);
        }

//        System.out.println(nuevo);
//
//        nuevo = new Gson().toJson(nuevo);
//        System.out.println(nuevo);
//        var loka = nuevo.replaceAll("[\\\\|\\\\n]\\s*", "");
//        System.out.println(loka);

//        JsonElement jsonData2 = new Gson().fromJson(JSON_DATA, JsonElement.class);
//
//        AgregarMap loka = new ProccessMap();
//        loka.accederElementos(jsonData2);
//        System.out.println(new Gson().toJson(loka.addMapNuevo()));
    }


    // Función recursiva para acceder a los elementos de forma dinámica
    private static void accederElementos(JsonElement jsonElement) {

        if (jsonElement.isJsonObject()) {

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            for (String key : jsonObject.keySet()) {
                JsonElement element = jsonObject.get(key);
                if (key.equalsIgnoreCase("uuid")) {
                    System.out.println("uuid : " + element);
                }
                if (key.equalsIgnoreCase("user")) {
                    System.out.println("user : " + element);
                }
                if (key.equalsIgnoreCase("updateOrder")) {
                    System.out.println("updateOrder : " + element);
                }
                if (key.equalsIgnoreCase("paid")) {
                    System.out.println("paid : " + element);
                }
                if (key.equalsIgnoreCase("total")) {
                    System.out.println("total : " + element);
                }
                accederElementos(element);
            }

        } else if (jsonElement.isJsonArray()) {

            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                accederElementos(element);
            }

        } else {
            // Realiza operaciones con el elemento
            // ...
        }
    }
}

interface AgregarMap {

    Map<String, Object> addMapNuevo();

    void accederElementos(JsonElement jsonElement);

}

class ProccessMap implements AgregarMap {

    private static List<String> nueva = new ArrayList<>();
    private static Map<String, Object> actions = new TreeMap<>();

    @Override
    public void accederElementos(JsonElement jsonElement) {

        nueva.add("uuid");
        nueva.add("user");
        nueva.add("updateOrder");
        nueva.add("paid");
        nueva.add("total");

        if (jsonElement.isJsonObject()) {
            for (String key : jsonElement.getAsJsonObject().keySet()) {
                JsonElement element = jsonElement.getAsJsonObject().get(key);
                if (!nueva.contains(key)) {
                    accederElementos(element);
                    continue;
                }
                if (actions.containsKey(key)) {
                    actions.put(key + "-trans", element.toString());
                    continue;
                }
                actions.put(key, element.toString());
            }

        } else if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                accederElementos(element);
            }
        }

    }

    @Override
    public Map<String, Object> addMapNuevo() {
        return actions;
    }
}


