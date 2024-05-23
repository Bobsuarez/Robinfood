package com.robinfood.app.controllers.transactions;

import com.robinfood.app.commandhandler.IInvokerCommand;
import com.robinfood.core.logging.mappeddiagnosticcontext.CreateTransactionCustomLog;
import com.robinfood.core.util.SaveDataInMemoryUtil;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO;
import com.robinfood.core.enums.logs.OrderLogEnum;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.mappers.transactionv2.TransactionV2Mapper;
import com.robinfood.core.util.ObjectMapperSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.robinfood.core.constants.APIConstants.TRANSACTION_V1;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION;
import static com.robinfood.core.constants.PermissionConstants.CREATE_TRANSACTION;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of 'ITransactionController'
 */
@RestController
@RequestMapping(TRANSACTION_V1)
@Slf4j
public class TransactionController implements ITransactionController {

    private final IInvokerCommand invokerCommand;

    public TransactionController(
            IInvokerCommand invokerCommand
    ) {
        this.invokerCommand = invokerCommand;
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('" + CREATE_TRANSACTION + "')")
    public ResponseEntity<ApiResponseDTO<TransactionCreationResponseDTO>> createTransaction(
            @Valid @RequestBody() final TransactionRequestDTO transactionRequestDTO
    ) throws TransactionCreationException {

        CreateTransactionCustomLog.invoke(transactionRequestDTO);

        TransactionV2Mapper.transactionRequestDTOV1ToTransactionRequestDTOV2(transactionRequestDTO);

        invokerCommand.group(EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, transactionRequestDTO);

        Object objectResponseTransaction = SaveDataInMemoryUtil.getValue(transactionRequestDTO.getUuid().toString());

        TransactionCreationResponseDTO transactionCreationResponseDTO =
                ObjectMapperSingleton.objectToClassConvertValue(
                        objectResponseTransaction,
                        TransactionCreationResponseDTO.class
                );

        log.info(
                OrderLogEnum.ORDER_TRANSACTION_CREATED_HTTP.getMessage() + " {}",
                objectToJson(transactionCreationResponseDTO)
        );

        final ApiResponseDTO<TransactionCreationResponseDTO> apiResponseDTO = new ApiResponseDTO<>(
                transactionCreationResponseDTO
        );

        SaveDataInMemoryUtil.removeValue(transactionRequestDTO.getUuid().toString());

        return new ResponseEntity<>(apiResponseDTO, HttpStatus.CREATED);
    }
}
