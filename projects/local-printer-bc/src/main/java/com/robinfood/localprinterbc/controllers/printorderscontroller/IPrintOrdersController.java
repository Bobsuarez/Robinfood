package com.robinfood.localprinterbc.controllers.printorderscontroller;

import com.robinfood.localprinterbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.localprinterbc.dtos.orders.PrintKitchenTicketDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;
import java.util.Map;

public interface IPrintOrdersController {

    ResponseEntity<APIResponseDTO<Map<String, Object>>> invoke(@RequestHeader("Authorization") String token,
        @RequestBody PrintKitchenTicketDTO printKitchenTicketDTO) throws IOException;
}
