package com.robinfood.core.extensions

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.robinfood.core.dtos.menu.response.MenuProductGroupDTO
import org.assertj.core.api.Assertions
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.junit.jupiter.api.Assertions.assertNull

class ObjectExtensionsTest {

    @Test
    fun test_Object_Is_Converted_To_JSON_Correctly() {
        val test1 = "test1 with looooooooong String"
        val test2 = ArrayList<String>()
        test2.add("element1")
        test2.add("element2")
        test2.add("element3")
        test2.add("element4")
        val test3 = DummyTestClass("Dummy string", 1, 2.1, test2)
        Assertions.assertThat(test1.toJson()).isEqualTo("\"test1 with looooooooong String\"")
        Assertions.assertThat(test2.toJson()).isEqualTo("[\"element1\",\"element2\",\"element3\",\"element4\"]")
        Assertions.assertThat(test3.toJson())
            .isEqualTo("{\"property1\":\"Dummy string\",\"property2\":1,\"property3\":2.1,\"property4\":[\"element1\",\"element2\",\"element3\",\"element4\"]}")
    }

    @Test
    fun test_Object_Is_Converted_To_JSON_Fail() {
        val om = Mockito.spy(ObjectMapper())
        `when`(om.writeValueAsString(anyString())).thenThrow(object : JsonProcessingException("") {}).toJson()
    }

    @Test
    fun test_Object_Is_Converted_To_GSON_Ok() {
        var jsonString = """{"description":"Test","id":1}"""
        val testModel = jsonString.serializeToMap()
        Assertions.assertThat(testModel.toJson()).isEqualTo("{\"description\":\"Test\",\"id\":1.0}")

    }

    @Test
    fun test_Object_Is_Converted_To_GSON_Fail() {
        var jsonString = """{"description""Test","id":1}"""
        val testModel = jsonString.serializeToMap()
        assertNull(testModel)
    }

    @Test
    fun test_Objects_Are_Casted_Correctly() {
        val test1: Any = "test"
        val test2: Any = 1
        val test3: Any? = null
        val test4: Any = ArrayList<Any>()
        MatcherAssert.assertThat(test1.tryCast(), CoreMatchers.instanceOf<Any?>(String::class.java))
        MatcherAssert.assertThat(test2.tryCast(), CoreMatchers.instanceOf<Any?>(Int::class.java))
        MatcherAssert.assertThat(test3.tryCast<Any?>() == null, CoreMatchers.instanceOf(Boolean::class.java))
        MatcherAssert.assertThat(test4.tryCast(), CoreMatchers.instanceOf<Any?>(ArrayList::class.java))
    }

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

        val test3 = MenuProductGroupDTO(
                1,
                3L,
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

        val test4 = MenuProductGroupDTO(
                1,
                4L,
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

        org.junit.jupiter.api.Assertions.assertEquals(test1.toGroupSelectionType(), 2)

        org.junit.jupiter.api.Assertions.assertEquals(test2.toGroupSelectionType(), 1)

        org.junit.jupiter.api.Assertions.assertEquals(test3.toGroupSelectionType(), 0)

        org.junit.jupiter.api.Assertions.assertEquals(test4.toGroupSelectionType(), 2)
    }

    internal data class DummyTestClass(
        val property1: String,
        val property2: Int,
        val property3: Double,
        val property4: ArrayList<String>
    )
}