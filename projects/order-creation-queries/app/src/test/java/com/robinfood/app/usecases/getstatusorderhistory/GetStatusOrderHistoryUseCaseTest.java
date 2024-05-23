package com.robinfood.app.usecases.getstatusorderhistory;

import com.robinfood.app.mocks.statusorderhistory.StatusOrderHistoryDTOMock;
import com.robinfood.app.mocks.statusorderhistory.UserDTOMock;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.statusorderhistory.IStatusOrderHistoryRepository;
import com.robinfood.repository.user.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetStatusOrderHistoryUseCaseTest {

    @Mock
    private IStatusOrderHistoryRepository statusOrderHistoryRepository;
    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    @Mock
    private IUserRepository userRepository;
    @InjectMocks
    private GetStatusOrderHistoryUseCase getStatusOrderHistoryUseCase;

    @Test
    void test_invoke_Should_GetStatusOrderHistory_When_invokeUseCase() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(statusOrderHistoryRepository.getStatusOrderHistory(anyString(), anyString()))
                .thenReturn(new Result.Success<>(
                        List.of(StatusOrderHistoryDTOMock.getDataDefault())
                ));

        when(userRepository.getUsersByIds(anyString(), anyList()))
                .thenReturn(new Result.Success<>(
                        List.of(UserDTOMock.getDataDefault())
                ));

        getStatusOrderHistoryUseCase.invoke("uuid");

        verify(getTokenBusinessCapabilityUseCase)
                .invoke();
        verify(statusOrderHistoryRepository)
                .getStatusOrderHistory(anyString(), anyString());
    }

    @Test
    void test_invoke_Should_ErrorException_When_invokeUseCase() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(statusOrderHistoryRepository.getStatusOrderHistory(anyString(), anyString()))
                .thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        assertThrows(Exception.class, () -> getStatusOrderHistoryUseCase.invoke("uuid"));
    }
}