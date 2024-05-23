package com.robinfood.localorderbc.controllers.changestatecontroller;

import com.robinfood.localorderbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.localorderbc.dtos.request.ChangeStateDTO;
import com.robinfood.localorderbc.enums.ApiResponseEnum;
import com.robinfood.localorderbc.usecases.changestatususecase.IChangeStateUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.robinfood.localorderbc.configs.constants.APIConstants.CHANGE_STATE;

@RestController
@RequestMapping(CHANGE_STATE)
@Slf4j
public class ChangeStateController implements IChangeStateController {

    private final IChangeStateUseCase changeStateUseCase;

    public ChangeStateController(IChangeStateUseCase changeStateUseCase) {
        this.changeStateUseCase = changeStateUseCase;
    }

    @Override
    @PostMapping
    public ResponseEntity<APIResponseDTO<ChangeStateDTO>> invoke(@RequestBody() ChangeStateDTO changeStateDTO) {

        log.info("Change State Order Controller Execute data {}", changeStateDTO);

        final ChangeStateDTO changeStateResultDTO = changeStateUseCase.invoke(changeStateDTO);

        log.info("Change State Order result Controller Execute data {}", changeStateResultDTO);

        AbstractApiResponseBuilderDTO<ChangeStateDTO> responseBuilderDTO =
                new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(changeStateResultDTO, ApiResponseEnum.RESPONSE_OK_ORDER_CHANGE_STATE);

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());

    }

}
