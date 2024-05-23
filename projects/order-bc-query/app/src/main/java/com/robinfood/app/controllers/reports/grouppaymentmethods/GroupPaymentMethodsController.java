package com.robinfood.app.controllers.reports.grouppaymentmethods;

import com.robinfood.app.usecases.getsalesbystore.IGetSalesByStoreUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.report.salebysegment.DataIdsToFindTheSegment;
import com.robinfood.core.dtos.report.salebystore.GetSaleByStoreResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.REPORT_SALES_BY_STORE_GROUP_PAYMENT_METHODS;
import static com.robinfood.core.constants.APIConstants.REPORT_V1;

@RestController
@RequestMapping(REPORT_V1)
public class GroupPaymentMethodsController implements IGroupPaymentMethodsController {

    private final IGetSalesByStoreUseCase getSalesByStoreUseCase;

    public GroupPaymentMethodsController(IGetSalesByStoreUseCase getSalesByStoreUseCase) {
        this.getSalesByStoreUseCase = getSalesByStoreUseCase;
    }

    @Override
    @GetMapping(REPORT_SALES_BY_STORE_GROUP_PAYMENT_METHODS)
    public ResponseEntity<ApiResponseDTO<GetSaleByStoreResponseDTO>> groupPaymentMethodsByStore(
            @PathVariable
                    List<Long> storeId,
            LocalDateTime dateTimeCurrent,
            String timezone
    ) {
        final DataIdsToFindTheSegment toFindTheSegment = DataIdsToFindTheSegment.builder()
                .storesList(storeId)
                .dateToSearch(dateTimeCurrent)
                .timeZone(timezone)
                .build();

        return ResponseEntity.ok(new ApiResponseDTO<>(getSalesByStoreUseCase.invoke(toFindTheSegment)));
    }
}
