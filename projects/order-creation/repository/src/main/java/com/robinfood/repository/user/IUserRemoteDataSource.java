package com.robinfood.repository.user;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import com.robinfood.core.entities.userentities.UserDetailEntity;

/**
 * Remote data source that handles all user related data
 */
public interface IUserRemoteDataSource {

    /**
     * Connects to an endpoint and checks if the user has applied consumption discount on a certain date
     * @param token the authorization date
     * @param aDate the date the user wants to check
     * @param userId the id of the user
     * @return true if the user has applied the consumption discount in a certain date, false otherwise
     */
    @Async
    CompletableFuture<Boolean> hasUserAppliedConsumptionByDate(
            String token,
            LocalDate aDate,
            Long userId
    );

    /**
     * Connects to an endpoint and return the user information
     * @param token the authorization token
     * @param userId the id of the user
     * @return the user detail information
     */
    @Async
    CompletableFuture<UserDetailEntity> getUserDetail(String token, Long userId);
}
