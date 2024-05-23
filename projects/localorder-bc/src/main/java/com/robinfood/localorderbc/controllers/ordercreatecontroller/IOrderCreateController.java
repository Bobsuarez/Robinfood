package com.robinfood.localorderbc.controllers.ordercreatecontroller;

import com.robinfood.localorderbc.dtos.OrderDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.localorderbc.dtos.request.OrderUpdateRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IOrderCreateController {

    ResponseEntity<APIResponseDTO<OrderDTO>>  invoke(@RequestBody() OrderDTO order);
    ResponseEntity<APIResponseDTO<OrderDTO>> updateOrder(@RequestBody() OrderUpdateRequestDTO orderUpdateRequestDTO);

}
