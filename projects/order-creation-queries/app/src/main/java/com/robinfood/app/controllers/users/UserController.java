package com.robinfood.app.controllers.users;

import static com.robinfood.core.constants.APIConstants.USERS_ACTIVE_ORDERS;
import static com.robinfood.core.constants.APIConstants.USERS_ORDERS;
import static com.robinfood.core.constants.APIConstants.USERS_ORDER_DETAIL;
import static com.robinfood.core.constants.APIConstants.USERS_V1;

import com.robinfood.app.security.Permissions;
import com.robinfood.app.usecases.getuseractiveorder.IGetUserActiveOrderUseCase;
import com.robinfood.app.usecases.getuserorderdetail.IGetUserOrderDetailByUIdUseCase;
import com.robinfood.app.usecases.getuserorderhistory.IGetUserOrderHistoryUseCase;
import com.robinfood.app.usecases.hasaccessinformationbyuser.IHasAccessInformationByUserUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDetailDTO;
import com.robinfood.core.enums.Result;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of 'IUserController'
 */
@RestController
@Log4j2
@RequestMapping(USERS_V1)
public class UserController implements IUserController {

    private final IGetUserOrderHistoryUseCase getOrderHistoryUseCase;
    private final IGetUserActiveOrderUseCase getUserActiveOrderUseCase;
    private final IGetUserOrderDetailByUIdUseCase getUserOrderDetailUseCase;
    private final IHasAccessInformationByUserUseCase hasAccessInformationByUserUseCase;

    public UserController(IGetUserOrderHistoryUseCase getOrderHistoryUseCase,
            IGetUserActiveOrderUseCase getUserActiveOrderUseCase,
            IGetUserOrderDetailByUIdUseCase getUserOrderDetailUseCase,
            IHasAccessInformationByUserUseCase hasAccessInformationByUserUseCase) {
        this.getOrderHistoryUseCase = getOrderHistoryUseCase;
        this.getUserActiveOrderUseCase = getUserActiveOrderUseCase;
        this.getUserOrderDetailUseCase = getUserOrderDetailUseCase;
        this.hasAccessInformationByUserUseCase = hasAccessInformationByUserUseCase;
    }

    @Override
    @GetMapping(USERS_ORDERS)
    @PreAuthorize(Permissions.OR_OC_QUERIES_USER_ORDER_LIST)
    public ResponseEntity<ApiResponseDTO<EntityDTO<ResponseOrderDTO>>> getOrderHistory(
            @RequestParam(
                    value = "currentPage",
                    required = false
            ) Integer currentPage,
            @RequestParam(
            value = "perPage",
            required = false
        ) Integer perPage,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
        @PathVariable(
            value = "id"
        ) Long userId
    ) {

        final Result<Long> userAuthenticated = hasAccessInformationByUserUseCase.invoke(userId);

        if (userAuthenticated instanceof Result.Error) {
            Result.Error error = ((Result.Error) userAuthenticated);

            return new ResponseEntity<>(
                new ApiResponseDTO<>(
                    error.getException().getLocalizedMessage(),
                    error.getHttpStatus()
                ), error.getHttpStatus()
            );
        }

        final Result<EntityDTO<ResponseOrderDTO>> orderHistoryResult = getOrderHistoryUseCase.invoke(
            currentPage,
            perPage,
            userId
        );

        if (orderHistoryResult instanceof Result.Error) {
            Result.Error error = ((Result.Error) orderHistoryResult);

            return new ResponseEntity<>(
                new ApiResponseDTO<>(
                    error.getException().getLocalizedMessage(),
                    error.getHttpStatus()
                ), error.getHttpStatus()
            );
        } else {
            return new ResponseEntity<>(
                new ApiResponseDTO<>(
                    ((Result.Success<EntityDTO<ResponseOrderDTO>>) orderHistoryResult).getData(),
                    HttpStatus.OK
                ), HttpStatus.OK
            );
        }
    }

    @Override
    @GetMapping(USERS_ORDER_DETAIL)
    @PreAuthorize(Permissions.OR_OC_QUERIES_USER_ORDER_LIST)
    public ResponseEntity<ApiResponseDTO<ResponseOrderDetailDTO>> getOrderDetail(
        @PathVariable(value = "orderUId") String orderUId,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
        @PathVariable(value = "id") Long userId
    ) {

        final Result<Long> userAuthenticated = hasAccessInformationByUserUseCase.invoke(userId);

        if (userAuthenticated instanceof Result.Error) {
            Result.Error error = ((Result.Error) userAuthenticated);

            return new ResponseEntity<>(
                new ApiResponseDTO<>(
                    error.getException().getLocalizedMessage(),
                    error.getHttpStatus()
                ), error.getHttpStatus()
            );
        }

        final Result<ResponseOrderDetailDTO> orderDetailDTOResult = getUserOrderDetailUseCase.invoke(
            orderUId,
            userId
        );

        if (orderDetailDTOResult instanceof Result.Error) {
            Result.Error error = ((Result.Error) orderDetailDTOResult);

            return new ResponseEntity<>(
                new ApiResponseDTO<>(
                    error.getException().getLocalizedMessage(),
                    error.getHttpStatus()
                ), error.getHttpStatus()
            );
        } else {
            return new ResponseEntity<>(
                new ApiResponseDTO<>(
                    ((Result.Success<ResponseOrderDetailDTO>) orderDetailDTOResult).getData(),
                    HttpStatus.OK
                ), HttpStatus.OK
            );
        }
    }

    @Override
    @GetMapping(USERS_ACTIVE_ORDERS)
    @PreAuthorize(Permissions.OR_OC_QUERIES_USER_ORDER_LIST)
    public ResponseEntity<ApiResponseDTO<List<ResponseOrderDTO>>> getActiveOrders(
        @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
        @PathVariable(
            value = "id"
        ) Long userId
    ) {

        final Result<Long> userAuthenticated = hasAccessInformationByUserUseCase.invoke(userId);

        if (userAuthenticated instanceof Result.Error) {
            Result.Error error = ((Result.Error) userAuthenticated);

            return new ResponseEntity<>(
                new ApiResponseDTO<>(
                    error.getException().getLocalizedMessage(),
                    error.getHttpStatus()
                ), error.getHttpStatus()
            );
        }

        final Result<List<ResponseOrderDTO>> activeOrdersResult = getUserActiveOrderUseCase.invoke(
            userId
        );

        if (activeOrdersResult instanceof Result.Error) {
            Result.Error error = ((Result.Error) activeOrdersResult);

            return new ResponseEntity<>(
                new ApiResponseDTO<>(
                    error.getException().getLocalizedMessage(),
                    error.getHttpStatus()
                ), error.getHttpStatus()
            );
        } else {
            return new ResponseEntity<>(
                new ApiResponseDTO<>(
                    ((Result.Success<List<ResponseOrderDTO>>) activeOrdersResult).getData(),
                    HttpStatus.OK
                ), HttpStatus.OK
            );
        }
    }

}
