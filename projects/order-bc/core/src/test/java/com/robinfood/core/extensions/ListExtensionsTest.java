package com.robinfood.core.extensions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ListExtensionsTest {

    private final List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

    @Test
    void test_Find_When_Element_Exists() {
        final Integer foundElement = ListExtensions.find(list, (Integer number) -> number == 3);
        assertNotNull(foundElement);
    }

    @Test
    void test_Find_When_Element_Does_Not_Exists() {
        final Integer foundElement = ListExtensions.find(list, (Integer number) -> number == 5);
        assertNull(foundElement);
    }
}
