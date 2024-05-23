package com.robinfood.app.controllers.deductions;

import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IDeductionsController {
    /**
     * Get state parent
     * @return Status parent
     */
    ResponseEntity<ApiResponseDTO<Map<Long,String>>> getAllActiveTypeDeducctions(
    );
}
