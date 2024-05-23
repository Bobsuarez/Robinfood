package com.robinfood.app.usecases.getmenucurrent;

import com.robinfood.core.dtos.MenuCurrentDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.menu.IMenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementation of IGetMenuCurrentUseCase
 */
@Slf4j
public class GetMenuCurrentUseCase implements IGetMenuCurrentUseCase {

    private final IMenuRepository menuRepository;

    public GetMenuCurrentUseCase(IMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public MenuCurrentDTO invoke(
            Long brandId,
            Long countryId,
            Long flowId,
            Long storeId,
            String token
    ) {
        log.info("Starting process to get the current Menu with: BrandId: {}, countryId: {}, flowId: {}, storeId: {}",
                brandId, countryId, flowId, storeId);
        Result<MenuCurrentDTO> menuCurrentDTOResult = menuRepository.getMenuCurrent(
                brandId,
                countryId,
                flowId,
                storeId,
                token
        );

        if (menuCurrentDTOResult instanceof Result.Error) {
            throw new ResponseStatusException(
                    ((Result.Error) menuCurrentDTOResult).getHttpStatus(),
                    ((Result.Error) menuCurrentDTOResult).getException().getMessage()
            );
        }

        return ((Result.Success<MenuCurrentDTO>) menuCurrentDTOResult)
                .getData();
    }
}
