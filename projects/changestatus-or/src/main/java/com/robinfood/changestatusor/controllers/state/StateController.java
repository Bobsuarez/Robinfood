package com.robinfood.changestatusor.controllers.state;

import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusor.dtos.response.AbstractApiResponseBuilderDTO;
import com.robinfood.changestatusor.dtos.response.ApiResponseDTO;
import com.robinfood.changestatusor.dtos.response.OkAbstractApiResponseBuilderDTO;
import com.robinfood.changestatusor.usecases.changeorderstatus.IChangeOrderStatusUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.robinfood.changestatusor.constants.APIConstants.STATE_V1;

@RestController
@RequestMapping(STATE_V1)
@Slf4j
public class StateController implements IStateController{

    private IChangeOrderStatusUseCase changeOrderStatusUseCase;

    public StateController(
            IChangeOrderStatusUseCase changeOrderStatusUseCase
    ){
        this.changeOrderStatusUseCase = changeOrderStatusUseCase;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponseDTO<ChangeOrderStatusDTO>> changeStateOrder(
            @Valid @RequestBody() ChangeOrderStatusDTO changeOrderStatusDTO
    ) {

        ChangeOrderStatusDTO responseChangeOrderStatusDTO = this.changeOrderStatusUseCase.invoke(changeOrderStatusDTO);

        AbstractApiResponseBuilderDTO<ChangeOrderStatusDTO> apiResponseDTOBuilder;

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(responseChangeOrderStatusDTO);

        log.info("successfully");
        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }
}
