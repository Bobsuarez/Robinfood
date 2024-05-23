package com.robinfood.taxes.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.robinfood.taxes.domain.RuleVariable;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RuleFactoryTest {

    @InjectMocks
    private RuleFactory ruleFactory;

    @Test
    void test_Equal_Should_ReturnTrue_When_ParametersAreNumber() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("number")
            .value("1")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("number")
            .value("1")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("Equal", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(true, evaluate);
    }

    @Test
    void test_Equal_Should_ReturnFalse_When_ParametersAreNumber() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("number")
            .value("2")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("number")
            .value("1")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("Equal", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(false, evaluate);
    }

    @Test
    void test_Equal_Should_ReturnTrue_When_ParametersAreString() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("string")
            .value("Test")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("string")
            .value("Test")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("Equal", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(true, evaluate);
    }

    @Test
    void test_Equal_Should_ThrowBusinessRuleException_When_VariableTypeIsEmpty() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("")
            .value("1")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("string")
            .value("Test")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("Equal", left, right);

        assertThrows(IllegalArgumentException.class, rule::evaluate);
    }

    @Test
    void test_Equal_Should_ThrowBusinessRuleException_When_VariableTypeNotEquals() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("number")
            .value("1")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("string")
            .value("Test")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("Equal", left, right);

        assertThrows(IllegalArgumentException.class, rule::evaluate);
    }

    @Test
    void test_Equal_Should_ReturnFalse_When_ParametersAreString() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("string")
            .value("test")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("string")
            .value("Test")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("Equal", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(false, evaluate);
    }

    @Test
    void test_NotEqual_Should_ReturnTrue_When_ParametersAreString() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("string")
            .value("Test1")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("string")
            .value("Test")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("NotEqual", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(true, evaluate);
    }

    @Test
    void test_NotEqual_Should_ReturnFalse_When_ParametersAreString() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("string")
            .value("Test")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("string")
            .value("Test")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("NotEqual", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(false, evaluate);
    }

    @Test
    void test_Greater_Should_ReturnFalse_When_ParametersAreNumber() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("number")
            .value("1")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("number")
            .value("2")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("Greater", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(false, evaluate);
    }

    @Test
    void test_Greater_Should_ReturnTrue_When_ParametersAreNumber() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("number")
            .value("2")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("number")
            .value("1")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("Greater", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(true, evaluate);
    }

    @Test
    void test_Greater_Should_ThrowBusinessRuleException_When_VariableTypeDifferentNumber()
        throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("string")
            .value("2")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("string")
            .value("1")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("Greater", left, right);

        assertThrows(IllegalArgumentException.class, rule::evaluate);
    }

    @Test
    void test_Less_Should_ReturnFalse_When_ParametersAreNumber() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("number")
            .value("2")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("number")
            .value("1")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("Less", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(false, evaluate);
    }

    @Test
    void test_Less_Should_ReturnTrue_When_ParametersAreNumber() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("number")
            .value("1")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("number")
            .value("2")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("Less", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(true, evaluate);
    }

    @Test
    void test_Less_Should_ThrowBusinessRuleException_When_VariableTypeDifferentNumber()
        throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("string")
            .value("2")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("string")
            .value("1")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("Less", left, right);

        assertThrows(IllegalArgumentException.class, () -> rule.evaluate());
    }

    @Test
    void test_NotIn_Should_ReturnTrue_When_RightVariableIsArrayNumber() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("number")
            .value("4")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("arrayNumber")
            .value("1,2,3")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("NotIn", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(true, evaluate);
    }

    @Test
    void test_NotIn_Should_ReturnFalse_When_RightVariableIsArrayNumber() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("number")
            .value("1")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("arrayNumber")
            .value("1,2,3")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("NotIn", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(false, evaluate);
    }

    @Test
    void test_NotIn_Should_ReturnTrue_When_RightVariableIsArrayString() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("string")
            .value("ORIGINAL")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("arrayString")
            .value("muy, pixi, pecado")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("NotIn", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(true, evaluate);
    }

    @Test
    void test_NotIn_Should_ReturnFalse_When_RightVariableIsArrayString() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("string")
            .value("muy")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("arrayString")
            .value("muy, pixi, pecado")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("NotIn", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(false, evaluate);
    }

    @Test
    void test_In_Should_ReturnTrue_When_RightVariableIsArrayNumber() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("number")
            .value("1")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("arrayNumber")
            .value("1,2,3")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("In", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(true, evaluate);
    }

    @Test
    void test_In_Should_ReturnFalse_When_RightVariableIsArrayNumber() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("number")
            .value("4")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("arrayNumber")
            .value("1,2,3")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("In", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(false, evaluate);
    }

    @Test
    void test_In_Should_ReturnTrue_When_RightVariableIsArrayString() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("string")
            .value("muy")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("arrayString")
            .value("muy, pixi, pecado")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("In", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(true, evaluate);
    }

    @Test
    void test_In_Should_ReturnFalse_When_RightVariableIsArrayString() throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("string")
            .value("theCut")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("arrayString")
            .value("muy, pixi, pecado")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("In", left, right);
        Boolean evaluate = rule.evaluate();

        assertEquals(false, evaluate);
    }

    @Test
    void test_NotIn_Should_ThrowBusinessRuleException_When_VariableNotIsArray()
        throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("string")
            .value("2")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("string")
            .value("1")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("NotIn", left, right);

        assertThrows(IllegalArgumentException.class, () -> rule.evaluate());
    }

    @Test
    void test_In_Should_ThrowBusinessRuleException_When_VariableNotIsArray()
        throws BusinessRuleException {

        //Data
        RuleVariable left = RuleVariable.builder()
            .type("string")
            .value("2")
            .build();

        RuleVariable right = RuleVariable.builder()
            .type("string")
            .value("1")
            .build();

        //Call
        AbstractRule rule = ruleFactory.createRule("In", left, right);

        assertThrows(IllegalArgumentException.class, () -> rule.evaluate());
    }
}
