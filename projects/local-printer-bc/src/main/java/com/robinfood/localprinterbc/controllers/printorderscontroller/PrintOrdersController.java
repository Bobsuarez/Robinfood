package com.robinfood.localprinterbc.controllers.printorderscontroller;

import com.robinfood.localprinterbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.localprinterbc.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.localprinterbc.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.localprinterbc.dtos.orders.PrintKitchenTicketDTO;
import com.robinfood.localprinterbc.enums.ApiResponseEnum;
import com.robinfood.localprinterbc.usecases.printordersusecase.IExecutePrintOrdersUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

import static com.robinfood.localprinterbc.configs.constants.APIConstants.PRINT;
import static com.robinfood.localprinterbc.configs.constants.APIConstants.PRINT_ORDERS;

@RestController
@RequestMapping(PRINT)
@Slf4j
public class PrintOrdersController implements IPrintOrdersController {
    private final IExecutePrintOrdersUseCase executePrintOrdersUseCase;

    public PrintOrdersController(IExecutePrintOrdersUseCase executePrintOrdersUseCase) {
        this.executePrintOrdersUseCase = executePrintOrdersUseCase;
    }

    @Override
    @PostMapping(PRINT_ORDERS)
    public ResponseEntity<APIResponseDTO<Map<String, Object>>> invoke(@RequestHeader("Authorization") String token,
                                                                      PrintKitchenTicketDTO printKitchenTicketDTO)
            throws IOException {
        log.info("Started PrintOrdersController method invoke().  orderDetailDTO {}", printKitchenTicketDTO.toString());
        Map<String, Object> response = this.executePrintOrdersUseCase.printOrder(token, printKitchenTicketDTO);
        AbstractApiResponseBuilderDTO<Map<String, Object>> responseData =
                new OkAbstractApiResponseBuilderDTO<>();
        responseData.build(response, ApiResponseEnum.RESPONSE_OK_PRINT_ORDERS);

        return ResponseEntity.ok().body(responseData.getApiResponseDTO());
    }
}
