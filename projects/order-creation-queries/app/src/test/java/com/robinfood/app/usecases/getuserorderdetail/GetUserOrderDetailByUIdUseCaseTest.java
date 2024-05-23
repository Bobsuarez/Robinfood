package com.robinfood.app.usecases.getuserorderdetail;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.robinfood.app.usecases.getservicedetail.IGetServiceDetailByUIdUseCase;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDetailDTO;
import com.robinfood.core.dtos.services.ServiceDeliveryCourierDTO;
import com.robinfood.core.dtos.services.ServiceDetailDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.mocks.dto.ResponseOrderDetailMock;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.userorderdetail.IUserOrderDetailRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

/**
 * Test of GetUserOrderDetailByUIdUseCase
 */
@ExtendWith(MockitoExtension.class)
public class GetUserOrderDetailByUIdUseCaseTest {

    @Mock
    private IUserOrderDetailRepository userOrderDetailRepository;
    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    @Mock
    private IGetServiceDetailByUIdUseCase getServiceDetailByUIdUseCase;
    @InjectMocks
    private GetUserOrderDetailByUIdUseCase useCase;

    @Test
    void test_OrderDetail_Returns_Correctly() {
        final String token = "token";

        ResponseOrderDetailDTO responseMock = ResponseOrderDetailMock.getResponseOrderDetail();

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
            TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(userOrderDetailRepository.getOrderDetail(
            "12345abcde",
            token,
            1L
        )).thenReturn(new Result.Success<>(responseMock));

        when(getServiceDetailByUIdUseCase.invoke(
            "12345abcde"
        )).thenReturn(getResultServiceDTO());

        final Result<ResponseOrderDetailDTO> result = useCase.invoke(
            "12345abcde",
            1L
        );

        assertInstanceOf(Result.Success.class, result);

        ResponseOrderDetailDTO orderDetailDTO = ((Result.Success<ResponseOrderDetailDTO>) result).getData();

        assertNotNull(orderDetailDTO.getDeliveryCourier());
        assertThat(orderDetailDTO.getId(), is(equalTo(1L)));
        assertThat(orderDetailDTO.getDeliveryCourier().getName(), is(equalTo("TEST TEST")));
    }

    @Test
    void test_OrderDetail_With_Services_Detail_Error_Returns_Correctly() {
        final String token = "token";

        ResponseOrderDetailDTO responseMock = ResponseOrderDetailMock.getResponseOrderDetail();

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
            TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(userOrderDetailRepository.getOrderDetail(
            "12345abcde",
            token,
            1L
        )).thenReturn(new Result.Success<>(responseMock));

        when(getServiceDetailByUIdUseCase.invoke(
            "12345abcde"
        )).thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        final Result<ResponseOrderDetailDTO> result = useCase.invoke(
            "12345abcde",
            1L
        );

        assertInstanceOf(Result.Success.class, result);

        ResponseOrderDetailDTO orderDetailDTO = ((Result.Success<ResponseOrderDetailDTO>) result).getData();

        assertNotNull(orderDetailDTO);
        assertNull(orderDetailDTO.getDeliveryCourier());
    }

    @Test
    void test_OrderDetail_Without_Services_Detail_Returns_Correctly() {
        final String token = "token";

        ResponseOrderDetailDTO responseMock = ResponseOrderDetailMock.getResponseOrderDetail();

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
            TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(userOrderDetailRepository.getOrderDetail(
            "12345abcde",
            token,
            1L
        )).thenReturn(new Result.Success<>(responseMock));

        when(getServiceDetailByUIdUseCase.invoke(
            "12345abcde"
        )).thenReturn(getResultServiceWithoutDeliveryCourierDTO());

        final Result<ResponseOrderDetailDTO> result = useCase.invoke(
            "12345abcde",
            1L
        );

        assertInstanceOf(Result.Success.class, result);

        ResponseOrderDetailDTO orderDetailDTO = ((Result.Success<ResponseOrderDetailDTO>) result).getData();

        assertNotNull(orderDetailDTO);
        assertNull(orderDetailDTO.getDeliveryCourier());
    }

    @Test
    void test_OrderDetail_Returns_Failure() {
        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
            TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(userOrderDetailRepository.getOrderDetail(
            "12345abcde",
            token,
            1L
        )).thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        final Result<ResponseOrderDetailDTO> result = useCase.invoke(
            "12345abcde",
            1L
        );

        assertTrue(result instanceof Result.Error);
        assertTrue(((Result.Error) result).getException() instanceof Exception);
    }

    private Result<ServiceDetailDTO> getResultServiceDTO() {
        return new Result.Success<>(
            ServiceDetailDTO.builder()
                .id(1L)
                .referenceUid("12345abcde")
                .deliveryCourier(
                    ServiceDeliveryCourierDTO.builder()
                        .name("TEST TEST")
                        .phone("+573100000000")
                        .vehiclePlate("AAA000")
                        .build()
                )
                .build()
        );
    }

    private Result<ServiceDetailDTO> getResultServiceWithoutDeliveryCourierDTO() {
        return new Result.Success<>(
            ServiceDetailDTO.builder()
                .id(1L)
                .referenceUid("12345abcde")
                .integrationId("123abc")
                .build()
        );
    }
}
