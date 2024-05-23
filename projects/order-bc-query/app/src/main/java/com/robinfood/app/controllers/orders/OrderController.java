package com.robinfood.app.controllers.orders;

import com.robinfood.app.usecases.exitstransactionuuidandorderuids.IExitsTransactionUuidAndOrderUuidsUseCase;
import com.robinfood.app.usecases.getdailysalessummary.IGetDailySalesSummaryByStoreIdAndDateUseCase;
import com.robinfood.app.usecases.getorderdetailbyidsanduids.IGetOrderDetailByIdsAndsUidsUseCase;
import com.robinfood.app.usecases.getordersbytransaction.IGetOrdersByTransactionIdUseCase;
import com.robinfood.app.usecases.getorderspaymentmethodsbyposid.IGetOrdersPaymentMethodsByPosIdUseCase;
import com.robinfood.app.usecases.getordertotaldailysalesbyparams.IGetOrdersTotalDailySalesByParamsUseCase;
import com.robinfood.app.usecases.getstate.IGetStateOrderWithCodeUseCase;
import com.robinfood.app.usecases.getstateorders.IGetStateOrderUseCase;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.GetOrderDetailDTO;
import com.robinfood.core.dtos.GetOrderPaymentMethodsDTO;
import com.robinfood.core.dtos.GetOrderTotalDailySalesDTO;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;
import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.BadRequestAbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.response.order.ResponseExistsTransactionUuidOrderUidDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.EXIST_TRANSACTION_UUID_ORDER_UID;
import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static com.robinfood.core.constants.APIConstants.ORDER_BY_TRANSACTION_ID;
import static com.robinfood.core.constants.APIConstants.ORDER_DETAIL_PRINT;
import static com.robinfood.core.constants.APIConstants.ORDER_GET_CODE_STATE;
import static com.robinfood.core.constants.APIConstants.ORDER_GET_DAILY_SALE_SUMMARY;
import static com.robinfood.core.constants.APIConstants.ORDER_GET_PAYMENT_METHODS;
import static com.robinfood.core.constants.APIConstants.ORDER_GET_STATE;
import static com.robinfood.core.constants.APIConstants.ORDER_TOTAL_DAILY_SALES_BY_PARAMS;
import static com.robinfood.core.constants.GlobalConstants.LIMIT_DAYS;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Implementation of 'IOrderController'
 */
@AllArgsConstructor
@RestController
@RequestMapping(ORDERS_V1)
@Slf4j
public class OrderController implements IOrderController {

    private final IGetOrderDetailByIdsAndsUidsUseCase getOrderDetailByIdsAndsUidsUseCase;
    private final IGetStateOrderUseCase getStateOrderUseCase;
    private final IGetStateOrderWithCodeUseCase getStateUseCase;
    private final IGetOrdersByTransactionIdUseCase getOrdersByTransactionIdUseCase;

    private final IGetOrdersPaymentMethodsByPosIdUseCase getOrdersPaymentMethodsByPosIdUseCase;
    private final IGetOrdersTotalDailySalesByParamsUseCase getOrdersTotalDailySalesByParamsUseCase;

    private final IExitsTransactionUuidAndOrderUuidsUseCase exitsTransactionUuidAndOrderUidsUseCase;

    private final IGetDailySalesSummaryByStoreIdAndDateUseCase getDailySalesSummaryUseCase;

    @NotNull
    private ResponseEntity<ApiResponseDTO<List<GetOrderDetailDTO>>> getApiResponseDTOResponseEntityByOrderDetail(
            @RequestParam(value = "orderIds", required = false, defaultValue = "") List<Long> orderIds,
            @RequestParam(value = "orderUids", required = false, defaultValue = "") List<String> orderUids,
            @RequestParam(value = "orderUuid", required = false, defaultValue = "") List<String> orderUuid
    ) {
        AbstractApiResponseBuilderDTO<List<GetOrderDetailDTO>> apiResponseDTOBuilder;

        if (orderUids.isEmpty() && orderIds.isEmpty() && orderUuid.isEmpty()) {
            apiResponseDTOBuilder = new BadRequestAbstractApiResponseBuilderDTO<>();
            apiResponseDTOBuilder.build("Require parameters orderIds, orderUids or orderUuid");
            return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(),
                    HttpStatus.BAD_REQUEST);
        }

        List<GetOrderDetailDTO> getOrderDetailDTOS = getOrderDetailByIdsAndsUidsUseCase.invoke(
                orderIds, orderUids, orderUuid);

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(getOrderDetailDTOS);

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }

    @GetMapping(ORDER_GET_STATE)
    public ResponseEntity<ApiResponseDTO<OrderStateDTO>> getState(@PathVariable Long idOrder) {

        if (idOrder < GlobalConstants.DEFAULT_INTEGER_VALUE) {
            throw new IllegalArgumentException("The value cannot be less than zero");
        }

        AbstractApiResponseBuilderDTO<OrderStateDTO> apiResponseDTOBuilder;

        log.info(
                "Receiving request to get the state of a Order: {}", idOrder);

        OrderStateDTO orderStateDTO = getStateOrderUseCase.invoke(idOrder);

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(orderStateDTO);
        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }

    @GetMapping(ORDER_GET_CODE_STATE)
    public ResponseEntity<ApiResponseDTO<OrderStateDTO>> getStateParent(@PathVariable String code) {

        AbstractApiResponseBuilderDTO<OrderStateDTO> apiResponseDTOBuilder;
        log.info("Receiving request to get the state parent: {}", code);

        OrderStateDTO orderStateDTO = getStateUseCase.invoke(code);

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(orderStateDTO);
        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }

    @GetMapping(ORDER_GET_DAILY_SALE_SUMMARY)
    public ResponseEntity<ApiResponseDTO<OrderDailySaleSummaryDTO>> getDailySaleSummary(
            @PathVariable("storeId") long storeId,
            @RequestParam(value = "createdAt")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAt
    ) {

        AbstractApiResponseBuilderDTO<OrderDailySaleSummaryDTO> apiResponseDTOBuilder;
        log.info("Receiving request to get orders daily sales summary with store id: {} and createdAt: {}",
                storeId, createdAt);

        OrderDailySaleSummaryDTO dailySaleSummaryDTO = getDailySalesSummaryUseCase.invoke(storeId, createdAt);

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(dailySaleSummaryDTO);
        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ApiResponseDTO<List<GetOrderDetailDTO>>> getOrdersDetail(
            @RequestParam(
                    value = "orderIds",
                    required = false,
                    defaultValue = ""
            ) List<Long> orderIds,
            @RequestParam(
                    value = "orderUids",
                    required = false,
                    defaultValue = ""
            ) List<String> orderUids,
            @RequestParam(
                    value = "orderUuid",
                    required = false,
                    defaultValue = ""
            ) List<String> orderUuid
    ) {
        log.info("Receiving request to get orders detail with order ids: {}, order uids: {}, and order uuids: {}",
                orderIds, orderUids, orderUuid);

        return getApiResponseDTOResponseEntityByOrderDetail(orderIds, orderUids, orderUuid);
    }

    @GetMapping(ORDER_DETAIL_PRINT)
    public ResponseEntity<ApiResponseDTO<List<GetOrderDetailDTO>>> getOrdersDetailPrint(
            @RequestParam(
                    value = "orderIds",
                    required = false,
                    defaultValue = ""
            ) List<Long> orderIds,
            @RequestParam(
                    value = "orderUids",
                    required = false,
                    defaultValue = ""
            ) List<String> orderUids,
            @RequestParam(
                    value = "orderUuid",
                    required = false,
                    defaultValue = ""
            ) List<String> orderUuid
    ) {
        log.info(
                "Receiving request to get orders detail print with order ids: {}, order uids: {} and order uuid: {}",
                orderIds,
                orderUids,
                orderUuid
        );

        return getApiResponseDTOResponseEntityByOrderDetail(orderIds, orderUids, orderUuid);
    }

    @GetMapping(ORDER_GET_PAYMENT_METHODS)
    public ResponseEntity<ApiResponseDTO<List<GetOrderPaymentMethodsDTO>>> getOrderPaymentMethods(
            LocalDate localDateStart,
            LocalDate localDateEnd,
            Long posId,
            String timeZone
    ) {

        log.info(
                "retrieves the grouped details of the payment methods with " +
                        " posId: {}, localDateStart: {}, localDateEnd: {}", posId, localDateStart, localDateEnd
        );

        AbstractApiResponseBuilderDTO<List<GetOrderPaymentMethodsDTO>> apiResponseDTOBuilder;

        if (localDateEnd.isBefore(localDateStart)) {

            apiResponseDTOBuilder = new BadRequestAbstractApiResponseBuilderDTO<>();
            apiResponseDTOBuilder.build("The start date cannot be greater than the end date");
            return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.BAD_REQUEST);
        }

        final long days = DAYS.between(localDateStart, localDateEnd);

        if (days > LIMIT_DAYS) {
            apiResponseDTOBuilder = new BadRequestAbstractApiResponseBuilderDTO<>();
            apiResponseDTOBuilder.build("The range of days must be one");
            return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.BAD_REQUEST);
        }

        List<GetOrderPaymentMethodsDTO> getOrdersByTransactionIdDTO =
                getOrdersPaymentMethodsByPosIdUseCase.invoke(localDateStart, localDateEnd, posId, timeZone);

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(getOrdersByTransactionIdDTO);

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }


    @GetMapping(ORDER_BY_TRANSACTION_ID)
    public ResponseEntity<ApiResponseDTO<List<OrderDTO>>> getOrdersByTransactionId(
            @PathVariable("id") Long transactionId
    ) {
        log.info(
                "Receiving request to get orders detail with transactionId: {}",
                transactionId);

        AbstractApiResponseBuilderDTO<List<OrderDTO>> apiResponseDTOBuilder;

        List<OrderDTO> getOrdersByTransactionIdDTO = getOrdersByTransactionIdUseCase.invoke(transactionId);

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(getOrdersByTransactionIdDTO);
        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }

    @GetMapping(ORDER_TOTAL_DAILY_SALES_BY_PARAMS)
    public ResponseEntity<ApiResponseDTO<List<GetOrderTotalDailySalesDTO>>> getTotalDailySalesByParams(
            @PathVariable("storeId") Long storeId,
            @RequestParam(value = "createdAt")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAt
    ) {

        log.info("Receiving request to get total daily sales with storeId: {}, createdAt: {}", storeId, createdAt);

        AbstractApiResponseBuilderDTO<List<GetOrderTotalDailySalesDTO>> apiResponseDTOBuilder;

        List<GetOrderTotalDailySalesDTO> getOrdersByTransactionIdDTO = getOrdersTotalDailySalesByParamsUseCase
                .invoke(storeId, createdAt);

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(getOrdersByTransactionIdDTO);
        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }

    @GetMapping(EXIST_TRANSACTION_UUID_ORDER_UID)
    public ResponseEntity<ApiResponseDTO<ResponseExistsTransactionUuidOrderUidDTO>> existsTransactionUuidOrderUid(
            @RequestParam(
                    value = "transactionUuid",
                    required = false,
                    defaultValue = ""
            ) String transactionUuid,
            @RequestParam(
                    value = "orderUuids",
                    required = false,
                    defaultValue = ""
            ) List<String> orderUuids
    ) {
        log.info("Receiving request to check if transactionUuid {} or order uids {} exits",
                transactionUuid, orderUuids);

        AbstractApiResponseBuilderDTO<ResponseExistsTransactionUuidOrderUidDTO> apiResponseDTOBuilder;

        if (orderUuids.isEmpty() && transactionUuid.isEmpty()) {
            log.error("Require parameters transactionUuid or order uid are empty");

            apiResponseDTOBuilder = new BadRequestAbstractApiResponseBuilderDTO<>();
            apiResponseDTOBuilder.build("Require parameters transactionUuid or order uids");
            return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(),
                    HttpStatus.BAD_REQUEST);
        }

        ResponseExistsTransactionUuidOrderUidDTO response = exitsTransactionUuidAndOrderUidsUseCase.invoke(
                transactionUuid, orderUuids);

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(response);
        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }
}
