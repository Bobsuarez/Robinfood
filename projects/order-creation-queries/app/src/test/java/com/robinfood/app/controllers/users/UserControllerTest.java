package com.robinfood.app.controllers.users;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.robinfood.app.OrderCreationQueriesApplication;
import com.robinfood.app.usecases.getuseractiveorder.IGetUserActiveOrderUseCase;
import com.robinfood.app.usecases.getuserorderdetail.IGetUserOrderDetailByUIdUseCase;
import com.robinfood.app.usecases.getuserorderhistory.IGetUserOrderHistoryUseCase;
import com.robinfood.app.usecases.hasaccessinformationbyuser.IHasAccessInformationByUserUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDetailDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.exceptions.UnauthorizedAccessException;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.core.mocks.dto.ResponseActiveOrderMock;
import com.robinfood.core.mocks.dto.ResponseOrderDetailMock;
import com.robinfood.core.mocks.dto.ResponseOrderHistoryMock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@SpringBootTest(classes = OrderCreationQueriesApplication.class)
@TestPropertySource(properties = {
    "jwt-token-prefix=Bearer ",
    "jwt.token.secret=secretForTesting",
    "jwt-token-aud=internal",
    "jwt-token-mod=order_creation_queries"
})
public class UserControllerTest {

    private static final String BEARER_AUTH = "Bearer ";

    private static final String OR_OCQUERIES_USERORDER_LIST = "OR_OCQUERIES_USERORDER_LIST";
    private static final String OTHER_PERMISSION = "OTHER_PERMISSION";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IHasAccessInformationByUserUseCase hasAccessInformationByUserUseCase;

    @MockBean
    private IGetUserOrderHistoryUseCase getUserOrderHistoryUseCase;

    @MockBean
    private IGetUserOrderDetailByUIdUseCase getUserOrderDetailByUIdUseCase;

    @MockBean
    private IGetUserActiveOrderUseCase getUserActiveOrderUseCase;

    @Test
    public void test_GetOrderHistory_Should_Return_Ok() throws Exception {

        String token = getJWT(OR_OCQUERIES_USERORDER_LIST);

        EntityDTO<ResponseOrderDTO> responseMock =
            ResponseOrderHistoryMock.getEntityResponseOrderHistoryMock();

        when(hasAccessInformationByUserUseCase.invoke(
            1L
        )).thenReturn(new Result.Success<>(1L));

        when(getUserOrderHistoryUseCase.invoke(
            1,
            1,
            1L
        )).thenReturn(new Result.Success<>(responseMock));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/1/orders")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + token)
                .queryParam("currentPage", "1")
                .queryParam("perPage", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(ObjectExtensions.toJson(new ApiResponseDTO<>(responseMock))));
    }

    @Test
    public void test_GetOrderHistory_Without_Permissions_Should_Return_Failure() throws Exception {

        String token = getJWT(OTHER_PERMISSION);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/1/orders")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + token)
                .queryParam("currentPage", "1")
                .queryParam("perPage", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden());
    }

    @Test
    public void test_GetOrderHistory_Should_Return_Failure() throws Exception {

        String token = getJWT(OR_OCQUERIES_USERORDER_LIST);

        when(hasAccessInformationByUserUseCase.invoke(
            1L
        )).thenReturn(new Result.Success<>(1L));

        when(getUserOrderHistoryUseCase.invoke(
            1,
            1,
            1L
        )).thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/1/orders")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + token)
                .queryParam("currentPage", "1")
                .queryParam("perPage", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().json(ObjectExtensions.toJson(new ApiResponseDTO<>("Some error"))));
    }

    @Test
    public void test_GetOrderHistoryDifferentUser_Should_Return_Failure() throws Exception {
        String token = getJWT(OR_OCQUERIES_USERORDER_LIST);

        when(hasAccessInformationByUserUseCase.invoke(
            1L
        )).thenReturn(
            new Result.Error(
                new UnauthorizedAccessException("You cannot consult information of other users"),
                HttpStatus.UNAUTHORIZED
            )
        );

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/1/orders")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + token)
                .queryParam("currentPage", "1")
                .queryParam("perPage", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(content().json(
                ObjectExtensions.toJson(
                    new ApiResponseDTO<>("You cannot consult information of other users")
                )
            ));
    }

    @Test
    public void test_GetOrderDetail_Should_Return_Ok() throws Exception {
        String token = getJWT(OR_OCQUERIES_USERORDER_LIST);

        ResponseOrderDetailDTO responseMock =
            ResponseOrderDetailMock.getResponseOrderDetail();

        when(hasAccessInformationByUserUseCase.invoke(
            1L
        )).thenReturn(new Result.Success<>(1L));

        when(getUserOrderDetailByUIdUseCase.invoke(
            "12345abcde",
            1L
        )).thenReturn(new Result.Success<>(responseMock));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/1/orders/12345abcde")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(ObjectExtensions.toJson(new ApiResponseDTO<>(responseMock))));
    }

    @Test
    public void test_GetOrderDetail_Without_Permission_Should_Return_Failure() throws Exception {
        String token = getJWT(OTHER_PERMISSION);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/1/orders/12345abcde")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden());
    }

    @Test
    public void test_GetOrderDetail_Should_Return_Failure() throws Exception {
        String token = getJWT(OR_OCQUERIES_USERORDER_LIST);

        when(hasAccessInformationByUserUseCase.invoke(
            1L
        )).thenReturn(new Result.Success<>(1L));

        when(getUserOrderDetailByUIdUseCase.invoke(
            "12345abcde",
            1L
        )).thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/1/orders/12345abcde")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().json(ObjectExtensions.toJson(new ApiResponseDTO<>("Some error"))));
    }

    @Test
    public void test_GetOrderDetailDifferentUser_Should_Return_Failure() throws Exception {
        String token = getJWT(OR_OCQUERIES_USERORDER_LIST);

        when(hasAccessInformationByUserUseCase.invoke(
            1L
        )).thenReturn(
            new Result.Error(
                new UnauthorizedAccessException("You cannot consult information of other users"),
                HttpStatus.UNAUTHORIZED
            )
        );

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/1/orders/12345abcde")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + token)
                .queryParam("currentPage", "1")
                .queryParam("perPage", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(content().json(
                ObjectExtensions.toJson(
                    new ApiResponseDTO<>("You cannot consult information of other users")
                )
            ));
    }

    @Test
    public void test_GetActiveOrders_Should_Return_Ok() throws Exception {
        String token = getJWT(OR_OCQUERIES_USERORDER_LIST);

        List<ResponseOrderDTO> responseMock =
            ResponseActiveOrderMock.getResponseActiveOrderMock();

        when(hasAccessInformationByUserUseCase.invoke(
            1L
        )).thenReturn(new Result.Success<>(1L));

        when(getUserActiveOrderUseCase.invoke(
            1L
        )).thenReturn(new Result.Success<>(responseMock));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/1/orders/active")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(ObjectExtensions.toJson(new ApiResponseDTO<>(responseMock))));
    }

    @Test
    public void test_GetActiveOrders_Without_Permissions_Should_Return_Failure() throws Exception {

        String token = getJWT(OTHER_PERMISSION);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/1/orders/active")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden());
    }

    @Test
    public void test_GetActiveOrders_Should_Return_Failure() throws Exception {
        String token = getJWT(OR_OCQUERIES_USERORDER_LIST);

        when(hasAccessInformationByUserUseCase.invoke(
            1L
        )).thenReturn(new Result.Success<>(1L));

        when(getUserActiveOrderUseCase.invoke(
            1L
        )).thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/1/orders/active")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().json(ObjectExtensions.toJson(new ApiResponseDTO<>("Some error"))));
    }

    @Test
    public void test_GetActiveOrdersDifferentUser_Should_Return_Failure() throws Exception {
        String token = getJWT(OR_OCQUERIES_USERORDER_LIST);

        when(hasAccessInformationByUserUseCase.invoke(
            1L
        )).thenReturn(
            new Result.Error(
                new UnauthorizedAccessException("You cannot consult information of other users"),
                HttpStatus.UNAUTHORIZED
            )
        );

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/1/orders/active")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(content().json(
                ObjectExtensions.toJson(
                    new ApiResponseDTO<>("You cannot consult information of other users")
                )
            ));
    }

    protected String getJWT(String permissions) {
        String secretKey = "secretForTesting";

        List<GrantedAuthority> listModules = AuthorityUtils
            .commaSeparatedStringToAuthorityList("order_creation_queries");

        List<GrantedAuthority> listPermissions = AuthorityUtils
            .commaSeparatedStringToAuthorityList(permissions);

        Map<String, String> user = new HashMap<>();
        user.put("user_id", "123");
        user.put("first_name", "name");
        user.put("last_name", "lasName");
        user.put("email", "mail.test.com");

        String token = Jwts
            .builder()
            .setId(LocalDateTime.now().toString())
            .setSubject("123")
            .setAudience("internal")
            .claim("mod",
                listModules.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()))
            .claim("per",
                listPermissions.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()))
            .claim("user", user)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 60000000))
            .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return BEARER_AUTH + token;
    }

    @Test
    void test_GetOrderDetail_Should_Return_swagger() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/swagger-ui/index.html"))

                .andExpect(status().isOk());
    }
}
