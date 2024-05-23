package com.robinfood.localorderbc.controllers.changestatecontroller;

import com.robinfood.localorderbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.localorderbc.dtos.request.ChangeStateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IChangeStateController {

    ResponseEntity<APIResponseDTO<ChangeStateDTO>>  invoke(@RequestBody() ChangeStateDTO changeStateDTO);

}
