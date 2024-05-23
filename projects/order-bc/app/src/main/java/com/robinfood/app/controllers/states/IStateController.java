package com.robinfood.app.controllers.states;

import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.queue.WriteChangeStatusDTO;
import org.springframework.http.ResponseEntity;

public interface IStateController {

    /**
     * Change state of a order
     * @return Status parent
     */
    ResponseEntity<ApiResponseDTO<WriteChangeStatusDTO>>changeStateOrder(ChangeOrderStatusDTO changeOrderStatusDTO);
}
