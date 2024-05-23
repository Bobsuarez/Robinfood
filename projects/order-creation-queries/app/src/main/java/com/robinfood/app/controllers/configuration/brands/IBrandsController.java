package com.robinfood.app.controllers.configuration.brands;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.configuration.BrandDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBrandsController {

    ResponseEntity<ApiResponseDTO<List<BrandDTO>>> invoke();
}
