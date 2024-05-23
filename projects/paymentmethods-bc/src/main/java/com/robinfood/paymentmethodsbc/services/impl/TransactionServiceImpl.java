package com.robinfood.paymentmethodsbc.services.impl;

import static com.robinfood.paymentmethodsbc.utils.JsonUtils.convertToJson;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.configs.PlatformTypeConfig;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentInitResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.RefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.RefundResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.TransactionEntityDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.TransactionStatusUpdateRequestDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsEntityRefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.RefundPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.enums.PlatformType;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundRuntimeException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.mappers.TransactionEntityMapper;
import com.robinfood.paymentmethodsbc.mappers.TransactionMapper;
import com.robinfood.paymentmethodsbc.model.GeneralPaymentMethod;
import com.robinfood.paymentmethodsbc.model.PaymentGateway;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.repositories.GeneralPaymentMethodsRepository;
import com.robinfood.paymentmethodsbc.repositories.TransactionsRepository;
import com.robinfood.paymentmethodsbc.services.TransactionService;
import com.robinfood.paymentmethodsbc.services.steps.StepGroupDefinitions;
import com.robinfood.paymentmethodsbc.services.steps.TransactionStepGroupDefinitionsFactory;
import com.robinfood.paymentmethodsbc.specifications.TransactionSpecifications;
import com.robinfood.paymentmethodsbc.utils.StepsControl;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private final GeneralPaymentMethodsRepository generalPaymentMethodsRepository;
    private final PlatformTypeConfig platformTypeConfig;
    private final TransactionsRepository transactionsRepository;

    public TransactionServiceImpl(
        GeneralPaymentMethodsRepository generalPaymentMethodsRepository,
        PlatformTypeConfig platformTypeConfig,
        TransactionsRepository transactionsRepository
    ) {
        this.generalPaymentMethodsRepository = generalPaymentMethodsRepository;
        this.platformTypeConfig = platformTypeConfig;
        this.transactionsRepository = transactionsRepository;
    }

    @BasicLog
    @Override
    public PaymentInitResponseDTO createPaymentInitialTransaction(
        PaymentRequestDTO transactionRequestDTO
    ) throws EntityNotFoundException, EntityNotFoundRuntimeException, BaseException, PaymentStepException {

        GeneralPaymentMethod paymentMethod = getGeneralPaymentMethod(
            transactionRequestDTO
                .getPaymentMethod()
                .getId()
        );

        PaymentPipeDTO initialTransactionPipeDTO = PaymentPipeDTO
            .builder()
            .transactionRequestDTO(transactionRequestDTO)
            .paymentMethod(paymentMethod)
            .paymentGateway(paymentMethod.getPaymentGateway())
            .build();

        PlatformType platformType = platformTypeConfig.getPlatformById(
            paymentMethod
                .getPaymentGateway()
                .getPlatform()
                .getId()
        );
        log.info("createPaymentInitialTransaction PlatformType ==> {}", platformType);

        StepsControl.validateSteps(
            initialTransactionPipeDTO,
            TransactionStepGroupDefinitionsFactory.getInitialTransactionDefinitions(platformType)
        );

        return TransactionMapper.getPaymentResponseDTO(
            initialTransactionPipeDTO.getTransaction(),
            initialTransactionPipeDTO.getTransactionDetail()
        );
    }

    @BasicLog
    @Override
    public void doPayment(
        PaymentRequestDTO paymentDTO
    ) throws EntityNotFoundException, BaseException, PaymentStepException {

        PaymentPipeDTO paymentPipeDTO = new PaymentPipeDTO(paymentDTO);

        GeneralPaymentMethod paymentMethod =
            getGeneralPaymentMethod(paymentDTO.getPaymentMethod().getId());

        paymentPipeDTO.setPaymentGateway(paymentMethod.getPaymentGateway());

        PlatformType platformType = platformTypeConfig.getPlatformById(
            paymentMethod
                .getPaymentGateway()
                .getPlatform()
                .getId()
        );
        log.info("doPayment PlatformType ==> {}", platformType);

        StepsControl.validateSteps(
            paymentPipeDTO,
            TransactionStepGroupDefinitionsFactory.getGenerateTransactionDefinitions(platformType)
        );

        PaymentInitResponseDTO response = TransactionMapper.getPaymentResponseDTO(
            paymentPipeDTO.getTransaction(),
            paymentPipeDTO.getTransactionDetail()
        );
        log.info("doPayment() response data: \n{}", convertToJson(response));
    }

    @BasicLog
    @Override
    public RefundResponseDTO doRefund(RefundRequestDTO refundRequestDTO)
        throws EntityNotFoundException, PaymentStepException {

        RefundPipeDTO pipe = RefundPipeDTO
            .builder()
            .originalTransactionId(refundRequestDTO.getTransactionId())
            .refundReason(refundRequestDTO.getReason())
            .build();

        StepsControl.validateSteps(
            pipe,
            StepGroupDefinitions.REFUND_TRANSACTION_STEPS
        );

        RefundResponseDTO response = TransactionMapper.getRefundResponseDTO(
            pipe
        );

        log.info("refundTransactionStatus() response data: \n{}", convertToJson(response));

        return response;
    }

    @BasicLog
    @Override
    public String updateTransactionStatus(
        TransactionStatusUpdateRequestDTO transactionStatusUpdateDTO
    ) throws EntityNotFoundException, PaymentStepException {

        TransactionStatusPipeDTO pipe = TransactionStatusPipeDTO
            .builder()
            .transactionStatusUpdateRequestDTO(transactionStatusUpdateDTO)
            .build();

        StepsControl.validateSteps(
            pipe,
            StepGroupDefinitions.UPDATE_TRANSACTION_STATUS_STEPS
        );

        log.info(
            "updateTransactionStatus() reference: {} response: {}",
            transactionStatusUpdateDTO.getIdentificator(),
            convertToJson(pipe.getTransactionStatusResultDTO())
        );

        return TransactionMapper.getUpdateTransactionStatusResponse(pipe);
    }

    @BasicLog
    @Override
    public List<TransactionEntityDTO> getTransactionsByEntityInfo(
        Long entitySourceId, String entityReference
    ) {

        if (Objects.nonNull(entitySourceId) && Objects.nonNull(entityReference)) {
            entityReference = null;
        }

        Specification<Transaction> specification =
            TransactionSpecifications.getEqualTransactionEntity(entitySourceId, entityReference);

        return transactionsRepository.findAll(specification)
            .stream()
            .map(TransactionEntityMapper::getTransactionEntityDTO)
            .collect(Collectors.toList());
    }

    private GeneralPaymentMethod getGeneralPaymentMethod(
        Long generalPaymentMethodId
    ) throws EntityNotFoundRuntimeException, BaseException {

        GeneralPaymentMethod paymentMethod = generalPaymentMethodsRepository
            .findByIdAndStatus(
                generalPaymentMethodId, GeneralPaymentMethod.STATUS_ENABLED
            )
            .orElseThrow(() ->
                new EntityNotFoundRuntimeException(
                    GeneralPaymentMethod.class.getSimpleName(),
                    String.valueOf(generalPaymentMethodId)
                )
            );

        PaymentGateway paymentGateway = paymentMethod.getPaymentGateway();

        if (Objects.isNull(paymentGateway)) {
            throw new BaseException(
                String.format(
                    "No se encontró un gateway válido para el método de pago con id %s",
                    paymentMethod.getName()
                )
            );
        }

        if (Objects.isNull(paymentGateway.getPlatform())) {
            throw new BaseException(
                String.format(
                    "No se encontró un platform válido para el método de pago con id %s",
                    paymentMethod.getName()
                )
            );
        }

        return paymentMethod;
    }

    @BasicLog
    @Override
    public void updateTransactionStatusJMS(
        JmsUpdateTransactionStatusDTO jmsUpdateTransactionStatusDTO, String rawMessage)
        throws EntityNotFoundException, PaymentStepException {

        JmsTransactionStatusPipeDTO pipe = JmsTransactionStatusPipeDTO
            .builder()
            .jmsUpdateTransactionStatusDTO(jmsUpdateTransactionStatusDTO)
            .jmsUpdateTransactionStatusJsonMessage(rawMessage)
            .build();

        StepsControl.validateSteps(pipe, StepGroupDefinitions.JMS_UPDATE_TRANSACTION_STATUS_STEPS);
    }

    @BasicLog
    @Override
    public void entityRefund(
        JmsEntityRefundRequestDTO jmsEntityRefundRequestDTO
    ) {
        Long sourceId = jmsEntityRefundRequestDTO.getEntitySourceId();
        String sourceReference = jmsEntityRefundRequestDTO.getEntitySourceReference();
        String reason = jmsEntityRefundRequestDTO.getReason();

        if (Objects.nonNull(sourceId) && Objects.nonNull(sourceReference)) {
            sourceReference = null;
        }

        Specification<Transaction> spec = TransactionSpecifications
            .getEntityLongEqual(
                jmsEntityRefundRequestDTO.getEntityId(), "id"
            )
            .and(
                TransactionSpecifications.getEqualTransactionEntity(
                    sourceId,
                    sourceReference
                )
            );

        transactionsRepository.findAll(spec)
            .forEach((Transaction transaction) -> safeDoRefund(transaction, reason));
    }

    private void safeDoRefund(Transaction transaction, String reason) {
        try {
            doRefund(
                RefundRequestDTO
                    .builder()
                    .transactionId(transaction.getId())
                    .reason(reason)
                    .build()
            );
        } catch (EntityNotFoundException | PaymentStepException e) {
            log.error(
                String.format("Error entity refund for transaction id %s", transaction.getId()), e);
        }
    }

    @Override
    public List<TransactionEntityDTO> getTransactionsByEntityInfo(
        Long entitySourceId,
        String entityReference,
        String uuid
    ) {

        Specification<Transaction> specification =
            TransactionSpecifications.getEqualTransactionEntity(entitySourceId, entityReference,
                uuid);

        return transactionsRepository.findAll(specification)
            .stream()
            .map(TransactionEntityMapper::getTransactionEntityDTO)
            .collect(Collectors.toList());
    }
}
