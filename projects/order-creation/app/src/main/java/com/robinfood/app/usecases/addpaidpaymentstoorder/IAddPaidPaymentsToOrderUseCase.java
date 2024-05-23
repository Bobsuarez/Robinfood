package com.robinfood.app.usecases.addpaidpaymentstoorder;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

public interface IAddPaidPaymentsToOrderUseCase {

    /**
     * Add paid payments to order
     *
     * @param transactionRequest Transaction with the paid payments
     */
    void invoke(TransactionRequestDTO transactionRequest);

}
