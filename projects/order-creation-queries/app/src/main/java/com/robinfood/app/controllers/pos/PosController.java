package com.robinfood.app.controllers.pos;

import com.google.gson.Gson;
import com.robinfood.app.usecases.getposresolution.IGetPosResolutionUseCase;
import com.robinfood.app.usecases.getposresolutionbystore.IGetPosResolutionByStoreUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.posresolution.DataPosResolutionRequestDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
import com.robinfood.core.enums.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static com.robinfood.core.constants.APIConstants.POS_RESOLUTION_SEQUENCE_BY_POS_ID;
import static com.robinfood.core.constants.APIConstants.POS_RESOLUTION_SEQUENCE_BY_STORE_ID;

@RestController
@RequestMapping(ORDERS_V1)
@Slf4j
public class PosController implements IPosController {

    private final IGetPosResolutionUseCase getPosResolutionUseCase;
    private final IGetPosResolutionByStoreUseCase getPosResolutionByStoreUseCase;

    public PosController(IGetPosResolutionUseCase getPosResolutionUseCase,
                         IGetPosResolutionByStoreUseCase getPosResolutionByStoreUseCase
    ) {
        this.getPosResolutionUseCase = getPosResolutionUseCase;
        this.getPosResolutionByStoreUseCase = getPosResolutionByStoreUseCase;
    }

    @Override
    @GetMapping(POS_RESOLUTION_SEQUENCE_BY_POS_ID)
    public ResponseEntity<ApiResponseDTO<GetPosResolutionsDTO>> invoke(
            Long posId,
            String timeZone,
            LocalDate localDateStart,
            LocalDate localDateEnd
    ) {

        DataPosResolutionRequestDTO dataRequestDTO = DataPosResolutionRequestDTO
                .builder()
                .posId(posId)
                .localDateStart(localDateStart)
                .localDateEnd(localDateEnd)
                .timeZone(timeZone).build();

        log.info("Receiving request get Pos resolutions {}", new Gson().toJson(dataRequestDTO));

        Result<GetPosResolutionsDTO> resolutionResponseDTO = getPosResolutionUseCase.invoke(dataRequestDTO);

        ApiResponseDTO<GetPosResolutionsDTO> apiResponseDTO;
        HttpStatus httpStatus;

        if (resolutionResponseDTO instanceof Result.Error) {

            httpStatus = ((Result.Error) resolutionResponseDTO).getHttpStatus();

            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) resolutionResponseDTO).getException().getLocalizedMessage(),
                    httpStatus
            );
        } else {

            httpStatus = HttpStatus.OK;

            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<GetPosResolutionsDTO>) resolutionResponseDTO).getData(),
                    httpStatus
            );
        }

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }

    @Override
    @GetMapping(POS_RESOLUTION_SEQUENCE_BY_STORE_ID)
    public ResponseEntity<ApiResponseDTO<List<GetPosResolutionsDTO>>> invoke(
            LocalDate localDateStart,
            LocalDate localDateEnd,
            Long storeId,
            String timeZone
    ) {

        DataPosResolutionRequestDTO dataRequestDTO = DataPosResolutionRequestDTO
                .builder()
                .storeId(storeId)
                .localDateStart(localDateStart)
                .localDateEnd(localDateEnd)
                .timeZone(timeZone)
                .build();

        log.info("Receiving request get Pos resolution by store {}", new Gson().toJson(dataRequestDTO));

        Result<List<GetPosResolutionsDTO>> resolutionResponseDTO =
                getPosResolutionByStoreUseCase.invoke(dataRequestDTO);

        ApiResponseDTO<List<GetPosResolutionsDTO>> apiResponseDTO;
        HttpStatus httpStatus;

        if (resolutionResponseDTO instanceof Result.Error) {

            httpStatus = ((Result.Error) resolutionResponseDTO).getHttpStatus();

            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) resolutionResponseDTO).getException().getLocalizedMessage(),
                    httpStatus
            );
        } else {

            httpStatus = HttpStatus.OK;

            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<List<GetPosResolutionsDTO>>) resolutionResponseDTO).getData(),
                    httpStatus
            );
        }

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
