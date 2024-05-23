package com.robinfood.app.usecases.hasaccessinformationbyuser;

import com.robinfood.core.enums.Result;

public interface IHasAccessInformationByUserUseCase {

    /**
     * Get user authenticated
     *
     * @return user authenticated
     */
    Result<Long> invoke(Long userId);

}
