package com.robinfood.localorderbc.controllers.getorderdetailcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.APIResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IGetOrderDetailController {

    ResponseEntity<APIResponseDTO<JsonNode>> invoke(@PathVariable final Long orderId) throws JsonProcessingException;

}
