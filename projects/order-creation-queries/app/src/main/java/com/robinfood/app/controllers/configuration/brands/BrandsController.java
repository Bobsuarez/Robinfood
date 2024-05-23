package com.robinfood.app.controllers.configuration.brands;

import com.robinfood.app.usecases.getbrands.IGetBrandsUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.configuration.BrandDTO;
import com.robinfood.core.enums.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.robinfood.core.constants.APIConstants.BRANDS_V1;
import static com.robinfood.core.constants.APIConstants.BRANDS;

@RequestMapping(BRANDS_V1)
@RestController
@Slf4j
public class BrandsController implements IBrandsController {

    private final IGetBrandsUseCase getBrandsByCompaniesUseCase;

    public BrandsController(IGetBrandsUseCase getBrandsByCompaniesUseCase) {
        this.getBrandsByCompaniesUseCase = getBrandsByCompaniesUseCase;
    }

    @Override
    @GetMapping(BRANDS)
    public ResponseEntity<ApiResponseDTO<List<BrandDTO>>> invoke() {

        log.info("Receiving request get brands");

        final Result<List<BrandDTO>> getBrands = getBrandsByCompaniesUseCase.invoke();

        ApiResponseDTO<List<BrandDTO>> apiResponseDTO;
        HttpStatus httpStatus;

        if (getBrands instanceof Result.Error) {
            httpStatus = ((Result.Error) getBrands).getHttpStatus();
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) getBrands).getException().getLocalizedMessage(),
                    httpStatus
            );
        } else {
            httpStatus = HttpStatus.OK;
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<List<BrandDTO>>) getBrands).getData(),
                    httpStatus
            );
        }

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
