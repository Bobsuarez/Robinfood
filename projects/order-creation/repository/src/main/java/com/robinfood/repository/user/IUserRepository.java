package com.robinfood.repository.user;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import com.robinfood.core.entities.userentities.UserDetailEntity;

/**
 * Repository that handles all user related data
 */
public interface IUserRepository {

    /**
     * Checks if a user has applied the consumption discount on an certain date
     * @param token the authorization token
     * @param aDate the date the user wants to check
     * @param userId the id of the user
     * @return true if the user has applied the consumption discount on a certain date, false otherwise
     */
    @Async
    CompletableFuture<Boolean> hasUserAppliedConsumptionByDate(
            String token,
            LocalDate aDate,
            Long userId
    );

    /**
     * Get the user detail info
     * @param token the authorization token
     * @param userId the id of the user
     * @return return the user detail information
     */
    @Async
    CompletableFuture<UserDetailEntity> getUserDetail(String token, Long userId);
}
