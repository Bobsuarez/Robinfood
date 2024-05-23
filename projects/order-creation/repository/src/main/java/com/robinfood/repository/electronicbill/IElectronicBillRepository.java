package com.robinfood.repository.electronicbill;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

/**
 * Repository that manages the data of an order to invoice electronically.
 */
public interface IElectronicBillRepository {

    /**
     * Method that sends an order as an electronic invoice.
     *
     * @param token token from SSO
     * @param transactionRequestDTO transaction order
     * @return boolean indicating whether the electronic invoice is sent
     */
    Boolean sendElectronicBill(String token, TransactionRequestDTO transactionRequestDTO);
}
