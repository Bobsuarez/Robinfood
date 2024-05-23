package com.robinfood.paymentmethodsbc.controllers.v1;

import com.robinfood.paymentmethodsbc.annotations.BaseResponse;
import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.controllers.v1.docs.TransactionsDocs;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentInitResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.RefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.RefundResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.TransactionEntityDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.TransactionStatusUpdateRequestDTO;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.enums.ResponseCode;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundRuntimeException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.services.TransactionService;
import com.robinfood.paymentmethodsbc.utils.TransactionLogger;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@BaseResponse
@RequestMapping("/api/v1/transactions")
public class TransactionsController implements TransactionsDocs {
    private final TransactionService transactionService;


    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    @BasicLog
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority(@Permissions.PERM_SERVICE)")
    public PaymentInitResponseDTO payment(
        @Valid @RequestBody PaymentRequestDTO transactionDTO
    ) throws BaseException, EntityNotFoundException, EntityNotFoundRuntimeException, PaymentStepException {
        TransactionLogger.invoke(transactionDTO);

        PaymentInitResponseDTO paymentInitResponseDTO = 
            transactionService.createPaymentInitialTransaction(
                transactionDTO
            );

        TransactionLogger.clear();
        return paymentInitResponseDTO;
    }

    @Override
    @BasicLog
    @PutMapping("/refund")
    @ResponseStatus(HttpStatus.OK)
    public RefundResponseDTO refund(
        @Valid @RequestBody RefundRequestDTO refundRequestDTO
    )
        throws BaseException, EntityNotFoundException, PaymentStepException {
        return transactionService.doRefund(refundRequestDTO);
    }

    @Override
    @BasicLog
    @PostMapping("/{identificator}/status")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO<String> updateTransactionStatus(
        final String notification,
        final String identificator,
        final String key,
        final String type
    )
        throws BaseException, EntityNotFoundException, PaymentStepException {
        String result = transactionService.updateTransactionStatus(
            TransactionStatusUpdateRequestDTO
                .builder()
                .notification(notification)
                .identificator(identificator)
                .key(key)
                .type(type)
                .build()
        );

        return new ResponseDTO<>(
            ResponseCode.SUCCESS.getCode(),
            result,
            result,
            new ResponseDTO.ErrorDTO()
        );
    }

    @Override
    @BasicLog
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionEntityDTO> getTransactionsByEntityInfo(
        Long entitySourceId,
        String entityReference,
        String uuid
    ) throws BaseException {
        if(entitySourceId==null && entityReference == null && uuid == null){
            throw new BaseException("at least one parameter is required");
        }

        return transactionService.getTransactionsByEntityInfo(entitySourceId, entityReference, uuid);
    }
}
