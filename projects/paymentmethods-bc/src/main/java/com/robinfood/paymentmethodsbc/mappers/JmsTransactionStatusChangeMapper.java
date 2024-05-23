package com.robinfood.paymentmethodsbc.mappers;

import java.util.Optional;

import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO.TransactionStatusResponse;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionDetailDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionStatusChangeDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import com.robinfood.paymentmethodsbc.model.GeneralPaymentMethod;
import com.robinfood.paymentmethodsbc.model.Platform;
import com.robinfood.paymentmethodsbc.model.Terminal;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;

/**
 * Mapper para datos correspondientes a mensajes de notificación
 * de cambio de estado
 */
public final class JmsTransactionStatusChangeMapper {

    private JmsTransactionStatusChangeMapper() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Mapea los datos del mensaje a enviar para notificación de cambio de estado
     * @param transaction {@linkplain Transaction} datos de transacción
     * @param transactionDetail {@linkplain TransactionDetail} detalle de transacción
     * @return {@linkplain JmsTransactionStatusChangeDTO}
     */
    public static JmsTransactionStatusChangeDTO getJmsTransactionStatusChangeDTO(
        final Transaction transaction,
        final TransactionDetail transactionDetail
    ) {
        String entityReference = null;
        if (transactionDetail != null) {
            entityReference = transactionDetail.getEntityReference();
        }

        final JmsTransactionStatusChangeDTO.EntityTransactionStatusChangeDTO entity = JmsTransactionStatusChangeDTO
            .EntityTransactionStatusChangeDTO.builder()
            .id(transaction.getEntity().getId())
            .sourceId(transaction.getEntitySource())
            .reference(entityReference)
            .build();

        final JmsTransactionStatusChangeDTO.TransactionStatusChangeDTO status = JmsTransactionStatusChangeDTO
            .TransactionStatusChangeDTO.builder()
            .id(transaction.getTransactionStatus().getId())
            .name(transaction.getTransactionStatus().getName())
            .date(UtilMapper.toIsoFormat(transaction.getUpdatedAt()))
            .build();

        final JmsTransactionStatusChangeDTO.PaymentDTO payment = JmsTransactionStatusChangeDTO
            .PaymentDTO.builder()
            .subtotal(transaction.getSubtotal())
            .tax(transaction.getTax())
            .total(transaction.getTotal())
            .build();

        final JmsTransactionStatusChangeDTO.PaymentGatewayDTO paymentGateway = JmsTransactionStatusChangeDTO
            .PaymentGatewayDTO.builder()
            .id(transaction.getPaymentGateway().getId())
            .name(transaction.getPaymentGateway().getName())
            .build();

        GeneralPaymentMethod generalPaymentMethod = Optional.ofNullable(
            transaction.getPaymentMethod()
        ).orElse(
            GeneralPaymentMethod.builder().id(0L).build()
        );

        Platform platform = Optional.ofNullable(
            transaction.getPlatform()
        ).orElse(
            Platform.builder().id(0L).build()
        );

        int storeId = Optional.ofNullable(
            transactionDetail.getStoreId()
        ).orElse(0L)
        .intValue();

        int userId = Optional.ofNullable(
            transaction.getUserId()
        ).orElse(0L)
        .intValue();

        Terminal terminal = Optional.ofNullable(
            transactionDetail.getTerminal()
        ).orElse(
            Terminal.builder().build()
        );

        final JmsTransactionStatusChangeDTO.TransactionDetailDTO transactionDetaildDto = 
            JmsTransactionStatusChangeDTO.TransactionDetailDTO.builder()
            .creditCardMaskedNumber(transactionDetail.getCreditCardMaskedNumber())
            .dataphoneReceiptNumber(transactionDetail.getDataphoneReceiptNumber())
            .creditCardMaskedNumber(transactionDetail.getCreditCardMaskedNumber())
            .dataphoneReceiptNumber(transactionDetail.getDataphoneReceiptNumber())
            .establishmentCode(transactionDetail.getEstablishmentCode())
            .franchise(transactionDetail.getFranchise())
            .generalPaymentMethodId(generalPaymentMethod.getId())
            .generalPaymentMethodName(generalPaymentMethod.getName())
            .installments(transactionDetail.getInstallments())
            .platformId(platform.getId().intValue())
            .platformName(platform.getName())
            .storeId(storeId)
            .userId(userId)
            .transactionTerminalName(terminal.getName())        
            .transactionTerminalUuid(terminal.getUuid())
            .transactionDataphoneTerminalCode(transactionDetail.getDataphoneCode())
            .transactionDataphoneUniqueCode(transactionDetail.getEstablishmentCode())
            .build();

        return JmsTransactionStatusChangeDTO
            .builder()
            .entity(entity)
            .transactionId(transaction.getId())
            .transactionUuid(transaction.getUuid())
            .transactionStatus(status)
            .payment(payment)
            .paymentGateway(paymentGateway)
            .transactionDetail(transactionDetaildDto)
            .build();
    }

    /**
     * Mapea las propiedades del mensaje a enviar para notificación de cambio de estado
     * @param transaction {@linkplain Transaction} datos de transacción
     * @return {@linkplain JmsTransactionDetailDTO}
     */
    public static JmsTransactionDetailDTO getJmsTransactionDetailDTO(
        final Transaction transaction
    ) {
        return JmsTransactionDetailDTO
            .builder()
            .status(transaction.getTransactionStatus().getId())
            .paymentGatewayId(transaction.getPaymentGateway().getId())
            .countryId(transaction.getCountry().getId())
            .entityId(transaction.getEntity().getId())
            .build();
    }

    public static BCITransactionStatusResponseDTO bciTransactionStatusResponseDTO(
        JmsUpdateTransactionStatusDTO jmsData
    ){
        return BCITransactionStatusResponseDTO.builder()
            .transaction(TransactionStatusResponse.builder()
                .transactionCode(jmsData.getTransactionCode())
                .transactionReference(jmsData.getTransactionReference())
                .authorizationCode(jmsData.getAuthorizationCode())
                .build())
            .build();
    }
}
