package com.robinfood.app.controllers.configuration.stores;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.configuration.StoreWithIdAndNameDTO;
import io.swagger.v3.oas.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IStoresController {

    ResponseEntity<ApiResponseDTO<List<StoreWithIdAndNameDTO>>> invoke(
            @RequestParam(required = false)
            @Length(min = 3, max = 60)
            @Parameter(
                    name = "countryId",
                    description = "Identifier country"
            ) Long countryId
    );
}
