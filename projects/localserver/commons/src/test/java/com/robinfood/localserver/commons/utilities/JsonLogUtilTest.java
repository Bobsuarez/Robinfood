package com.robinfood.localserver.commons.utilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@ExtendWith(MockitoExtension.class)
class JsonLogUtilTest {

    private final String VALUE = "test";

    @Test
    void test_When_logJson_fail() {
        HashMap<String, String> invalidJSONPayload= new HashMap<>();
        invalidJSONPayload.put(null, VALUE);
        invalidJSONPayload.put("x",VALUE);
        invalidJSONPayload.put("y",VALUE);

        String response = JsonLogUtil.logJson(invalidJSONPayload, Object.class);
        assertNotNull(response);
        assertTrue( response instanceof String);
    }


    @Test
    void test_When_logJson_success() {
        HashMap<String, String> invalidJSONPayload= new HashMap<>();

        invalidJSONPayload.put("x", VALUE);
        invalidJSONPayload.put("y",VALUE);
        invalidJSONPayload.put("z",VALUE);

        String response = JsonLogUtil.logJson(invalidJSONPayload, Object.class);
        assertNotNull(response);
        assertTrue( response instanceof String);

    }
}
