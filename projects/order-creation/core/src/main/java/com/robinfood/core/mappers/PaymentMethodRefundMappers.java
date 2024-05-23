package com.robinfood.core.mappers;

import static com.robinfood.core.constants.GlobalConstants.REASON_REFUND_MESSAGE;

import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodRefundResponseDTO;
import com.robinfood.core.dtos.queue.paymentmethod.TransactionDTO;
import com.robinfood.core.dtos.queue.paymentmethod.refunds.EntityDTO;
import com.robinfood.core.dtos.queue.paymentmethod.refunds.OriginDTO;
import com.robinfood.core.dtos.queue.paymentmethod.refunds.PaymentMethodRefundDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundResponseEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class PaymentMethodRefundMappers {

    private PaymentMethodRefundMappers() {
    }

    public static PaymentMethodRefundDTO transactionDTOtoPaymentMethodRefundDTO(TransactionDTO transactionDTO,
            TransactionRequestDTO transactionRequestDTO) {

        return PaymentMethodRefundDTO.builder()
                .entity(EntityDTO.builder()
                        .id(transactionDTO.getEntity().getId())
                        .reference(transactionDTO.getEntity().getReference())
                        .sourceId(transactionDTO.getEntity().getSourceId())
                        .build())
                .origin(OriginDTO.builder()
                        .channelId(transactionRequestDTO.getOrigin().getId())
                        .ip(transactionRequestDTO.getDevice().getIp())
                        .build())
                .reason(REASON_REFUND_MESSAGE)
                .userId(transactionDTO.getEntity().getSourceId().intValue())
                .build();
    }

    public static PaymentMethodRefundResponseDTO toPaymentMethodRefundsResponseDTO(
            ApiResponseEntity<PaymentMethodRefundResponseEntity> paymentMethodRefundResponseEntity){
        return PaymentMethodRefundResponseDTO.builder().code(paymentMethodRefundResponseEntity.getCode())
                .message(paymentMethodRefundResponseEntity.getMessage())
                .build();
    }
}
