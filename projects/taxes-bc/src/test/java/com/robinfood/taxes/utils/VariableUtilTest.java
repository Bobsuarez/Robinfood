package com.robinfood.taxes.utils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VariableUtilTest {


    @Test
    void test_CastToType_Should_ReturnDouble_When_typeIsNumber(){
        //Call
        assertAll(() -> VariableUtil.castToType("number", "1"));
    }

    @Test
    void test_CastToType_Should_ReturnDouble_When_typeIsString(){
        //Call
        assertAll(() -> VariableUtil.castToType("string", "1"));
    }

    @Test
    void test_CastToType_Should_ReturnDouble_When_typeIsArrayString(){
        //Call
        assertAll(() -> VariableUtil.castToType("arrayString", "1"));
    }

    @Test
    void test_CastToType_Should_ReturnDouble_When_typeIsArrayNumber(){
        //Call
        assertAll(() -> VariableUtil.castToType("arrayNumber", "1"));
    }

    @Test
    void test_CastToType_Should_ReturnDouble_When_typeIsBoolean(){
        //Call
        assertAll(() -> VariableUtil.castToType("boolean", "true"));
    }

    @Test
    void test_CastToType_Should_ThrowReturnDouble_When_typeIsNumber(){
        //Call
        assertThatThrownBy(() -> VariableUtil.castToType("number", "Value"));
    }

}
