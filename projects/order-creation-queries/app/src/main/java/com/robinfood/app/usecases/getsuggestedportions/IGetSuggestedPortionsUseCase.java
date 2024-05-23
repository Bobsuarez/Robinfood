package com.robinfood.app.usecases.getsuggestedportions;

import com.robinfood.core.dtos.menu.MenuSuggestedPortionResponseDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Use case to get the suggested portions for given products
 */
public interface IGetSuggestedPortionsUseCase {

    /**
     * Retrieve the suggested portions
     *
     * @param portionIds list with the portions identifier
     * @param token the authentication token to be used
     * @return the change portions
     */
    Result<List<MenuSuggestedPortionResponseDTO>> invoke(
            List<Long> portionIds,
            String token
    );
}
