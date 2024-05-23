package com.robinfood.app.usecases.saveinputrequest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.robinfood.core.util.SaveDataInMemoryUtil;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SaveInputRequestUseCase implements ISaveInputRequestUseCase {

    @Override
    public void invoke(@NotNull TransactionRequestDTO transactionRequestDTO) {
        final TransactionRequestDTO inputTransactionRequestDTO = getUnmodifiedRequest(transactionRequestDTO);
        SaveDataInMemoryUtil.setData(inputTransactionRequestDTO.getUuid().toString(), inputTransactionRequestDTO);
    }

    private TransactionRequestDTO getUnmodifiedRequest(TransactionRequestDTO transactionRequestDTO) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final TransactionRequestDTO copyTransactionRequestDTO = objectMapper.convertValue(
                transactionRequestDTO, TransactionRequestDTO.class);

        log.info("Copy input request: {}", copyTransactionRequestDTO);

        return copyTransactionRequestDTO;
    }
}
