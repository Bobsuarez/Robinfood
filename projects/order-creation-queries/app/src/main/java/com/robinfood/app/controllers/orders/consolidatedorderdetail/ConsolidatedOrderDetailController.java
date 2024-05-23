package com.robinfood.app.controllers.orders.consolidatedorderdetail;

import com.robinfood.app.security.Permissions;
import com.robinfood.app.usecases.getorderdetailsplushistory.IGetConsolidatedOrderDetailUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.orderdetailplushistory.OrderDetailPlusHistoryDTO;
import com.robinfood.core.enums.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.robinfood.core.constants.APIConstants.ORDER_PAID_DETAILS;
import static com.robinfood.core.constants.APIConstants.ORDER_PAID_V1;

@RestController
@RequestMapping(ORDER_PAID_V1)
@Slf4j
public class ConsolidatedOrderDetailController implements IConsolidatedOrderDetailController{

    private final IGetConsolidatedOrderDetailUseCase getConsolidatedOrderDetailUseCase;

    public ConsolidatedOrderDetailController(
            IGetConsolidatedOrderDetailUseCase getConsolidatedOrderDetailUseCase
    ){
        this.getConsolidatedOrderDetailUseCase = getConsolidatedOrderDetailUseCase;
    }



    @Override
    @GetMapping(ORDER_PAID_DETAILS)
    @PreAuthorize(Permissions.OR_VIEW_HISTORY_CANCELED)
    public ResponseEntity<ApiResponseDTO<OrderDetailPlusHistoryDTO>> invoke(
            String uuid
    ) {

        Result<OrderDetailPlusHistoryDTO> orderDetailPlusHistoryResult = getConsolidatedOrderDetailUseCase.invoke(uuid);

        ApiResponseDTO<OrderDetailPlusHistoryDTO> apiResponseDTO;
        HttpStatus httpStatus;

        if (orderDetailPlusHistoryResult instanceof Result.Error) {

            httpStatus = ((Result.Error) orderDetailPlusHistoryResult).getHttpStatus();

            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) orderDetailPlusHistoryResult).getException().getLocalizedMessage(),
                    httpStatus
            );
        } else {

            httpStatus = HttpStatus.OK;

            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<OrderDetailPlusHistoryDTO>) orderDetailPlusHistoryResult).getData(),
                    httpStatus
            );
        }

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
