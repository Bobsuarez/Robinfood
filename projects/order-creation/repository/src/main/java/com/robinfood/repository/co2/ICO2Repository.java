package com.robinfood.repository.co2;

import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.entities.CO2CalculateResponseEntity;

/**
 * Repository that handles menu related data
 */
public interface ICO2Repository {

    /**
     * Method that invokes the remote data source to obtain the co2 compensation calculation
     * @param token token from SSO
     * @param request order products info
     * @return co2 compensation calculation
     */
    CO2CalculateResponseEntity calculateCO2Compensation(
            String token,
            OrderDTO orderDTO
    );
}
