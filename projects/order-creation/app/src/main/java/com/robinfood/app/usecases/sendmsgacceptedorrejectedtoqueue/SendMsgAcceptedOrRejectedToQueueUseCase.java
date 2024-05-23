package com.robinfood.app.usecases.sendmsgacceptedorrejectedtoqueue;

import com.robinfood.app.commandhandler.IInvokerCommand;
import com.robinfood.app.enums.paymentmethod.PaymentMethodStatusEnum;
import com.robinfood.app.usecases.gettemporarytransaction.IGetTemporaryTransactionUseCase;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.constants.StatusCodeConstants;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.queue.paymentmethod.TransactionDTO;
import com.robinfood.core.dtos.transactionrequestdto.NewUserDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundEntity;
import com.robinfood.core.models.domain.Token;
import com.robinfood.core.util.SaveDataInMemoryUtil;
import com.robinfood.repository.queue.activemq.IProducerRepository;
import com.robinfood.repository.queue.activemq.paymentmethodsrefunds.IPaymentMethodRefundsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.robinfood.app.enums.paymentmethod.PaymentMethodStatusEnum.paymentMethodStatusEnum;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION;
import static com.robinfood.core.constants.GlobalConstants.ENTITY_ID;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToClassConvertValue;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Slf4j
@Service
public class SendMsgAcceptedOrRejectedToQueueUseCase implements ISendMsgAcceptedOrRejectedToQueueUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final IGetTemporaryTransactionUseCase getTemporaryTransactionUseCase;

    private final IInvokerCommand invokerCommand;

    private final IProducerRepository producerRepository;

    private final IPaymentMethodRefundsRepository paymentMethodRefundsRepository;

    public SendMsgAcceptedOrRejectedToQueueUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IGetTemporaryTransactionUseCase getTemporaryTransactionUseCase,
            IInvokerCommand invokerCommand,
            IProducerRepository producerRepository,
            IPaymentMethodRefundsRepository paymentMethodRefundsRepository
    ) {

        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.getTemporaryTransactionUseCase = getTemporaryTransactionUseCase;
        this.invokerCommand = invokerCommand;
        this.producerRepository = producerRepository;
        this.paymentMethodRefundsRepository = paymentMethodRefundsRepository;

    }

    @Override
    public void invoke(TransactionDTO transactionDTO) {

        final PaymentMethodStatusEnum paymentStatus = paymentMethodStatusEnum(
                transactionDTO.getTransactionStatus().getId()
        );

        if (ENTITY_ID != transactionDTO.getEntity().getId() ||
                PaymentMethodStatusEnum.REFUNDED.equals(paymentStatus)) {
            return;
        }

        final TransactionRequestDTO transactionRequestDTO = getTransactionInDynamo(transactionDTO);

        Token token = getTokenBusinessCapabilityUseCase.invoke();

        if (PaymentMethodStatusEnum.REJECTED.equals(paymentStatus)) {

            changeOrderStatus(
                    paymentStatus.getCode(),
                    transactionRequestDTO.getOrders(),
                    transactionRequestDTO.getOrders(),
                    transactionRequestDTO,
                    transactionRequestDTO.getUser()
            );

            paymentMethodRefundsRepository.sendRefundMessage(token.getAccessToken(),
                    buildPaymentMethodRefundEntity(transactionDTO));
            return;
        }

        if (PaymentMethodStatusEnum.ACCEPTED.equals(paymentStatus)) {

            transactionRequestDTO.setPaid(Boolean.TRUE);

            TransactionRequestDTO unmodifiedRequest = getUnmodifiedRequest(transactionRequestDTO);

            invokerCommand.group(EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, transactionRequestDTO);

            changeOrderStatus(
                    StatusCodeConstants.ORDER_PAID,
                    unmodifiedRequest.getOrders(),
                    transactionRequestDTO.getOrders(),
                    transactionRequestDTO,
                    transactionRequestDTO.getUser()
            );
        }
    }

    private static PaymentMethodRefundEntity buildPaymentMethodRefundEntity(TransactionDTO transactionDTO) {

        return PaymentMethodRefundEntity.builder()
                .entityId(transactionDTO.getEntity().getId())
                .entitySourceId(transactionDTO.getEntity().getSourceId())
                .entitySourceReference(transactionDTO.getEntity().getReference())
                .reason("Canceled").build();

    }

    private TransactionRequestDTO getUnmodifiedRequest(TransactionRequestDTO transactionRequestDTO) {
        return objectToClassConvertValue(transactionRequestDTO, TransactionRequestDTO.class);
    }

    private void changeOrderStatus(
            StatusCodeConstants code,
            List<OrderDTO> oldOrderDTOS,
            List<OrderDTO> newOrderDTOS,
            TransactionRequestDTO transactionRequestDTO,
            NewUserDTO userDTO
    ) {
        for (int index = 0; index < oldOrderDTOS.size(); index++) {

            ChangeOrderStatusDTO changeStatus = ChangeOrderStatusDTO.builder()
                    .notes("Order status change")
                    .orderId(newOrderDTOS.get(index).getId())
                    .orderUid(oldOrderDTOS.get(index).getId())
                    .orderUuid(newOrderDTOS.get(index).getUuid().toString())
                    .transactionUuid(transactionRequestDTO.getUuid().toString())
                    .origin("Payment-method queue")
                    .statusCode(code.name())
                    .userId(userDTO.getId())
                    .uuid(oldOrderDTOS.get(index).getUuid())
                    .build();

            log.info("Send change status {} to queue", objectToJson(changeStatus));

            producerRepository.sendChangeStatusMessage(changeStatus);
        }
        SaveDataInMemoryUtil.removeValue(transactionRequestDTO.getUuid().toString());
    }

    private TransactionRequestDTO getTransactionInDynamo(TransactionDTO transactionDTO) {

        return getTemporaryTransactionUseCase.invoke(
                transactionDTO.getEntity().getReference()
        );
    }
}
