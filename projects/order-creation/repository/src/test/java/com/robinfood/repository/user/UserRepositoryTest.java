package com.robinfood.repository.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

import com.robinfood.core.entities.userentities.UserDetailEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    private IUserRemoteDataSource mockUserRemoteDataSource;

    @InjectMocks
    private UserRepository userRepository;

    private final LocalDate currentDate = LocalDate.now();
    private final String token = "token";
    private final Long userId = 1L;

    @Test
    void test_Has_User_Applied_Consumption_Discount_By_Date_Returns_Correctly() {
        when(mockUserRemoteDataSource.hasUserAppliedConsumptionByDate(token, currentDate, userId))
            .thenReturn(CompletableFuture.completedFuture(true));

        final Boolean result = userRepository.hasUserAppliedConsumptionByDate(token, currentDate, userId).join();

        assertEquals(true, result);
    }

    @Test
    void test_Validate_Get_User_Details_Returns_Correctly() {
        when(mockUserRemoteDataSource.getUserDetail(token, userId))
                .thenReturn(
                        CompletableFuture.completedFuture(
                                userDetailEntity
                        ));

        final UserDetailEntity result = userRepository
                .getUserDetail(token, userId)
                .join();

        assertEquals(userDetailEntity, result);
    }

    private final UserDetailEntity userDetailEntity = new UserDetailEntity(
            1L,
            "User",
            "Test",
            "57",
            "9999999999",
            "test@test.com",
            false,
            Collections.singletonList(
                    UserDetailEntity
                            .Counter.builder()
                            .key("test")
                            .reference(1L)
                            .value(1L)
                            .build()
            )
    );
}
