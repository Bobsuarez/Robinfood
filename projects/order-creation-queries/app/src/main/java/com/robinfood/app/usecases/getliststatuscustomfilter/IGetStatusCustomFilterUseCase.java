package com.robinfood.app.usecases.getliststatuscustomfilter;

import com.robinfood.core.dtos.StatusCustomFilterDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Use case that returns the list of status filter
 */
public interface IGetStatusCustomFilterUseCase {

    /**
     * retrieve the estatus filter
     *
     * @return object with the data
     */
    Result<List<StatusCustomFilterDTO>> invoke();
}
