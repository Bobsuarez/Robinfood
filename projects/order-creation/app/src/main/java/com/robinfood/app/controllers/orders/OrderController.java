package com.robinfood.app.controllers.orders;

import com.robinfood.app.usecases.changestatusorders.IChangeOrdersStatusUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractBuilderApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.OkApiResponseDTO;
import com.robinfood.core.dtos.changestatusordersrequestdto.ChangeStatusOrdersRequestDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of 'IOrderController'
 */
@AllArgsConstructor
@RestController
@RequestMapping(ORDERS_V1)
@Slf4j
public class OrderController implements IOrderController {

    private final IChangeOrdersStatusUseCase changeStatusOrdersUseCase;

    @Override
    @PostMapping("/update/status")
    public ResponseEntity<ApiResponseDTO<Boolean>> changeStatus(
            HttpServletRequest httpServletRequest,
            @Valid @RequestBody() final ChangeStatusOrdersRequestDTO changeStatusOrderRequestDTO
    ) {
        log.info("Order change status has started with request: {}", objectToJson(changeStatusOrderRequestDTO));

        final AbstractBuilderApiResponseDTO<Boolean> apiResponseDTOBuilder = new OkApiResponseDTO<>();
        changeStatusOrdersUseCase.invoke(changeStatusOrderRequestDTO);

        apiResponseDTOBuilder.build("Change status success");

        log.info("Order change status has finished");

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }

}
