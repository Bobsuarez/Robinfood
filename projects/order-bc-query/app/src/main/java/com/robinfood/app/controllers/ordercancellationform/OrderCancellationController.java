package com.robinfood.app.controllers.ordercancellationform;

import com.google.gson.Gson;
import com.robinfood.app.usecases.getordercancellation.IGetOrderCancellationUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.report.ordercancellation.DataToSearchIdsCanceledOrdersDTO;
import com.robinfood.core.dtos.report.ordercancellation.GetOrderCancellationResponseDTO;
import com.robinfood.core.dtos.response.EntityDTO;
import com.robinfood.core.utilities.ValidateDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.ORDERS_CANCELLATION;

@RestController
@RequestMapping
@Slf4j
public class OrderCancellationController implements IOrderCancellationController {

    private final IGetOrderCancellationUseCase getOrderCancellationUseCase;

    public OrderCancellationController(
            IGetOrderCancellationUseCase getOrderCancellationUseCase
    ) {
        this.getOrderCancellationUseCase = getOrderCancellationUseCase;
    }

    @Override
    @GetMapping(ORDERS_CANCELLATION)
    public ResponseEntity<ApiResponseDTO<EntityDTO<GetOrderCancellationResponseDTO>>> getResponseOrderCancellation(
            List<Long> brandsIds,
            Long companyId,
            Integer currentPage,
            Long idCustomFilter,
            LocalDate localDateStart,
            LocalDate localDateEnd,
            List<Long> originIds,
            List<Long> paymentMethodIds,
            Integer perPage,
            String statusCode,
            List<Long> storesIds,
            String timeZone,
            String valueCustomFilter
    ) {

        ValidateDateUtil.isEmptyDateStartAndDateFinal(localDateStart, localDateEnd);

        final DataToSearchIdsCanceledOrdersDTO toSearchRequestIds = DataToSearchIdsCanceledOrdersDTO.builder()
                .brandsIdsDTO(brandsIds)
                .companyIdDTO(companyId)
                .currentPageDTO(currentPage)
                .idCustomFilterDTO(idCustomFilter)
                .localDateEndDTO(localDateEnd)
                .localDateStartDTO(localDateStart)
                .originIdsDTO(originIds)
                .paymentMethodIdsDTO(paymentMethodIds)
                .perPageDTO(perPage)
                .statusCodeDTO(statusCode)
                .storesIdsDTO(storesIds)
                .timeZoneDTO(timeZone)
                .valueCustomFilterDTO(valueCustomFilter)
                .build();

        log.info("Receiving request get order cancellation by : {}", new Gson().toJson(toSearchRequestIds));

        EntityDTO<GetOrderCancellationResponseDTO> cancellationResponseDTOEntityDTO =
                getOrderCancellationUseCase.invoke(toSearchRequestIds);

        return ResponseEntity.ok(new ApiResponseDTO<>(cancellationResponseDTOEntityDTO));
    }
}
