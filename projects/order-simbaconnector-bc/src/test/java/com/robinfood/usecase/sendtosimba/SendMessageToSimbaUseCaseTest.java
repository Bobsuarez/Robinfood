package com.robinfood.usecase.sendtosimba;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.SimbaContractDTO;
import com.robinfood.dtos.response.OrderElectronicBillingDTO;
import com.robinfood.dtos.response.ResponseDTO;
import com.robinfood.exceptions.ApiClientsException;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.mocks.OrderElectronicBillingDTOMock;
import com.robinfood.repository.connectorsimba.IConnectorSimbaRepository;
import com.robinfood.usecase.buildcontratsimba.IBuildContractSimbaUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import com.robinfood.util.ValidateNodeUtil;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SendMessageToSimbaUseCaseTest {

    @Mock
    private IBuildContractSimbaUseCase buildContractSimbaUseCase;

    @Mock
    private IConnectorSimbaRepository connectorSimbaRepository;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString(), any());

        mockStatic(ValidateNodeUtil.class);
        doNothing().when(ValidateNodeUtil.class);
        ValidateNodeUtil.validateNode(any());

        System.setProperty("URL_QR", "https://catalogo-vpfe-hab.dian.gov.co/document/searchqr?documentkey=");
    }

    @AfterEach
    public void setupAfter() {
        clearAllCaches();
    }

    private final OrderElectronicBillingDTO orderElectronicBillingDTODefault =
            OrderElectronicBillingDTOMock.getDefaultAcceptedAndIssuable();

    private final OrderElectronicBillingDTO getOrderElectronicBillingDTODefaultAndNotIssuable =
            OrderElectronicBillingDTOMock.getDefaultAcceptedAndNotIssuable();

    private final OrderElectronicBillingDTO getOrderElectronicBillingDTODefaultAndIssuableNotFound =
            OrderElectronicBillingDTOMock.getDefaultAcceptedAndIssuableNotFound();

    TransactionRequestDTO transactionRequestDTO = TransactionRequestDTO.builder().id(1L).build();
    SimbaContractDTO simbaContractDTO = SimbaContractDTO.builder()
            .lineas(new ArrayList<>())
            .build();

    @Test
    void test_SendMessageToSimbaUseCase_Accepted() {

        when(buildContractSimbaUseCase.invoke(transactionRequestDTO))
                .thenReturn(simbaContractDTO);

        when(connectorSimbaRepository.invoke(simbaContractDTO)).thenReturn(
                ObjectMapperSingleton.stringToJsonNode(orderElectronicBillingDTODefault.getPayload().toString())
        );

        SendMessageToSimbaUseCase sendMessageToSimbaUseCase = new SendMessageToSimbaUseCase(connectorSimbaRepository,
                buildContractSimbaUseCase);

        OrderElectronicBillingDTO orderElectronicBillingDTO = sendMessageToSimbaUseCase
                .invoke(transactionRequestDTO);

        Assertions.assertEquals(200, orderElectronicBillingDTO.getStatusCode());

        verify(connectorSimbaRepository, times(1))
                .invoke(simbaContractDTO);
    }

    @Test
    void test_SendMessageToSimbaUseCase_Not_Accepted() {

        when(buildContractSimbaUseCase.invoke(transactionRequestDTO))
                .thenReturn(simbaContractDTO);

        when(connectorSimbaRepository.invoke(simbaContractDTO)).thenReturn(
                ObjectMapperSingleton.stringToJsonNode(
                        getOrderElectronicBillingDTODefaultAndNotIssuable.getPayload()
                                .toString()
                )
        );

        SendMessageToSimbaUseCase sendMessageToSimbaUseCase = new SendMessageToSimbaUseCase(
                connectorSimbaRepository,
                buildContractSimbaUseCase
        );

        OrderElectronicBillingDTO orderElectronicBillingDTO = sendMessageToSimbaUseCase
                .invoke(transactionRequestDTO);

        Assertions.assertEquals(500, orderElectronicBillingDTO.getStatusCode());

        verify(connectorSimbaRepository, times(1))
                .invoke(simbaContractDTO);
    }

    @Test
    void test_SendMessageToSimbaUseCase_When_EmisibleNotFound_Should_StatusInternalError() {

        when(buildContractSimbaUseCase.invoke(transactionRequestDTO))
                .thenReturn(simbaContractDTO);

        when(connectorSimbaRepository.invoke(simbaContractDTO)).thenReturn(
                ObjectMapperSingleton.stringToJsonNode(
                        getOrderElectronicBillingDTODefaultAndIssuableNotFound.getPayload()
                                .toString()
                )
        );

        SendMessageToSimbaUseCase sendMessageToSimbaUseCase = new SendMessageToSimbaUseCase(
                connectorSimbaRepository,
                buildContractSimbaUseCase
        );

        OrderElectronicBillingDTO orderElectronicBillingDTO = sendMessageToSimbaUseCase
                .invoke(transactionRequestDTO);

        Assertions.assertEquals(500, orderElectronicBillingDTO.getStatusCode());

        verify(connectorSimbaRepository, times(1))
                .invoke(simbaContractDTO);
    }

    @Test
    void test_SendMessageToSimbaUseCase_Accepted_With_500() {

        JsonNode responseSimba = ObjectMapperSingleton.stringToJsonNode(
                orderElectronicBillingDTODefault.getPayload().toString());

        ObjectNode respuestaUnitaria = (ObjectNode) responseSimba.path("RespuestaUnitaria");
        ObjectNode encabezadoRespuesta = (ObjectNode) respuestaUnitaria.path("EncabezadoRespuesta");

        encabezadoRespuesta.put("SolicitudAceptada", false);

        when(buildContractSimbaUseCase.invoke(transactionRequestDTO))
                .thenReturn(simbaContractDTO);

        when(connectorSimbaRepository.invoke(simbaContractDTO)).thenReturn(
                responseSimba
        );

        SendMessageToSimbaUseCase sendMessageToSimbaUseCase = new SendMessageToSimbaUseCase(connectorSimbaRepository,
                buildContractSimbaUseCase);

        OrderElectronicBillingDTO orderElectronicBillingDTO = sendMessageToSimbaUseCase
                .invoke(transactionRequestDTO);

        Assertions.assertEquals(500, orderElectronicBillingDTO.getStatusCode());

        verify(connectorSimbaRepository, times(1))
                .invoke(simbaContractDTO);
    }

    @Test
    void testInvoke_Failure() {

        when(buildContractSimbaUseCase.invoke(any(TransactionRequestDTO.class)))
                .thenThrow(new ArithmeticException());

        SendMessageToSimbaUseCase sendMessageToSimbaUseCase = new SendMessageToSimbaUseCase(connectorSimbaRepository,
                buildContractSimbaUseCase);

        ApiClientsException thrown = assertThrows(ApiClientsException.class, () -> {
            sendMessageToSimbaUseCase.invoke(transactionRequestDTO);
        });

        assertNotNull(thrown);
        verify(buildContractSimbaUseCase).invoke(transactionRequestDTO);
    }

    @Test
    void testInvoke_Failure_ApplicationException() {

        when(buildContractSimbaUseCase.invoke(transactionRequestDTO))
                .thenReturn(simbaContractDTO);

        when(connectorSimbaRepository.invoke(simbaContractDTO))
                .thenThrow(new ApiClientsException(ResponseDTO.builder().code(500).message("").build(),""));

        SendMessageToSimbaUseCase sendMessageToSimbaUseCase = new SendMessageToSimbaUseCase(connectorSimbaRepository,
                buildContractSimbaUseCase);

        ApiClientsException thrown = assertThrows(ApiClientsException.class, () -> {
            sendMessageToSimbaUseCase.invoke(transactionRequestDTO);
        });

        assertNotNull(thrown);
        verify(buildContractSimbaUseCase).invoke(transactionRequestDTO);
    }

    @Test
    void testInvoke_Failure_NullPointException() {

        when(buildContractSimbaUseCase.invoke(any(TransactionRequestDTO.class)))
                .thenThrow(new NullPointerException());

        SendMessageToSimbaUseCase sendMessageToSimbaUseCase = new SendMessageToSimbaUseCase(connectorSimbaRepository,
                buildContractSimbaUseCase);

        ApiClientsException thrown = assertThrows(ApiClientsException.class, () -> {
            sendMessageToSimbaUseCase.invoke(transactionRequestDTO);
        });

        assertNotNull(thrown);
        verify(buildContractSimbaUseCase).invoke(transactionRequestDTO);
    }

    @Test
    void test_Invoke_SendMessageToSimbaUseCase_Accepted_Should_When_Instance() {

        SendMessageToSimbaUseCase sendMessageToSimbaUseCase =
                new SendMessageToSimbaUseCase();

        Assert.notNull(sendMessageToSimbaUseCase);
    }
}
