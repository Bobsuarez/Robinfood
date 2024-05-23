package com.robinfood.repository.user;

import com.robinfood.core.dtos.user.UserDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.UserBcAPI;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private final UserBcAPI userBcAPI;

    public UserRepository(UserBcAPI userBcAPI) {
        this.userBcAPI = userBcAPI;
    }

    @Override
    public Result<List<UserDTO>> getUsersByIds(String token, List<Long> userIds) {

        final Result<APIResponseEntity<List<UserDTO>>> result = NetworkExtensionsKt.safeAPICall(
                userBcAPI.getUsersByIds(token, userIds)
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<List<UserDTO>>> data =
                ((Result.Success<APIResponseEntity<List<UserDTO>>>) result);

        return new Result.Success(data.getData().getData());
    }
}
