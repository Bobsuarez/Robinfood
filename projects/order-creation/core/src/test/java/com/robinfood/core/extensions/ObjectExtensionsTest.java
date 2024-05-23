package com.robinfood.core.extensions;

import java.util.ArrayList;

import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ObjectExtensionsTest {

    @Test
    void test_Object_Is_Converted_To_JSON_Correctly() {
        final String test1 = "test1 with looooooooong String";
        final ArrayList<String> test2 = new ArrayList<>();
        test2.add("element1");
        test2.add("element2");
        test2.add("element3");
        test2.add("element4");
        final DummyTestClass test3 = new DummyTestClass("Dummy string", 1, 2.1, test2);
        Assertions.assertThat(ObjectExtensions.toJson(test1)).isEqualTo("\"test1 with looooooooong String\"");
        Assertions.assertThat(ObjectExtensions.toJson(test2)).isEqualTo("[\"element1\",\"element2\",\"element3\",\"element4\"]");
        Assertions.assertThat(ObjectExtensions.toJson(test3))
                .isEqualTo("{\"property1\":\"Dummy string\",\"property2\":1,\"property3\":2.1,\"property4\":[\"element1\",\"element2\",\"element3\",\"element4\"]}");
    }

    @Test
    void test_serializeToMap_Exception() {

        final String data = "<html> <head><title>503 Service Temporarily Unavailable</title></head> <body> " +
            "<center><h1>503 Service Temporarily Unavailable</h1></center> <hr><center>nginx/1.21.3</center> </body> " +
            "</html> in service https://tech-cai-615-descripcion-comanda-tributo.order-bc.muydev.com/api/v1/orders";

        assertNotNull(ObjectExtensions.serializeToMap(data));

    }

    @Test
    void test_serializeToMap_Data_Is_Null() {

        final String data = "{\"code\":5000,\"locale\":\"2022-06-22T21:54:55.556550231Z\",\"status\":\"INTERNAL_SERVER_ERROR\",\"message\":\"Data source error\"}";

        assertNotNull(ObjectExtensions.serializeToMap(data));

    }

    @Data
    static class DummyTestClass {
        final String property1;
        final int property2;
        final double property3;
        final ArrayList<String> property4;
    }
}
