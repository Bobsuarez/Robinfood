package com.robinfood.app.usecases.getuserdatalistbyuserid;

import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.entities.OrderUserDataEntity;
import com.robinfood.repository.userdata.IUserDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserDataListByUserIdAndDateUseCaseTest {

    @Mock
    private IUserDataRepository mockUserDataRepository;

    @InjectMocks
    private GetUserDataListByUserIdAndDateUseCase getUserDataListByUserIdAndDateUseCase;

    private final LocalDate currentTime = LocalDate.of(2021, 6, 3);
    private final LocalDateTime initialTime = LocalDateTime.of(2021, 6, 3, 0, 0, 0);
    private final LocalDateTime finalTime = LocalDateTime.of(2021, 6, 3, 23, 59, 59);
    private final List<OrderUserDataEntity> userDataEntities = new ArrayList<>(Arrays.asList(
            new OrderUserDataEntity(
                    initialTime,
                    "michael@gmail.com",
                    "Michael",
                    1L,
                    "Jordan",
                    "300574846",
                    1L,
                    1L,
                    3L
            ),
            new OrderUserDataEntity(
                    initialTime,
                    "cristiano@gmail.com",
                    "Cristiano",
                    2L,
                    "Messi",
                    "300574846",
                    2L,
                    1L,
                    2L
            ),
            new OrderUserDataEntity(
                    initialTime,
                    "Lionel@gmail.com",
                    "Lionel",
                    3L,
                    "Ronaldo",
                    "300574846",
                    3L,
                    1L,
                    1L
            )
    ));

    private final List<UserDataDTO> userDataDTOS = new ArrayList<>(Arrays.asList(
            new UserDataDTO(
                    "michael@gmail.com",
                    "Michael",
                    3L,
                    "Jordan",
                    "300574846",
                    1L
            ),
            new UserDataDTO(
                    "cristiano@gmail.com",
                    "Cristiano",
                    2L,
                    "Messi",
                    "300574846",
                    2L
            ),
            new UserDataDTO(
                    "Lionel@gmail.com",
                    "Lionel",
                    1L,
                    "Ronaldo",
                    "300574846",
                    3L
            )
    ));

    @Test
    void test_UserData_Is_Returned_Correctly_For_Dates_And_Id() {
        final Long userId = 1L;
        when(mockUserDataRepository.findByUserIdAndCreatedAtBetween(userId, initialTime, finalTime))
                .thenReturn(userDataEntities);

        final List<UserDataDTO> result = getUserDataListByUserIdAndDateUseCase.invoke(currentTime, userId);

        assertEquals(userDataDTOS, result);
    }
}
