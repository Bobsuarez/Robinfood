package com.robinfood.app.usecases.completereplacementportionsdata;

import com.robinfood.core.dtos.OrderDetailProductGroupPortionDTO;

import java.util.List;

/**
 * Use case that completes the replacement portion data
 */
public interface ICompleteReplacementPortionsDataUseCase {

    /**
     * completes the replacement portion data for given portions that have replacement
     *
     * @param portions the portions with replacement to complete
     * @param token the authentication token
     */
    void invoke(
            List<OrderDetailProductGroupPortionDTO> portions,
            String token
    );
}
