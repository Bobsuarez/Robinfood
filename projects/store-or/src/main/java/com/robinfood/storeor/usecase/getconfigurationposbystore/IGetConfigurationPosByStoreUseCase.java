package com.robinfood.storeor.usecase.getconfigurationposbystore;

import com.robinfood.storeor.dtos.configurationposbystore.StorePosDTO;

import java.util.List;

public interface IGetConfigurationPosByStoreUseCase {

    List<StorePosDTO> invoke(Long storeId);
}
