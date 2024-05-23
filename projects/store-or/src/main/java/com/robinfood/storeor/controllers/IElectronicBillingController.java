package com.robinfood.storeor.controllers;

import com.robinfood.storeor.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingRequestDTO;
import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingResponseDTO;
import com.robinfood.storeor.dtos.electronicbilling.ElectronicBillDTO;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IElectronicBillingController {

    /**
     * Create electronic billing record for order
     * @param createElectronicBillingRequestDTO contain the stored billing provider response information
     * @return basic information on the creation of the electronic invoice
     */
    ResponseEntity<APIResponseDTO<CreateElectronicBillingResponseDTO>> createElectronicBilling(
            HttpServletRequest httpServletRequest,
            CreateElectronicBillingRequestDTO createElectronicBillingRequestDTO
    );

    ResponseEntity<APIResponseDTO<List<ElectronicBillDTO>>> getElectronicBillByOrdersId(
             List<Long> orderIds
    );
}
