package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.TransactionDTO;
import lombok.Data;

@Data
public class TransactionDTODataMock {
    public TransactionDTO getDataDefault() {
        return new TransactionDTO(null, 1, null, 1L, 10000.0);
    }
}
