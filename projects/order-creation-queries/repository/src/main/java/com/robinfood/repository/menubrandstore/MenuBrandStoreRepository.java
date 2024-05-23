package com.robinfood.repository.menubrandstore;

import com.robinfood.core.dtos.menu.MenuBrandDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.menu.MenuBrandEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.core.mappers.MenuBrandStoreMappers;
import com.robinfood.network.api.MenuBcAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class MenuBrandStoreRepository implements IMenuBrandStoreRepository {

    private final MenuBcAPI menuBcAPI;

    public MenuBrandStoreRepository(MenuBcAPI menuBcAPI) {
        this.menuBcAPI = menuBcAPI;
    }

    public Result<List<MenuBrandDTO>> getMenuBrandStore(Long storeId, String token) {

        log.info("Execute Get Menu Brand Store Repository storeId {}, token {}", storeId, token);

        final Result<APIResponseEntity<List<MenuBrandEntity>>> result = NetworkExtensionsKt.safeAPICall(
                menuBcAPI.getMenuBrandStore(
                        token,
                        storeId
                )
        );

        log.info("Result Get Menu Brand Store Menu API {}", result);

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<List<MenuBrandEntity>>> data =
                ((Result.Success<APIResponseEntity<List<MenuBrandEntity>>>) result);

        return new Result.Success(
                MenuBrandStoreMappers.menuBrandEntityToMenuBrandStoreDTOList(data.getData().getData())
        );
    }
}
