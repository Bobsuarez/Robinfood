package com.robinfood.configurationsposbc.controllers;

import com.robinfood.configurationsposbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.configurationsposbc.dtos.pos.PosResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IConfigurationPosController {

    ResponseEntity<APIResponseDTO<PosResponseDTO>> getConfigurationPosByStoreAndUser(Long storeId, Long userId);

}
