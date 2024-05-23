package com.robinfood.app.controllers.users;

import com.robinfood.app.usecases.getuseractiveorder.IGetUserActiveOrderUseCase;
import com.robinfood.app.usecases.getuserorderdetail.IGetUserOrderDetailByUIdUseCase;
import com.robinfood.app.usecases.getuserorderhistory.IGetUserOrderHistoryUseCase;
import com.robinfood.app.usecases.hasuserappliedconsumptiontoday.IHasUserAppliedConsumptionTodayUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.response.EntityDTO;
import com.robinfood.core.dtos.response.PaginationDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDetailDTO;
import com.robinfood.core.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.USERS_ACTIVE_ORDERS;
import static com.robinfood.core.constants.APIConstants.USERS_HAS_APPLIED_CONSUMPTION_TODAY;
import static com.robinfood.core.constants.APIConstants.USERS_ORDERS;
import static com.robinfood.core.constants.APIConstants.USERS_ORDER_DETAIL;
import static com.robinfood.core.constants.APIConstants.USERS_V1;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_CURRENT_PAGE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_PER_PAGE;

@RequestMapping(USERS_V1)
@RestController
@Slf4j
public class UsersController implements IUsersController {

    private final IHasUserAppliedConsumptionTodayUseCase hasUserAppliedConsumptionTodayUseCase;
    private final IGetUserOrderDetailByUIdUseCase getUserOrderDetailByUIdUseCase;
    private final IGetUserOrderHistoryUseCase getOrderHistoryByUserIdUseCase;
    private final IGetUserActiveOrderUseCase getUserActiveOrderUseCase;

    public UsersController(
            IHasUserAppliedConsumptionTodayUseCase hasUserAppliedConsumptionTodayUseCase,
            IGetUserOrderDetailByUIdUseCase getUserOrderDetailByUIdUseCase,
            IGetUserOrderHistoryUseCase getOrderHistoryByUserIdUseCase,
            IGetUserActiveOrderUseCase getUserActiveOrderUseCase
    ) {
        this.hasUserAppliedConsumptionTodayUseCase = hasUserAppliedConsumptionTodayUseCase;
        this.getUserOrderDetailByUIdUseCase = getUserOrderDetailByUIdUseCase;
        this.getOrderHistoryByUserIdUseCase = getOrderHistoryByUserIdUseCase;
        this.getUserActiveOrderUseCase = getUserActiveOrderUseCase;
    }

    @GetMapping(USERS_HAS_APPLIED_CONSUMPTION_TODAY)
    public ResponseEntity<ApiResponseDTO<Boolean>> hasUserAppliedConsumptionToday(
            @RequestParam("createdAt")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAt,
            @PathVariable("id") Long userId
    ) {
        log.info("Receiving request to has user applied consumption today");

        final Boolean hasUserAppliedConsumptionToday = hasUserAppliedConsumptionTodayUseCase.invoke(createdAt, userId);
        return ResponseEntity.ok(new ApiResponseDTO<>(hasUserAppliedConsumptionToday));
    }

    @Override
    @GetMapping(USERS_ORDERS)
    public ResponseEntity<ApiResponseDTO<EntityDTO<ResponseOrderDTO>>> getOrderHistory(
            @RequestParam(
                    value = "currentPage",
                    required = false,
                    defaultValue = DEFAULT_CURRENT_PAGE
            ) Integer currentPage,
            @RequestParam(
                    value = "perPage",
                    required = false,
                    defaultValue = DEFAULT_PER_PAGE
            ) Integer perPage,
            @PathVariable("id") Long userId
    ) {
        log.info("Receiving request to get order history by user");

        final Page<ResponseOrderDTO> orderHistory = getOrderHistoryByUserIdUseCase.invoke(
                currentPage,
                perPage,
                userId
        );

        EntityDTO<ResponseOrderDTO> response = EntityDTO.<ResponseOrderDTO>builder()
                .items(orderHistory.getContent())
                .pagination(
                        PaginationDTO.builder()
                                .perPage(perPage)
                                .lastPage(orderHistory.getTotalPages())
                                .total(orderHistory.getTotalElements())
                                .page(currentPage)
                                .build()
                )
                .build();

        return ResponseEntity.ok(new ApiResponseDTO<>(response));
    }

    @Override
    @GetMapping(USERS_ORDER_DETAIL)
    public ResponseEntity<ApiResponseDTO<ResponseOrderDetailDTO>> getOrderDetail(
            @PathVariable("orderUId") String orderUId,
            @PathVariable("id") Long userId
    ) throws ResourceNotFoundException {
        log.info("Receiving request to get order detail by user");

        final ResponseOrderDetailDTO orderDetail = getUserOrderDetailByUIdUseCase.invoke(
                orderUId,
                userId
        );

        return ResponseEntity.ok(new ApiResponseDTO<>(orderDetail));
    }

    @Override
    @GetMapping(USERS_ACTIVE_ORDERS)
    public ResponseEntity<ApiResponseDTO<List<ResponseOrderDTO>>> getActiveOrdersByUser(
            @PathVariable("id") Long userId
    ) {
        log.info("Receiving request to get active orders by user");

        final List<ResponseOrderDTO> activeOrders = getUserActiveOrderUseCase.invoke(
                userId
        );

        return ResponseEntity.ok(new ApiResponseDTO<>(activeOrders));
    }
}
