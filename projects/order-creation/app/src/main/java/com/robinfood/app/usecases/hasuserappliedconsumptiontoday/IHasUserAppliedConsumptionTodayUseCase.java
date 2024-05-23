package com.robinfood.app.usecases.hasuserappliedconsumptiontoday;

import java.util.concurrent.CompletableFuture;

/**
 * Use case that checks if a user has applied the consumption discount today
 */
public interface IHasUserAppliedConsumptionTodayUseCase {

    /**
     * Checks if a user has applied the consumption discount today
     * @param token the authorization token
     * @param userId the id of the user
     * @return true if the user applied the consumption discount today, false otherwise
     */
    CompletableFuture<Boolean> invoke(String token, Long userId);
}
