package com.robinfood.core.utils

import org.junit.jupiter.api.Assertions.assertEquals;
import com.robinfood.core.dtos.menu.response.MenuProductGroupDTO
import com.robinfood.core.extensions.toGroupSelectionType
import org.junit.jupiter.api.Test

class SizeUtilsTest {

    @Test
    fun est_GroupType_Are_Converted_Correctly() {
        val test1 = MenuProductGroupDTO(
                1,
                1L,
                1L,
                0,
                2,
                "Test",
                "Tests",
                listOf(),
                "Test",
                "Tests",
                "sku",
                0
        )

        val test2 = MenuProductGroupDTO(
                1,
                2L,
                1L,
                0,
                2,
                "Test",
                "Tests",
                listOf(),
                "Test",
                "Tests",
                "sku",
                0
        )
        assertEquals(test1.toGroupSelectionType(), 2)

        assertEquals(test2.toGroupSelectionType(), 1)
    }

    @Test
    fun test_GroupType_When_Size_Id_Is_Null() {
        val size = getSizeName(null)
        assertEquals("", size)
    }

    @Test
    fun test_GroupType_When_Size_Contains_Key() {
        val size = getSizeName(15L)
        assertEquals("Sencilla", size)
    }

    @Test
    fun test_GroupType_When_Size_Not_Contains_Key() {
        val size = getSizeName(100L)
        assertEquals("", size)
    }
}