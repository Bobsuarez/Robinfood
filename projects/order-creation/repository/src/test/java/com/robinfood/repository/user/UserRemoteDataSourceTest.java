package com.robinfood.repository.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.userentities.UserDetailEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBCApi;
import java.time.LocalDate;
import java.util.Collections;

import com.robinfood.network.api.UserBCAPI;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

@ExtendWith(MockitoExtension.class)
class UserRemoteDataSourceTest {

    @Mock
    private Call<ApiResponseEntity<Object>> mockValidateUserIsEmployee;

    @Mock
    private OrderBCApi mockOrderBc;

    @Mock
    private UserBCAPI mockUserBCAPI;

    @Mock
    private Call<ApiResponseEntity<Boolean>> mockHasAppliedConsumptionByDate;

    @Mock
    private Call<ApiResponseEntity<UserDetailEntity>> mockUserDetailResult;

    @InjectMocks
    private UserRemoteDataSource userRemoteDataSource;

    private final LocalDate currentDate = LocalDate.now();
    private final String token = "token";
    private final Long userId = 1L;

    final ApiResponseEntity<Boolean> apiResponseEntityConsumption = ApiResponseEntity.<Boolean>builder()
            .code(200)
            .data(true)
            .locale("CO")
            .message("Has applied consumption today")
            .build();

    private final UserDetailEntity userDetailEntity = new UserDetailEntity(
            1L,
            "User",
            "Test",
            "57",
            "9999999999",
            "test@test.com",
            false,
            Collections.singletonList(
                    UserDetailEntity
                            .Counter.builder()
                            .key("test")
                            .reference(1L)
                            .value(1L)
                            .build()
            )
    );

    final ApiResponseEntity<UserDetailEntity> apiResponseUserDetailsEntity = ApiResponseEntity.<UserDetailEntity>builder()
            .code(200)
            .data(userDetailEntity)
            .locale("CO")
            .message("Success")
            .build();

    final ApiResponseEntity<UserDetailEntity> apiErrorResponseUserDetailsEntity = ApiResponseEntity.<UserDetailEntity>builder()
            .code(422)
            .message("Not found the entity")
            .build();

    final ApiResponseEntity<Boolean> apiErrorResponseEntityConsumption = ApiResponseEntity.<Boolean>builder()
            .code(500)
            .error("Error")
            .locale("CO")
            .message("Has not applied consumption today")
            .build();

    final ApiResponseEntity<Object> apiResponseEntityValidateUser = ApiResponseEntity.builder()
            .code(200)
            .data(true)
            .locale("CO")
            .message("User is employee")
            .build();

    final ApiResponseEntity<Boolean> apiErrorResponseEntityValidateUser = ApiResponseEntity.<Boolean>builder()
            .code(500)
            .error("Error")
            .locale("CO")
            .message("User is not employee")
            .build();

    @Test
    void test_HasUserAppliedConsumptionByDate_Returns_Correctly() throws Exception {
        when(mockOrderBc.hasUserAppliedConsumptionByDate(token, userId, currentDate))
                .thenReturn(mockHasAppliedConsumptionByDate);
        when(mockHasAppliedConsumptionByDate.execute()).thenReturn(Response.success(apiResponseEntityConsumption));

        final Boolean result = userRemoteDataSource.hasUserAppliedConsumptionByDate(token, currentDate, userId).join();

        assertEquals(true, result);
    }

    @Test
    void test_HasUserAppliedConsumptionByDate_Returns_WithFailure() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseEntityConsumption);
        when(mockOrderBc.hasUserAppliedConsumptionByDate(token, userId, currentDate))
                .thenReturn(mockHasAppliedConsumptionByDate);
        when(mockHasAppliedConsumptionByDate.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        try {
            userRemoteDataSource.hasUserAppliedConsumptionByDate(token, currentDate, userId).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntityConsumption.getMessage()));
        }
    }

    @Test
    void test_getUserDetails_Returns_Correctly() throws Exception {

        when(mockUserBCAPI.getUserDetails(token, userId))
                .thenReturn(mockUserDetailResult);

        when(mockUserDetailResult.execute()).thenReturn(Response.success(apiResponseUserDetailsEntity));

        final UserDetailEntity result = userRemoteDataSource.getUserDetail(token, userId).get();

        assertEquals(userDetailEntity, result);
    }

    @Test
    void test_getUserDetails_Returns_WithFailure() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseUserDetailsEntity);
        when(mockUserBCAPI.getUserDetails(token, userId))
                .thenReturn(mockUserDetailResult);
        when(mockUserDetailResult.execute()).thenReturn(Response.error(422, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        try {
            userRemoteDataSource.getUserDetail(token, userId).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseUserDetailsEntity.getMessage()));
        }
    }
}
