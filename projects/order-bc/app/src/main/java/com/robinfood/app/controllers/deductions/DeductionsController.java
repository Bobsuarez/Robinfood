package com.robinfood.app.controllers.deductions;

import com.robinfood.app.usecases.gettypedeductions.IGetAllActiveTypeOrderDeductionsUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.robinfood.core.constants.APIConstants.DEDUCTIONS_V1;

@RestController
@RequestMapping(DEDUCTIONS_V1)
@Slf4j
public class DeductionsController implements IDeductionsController {

    private final IGetAllActiveTypeOrderDeductionsUseCase getAllTypeOrderDeductions;

    public DeductionsController(IGetAllActiveTypeOrderDeductionsUseCase getAllTypeOrderDeductions) {
        this.getAllTypeOrderDeductions = getAllTypeOrderDeductions;
    }

    @Override
    @GetMapping()
    public ResponseEntity<ApiResponseDTO<Map<Long, String>>> getAllActiveTypeDeducctions() {

        AbstractApiResponseBuilderDTO<Map<Long, String>> apiResponseDTOBuilder;

        log.info(
                "Receiving request to get all active deductions types");

        Map<Long, String> responseTypeDeductionsDTO = getAllTypeOrderDeductions.invoke();

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(responseTypeDeductionsDTO);

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }
}
