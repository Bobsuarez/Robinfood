package com.robinfood.network.api;

import com.robinfood.core.dtos.user.UserDTO;
import com.robinfood.core.entities.APIResponseEntity;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import java.util.List;

/**
 * Defines all connections to User capability
 */
@Component
public interface UserBcAPI {

    /**
     * Get the users information
     * @param token token with authorization
     * @param ids list the user to search
     * @return the object list UserDTO
     */
    @GET("v1/users")
    Call<APIResponseEntity<List<UserDTO>>> getUsersByIds(
            @Header("Authorization") String token,
            @Query("ids") List<Long>ids
    );
}
