package com.robinfood.app.usecases.getuserdatabyorderids;

import com.robinfood.app.datamocks.dto.core.UserDataDTOMock;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.entities.OrderUserDataEntity;
import com.robinfood.repository.userdata.IUserDataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserDataByOrderIdsUseCaseTest {

    private LocalDateTime now = LocalDateTime.now();

    @Mock
    private IUserDataRepository userDataRepository;

    @InjectMocks
    private GetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase;

    private final LocalDateTime initialTime = LocalDateTime.of(2021, 6, 3, 5, 0, 0);
    private final List<Long> orderIds = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L));

    private final List<OrderUserDataEntity> userDataEntities = new ArrayList<>(Arrays.asList(
            new OrderUserDataEntity(
                    initialTime,
                    "cristiano@gmail.com",
                    "Cristiano",
                    null,
                    "Messi",
                    "300574846",
                    1L,
                    1L,
                    1L
            ),
            new OrderUserDataEntity(
                    initialTime,
                    "Lionel@gmail.com",
                    "Lionel",
                    3L,
                    "Ronaldo",
                    "300574846",
                    1L,
                    1L,
                    1L
            )
    ));

    private final List<UserDataDTO> userDataDTOS = new ArrayList<>(Arrays.asList(
            UserDataDTOMock.getDataDefault()
    ));

    @Test
    void test_GetUserData_Returns_Correctly() {
        when(userDataRepository.findAllByOrderIdIn(orderIds)).thenReturn(userDataEntities);

        final List<UserDataDTO> result = getUserDataByOrderIdsUseCase.invoke(orderIds);

        Assertions.assertNotNull(result);
    }
}
