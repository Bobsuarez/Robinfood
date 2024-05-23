package com.robinfood.core.mappers;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.template.TransactionCreatedEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static com.robinfood.core.util.ObjectMapperSingleton.jsonToClass;

@Slf4j
public final class TransactionCreatedMapper {


    private TransactionCreatedMapper() {
        // this constructor is empty because it is a mapper static class
    }

    @SneakyThrows
    public static TransactionRequestDTO buildTransactionCreatedDTO(TransactionCreatedEntity transactionCreatedEntity) {

        return jsonToClass(transactionCreatedEntity.getRequestTransaction(),
                        TransactionRequestDTO.class);
    }
}
