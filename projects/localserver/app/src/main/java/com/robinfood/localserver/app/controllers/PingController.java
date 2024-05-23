package com.robinfood.localserver.app.controllers;

import com.robinfood.localserver.commons.dtos.http.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.robinfood.localserver.commons.constants.GlobalConstants.PING_V1;

@RestController()
@RequestMapping(PING_V1)
public class PingController {

    @GetMapping()
    public ResponseEntity<ApiResponseDTO<String>> invoke() {
        ApiResponseDTO<String> response = new ApiResponseDTO<>();
        response.setData("The service is running");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
