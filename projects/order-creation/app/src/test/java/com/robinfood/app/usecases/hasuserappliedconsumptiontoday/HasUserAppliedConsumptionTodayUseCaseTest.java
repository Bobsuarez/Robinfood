package com.robinfood.app.usecases.hasuserappliedconsumptiontoday;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.robinfood.repository.user.IUserRepository;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HasUserAppliedConsumptionTodayUseCaseTest {

    @Mock
    private IUserRepository mockUserRepository;

    @InjectMocks
    private HasUserAppliedConsumptionTodayUseCase hasUserAppliedConsumptionTodayUseCase;

    private final LocalDate currentDate = LocalDate.now();
    private final String token = "token";
    private final Long userId = 1L;

    @Test
    void test_HasUserAppliedConsumptionTodayReturnsCorrectly() {
        when(mockUserRepository.hasUserAppliedConsumptionByDate(token, currentDate, userId))
                .thenReturn(CompletableFuture.completedFuture(true));

        final Boolean result = hasUserAppliedConsumptionTodayUseCase.invoke(token, userId).join();

        assertEquals(true, result);
    }
}
