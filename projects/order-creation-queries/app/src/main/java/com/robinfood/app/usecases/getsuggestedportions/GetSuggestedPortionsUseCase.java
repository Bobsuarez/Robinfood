package com.robinfood.app.usecases.getsuggestedportions;

import com.robinfood.core.dtos.menu.MenuSuggestedPortionResponseDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.menu.IMenuRepository;
import java.util.List;

/**
 * Implementation of IGetSuggestedPortionsUseCase
 */
public class GetSuggestedPortionsUseCase implements IGetSuggestedPortionsUseCase {

    private final IMenuRepository menuRepository;

    public GetSuggestedPortionsUseCase(IMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Result<List<MenuSuggestedPortionResponseDTO>> invoke(List<Long> portionIds, String token) {
        return menuRepository.getSuggestedPortions(portionIds, token);
    }
}
