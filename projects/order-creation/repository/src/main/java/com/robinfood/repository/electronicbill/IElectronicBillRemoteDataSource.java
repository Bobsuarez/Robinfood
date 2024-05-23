package com.robinfood.repository.electronicbill;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

/**
 * Remote configuration data source that connects to external APIs to Order Bill Number Generator
 */
public interface IElectronicBillRemoteDataSource {

    /**
     * Method that sends an order as an electronic invoice.
     *
     * @param token token from SSO
     * @param transactionRequestDTO transaction order
     * @return future resul boolean indicating whether the electronic invoice is sent
     */
    Boolean sendElectronicBill(String token, TransactionRequestDTO transactionRequestDTO);
}
