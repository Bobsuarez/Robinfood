package com.robinfood.app.usecases.changestatusorders;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.UserDTO;
import com.robinfood.core.dtos.staterequestdto.StateChangeRequestDTO;
import com.robinfood.core.dtos.staterespondto.StateChangeRespondDTO;
import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;
import com.robinfood.core.models.domain.Token;
import com.robinfood.repository.changestateorders.IChangeStateOrdersRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeOrderStateUseCaseTest {

    private final String TOKEN = "token";
    @InjectMocks
    ChangeOrderStateUseCase changeOrderStateUseCase;

    @Mock
    IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    IChangeStateOrdersRepository changeStateOrdersRepository;

    @BeforeAll
    static void beforeAll() {
        UserDTO userDTO = new UserDTO(DEFAULT_STRING_VALUE, null, "4");
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDTO, null));
    }

    @Test
    void test_When_Change_Order_State_Then_Return_Queue_Message() {

        StateChangeRequestDTO stateChangeRequestDTO = new StateChangeRequestDTO();
        ChangeStateOrderRespondEntity stateChangeRespondDTO = new ChangeStateOrderRespondEntity();
        StateChangeRespondDTO state = new StateChangeRespondDTO();

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(Token.builder()
                .accessToken(TOKEN)
                .build());

        when(changeStateOrdersRepository.invoke(stateChangeRequestDTO, TOKEN)).thenReturn(
                CompletableFuture.completedFuture(stateChangeRespondDTO)
        );

        StateChangeRespondDTO stateChangeRespondDTO1 = changeOrderStateUseCase.invoke(stateChangeRequestDTO);

        assertEquals(state, stateChangeRespondDTO1);
    }
}
