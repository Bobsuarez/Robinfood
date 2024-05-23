package com.robinfood.paymentmethodsbc.services.steps;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.robinfood.paymentmethodsbc.dto.steps.StepGroupDTO;
import com.robinfood.paymentmethodsbc.enums.PlatformType;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.List;
import org.junit.jupiter.api.Test;

class TransactionStepGroupDefinitionsFactoryTest {

    @Test
    public void testGetGenerateTransactionDefinitionsGatewayShouldBeOk() throws BaseException {

        List<StepGroupDTO> result = TransactionStepGroupDefinitionsFactory.getGenerateTransactionDefinitions(
            PlatformType.GATEWAY
        );

        List<StepGroupDTO> list = StepGroupDefinitions.GENERATE_TRANSACTION_STEPS;

        assertEquals(list, result);
    }

    @Test
    public void testGetGenerateTransactionDefinitionsDataphoneShouldBeOk() throws BaseException {

        List<StepGroupDTO> result = TransactionStepGroupDefinitionsFactory.getGenerateTransactionDefinitions(
            PlatformType.DATAPHONE
        );

        List<StepGroupDTO> list = StepGroupDefinitions.GENERATE_TRANSACTION_DATAPHONE_STEPS;

        assertEquals(list, result);
    }

    @Test
    public void testGetInitialTransactionDefinitionsGatewayShouldBeOk() throws BaseException {

        List<StepGroupDTO> result = TransactionStepGroupDefinitionsFactory.getInitialTransactionDefinitions(
            PlatformType.GATEWAY
        );

        List<StepGroupDTO> list = StepGroupDefinitions.INITIAL_TRANSACTION_STEPS;

        assertEquals(list, result);
    }

    @Test
    public void testGetInitialTransactionDefinitionsDataphoneShouldBeOk() throws BaseException {

        List<StepGroupDTO> result = TransactionStepGroupDefinitionsFactory.getInitialTransactionDefinitions(
            PlatformType.DATAPHONE
        );

        List<StepGroupDTO> list = StepGroupDefinitions.INITIAL_TRANSACTION_DATAPHONE_STEPS;

        assertEquals(list, result);
    }

    @Test
    public void testGetInitialTransactionDefinitionsNoValidationIntegrationsShouldBeOk() throws BaseException {

        List<StepGroupDTO> result = TransactionStepGroupDefinitionsFactory.getInitialTransactionDefinitions(
            PlatformType.INTEGRATIONS
        );

        List<StepGroupDTO> list = StepGroupDefinitions.GENERATE_TRANSACTION_NO_VALIDATION_STEPS;

        assertEquals(list, result);
    }

    @Test
    public void testGetInitialTransactionDefinitionsNoValidationCashShouldBeOk() throws BaseException {

        List<StepGroupDTO> result = TransactionStepGroupDefinitionsFactory.getInitialTransactionDefinitions(
            PlatformType.CASH
        );

        List<StepGroupDTO> list = StepGroupDefinitions.GENERATE_TRANSACTION_NO_VALIDATION_STEPS;

        assertEquals(list, result);
    }

    @Test
    public void testGetInitialTransactionDefinitionsShouldBeError() {

        assertThrows(
            BaseException.class,
            () -> TransactionStepGroupDefinitionsFactory.getInitialTransactionDefinitions(null)
        );
    }

    @Test
    public void testGetGenerateTransactionDefinitionsShouldBeError() {

        assertThrows(
            BaseException.class,
            () -> TransactionStepGroupDefinitionsFactory.getGenerateTransactionDefinitions(null)
        );
    }

    @Test
    public void testConstructorPrivateShouldBeError()
        throws NoSuchMethodException {
        Constructor<TransactionStepGroupDefinitionsFactory> constructor
            = TransactionStepGroupDefinitionsFactory.class.getDeclaredConstructor();

        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);

        assertThrows(
            InvocationTargetException.class,
            constructor::newInstance
        );
    }
}