package com.robinfood.paymentmethodsbc.mappers;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentInitResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.RefundResponseDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.RefundPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.model.PaymentGateway;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.model.TransactionStatus;
import com.robinfood.paymentmethodsbc.model.TransactionStatusLog;
import com.robinfood.paymentmethodsbc.model.TransactionUser;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public final class TransactionMapper {

    private TransactionMapper() {}

    public static Transaction getTransactionFromCreateDTO(
        PaymentPipeDTO transactionDetailDTO
    ) {
        PaymentRequestDTO transactionDTO = transactionDetailDTO.getTransactionRequestDTO();

        PaymentGateway paymentGateway = transactionDetailDTO.getPaymentGateway();

        return Transaction
            .builder()
            .uuid(getUuid(transactionDTO))
            .succeeded(false)
            .platform(paymentGateway.getPlatform())
            .paymentGateway(paymentGateway)
            .paymentMethod(transactionDetailDTO.getPaymentMethod())
            .creditCard(transactionDetailDTO.getCreditCard())
            .country(transactionDetailDTO.getCountry())
            .entity(transactionDetailDTO.getEntity())
            .entitySource(transactionDTO.getEntity().getSource())
            .userId(transactionDTO.getUser().getUserId())
            .subtotal(transactionDTO.getPayment().getSubtotal())
            .tax(transactionDTO.getPayment().getTax())
            .total(transactionDTO.getPayment().getTotal())
            .build();
    }

    public static Transaction mapRefundFromTransaction(Transaction original) {
        return Transaction
            .builder()
            .uuid(UUID.randomUUID().toString())
            .succeeded(false)
            .paymentGateway(original.getPaymentGateway())
            .creditCard(original.getCreditCard())
            .country(original.getCountry())
            .platform(original.getPlatform())
            .subtotal(original.getSubtotal())
            .total(original.getTotal())
            .tax(original.getTax())
            .userId(original.getUserId())
            .build();
    }

    public static PaymentInitResponseDTO getPaymentResponseDTO(
        Transaction transaction,
        TransactionDetail transactionDetail
    ) {
        PaymentInitResponseDTO.PaymentGatewayTransactionDTO gatewayTransactionDTO = null;
        PaymentInitResponseDTO.EntityDTO entityTransactionDTO = null;

        if (transaction.getPaymentGateway() != null) {
            gatewayTransactionDTO =
                PaymentInitResponseDTO
                    .PaymentGatewayTransactionDTO.builder()
                    .paymentGatewayId(transaction.getPaymentGateway().getId())
                    .paymentGatewayName(
                        transaction.getPaymentGateway().getName()
                    )
                    .build();
        }

        if (transaction.getEntity() != null) {
            entityTransactionDTO =
                PaymentInitResponseDTO
                    .EntityDTO.builder()
                    .id(transaction.getEntity().getId())
                    .source(transaction.getEntitySource())
                    .reference(transactionDetail.getEntityReference())
                    .build();
        }

        return PaymentInitResponseDTO
            .builder()
            .generated(transaction.isSucceeded())
            .id(transaction.getId())
            .uuid(transaction.getUuid())
            .authorizarionCode(transaction.getAuthorizationCode())
            .status(
                PaymentInitResponseDTO
                    .StatusDTO.builder()
                    .id(transaction.getTransactionStatus().getId())
                    .name(transaction.getTransactionStatus().getName())
                    .build()
            )
            .platformTypeId(transaction.getPlatform().getId())
            .paymentGateway(gatewayTransactionDTO)
            .entityTransactionDTO(entityTransactionDTO)
            .paymentTransactionDTO(
                PaymentInitResponseDTO
                    .PaymentDTO.builder()
                    .subtotal(transaction.getSubtotal())
                    .total(transaction.getTotal())
                    .tax(transaction.getTax())
                    .build()
            )
            .build();
    }

    public static RefundResponseDTO getRefundResponseDTO(RefundPipeDTO pipe) {
        return RefundResponseDTO
            .builder()
            .transactionId(pipe.getOriginalTransaction().getId())
            .status(
                RefundResponseDTO
                    .StatusRefundDTO.builder()
                    .id(
                        pipe
                            .getOriginalTransaction()
                            .getTransactionStatus()
                            .getId()
                    )
                    .name(
                        pipe
                            .getOriginalTransaction()
                            .getTransactionStatus()
                            .getName()
                    )
                    .build()
            )
            .build();
    }

    public static TransactionDetail getTransactionDetailInitial(
        PaymentPipeDTO transactionDetailDTO,
        Transaction transaction
    ) {
        PaymentRequestDTO transactionDTO = transactionDetailDTO.getTransactionRequestDTO();

        String entityReference = transactionDTO.getEntity().getReference();

        return TransactionDetail
            .builder()
            .transactionId(transaction.getId())
            .originPlatformId(transactionDTO.getOrigin().getChannelId())
            .storeId(transactionDTO.getOrigin().getStoreId())
            .entityReference(entityReference)
            .terminal(transactionDetailDTO.getTerminal())
            .build();
    }

    public static TransactionStatusLog getTransactionStatusLog(
        Transaction transaction
    ) {
        return TransactionStatusLog
            .builder()
            .transaction(transaction)
            .transactionStatus(transaction.getTransactionStatus())
            .build();
    }

    public static TransactionUser getTransactionUser(
        Transaction transaction,
        PaymentRequestDTO.UserDTO userTransactionDTO
    ) {
        return TransactionUser
            .builder()
            .transactionId(transaction.getId())
            .userId(transaction.getUserId())
            .firstName(userTransactionDTO.getFirstName())
            .lastName(userTransactionDTO.getLastName())
            .phoneCode(userTransactionDTO.getPhoneCode())
            .phoneNumber(userTransactionDTO.getPhoneNumber())
            .build();
    }

    public static String getUpdateTransactionStatusResponse(
        TransactionStatusPipeDTO pipe
    ) {
        return pipe
            .getTransactionStatusResultDTO()
            .getTransaction()
            .getAccepted();
    }

    private static String getUuid(PaymentRequestDTO transactionDTO) {
        return Optional
            .ofNullable(transactionDTO.getTransactionUuid())
            .orElse(transactionDTO.getEntity().getReference());
    }

    public static Long getFinalTransactionStatusId(Transaction transaction){

        TransactionStatus transactionStatus =  transaction.getTransactionStatus();

        return Optional.ofNullable(transactionStatus.getTransactionStatusParentId())
        .filter((Long  parentId) 
            -> parentId > BigDecimal.ZERO.longValue()
        )
        .orElse(
            transaction.getTransactionStatus().getId()
        );

    }
}
