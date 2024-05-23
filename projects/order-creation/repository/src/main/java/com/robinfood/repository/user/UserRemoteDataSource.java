package com.robinfood.repository.user;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.userentities.UserDetailEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBCApi;
import com.robinfood.network.api.UserBCAPI;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of IUserRemoteDataSource
 */
public class UserRemoteDataSource implements IUserRemoteDataSource {

    private final OrderBCApi orderBCApi;
    private final UserBCAPI userBCAPI;

    public UserRemoteDataSource(OrderBCApi orderBCApi, UserBCAPI userBCAPI) {
        this.orderBCApi = orderBCApi;
        this.userBCAPI = userBCAPI;
    }

    @Override
    public CompletableFuture<Boolean> hasUserAppliedConsumptionByDate(
            String token,
            LocalDate aDate,
            Long userId
    ) {
        final Result<ApiResponseEntity<Boolean>> hasUserAppliedConsumptionByDate =
                NetworkExtensionsKt.safeAPICall(orderBCApi.hasUserAppliedConsumptionByDate(token, userId, aDate));
        if (hasUserAppliedConsumptionByDate instanceof Result.Success) {
            return CompletableFuture.completedFuture(
                    ((Result.Success<ApiResponseEntity<Boolean>>) hasUserAppliedConsumptionByDate)
                            .getData()
                            .getData()
            );
        }
        return CompletableFuture.failedFuture(((Result.Error) hasUserAppliedConsumptionByDate).getException());
    }

    @Override
    public CompletableFuture<UserDetailEntity> getUserDetail(
            String token,
            Long userId
    ) {
        final Result<ApiResponseEntity<UserDetailEntity>> userDetailsResult =
                NetworkExtensionsKt.safeAPICall(
                        userBCAPI.getUserDetails(token, userId));

        if (userDetailsResult instanceof Result.Error) {
            final Result.Error error = (Result.Error) userDetailsResult;
            throw new TransactionCreationException(
                    error.getHttpStatus(),
                    error.getException().getLocalizedMessage(),
                    TransactionCreationErrors.USER_DETAILS_ERROR
            );
        }

        return CompletableFuture.completedFuture(
                ((Result.Success<ApiResponseEntity<UserDetailEntity>>) userDetailsResult)
                        .getData()
                        .getData()
        );
    }
}
