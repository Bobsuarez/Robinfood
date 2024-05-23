package com.robinfood.storeor.mocks.dto.resolutions;

import com.robinfood.storeor.dtos.response.ResolutionsListResponseDTO;

public class ResolutionsListResponseDTOMock {

    public ResolutionsListResponseDTO defaultData(){
         return ResolutionsListResponseDTO.builder()
                 .current(true)
                 .endDate("2025-01-19")
                 .id(1L)
                 .initialDate("2024-01-19")
                 .invoiceNumberEnd(100000)
                 .invoiceNumberInitial(0)
                 .invoiceNumberResolution("10000000000")
                 .name("Domicilios - RFN Paseo San Isidro")
                 .prefix("R003").build();
    }
}
