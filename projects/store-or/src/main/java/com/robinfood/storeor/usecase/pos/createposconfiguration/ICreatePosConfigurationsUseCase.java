package com.robinfood.storeor.usecase.pos.createposconfiguration;

import com.robinfood.storeor.dtos.configurationpos.StoreCreatePosDTO;
import org.jetbrains.annotations.NotNull;


/**
 * Use case that create POS by id store
 */
public interface ICreatePosConfigurationsUseCase {

    /**
     * Create Pos in configurations bc
     *
     * @param storeCreatePosDTO contain the stored pos provider response creation
     * @return object with the basic information of the creation of an pos response
     */
    StoreCreatePosDTO invoke(
            @NotNull StoreCreatePosDTO storeCreatePosDTO
    );
}
