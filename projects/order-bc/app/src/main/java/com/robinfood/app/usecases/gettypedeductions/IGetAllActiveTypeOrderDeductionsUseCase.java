package com.robinfood.app.usecases.gettypedeductions;

import com.robinfood.core.dtos.response.deductions.ResponseTypeDeductionsDTO;

import java.util.Map;

public interface IGetAllActiveTypeOrderDeductionsUseCase {

    /**
     * Method that searches for all of type Deductions
     * @return {@link ResponseTypeDeductionsDTO}
     */
    Map<Long, String> invoke ();
}
