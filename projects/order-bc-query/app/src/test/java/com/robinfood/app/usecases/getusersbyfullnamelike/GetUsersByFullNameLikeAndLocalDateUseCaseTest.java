package com.robinfood.app.usecases.getusersbyfullnamelike;

import com.robinfood.core.entities.OrderUserDataEntity;
import com.robinfood.repository.userdata.IUserDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_START;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUsersByFullNameLikeAndLocalDateUseCaseTest {

    @Mock
    private IUserDataRepository userDataRepository;

    @InjectMocks
    private GetUsersByFullNameLikeAndLocalDateUseCase getUsersByFullNameLikeAndLocalDateUseCase;

    @Test
    void test_invoke_Should_UsersList_When_InvokeTheUseCase() {

        final String firstName = "";
        final String lastName = "";
        final Long storeId = 1L;

        final Map<String, LocalDateTime> localDateTimeMap = Map.of(
                LOCAL_DATE_TIME_START,
                LocalDateTime.now(),
                LOCAL_DATE_TIME_END,
                LocalDateTime.now()
        );

        when(userDataRepository.findAllByFirstNameContainingOrLastNameContainingAndCreatedAtBetween(
                firstName,
                lastName,
                localDateTimeMap.get(LOCAL_DATE_TIME_START),
                localDateTimeMap.get(LOCAL_DATE_TIME_END),
                storeId
                )
        ).thenReturn(
                List.of(OrderUserDataEntity.builder().build())
        );

        getUsersByFullNameLikeAndLocalDateUseCase.invoke("", "", localDateTimeMap, storeId);

        verify(userDataRepository)
                .findAllByFirstNameContainingOrLastNameContainingAndCreatedAtBetween(
                        anyString(),
                        anyString(),
                        any(LocalDateTime.class),
                        any(LocalDateTime.class),
                        anyLong()
                );
    }
}