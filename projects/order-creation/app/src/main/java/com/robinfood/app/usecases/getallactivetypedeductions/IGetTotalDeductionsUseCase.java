package com.robinfood.app.usecases.getallactivetypedeductions;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

import java.math.BigDecimal;

public interface IGetTotalDeductionsUseCase {

    /**
     * Consult the type deductions and validate if the deductions of
     * the request are valid, then return the value of the valid deductions
     * @param token the authorization token
     * @param transactionRequestDTO the requestransaction
     */
    BigDecimal invoke (TransactionRequestDTO transactionRequestDTO,String token);
}
