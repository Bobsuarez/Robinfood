package com.robinfood.app.usecases.createorderpayments;

import static com.robinfood.app.datamocks.dto.input.RequestPaymentMethodDTODataMock.buildRequestPaymentMethodDTO;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.robinfood.app.datamocks.dto.input.RequestPaymentDetailDTODataMock;
import com.robinfood.app.datamocks.dto.output.OutputCreatedOrderDTODataMock;
import com.robinfood.app.usecases.createorderpaymentdetail.ICreateOrderPaymentDetailUseCase;
import com.robinfood.core.dtos.request.transaction.RequestPaymentDetailDTO;
import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;
import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import com.robinfood.core.entities.OrderPaymentEntity;
import com.robinfood.repository.orderpayment.IOrderPaymentRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateOrderPaymentUseCaseTest {

    @Mock
    private ICreateOrderPaymentDetailUseCase mockCreateOrderPaymentDetailUseCase;

    @Mock
    private IOrderPaymentRepository mockOrderPaymentRepository;

    @InjectMocks
    private CreateOrderPaymentUseCase createOrderPaymentUseCase;

    private final OrderPaymentEntity orderPaymentEntity = new OrderPaymentEntity(
        null,
        0.0,
        1L,
        1L,
        1L,
        1L,
        8900.0,
        0.0,
        null,
        8900.0
    );

    private final OrderPaymentEntity orderPaymentMultipleEntity = new OrderPaymentEntity(
        null,
        0.0,
        null,
        1L,
        1L,
        1L,
        4450.0,
        0.0,
        null,
        4450.0
    );

    @Test
    void test_CreateOrderPayment_When_Save_Success() {

        // Arrange
        when(mockOrderPaymentRepository.save(any())).thenReturn(orderPaymentEntity);

        // Act
        Boolean result = createOrderPaymentUseCase.invoke(
            singletonList(buildRequestPaymentMethodDTO()),
            new OutputCreatedOrderDTODataMock().getDataDefaultList()
        ).join();

        // Assert
        Mockito.verify(mockOrderPaymentRepository).save(any());
        assertTrue(result);
    }

    @Test
    void test_CreateOrderPayment_With_TotalValidate_One_When_Save_Success() {

        // Arrange
        ResponseCreatedOrderDTO responseCreatedOrderDTO = new OutputCreatedOrderDTODataMock().getDataDefault();
        responseCreatedOrderDTO.setTotal(0.0);

        when(mockOrderPaymentRepository.save(any())).thenReturn(orderPaymentEntity);

        // Act
        Boolean result = createOrderPaymentUseCase.invoke(
            singletonList(buildRequestPaymentMethodDTO()),
            singletonList(responseCreatedOrderDTO)
        ).join();

        // Assert
        Mockito.verify(mockOrderPaymentRepository).save(any());
        assertTrue(result);
    }

    @Test
    void test_CreateOrderPayment_When_Validation_False() {

        // Arrange
        ResponseCreatedOrderDTO responseCreatedOrderDTO = new OutputCreatedOrderDTODataMock().getDataDefault();
        responseCreatedOrderDTO.setTotal(20000.0);

        when(mockOrderPaymentRepository.save(any())).thenReturn(orderPaymentEntity);

        // Act
        Boolean result = createOrderPaymentUseCase.invoke(
            singletonList(buildRequestPaymentMethodDTO()),
            singletonList(responseCreatedOrderDTO)
        ).join();

        // Assert
        Mockito.verify(mockOrderPaymentRepository).save(any());
        assertFalse(result);
    }

    @Test
    void test_CreateOrderPayment_With_Detail_When_Save_Success() {

        // Arrange
        ArgumentCaptor<RequestPaymentDetailDTO> requestPaymentDetailDTOArgumentCaptor =
            ArgumentCaptor.forClass(RequestPaymentDetailDTO.class);
        RequestPaymentMethodDTO requestPaymentMethodDTO = buildRequestPaymentMethodDTO();
        requestPaymentMethodDTO.setDetail(new RequestPaymentDetailDTODataMock().getDataDefault());

        when(mockOrderPaymentRepository.save(any())).thenReturn(orderPaymentEntity);

        // Act
        Boolean result = createOrderPaymentUseCase.invoke(
            singletonList(requestPaymentMethodDTO),
            new OutputCreatedOrderDTODataMock().getDataDefaultList()
        ).join();

        // Assert
        Mockito.verify(mockOrderPaymentRepository).save(any());
        Mockito.verify(mockCreateOrderPaymentDetailUseCase).invoke(
            requestPaymentDetailDTOArgumentCaptor.capture()
        );
        assertTrue(result);
        assertNotNull(requestPaymentDetailDTOArgumentCaptor.getValue().getOrderPaymentId());
    }

    @Test
    void test_Create_Order_Payment_Multiple_When_Save_Success() {

        // Arrange
        final List<RequestPaymentMethodDTO> listPaymentMultipleMethodDTOS = Arrays
            .asList(buildRequestPaymentMethodDTO(), buildRequestPaymentMethodDTO());
        final OrderPaymentEntity orderPaymentEntity = orderPaymentMultipleEntity;

        when(mockOrderPaymentRepository.save(any())).thenReturn(orderPaymentEntity);

        // Act
        Boolean result = createOrderPaymentUseCase.invoke(
            listPaymentMultipleMethodDTOS,
            new OutputCreatedOrderDTODataMock().getDataDefaultList()
        ).join();

        // Assert
        Mockito.verify(mockOrderPaymentRepository, times(2)).save(any());
        assertTrue(result);
    }
}
