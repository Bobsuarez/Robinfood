package com.robinfood.app.controllers.orders;

import com.robinfood.app.mappers.PickupTimeMappers;
import com.robinfood.app.usecases.savepickuptime.ISavePickupTimeUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.pickuptime.PickupTimeDTO;
import com.robinfood.core.dtos.pickuptime.PickupTimeResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static com.robinfood.core.constants.APIConstants.ORDER_PICKUP_TIME;

@RestController
@RequestMapping(ORDERS_V1)
@Slf4j
public class PickupTimeController {

    private final ISavePickupTimeUseCase savePickupTimeUseCase;
    private final PickupTimeMappers pickupTimeMappers;

    public PickupTimeController(ISavePickupTimeUseCase savePickupTimeUseCase, PickupTimeMappers pickupTimeMappers) {
        this.savePickupTimeUseCase = savePickupTimeUseCase;
        this.pickupTimeMappers = pickupTimeMappers;
    }

    @PostMapping(ORDER_PICKUP_TIME)
    public ApiResponseDTO<List<PickupTimeResponseDTO>> save(
            @RequestBody PickupTimeDTO pickupTimeDTO
    ) {
        log.info("Receiving request to save pickup-time: [{}]", pickupTimeDTO);

        var pickupTimesSaved = savePickupTimeUseCase.invoke(
                pickupTimeMappers.dtoToDomain(pickupTimeDTO)
        );

        return new ApiResponseDTO<>(
                pickupTimeMappers.domainListToDtoList(pickupTimesSaved)
        );
    }

}
