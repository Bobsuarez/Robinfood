package com.robinfood.storeor.controllers;

import com.robinfood.storeor.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.storeor.dtos.user.PosConfigurationResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IUserController {

    ResponseEntity<APIResponseDTO<PosConfigurationResponseDTO>> getPosConfiguration(Long userId);
}
