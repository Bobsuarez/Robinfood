package com.robinfood.storeor.repositories.electronicbillingrepository;

import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingRequestDTO;
import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingResponseDTO;
import com.robinfood.storeor.dtos.electronicbilling.ElectronicBillDTO;

import java.util.List;

/**
 * Repository that handles the  the order electronic billing response and
 */

public interface IElectronicBillingRepository {

    /**
     * creates an order electronic billing response
     * @param createElectronicBillingRequestDTO the request info to create order electronic billing response
     * @param token the authorization token
     * @return a future containing the result of the operation
     */
    CreateElectronicBillingResponseDTO save(
            String token,
            CreateElectronicBillingRequestDTO createElectronicBillingRequestDTO

    );
    /**
     * Returns the result of list orders with  electronic billing response
     * @param orderId  list of order's id
     * @param token the authorization token
     * @return a future that contains the result of  electronic billing for each order.
     */
    List<ElectronicBillDTO> findAllByOrderIdIn(
            String token,
            List<Long> orderId
    );
}
