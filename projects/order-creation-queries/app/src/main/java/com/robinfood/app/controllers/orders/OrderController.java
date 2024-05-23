package com.robinfood.app.controllers.orders;

import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static com.robinfood.core.constants.APIConstants.ORDER_DETAIL;
import static com.robinfood.core.constants.APIConstants.ORDER_DETAIL_PRINT;
import static com.robinfood.core.constants.APIConstants.ORDER_GET_DAILY_SALE_SUMMARY;
import static com.robinfood.core.constants.APIConstants.ORDER_REPORT_TOTAL_DAILY_SALES;

import com.robinfood.app.security.Permissions;
import com.robinfood.app.usecases.getdailysalessummary.IGetDailySalesSummaryByStoreIdAndDateUseCase;
import com.robinfood.app.usecases.getorderdetail.IGetOrderDetailUseCase;
import com.robinfood.app.usecases.getorderdetailprint.IGetOrderDetailPrintUseCase;
import com.robinfood.app.usecases.getordertotaldailysales.IGetOrderTotalDailySalesUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.dtos.OrderTotalDailySalesDTO;
import com.robinfood.core.enums.Result;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of 'IOrderController'
 */
@RestController
@Log4j2
@RequestMapping(ORDERS_V1)
public class OrderController implements IOrderController {

    private final IGetOrderDetailUseCase getOrderDetailUseCase;
    private final IGetOrderTotalDailySalesUseCase getOrderTotalDailySalesUseCase;

    private final IGetDailySalesSummaryByStoreIdAndDateUseCase getDailySalesSummaryUseCase;
    private final IGetOrderDetailPrintUseCase getOrderDetailPrintUseCase;

    public OrderController(
            IGetOrderDetailUseCase getOrderDetailUseCase,
            IGetOrderTotalDailySalesUseCase getOrderTotalDailySalesUseCase,
            IGetDailySalesSummaryByStoreIdAndDateUseCase getDailySalesSummaryUseCase,
            IGetOrderDetailPrintUseCase getOrderDetailPrintUseCase
    ) {
        this.getOrderDetailUseCase = getOrderDetailUseCase;
        this.getOrderTotalDailySalesUseCase = getOrderTotalDailySalesUseCase;
        this.getDailySalesSummaryUseCase = getDailySalesSummaryUseCase;
        this.getOrderDetailPrintUseCase = getOrderDetailPrintUseCase;
    }
    
    @NotNull
    private ResponseEntity<ApiResponseDTO<List<OrderDetailDTO>>> getApiResponseDTOResponseEntity(
            Result<List<OrderDetailDTO>> orderDetailDTOResult
    ) {
        ApiResponseDTO<List<OrderDetailDTO>> apiResponseDTO;
        HttpStatus httpStatus;

        if (orderDetailDTOResult instanceof Result.Error) {
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) orderDetailDTOResult).getException().getLocalizedMessage()
            );
            httpStatus = ((Result.Error) orderDetailDTOResult).getHttpStatus();
        } else {
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<List<OrderDetailDTO>>) orderDetailDTOResult).getData()
            );
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }

    @Override
    @GetMapping(ORDER_DETAIL)
    public ResponseEntity<ApiResponseDTO<List<OrderDetailDTO>>> getOrderDetails(
            @RequestParam(value = "countryId") Long countryId,
            @RequestParam(value = "flowId") Long flowId,
            @RequestParam(
                    value = "orderIds",
                    defaultValue = ""
            ) List<Long> orderIds,
            @RequestParam(
                    value = "orderUids",
                    defaultValue = ""
            ) List<String> orderUids,
            @RequestParam(
                    value = "orderUuid",
                    required = false,
                    defaultValue = ""
            ) List<String> orderUuid
    ) {
        final Result<List<OrderDetailDTO>> orderDetailDTOResult = getOrderDetailUseCase.invoke(
                countryId,
                flowId,
                orderIds,
                orderUids,
                orderUuid
        );

        return getApiResponseDTOResponseEntity(orderDetailDTOResult);
    }

    @Override
    @GetMapping(ORDER_DETAIL_PRINT)
    public ResponseEntity<ApiResponseDTO<List<OrderDetailDTO>>> getOrderDetailPrint(
            @RequestParam(
                    value = "orderIds",
                    required = false
            ) List<Long> orderIds,
            @RequestParam(
                    value = "orderUids",
                    required = false
            ) List<String> orderUids,
            @RequestParam(
                    value = "orderUuid",
                    required = false
            ) List<String> orderUuid
    ) {
        log.info("Orders from request " + orderIds);
        final Result<List<OrderDetailDTO>> orderDetailDTOResult = getOrderDetailPrintUseCase.invoke(
                orderIds,
                orderUids,
                orderUuid
        );

        log.info("Response data order detail print " + orderDetailDTOResult);
        return getApiResponseDTOResponseEntity(orderDetailDTOResult);
    }

    @GetMapping(ORDER_GET_DAILY_SALE_SUMMARY)
    @Override
    @PreAuthorize(Permissions.ORDER_REPORT_DAILY_SALE_SUMMARY_DETAIL)
    public ResponseEntity<ApiResponseDTO<OrderDailySaleSummaryDTO>> getDailySaleSummary(
        @PathVariable("storeId") long storeId,
        @RequestParam(value = "createdAt")
        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAt) {

        log.info("Receiving request to get orders daily sales summary with store id: {} and createdAt: {}",
            storeId, createdAt);

        Result<OrderDailySaleSummaryDTO> dailySaleSummaryDTO = getDailySalesSummaryUseCase.invoke(storeId, createdAt);
        ApiResponseDTO<OrderDailySaleSummaryDTO> apiResponseDTO;
        HttpStatus httpStatus = HttpStatus.OK;

        if (dailySaleSummaryDTO instanceof Result.Error) {
            Result.Error error = ((Result.Error) dailySaleSummaryDTO);
            apiResponseDTO = new ApiResponseDTO<>(
                error.getException().getLocalizedMessage()
            );
            log.error(error);
            httpStatus = ((Result.Error) dailySaleSummaryDTO).getHttpStatus();
        } else {
            apiResponseDTO = new ApiResponseDTO<>(
                ((Result.Success<OrderDailySaleSummaryDTO>) dailySaleSummaryDTO).getData()
            );

        }
        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }

    @GetMapping(ORDER_REPORT_TOTAL_DAILY_SALES)
    @Override
    @PreAuthorize(Permissions.ORDER_REPORT_TOTAL_DAILY_SALES_DETAIL)
    public ResponseEntity<ApiResponseDTO<List<OrderTotalDailySalesDTO>>> getTotalDailySales (
        @PathVariable(value = "storeId") int storeId,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @RequestParam(value = "createdAt") LocalDate createdAt
    ) {
        final Result<List<OrderTotalDailySalesDTO>> result = getOrderTotalDailySalesUseCase.invoke(storeId, createdAt);
        ApiResponseDTO<List<OrderTotalDailySalesDTO>> apiResponseDTO;
        HttpStatus httpStatus = HttpStatus.OK;

        if (result instanceof Result.Error) {
            Result.Error error = ((Result.Error) result);
            apiResponseDTO = new ApiResponseDTO<>(error.getException().getLocalizedMessage());
            log.error(error);
            httpStatus = ((Result.Error) result).getHttpStatus();
        } else {
            apiResponseDTO = new ApiResponseDTO<>(((Result.Success<List<OrderTotalDailySalesDTO>>) result).getData());
        }
        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }

}
