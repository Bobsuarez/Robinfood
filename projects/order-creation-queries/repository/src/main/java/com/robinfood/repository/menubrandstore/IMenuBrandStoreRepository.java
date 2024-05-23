package com.robinfood.repository.menubrandstore;

import com.robinfood.core.dtos.menu.MenuBrandDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Get menu brand by store repository
 */
public interface IMenuBrandStoreRepository {

    /**
     * Get menu brand by store
     *
     * @param storeId Store identifier
     * @param token Token auth service
     * @return List Menu Brand
     */
    Result<List<MenuBrandDTO>> getMenuBrandStore(Long storeId, String token);

}
