package com.robinfood.ordereports_bc_muyapp.usecases.getuserorderdetailbyuid;

import com.robinfood.app.library.exception.ApplicationException;
import com.robinfood.app.library.exception.business.TransactionExecutionException;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseCouponsDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;
import com.robinfood.ordereports_bc_muyapp.usecases.getorderbyuid.IGetOrderByOrderIdUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.getordercouponbytransactionid.IGetOrderCouponByTransactionIdUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.getplatformbyorderid.IGetPlatformByOrderIdUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.gettransactionbyuuid.IGetTransactionByUuidUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.gettransactionflowbyid.IGetTransactionFlowByIdUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.getuserorderdetailadditionalinfo.IGetUserOrderDetailAdditionalInfoUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static com.robinfood.ordereports_bc_muyapp.enums.OrderDetailLogEnum.GETTING_COUPON_DETAIL;
import static com.robinfood.ordereports_bc_muyapp.enums.OrderDetailLogEnum.GETTING_FLOW_ID;
import static com.robinfood.ordereports_bc_muyapp.enums.OrderDetailLogEnum.GETTING_LIST_ORDER_HISTORY;
import static com.robinfood.ordereports_bc_muyapp.enums.OrderDetailLogEnum.GETTING_ORDER_DETAIL;
import static com.robinfood.ordereports_bc_muyapp.enums.OrderDetailLogEnum.GETTING_PLATFORM_ID;
import static com.robinfood.ordereports_bc_muyapp.enums.OrderDetailLogEnum.GETTING_TRANSACTION_ID_REQUEST;

/**
 * Implementation of IGetUserOrderDetailByUIdUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
public class GetUserOrderDetailByUIdUseCase implements IGetUserOrderDetailByUIdUseCase {

    private final IGetOrderByOrderIdUseCase getOrderByUIdUseCase;
    private final IGetPlatformByOrderIdUseCase getPlatformByOrderIdUseCase;
    private final IGetTransactionByUuidUseCase getTransactionByUuidUseCase;
    private final IGetTransactionFlowByIdUseCase getTransactionFlowByIdUseCase;
    private final IGetUserOrderDetailAdditionalInfoUseCase getUserOrderDetailAdditionalInfoUseCase;
    private final IGetOrderCouponByTransactionIdUseCase getOrderCouponByTransactionIdUseCase;

    @Override
    public ResponseOrderDetailDTO invoke(String transactionUuid) throws ApplicationException {

        try {

            log.info(GETTING_TRANSACTION_ID_REQUEST.getMessage(), transactionUuid);
            CompletableFuture<Integer> transactionIdFuture = getTransactionByUuidUseCase.invoke(transactionUuid);

            log.info(GETTING_ORDER_DETAIL.getMessage(), transactionUuid);
            CompletableFuture<ResponseOrderDetailDTO> orderDetailFuture = transactionIdFuture.thenCompose(
                    getOrderByUIdUseCase::invoke
            );

            log.info(GETTING_COUPON_DETAIL.getMessage(), transactionUuid);
            CompletableFuture<List<ResponseCouponsDTO>> couponsDTOFuture = transactionIdFuture.thenCompose(
                    getOrderCouponByTransactionIdUseCase::invoke
            );

            log.info(GETTING_FLOW_ID.getMessage(), transactionUuid);
            CompletableFuture<Short> flowIdFuture = orderDetailFuture.thenCompose(
                    orderDetail -> getTransactionFlowByIdUseCase.invoke(orderDetail.getTransactionId())
            );

            log.info(GETTING_PLATFORM_ID.getMessage(), transactionUuid);
            CompletableFuture<Short> platformIdFuture = orderDetailFuture.thenCompose(
                    orderDetail -> getPlatformByOrderIdUseCase.invoke(orderDetail.getId())
            );

            log.info(GETTING_LIST_ORDER_HISTORY.getMessage(), transactionUuid);
            CompletableFuture.allOf(orderDetailFuture, flowIdFuture, platformIdFuture, couponsDTOFuture)
                    .join();

            ResponseOrderDetailDTO orderDetailDTO = builderToResponseDetailDTO(
                    flowIdFuture, platformIdFuture, orderDetailFuture, couponsDTOFuture
            );

            orderDetailDTO.setTransactionUuid(transactionUuid);

            return getUserOrderDetailAdditionalInfoUseCase.invoke(orderDetailDTO);

        } catch (CompletionException exception) {

            if (exception.getCause() instanceof TransactionExecutionException transactionExecutionException) {

                int code = transactionExecutionException.getCode();
                String messageError =
                        transactionExecutionException.getResponseExceptionDTO()
                                .getErrorMessage();
                throw new TransactionExecutionException(messageError, code);
            }
            throw new ApplicationException(exception);
        }
    }

    private ResponseOrderDetailDTO builderToResponseDetailDTO(
            CompletableFuture<Short> flowIdFuture,
            CompletableFuture<Short> platformIdFuture,
            CompletableFuture<ResponseOrderDetailDTO> orderDetailDTOFuture,
            CompletableFuture<List<ResponseCouponsDTO>> couponsDTOFuture
    ) {

        ResponseOrderDetailDTO orderDetailDTO = orderDetailDTOFuture.join();

        List<ResponseCouponsDTO> responseCouponsDTO = couponsDTOFuture.join();

        Short flowId = flowIdFuture.join();

        Short platformId = platformIdFuture.join();

        return orderDetailDTO.toBuilder()
                .origin(
                        orderDetailDTO.getOrigin()
                                .toBuilder()
                                .platformId(platformId)
                                .build())
                .flowId(flowId)
                .coupons(responseCouponsDTO)
                .build();
    }
}
