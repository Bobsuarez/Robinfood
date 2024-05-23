package com.robinfood.app.usecases.getuseractiveorder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.mocks.dto.ResponseActiveOrderMock;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.useractiveorder.IUserActiveOrderRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

/**
 * Test of GetUserActiveOrderUseCase
 */
@ExtendWith(MockitoExtension.class)
public class GetUserActiveOrderUseCaseTest {

    @Mock
    private IUserActiveOrderRepository userActiveOrderRepository;
    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    @InjectMocks
    private GetUserActiveOrderUseCase useCase;

    @Test
    void test_ActiveOrders_Returns_Correctly() {
        final String token = "token";

        List<ResponseOrderDTO> responseMock = ResponseActiveOrderMock.getResponseActiveOrderMock();

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
            TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(userActiveOrderRepository.getActiveOrders(
            token,
            1L
        )).thenReturn(new Result.Success<>(responseMock));

        final Result<List<ResponseOrderDTO>> result = useCase.invoke(
            1L
        );

        assertEquals(responseMock, ((Result.Success<List<ResponseOrderDTO>>) result).getData());
    }

    @Test
    void test_ActiveOrders_Returns_Failure() {
        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
            TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(userActiveOrderRepository.getActiveOrders(
            token,
            1L
        )).thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        final Result<List<ResponseOrderDTO>> result = useCase.invoke(
            1L
        );

        assertTrue(result instanceof Result.Error);
        assertTrue(((Result.Error) result).getException() instanceof Exception);
    }
}
