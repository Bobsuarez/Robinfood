package com.robinfood.localorderbc.controllers.getorderexecutioncommandcontroller;

import com.robinfood.localorderbc.dtos.OrderCommandExecutionDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.APIResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGetOrderCommandExecutionController {

    ResponseEntity<APIResponseDTO<List<OrderCommandExecutionDTO>>> invoke();
}
