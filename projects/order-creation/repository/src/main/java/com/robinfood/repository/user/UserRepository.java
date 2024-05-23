package com.robinfood.repository.user;

import com.robinfood.core.entities.userentities.UserDetailEntity;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of IUserRepository
 */
public class UserRepository implements IUserRepository {

    private final IUserRemoteDataSource userRemoteDataSource;

    public UserRepository(IUserRemoteDataSource userRemoteDataSource) {
        this.userRemoteDataSource = userRemoteDataSource;
    }

    @Override
    public CompletableFuture<Boolean> hasUserAppliedConsumptionByDate(
            String token,
            LocalDate aDate,
            Long userId
    ) {
        return userRemoteDataSource.hasUserAppliedConsumptionByDate(token, aDate, userId);
    }

    @Override
    public CompletableFuture<UserDetailEntity> getUserDetail(
            String token,
            Long userId
    ) {
        return userRemoteDataSource.getUserDetail(token, userId);
    }
}
