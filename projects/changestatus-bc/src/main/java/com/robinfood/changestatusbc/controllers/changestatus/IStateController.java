package com.robinfood.changestatusbc.controllers.changestatus;

import com.robinfood.changestatusbc.dtos.ApiResponseDTO;
import com.robinfood.changestatusbc.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusbc.dtos.WriteChangeStatusDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IStateController {

    ResponseEntity<ApiResponseDTO<WriteChangeStatusDTO>> changeStatusOrder(
            @Valid @RequestBody ChangeOrderStatusDTO changeOrderStatusDTO
    );
}
