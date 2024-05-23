package com.robinfood.repository.menu;

import com.robinfood.core.dtos.MenuCurrentDTO;
import com.robinfood.core.dtos.menu.MenuHallProductResponseDTO;
import com.robinfood.core.dtos.menu.MenuSuggestedPortionResponseDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.MenuCurrentEntity;
import com.robinfood.core.entities.menu.MenuHallProductResponseEntity;
import com.robinfood.core.entities.menu.MenuSuggestedPortionResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.core.mappers.MenuCurrentMappers;
import com.robinfood.core.mappers.MenuHallProductMappers;
import com.robinfood.core.mappers.MenuSuggestedPortionsMappers;
import com.robinfood.network.api.MenuBcAPI;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Implementation of IMenuRepository
 */
@Repository
public class MenuRepository implements IMenuRepository {

    private final MenuBcAPI menuBcAPI;

    public MenuRepository(MenuBcAPI menuBcAPI) {
        this.menuBcAPI = menuBcAPI;
    }

    @Override
    public Result<MenuCurrentDTO> getMenuCurrent(
            Long brandId,
            Long countryId,
            Long flowId,
            Long storeId,
            String token
    ) {
        final Result<APIResponseEntity<MenuCurrentEntity>> result = NetworkExtensionsKt.safeAPICall(
                menuBcAPI.getMenuCurrent(
                        brandId,
                        countryId,
                        flowId,
                        storeId,
                        token
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<MenuCurrentEntity>> data =
                ((Result.Success<APIResponseEntity<MenuCurrentEntity>>) result);

        return new Result.Success(
                MenuCurrentMappers.menuCurrentDTO(data.getData().getData())
        );
    }

    @Override
    public Result<MenuHallProductResponseDTO> getProductDetail(
            Long brandId,
            Long countryId,
            Long flowId,
            Long productId,
            Long storeId,
            String token
    ) {
        final Result<APIResponseEntity<MenuHallProductResponseEntity>> result = NetworkExtensionsKt.safeAPICall(
                menuBcAPI.getProductDetail(
                        productId,
                        brandId,
                        countryId,
                        flowId,
                        storeId,
                        token
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<MenuHallProductResponseEntity>> data =
                ((Result.Success<APIResponseEntity<MenuHallProductResponseEntity>>) result);

        return new Result.Success(
                MenuHallProductMappers.toMenuHallProductResponseDTO(data.getData().getData())
        );
    }

    @Override
    public Result<List<MenuSuggestedPortionResponseDTO>> getSuggestedPortions(List<Long> portionsIds, String token) {
        final Result<APIResponseEntity<List<MenuSuggestedPortionResponseEntity>>> result =
                NetworkExtensionsKt.safeAPICall(
                        menuBcAPI.getSuggestedPortions(
                                portionsIds,
                                token
                        )
                );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<List<MenuSuggestedPortionResponseEntity>>> data =
                ((Result.Success<APIResponseEntity<List<MenuSuggestedPortionResponseEntity>>>) result);

        return new Result.Success(
                MenuSuggestedPortionsMappers.toMenuSuggestedPortionResponseDTOs(data.getData().getData())
        );
    }
}
