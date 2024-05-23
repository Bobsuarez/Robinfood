package com.robinfood.app.controllers.pos;

import com.robinfood.app.usecases.getposresolution.IGetPosResolutionUseCase;
import com.robinfood.app.usecases.getposresolutionbystore.IGetPosResolutionByStoreUseCase;
import com.robinfood.app.usecases.validatedate.IValidateDateUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.posresolution.DataPosResolutionRequestDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.*;

@RestController
@RequestMapping(ORDERS_V1)
public class PosController implements IPosController {

    private final IGetPosResolutionByStoreUseCase getPosResolutionByStoreUseCase;

    private final IGetPosResolutionUseCase getPosResolutionUseCase;

    private final IValidateDateUseCase validateDateUseCase;

    public PosController(
            IGetPosResolutionUseCase getPosResolutionUseCase,
            IGetPosResolutionByStoreUseCase getPosResolutionByStoreUseCase,
            IValidateDateUseCase validateDateUseCase
    ) {
        this.getPosResolutionUseCase = getPosResolutionUseCase;
        this.getPosResolutionByStoreUseCase = getPosResolutionByStoreUseCase;
        this.validateDateUseCase = validateDateUseCase;
    }

    @Override
    @GetMapping(POS_RESOLUTION_SEQUENCE_BY_POS_ID)
    public ResponseEntity<ApiResponseDTO<GetPosResolutionsDTO>> invoke(
            Long posId,
            String timeZone,
            LocalDate localDateStart,
            LocalDate localDateEnd
    ) {

        validateDateUseCase.invoke(localDateStart, localDateEnd);

        DataPosResolutionRequestDTO dataRequestDTO = DataPosResolutionRequestDTO
                .builder()
                .posId(posId)
                .localDateStart(localDateStart)
                .localDateEnd(localDateEnd)
                .timeZone(timeZone).build();

        return ResponseEntity.ok(new ApiResponseDTO<>(getPosResolutionUseCase.invoke(dataRequestDTO)));
    }

    @Override
    @GetMapping(POS_RESOLUTION_SEQUENCE_BY_STORE_ID)
    public ResponseEntity<ApiResponseDTO<List<GetPosResolutionsDTO>>> invoke(
            LocalDate localDateStart,
            LocalDate localDateEnd,
            Long storeId,
            String timeZone
    ) {

        validateDateUseCase.invoke(localDateStart, localDateEnd);

        DataPosResolutionRequestDTO dataRequestDTO = DataPosResolutionRequestDTO
                .builder()
                .localDateStart(localDateStart)
                .localDateEnd(localDateEnd)
                .storeId(storeId)
                .timeZone(timeZone)
                .build();

        return ResponseEntity.ok(new ApiResponseDTO<>(getPosResolutionByStoreUseCase.invoke(dataRequestDTO)));
    }
}
