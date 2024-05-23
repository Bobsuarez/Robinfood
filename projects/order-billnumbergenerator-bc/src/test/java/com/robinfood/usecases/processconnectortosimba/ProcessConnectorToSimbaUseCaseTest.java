package com.robinfood.usecases.processconnectortosimba;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.datamock.TransactionRequestDTOMock;
import com.robinfood.dtos.sendordertosimba.request.ThirdPartyDTO;
import com.robinfood.dtos.sendordertosimba.request.TransactionRequestDTO;
import com.robinfood.dtos.sendordertosimba.response.TransactionResponseDTO;
import com.robinfood.entities.OrderElectronicBillingsEntity;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.exceptions.DataBaseException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.saveelectronicbillings.ISaveElectronicBillingsUseCase;
import com.robinfood.usecases.savethirdparty.ISaveThirdPartyUseCase;
import com.robinfood.usecases.sendmessagetosimba.ISendMessageToSimbaUseCase;
import com.robinfood.util.LogsUtil;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_CONNECTION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class ProcessConnectorToSimbaUseCaseTest {

    @Mock
    private ISaveElectronicBillingsUseCase saveElectronicBillingsUseCase;

    @Mock
    private ISaveThirdPartyUseCase saveThirdPartyUseCase;

    @Mock
    private ISendMessageToSimbaUseCase sendMessageToSimbaUseCase;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString(), any());
    }

    @AfterEach
    public void setupAfter() {
        clearAllCaches();
    }

    @Test
    void test_ProcessConnectorToSimbaUseCase_Accepted() {

        doNothing().when(saveThirdPartyUseCase).invoke(anyLong(), any(ThirdPartyDTO.class));

        doNothing().when(saveElectronicBillingsUseCase).invoke(any(OrderElectronicBillingsEntity.class));

        when(sendMessageToSimbaUseCase.invoke(any(TransactionRequestDTO.class), anyString()))
                .thenReturn("Data response");

        ProcessConnectorToSimbaUseCase processConnectorToSimbaUseCase =
                new ProcessConnectorToSimbaUseCase(
                        saveElectronicBillingsUseCase,
                        saveThirdPartyUseCase,
                        sendMessageToSimbaUseCase
                );

        TransactionResponseDTO transactionResponseDTO =
                processConnectorToSimbaUseCase.invoke(TransactionRequestDTOMock.getDefault(), "token");

        Assertions.assertEquals(transactionResponseDTO.getOrderUuid(), UUID.fromString("da2bd496-1083-4c8c-9fe7-2403e442b73c"));
    }

    @Test
    void test_ProcessConnectorToSimbaUseCase_Accepted_But_NotPaid() {

        doNothing().when(saveThirdPartyUseCase).invoke(anyLong(), any(ThirdPartyDTO.class));

        doNothing().when(saveElectronicBillingsUseCase).invoke(any(OrderElectronicBillingsEntity.class));

        when(sendMessageToSimbaUseCase.invoke(any(TransactionRequestDTO.class), anyString()))
                .thenReturn("Data response");

        ProcessConnectorToSimbaUseCase processConnectorToSimbaUseCase =
                new ProcessConnectorToSimbaUseCase(
                        saveElectronicBillingsUseCase,
                        saveThirdPartyUseCase,
                        sendMessageToSimbaUseCase
                );

        TransactionResponseDTO transactionResponseDTO =
                processConnectorToSimbaUseCase.invoke(TransactionRequestDTOMock.getDefaultNotPaid(), "token");

        Assertions.assertEquals(transactionResponseDTO.getFullName(), "primer nombre y apellido");

        clearAllCaches();
    }

    @Test
    void test_ProcessConnectorToSimbaUseCase_Should_AppException_When_NoInsertRecordThirdParty() {

        doThrow(new DataBaseException(ResponseMapper
                .buildWithError(
                        HttpStatusConstants.SC_CONFLICT.getCodeHttp(),
                        ERROR_CONNECTION.getMessage(),
                        DEFAULT_BOOLEAN_TRUE),
                ERROR_CONNECTION.getMessage()))
                .when(saveThirdPartyUseCase).invoke(anyLong(), any(ThirdPartyDTO.class));

        doNothing().when(saveElectronicBillingsUseCase).invoke(any(OrderElectronicBillingsEntity.class));

        ProcessConnectorToSimbaUseCase processConnectorToSimbaUseCase =
                new ProcessConnectorToSimbaUseCase(
                        saveElectronicBillingsUseCase,
                        saveThirdPartyUseCase,
                        sendMessageToSimbaUseCase
                );

        Assertions.assertThrows(ApplicationException.class, () -> {
            processConnectorToSimbaUseCase.invoke(TransactionRequestDTOMock.getDefault(), "token");
        });
    }

    @Test
    void test_Invoke_ProcessConnectorToSimbaUseCase_Should_When_Instance() {

        ProcessConnectorToSimbaUseCase processConnectorToSimbaUseCase =
                new ProcessConnectorToSimbaUseCase();

        Assert.notNull(processConnectorToSimbaUseCase);
    }
}