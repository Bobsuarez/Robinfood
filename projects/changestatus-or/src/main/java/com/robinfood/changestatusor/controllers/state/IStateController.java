package com.robinfood.changestatusor.controllers.state;

import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusor.dtos.response.ApiResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IStateController {

    /**
     * Change state of a order
     * @return Status parent
     */
    ResponseEntity<ApiResponseDTO<ChangeOrderStatusDTO>> changeStateOrder(ChangeOrderStatusDTO changeOrderStatusDTO);
}
