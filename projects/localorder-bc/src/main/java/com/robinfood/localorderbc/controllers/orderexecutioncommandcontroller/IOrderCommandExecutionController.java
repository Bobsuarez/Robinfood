package com.robinfood.localorderbc.controllers.orderexecutioncommandcontroller;

import com.robinfood.localorderbc.dtos.OrderCommandExecutionDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.localorderbc.dtos.request.OrderCommandExecutionRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IOrderCommandExecutionController  {

    ResponseEntity<APIResponseDTO<OrderCommandExecutionDTO>> invoke(
            @RequestBody() OrderCommandExecutionRequestDTO orderCommandExecutionRequestDTO);

}
