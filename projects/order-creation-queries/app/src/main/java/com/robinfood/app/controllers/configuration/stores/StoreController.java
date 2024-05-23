package com.robinfood.app.controllers.configuration.stores;

import com.robinfood.app.usecases.getstores.IGetStoresUseCase;
import com.robinfood.app.usecases.getstoresbycountryid.IGetStoresByCountryIdUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.configuration.StoreWithIdAndNameDTO;
import com.robinfood.core.enums.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static com.robinfood.core.constants.APIConstants.STORES_V1;
import static com.robinfood.core.constants.APIConstants.STORES;

@RequestMapping(STORES_V1)
@RestController
@Slf4j
public class StoreController implements IStoresController {

    private final IGetStoresUseCase getStoresUseCase;
    private final IGetStoresByCountryIdUseCase getStoresByCountryIdUseCase;

    public StoreController(
            IGetStoresUseCase getConfigStoreUseCase,
            IGetStoresByCountryIdUseCase getStoresByCountryIdUseCase
    ) {
        this.getStoresUseCase = getConfigStoreUseCase;
        this.getStoresByCountryIdUseCase = getStoresByCountryIdUseCase;
    }

    @Override
    @GetMapping(STORES)
    public ResponseEntity<ApiResponseDTO<List<StoreWithIdAndNameDTO>>> invoke(Long countryId) {

        log.info("Receiving request get Stores with country id: {}", countryId);

        Result<List<StoreWithIdAndNameDTO>> getStores;

        if (Objects.nonNull(countryId)) {
            getStores = getStoresByCountryIdUseCase.invoke(countryId);
        } else {
            getStores = getStoresUseCase.invoke();
        }

        ApiResponseDTO<List<StoreWithIdAndNameDTO>> apiResponseDTO;

        HttpStatus httpStatus;

        if (getStores instanceof Result.Error) {
            httpStatus = ((Result.Error) getStores).getHttpStatus();
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) getStores).getException().getLocalizedMessage(),
                    httpStatus
            );
        } else {
            httpStatus = HttpStatus.OK;
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<List<StoreWithIdAndNameDTO>>) getStores).getData(),
                    httpStatus
            );
        }

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
