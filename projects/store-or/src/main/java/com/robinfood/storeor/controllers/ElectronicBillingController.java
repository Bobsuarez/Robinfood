package com.robinfood.storeor.controllers;

import com.robinfood.storeor.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingRequestDTO;
import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingResponseDTO;
import com.robinfood.storeor.dtos.electronicbilling.ElectronicBillDTO;
import com.robinfood.storeor.enums.ApiResponseEnum;
import com.robinfood.storeor.usecase.createelectronicbilling.ICreateElectronicBillingUseCase;
import com.robinfood.storeor.usecase.getelectronicbillbyordersid.IGetElectronicBillByOrdersIdUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static com.robinfood.storeor.configs.constants.APIConstants.BILLING_V1;
import static com.robinfood.storeor.configs.constants.APIConstants.ELECTRONIC_BILLING;

@Slf4j
@RestController
@RequestMapping(BILLING_V1)
public class ElectronicBillingController implements  IElectronicBillingController{

    private final ICreateElectronicBillingUseCase createElectronicBillingUseCase;
    private final IGetElectronicBillByOrdersIdUseCase getElectronicBillByOrdersId;

    public ElectronicBillingController(
            ICreateElectronicBillingUseCase createElectronicBillingUseCase,
            IGetElectronicBillByOrdersIdUseCase getElectronicBillByOrdersId
    ) {
        this.createElectronicBillingUseCase = createElectronicBillingUseCase;
        this.getElectronicBillByOrdersId = getElectronicBillByOrdersId;
    }

    @PostMapping(ELECTRONIC_BILLING)
    public ResponseEntity<APIResponseDTO<CreateElectronicBillingResponseDTO>> createElectronicBilling(
            HttpServletRequest httpServletRequest,
            @Valid @RequestBody() final CreateElectronicBillingRequestDTO createElectronicBillingRequestDTO
    ) {

        log.info("Create order electronic billing has started with request: {}",
                createElectronicBillingRequestDTO);

        AbstractApiResponseBuilderDTO<CreateElectronicBillingResponseDTO> apiResponseDTOBuilder;

        CreateElectronicBillingResponseDTO createElectronicBillingResponseDTO =
                createElectronicBillingUseCase.invoke(createElectronicBillingRequestDTO);

        log.info("The order electronic billing response created successfully {}",
                createElectronicBillingResponseDTO);

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(
                createElectronicBillingResponseDTO,
                ApiResponseEnum.RESPONSE_OK_ELECTRONIC_BILLING
        );

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.CREATED);
    }

    @GetMapping(ELECTRONIC_BILLING)
    public ResponseEntity<APIResponseDTO<List<ElectronicBillDTO>>> getElectronicBillByOrdersId(
            @Valid @RequestParam(
                    value = "orderIds"
            ) List<Long> orderIds
    ) {
        log.info("Receiving request to get Electronicbill response with order ids: {}", orderIds);

        AbstractApiResponseBuilderDTO<List<ElectronicBillDTO>> apiResponseDTOBuilder;

        List<ElectronicBillDTO> orders = getElectronicBillByOrdersId.invoke(orderIds);

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(
                orders,
                ApiResponseEnum.RESPONSE_OK_ELECTRONIC_BILL_LIST
        );

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }
}
