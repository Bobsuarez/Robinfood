package com.robinfood.app.controllers.statusorderhistory;

import com.robinfood.app.security.Permissions;
import com.robinfood.app.usecases.getstatusorderhistory.IGetStatusOrderHistoryUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.statusorderhistory.StatusOrderHistoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static com.robinfood.core.constants.APIConstants.STATUS_ORDER_HISTORY;

@RequestMapping(ORDERS_V1)
@RestController
@Slf4j
@Validated
public class StatusOrderHistoryController implements IStatusOrderHistoryController {

    private final IGetStatusOrderHistoryUseCase getStatusOrderHistoryUseCase;

    public StatusOrderHistoryController(IGetStatusOrderHistoryUseCase getStatusOrderHistoryUseCase) {
        this.getStatusOrderHistoryUseCase = getStatusOrderHistoryUseCase;
    }

    @Override
    @GetMapping(STATUS_ORDER_HISTORY)
    @PreAuthorize(Permissions.ORDER_HISTORY_STATUS_VIEW)
    public ResponseEntity<ApiResponseDTO<List<StatusOrderHistoryDTO>>> invoke(String uuid) {

        log.info("Receiving request status history uuid {}", uuid);

        return ResponseEntity.ok(new ApiResponseDTO<>(
                getStatusOrderHistoryUseCase.invoke(uuid)
        ));
    }
}
