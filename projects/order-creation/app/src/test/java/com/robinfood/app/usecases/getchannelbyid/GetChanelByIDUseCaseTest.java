package com.robinfood.app.usecases.getchannelbyid;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.mocks.domain.configuration.ChannelMock;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.repository.configurationsbc.IConfigurationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetChanelByIDUseCaseTest {

    @Mock
    private IConfigurationRepository configurationRepository;

    @InjectMocks
    private GetChannelByIdUseCase getChannelByIdUseCase;

    @Test
    void test_Given_Request_Then_Channel_Success() {

        // Arrange
        final String token = "token";
        final Long idChannel = 10L;

        final TransactionRequestDTO request = new TransactionRequestDTOMock().transactionRequestDTO;

        when(configurationRepository.getChannel(token, idChannel)).thenReturn(ChannelMock.build());

        // Act
        getChannelByIdUseCase.invoke("token", request);

        // Assert
        verify(configurationRepository).getChannel(anyString(), anyLong());
    }

    @Test
    void test_Given_Request_Then_Channel_Params_Is_Null() {

        assertThrows(
                NullPointerException.class,
                () -> getChannelByIdUseCase.invoke(null, null)
        );
    }
}
