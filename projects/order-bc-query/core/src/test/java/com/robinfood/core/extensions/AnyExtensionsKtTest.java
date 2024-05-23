package com.robinfood.core.extensions;

import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnyExtensionsKtTest {

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

    @SuppressWarnings("ConstantConditions")
    @Test
    void test_Objects_Are_Casted_Correctly() {
        final Object test1 = "test";
        final Object test2 = 1;
        final Object test3 = null;
        final Object test4 = new ArrayList<>();

        assertThat(ObjectExtensions.tryCast(test1), instanceOf(String.class));
        assertThat(ObjectExtensions.tryCast(test2), instanceOf(Integer.class));
        assertThat(ObjectExtensions.tryCast(test3) == null, instanceOf(Boolean.class));
        assertThat(ObjectExtensions.tryCast(test4), instanceOf(ArrayList.class));

    }

    @Data
    static class DummyTestClass {
        final String property1;
        final int property2;
        final double property3;
        final ArrayList<String> property4;
    }
}
