package com.robinfood.repository.user;

import com.robinfood.core.dtos.user.UserDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

public interface IUserRepository {

    Result<List<UserDTO>> getUsersByIds(String token, List<Long> userIds);
}
