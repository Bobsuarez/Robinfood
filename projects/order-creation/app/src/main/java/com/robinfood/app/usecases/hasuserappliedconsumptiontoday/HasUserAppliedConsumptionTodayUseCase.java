package com.robinfood.app.usecases.hasuserappliedconsumptiontoday;

import com.robinfood.repository.user.IUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of IHasUserAppliedConsumptionTodayUseCase
 */
@Service
public class HasUserAppliedConsumptionTodayUseCase implements IHasUserAppliedConsumptionTodayUseCase {

    private final IUserRepository userRepository;

    public HasUserAppliedConsumptionTodayUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CompletableFuture<Boolean> invoke(String token, Long userId) {
        final LocalDate currentDate = LocalDate.now();
        return userRepository.hasUserAppliedConsumptionByDate(token, currentDate, userId);
    }
}
