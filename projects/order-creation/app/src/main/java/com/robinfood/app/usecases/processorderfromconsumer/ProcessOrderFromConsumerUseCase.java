package com.robinfood.app.usecases.processorderfromconsumer;

import com.robinfood.app.commandhandler.IInvokerCommand;
import com.robinfood.app.usecases.messageproducer.IMessageProducerUseCase;
import com.robinfood.app.usecases.transformmenuerrormessage.ITransformMenuErrorMessageUseCase;
import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.exceptions.TransactionCreationException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.robinfood.app.enums.OrderStatusEnum.ORDER_STATUS_CREATED;
import static com.robinfood.app.enums.OrderStatusEnum.ORDER_STATUS_REJECTED;
import static com.robinfood.core.constants.AppConstants.ORDER_NOK;
import static com.robinfood.core.constants.AppConstants.ORDER_OK;
import static com.robinfood.core.constants.AppConstants.UNKNOWN_ERROR;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION;

/**
 * Implementation of IProcessOrderFromConsumerUseCase
 */
@Slf4j
@Service
public class ProcessOrderFromConsumerUseCase implements IProcessOrderFromConsumerUseCase {

    private final IInvokerCommand invokerCommand;
    private final IMessageProducerUseCase messageProducerUseCase;
    private final ITransformMenuErrorMessageUseCase transformMenuErrorMessageUseCase;
    private final ModelMapper modelMapper;

    public ProcessOrderFromConsumerUseCase(
            IInvokerCommand invokerCommand,
            IMessageProducerUseCase messageProducerUseCase,
            ITransformMenuErrorMessageUseCase transformMenuErrorMessageUseCase,
            ModelMapper modelMapper
    ) {
        this.invokerCommand = invokerCommand;
        this.messageProducerUseCase = messageProducerUseCase;
        this.transformMenuErrorMessageUseCase = transformMenuErrorMessageUseCase;
        this.modelMapper = modelMapper;
    }

    @Override
    public void invoke(
            OrderToCreateDTO orderToCreateDTO,
            String messageFrom,
            String messageCountry
    ) {
        log.info("Order received to process in ProcessOrderFromConsumer use case {}", orderToCreateDTO);

        log.info("Convert OrderToCreateDTO to TransactionRequestDTO {}", orderToCreateDTO);

        final TransactionRequestDTO transactionRequestDTO = modelMapper.map(
                orderToCreateDTO, TransactionRequestDTO.class
        );

        log.info("OrderToCreateDTO convert to TransactionRequestDTO successfully {}", transactionRequestDTO);

        orderToCreateDTO.setStatus(ORDER_STATUS_CREATED.getOrderStatus());
        orderToCreateDTO.setMessage(ORDER_OK);
        orderToCreateDTO.setDescription("Order created successfully");

        try {

            invokerCommand.group(EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, transactionRequestDTO);

        } catch (TransactionCreationException ex) {

            String errorMessage = UNKNOWN_ERROR;

            setValuesToOrderToCreate(orderToCreateDTO,
                    ex.getTransactionCreationError().getErrorCode(),
                    ex.getTransactionCreationError().name()
            );

            if (Objects.nonNull(ex.getData())) {
                errorMessage = ex.getData().toString();
            } else if (Objects.nonNull(ex.getDescription())) {
                errorMessage = ex.getDescription();
            }

            orderToCreateDTO.setErrorMessage(errorMessage);
            orderToCreateDTO.setDescription(errorMessage);

            transformMenuErrorMessageUseCase.invoke(orderToCreateDTO, ex);
        }

        log.info("Invoke MessageProducer use case {}.", orderToCreateDTO);
        messageProducerUseCase.invoke(orderToCreateDTO, messageFrom, messageCountry);
        log.info("Invoke MessageProducer use case successfully.");
    }

    private static void setValuesToOrderToCreate(
            OrderToCreateDTO orderToCreateDTO,
            Integer errorCode,
            String rejectType
    ) {
        orderToCreateDTO.setStatus(ORDER_STATUS_REJECTED.getOrderStatus());
        orderToCreateDTO.setMessage(ORDER_NOK);
        orderToCreateDTO.setErrorCode(errorCode);
        orderToCreateDTO.setRejectType(rejectType);
    }
}
