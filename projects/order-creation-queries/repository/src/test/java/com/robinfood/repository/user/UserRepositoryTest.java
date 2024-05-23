package com.robinfood.repository.user;

import com.robinfood.core.dtos.user.UserDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.UserBcAPI;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    private UserBcAPI userBcAPI;

    @Mock
    private Call<APIResponseEntity<List<UserDTO>>> mockUserDTO;

    @InjectMocks
    private UserRepository userRepository;

    @Test
    void test_GetUsersByIds_Should_InternalServerError_When_WrongAnswerOfUserBC() throws Exception {

        when(userBcAPI.getUsersByIds(anyString(), anyList())).thenReturn(mockUserDTO);

        when(mockUserDTO.execute())
                .thenReturn(Response.error(500,
                        ResponseBody.create(
                                MediaType.parse("application/json"),
                                ObjectExtensions.toJson(new APIResponseEntity<>(
                                        500,
                                        List.of(),
                                        "CO",
                                        "",
                                        "User not be returned"
                                ))
                        )
                ));

        userRepository.getUsersByIds("token", List.of(1L));

        verify(userBcAPI).getUsersByIds(anyString(), anyList());
    }

    @Test
    void test_GetUsersByIds_Should_RespondToUserList_When_InvokeRepository() throws Exception {

        when(userBcAPI.getUsersByIds(anyString(), anyList())).thenReturn(mockUserDTO);

        when(mockUserDTO.execute())
                .thenReturn(Response.success(
                        new APIResponseEntity<>(
                                200,
                                List.of(UserDTO.builder().build()),
                                "CO",
                                "Order filter returned",
                                "200"
                        )
                ));

        userRepository.getUsersByIds("token", List.of(1L));

        verify(userBcAPI).getUsersByIds(anyString(), anyList());
    }
}