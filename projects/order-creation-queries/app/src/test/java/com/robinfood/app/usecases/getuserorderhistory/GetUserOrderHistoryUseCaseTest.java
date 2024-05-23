package com.robinfood.app.usecases.getuserorderhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.mocks.dto.ResponseOrderHistoryMock;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.userorderhistory.IUserOrderHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

/**
 * Test of GetUserOrderHistoryUseCase
 */
@ExtendWith(MockitoExtension.class)
public class GetUserOrderHistoryUseCaseTest {

    @Mock
    private IUserOrderHistoryRepository userOrderHistoryRepository;
    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    @InjectMocks
    private GetUserOrderHistoryUseCase useCase;

    @Test
    void test_OrderHistory_Returns_Correctly() {
        final String token = "token";

        EntityDTO<ResponseOrderDTO> responseMock = ResponseOrderHistoryMock.getEntityResponseOrderHistoryMock();

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
            TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(userOrderHistoryRepository.getOrderHistory(
            1,
            1,
            token,
            1L
        )).thenReturn(new Result.Success<>(responseMock));

        final Result<EntityDTO<ResponseOrderDTO>> result = useCase.invoke(
            1,
            1,
            1L
        );

        assertEquals(responseMock, ((Result.Success<EntityDTO<ResponseOrderDTO>>) result).getData());
    }

    @Test
    void test_OrderHistory_Returns_Failure() {
        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
            TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(userOrderHistoryRepository.getOrderHistory(
            1,
            1,
            token,
            1L
        )).thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        final Result<EntityDTO<ResponseOrderDTO>> result = useCase.invoke(
            1,
            1,
            1L
        );

        assertTrue(result instanceof Result.Error);
        assertTrue(((Result.Error) result).getException() instanceof Exception);
    }
}
