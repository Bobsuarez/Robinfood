package com.robinfood.app.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.app.mocks.OrderResponseDTOMocks;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.inputrequestvalidation.IInputRequestValidationUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionresponsedto.OrderResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionResponseDTO;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.logging.LoggingAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoggingAspectTest {

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    private Signature signatureMock;

    @Mock
    private MethodSignature signatureController;

    @Mock
    private JoinPoint joinPoint;

    @Mock
    private IInputRequestValidationUseCase inputRequestValidationUseCase;

    @InjectMocks
    @Spy
    private LoggingAspect loggingAspect;

    private final Long TRANSACTION_ID = 1L;

    private final String UUID = "1234";

    private final List<OrderResponseDTO> orderResponseDTOS = Collections.singletonList(
        OrderResponseDTOMocks.build()
    );

    TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();

    TransactionRequestDTO transactionRequest = transactionRequestDTOMocks.transactionRequestDTO;

    private final TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO(
        TRANSACTION_ID,
        "",
        orderResponseDTOS,
        UUID
    );

    Object[] args ={transactionRequest};

    @Test
    void when_logAroundUseCases_Is_OK() throws Throwable {

        when(proceedingJoinPoint.getSignature()).thenReturn(signatureMock);
        when(signatureMock.getName()).thenReturn("invoke");
        when(proceedingJoinPoint.getTarget()).thenReturn(inputRequestValidationUseCase);
        when(proceedingJoinPoint.proceed()).thenReturn(transactionResponseDTO);
        Object result = loggingAspect.logAroundUseCases(proceedingJoinPoint);
        assertEquals(result, transactionResponseDTO);
    }

    @Test
    void when_logAroundUseCases_Is_Error() throws Throwable {

        when(proceedingJoinPoint.getSignature()).thenReturn(signatureMock);
        when(signatureMock.getName()).thenReturn("invoke");
        when(proceedingJoinPoint.getTarget()).thenReturn(inputRequestValidationUseCase);
        when(proceedingJoinPoint.proceed()).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class,
            () -> loggingAspect.logAroundUseCases(proceedingJoinPoint));
    }

    @Test
    void when_logAroundUseCases_Is_JsonProcessingException() throws Throwable {
        JsonProcessingException e = new JsonProcessingException("Fake error") {};

        when(proceedingJoinPoint.getSignature()).thenReturn(signatureMock);
        when(signatureMock.getName()).thenReturn("invoke");
        when(proceedingJoinPoint.getTarget()).thenReturn(inputRequestValidationUseCase);
        when(proceedingJoinPoint.proceed()).thenReturn(ResponseEntity.ok(transactionResponseDTO));
        Object object = loggingAspect.logAroundUseCases(proceedingJoinPoint);
        assertNotNull(object);
    }

    @Test
    void when_appLoggerException_Is_OK() {
        TransactionCreationException creationException = new TransactionCreationException(
            HttpStatus.FAILED_DEPENDENCY,
            "fail",
            TransactionCreationErrors.CO2_VALIDATION_ERROR);

        when(joinPoint.getSignature()).thenReturn(signatureMock);
        when(signatureMock.getName()).thenReturn("invoke");
        when(joinPoint.getTarget()).thenReturn(inputRequestValidationUseCase);
        loggingAspect.appLoggerException(joinPoint, creationException);
        assertEquals("fail", creationException.getMessage());
    }

    @Test
    void when_appLoggerException_Is_Exception() {

        Exception e = new Exception("fail");

        when(joinPoint.getSignature()).thenReturn(signatureMock);
        when(signatureMock.getName()).thenReturn("invoke");
        when(joinPoint.getTarget()).thenReturn(inputRequestValidationUseCase);
        loggingAspect.appLoggerException(joinPoint, e);
        assertEquals("fail", e.getMessage());
    }

    @Test
    void when_logControllerPost_Is_OK() throws Throwable{
        String[] path = {"/path"};
        when(proceedingJoinPoint.getSignature()).thenReturn(signatureController);

        Mockito.doReturn(path).when(loggingAspect).getValuePostAnnotation(any());
        Mockito.doReturn(path).when(loggingAspect).getValueClassAnnotation(any());
        when(proceedingJoinPoint.getArgs()).thenReturn(args);
        when(proceedingJoinPoint.proceed()).thenReturn(ResponseEntity.ok(transactionResponseDTO));
        Object object = loggingAspect.logControllerPost(proceedingJoinPoint);
        assertNotNull(object);
    }

    @Test
    void when_logControllerGet_Is_OK() throws Throwable{
        String[] path = {"/path"};

        when(proceedingJoinPoint.getSignature()).thenReturn(signatureController);
        Mockito.doReturn(path).when(loggingAspect).getValueGetAnnotation(any());
        Mockito.doReturn(path).when(loggingAspect).getValueClassAnnotation(any());
        when(proceedingJoinPoint.getArgs()).thenReturn(args);
        when(proceedingJoinPoint.proceed()).thenReturn(ResponseEntity.ok(transactionResponseDTO));
        Object object = loggingAspect.logControllerGet(proceedingJoinPoint);
        assertNotNull(object);
    }

    @Test
    void when_logControllerPost_Is_Error() throws Throwable{
        String[] path = {"/path"};
        when(proceedingJoinPoint.getSignature()).thenReturn(signatureController);

        Mockito.doReturn(path).when(loggingAspect).getValuePostAnnotation(any());
        Mockito.doReturn(path).when(loggingAspect).getValueClassAnnotation(any());
        when(proceedingJoinPoint.getArgs()).thenReturn(args);
        when(proceedingJoinPoint.proceed()).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class,
            () -> loggingAspect.logControllerPost(proceedingJoinPoint));
    }

    @Test
    void when_logControllerPost_Value_Empty() throws Throwable{
        String[] path = {"/path"};
        String[] path2 = {};
        when(proceedingJoinPoint.getSignature()).thenReturn(signatureController);

        Mockito.doReturn(path2).when(loggingAspect).getValuePostAnnotation(any());
        Mockito.doReturn(path).when(loggingAspect).getValueClassAnnotation(any());
        when(proceedingJoinPoint.getArgs()).thenReturn(args);
        when(proceedingJoinPoint.proceed()).thenReturn(ResponseEntity.ok(transactionResponseDTO));
        Object object = loggingAspect.logControllerPost(proceedingJoinPoint);
        assertNotNull(object);
    }

    @Test
    void When_pointCutUseCases() {
       loggingAspect.pointCutUseCases();
    }

    @Test
    void When_pointcutPost() {
        loggingAspect.pointcutPost();
    }

    @Test
    void When_pointcutGet() {
        loggingAspect.pointcutGet();
    }
}
