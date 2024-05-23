package com.robinfood.app.mappers.salesbystore;

import com.robinfood.core.dtos.report.salebystore.GetSaleByStoreResponseDTO;
import com.robinfood.core.dtos.report.salebystore.PaymentMethodsOfStoreDTO;

import java.util.List;

public class GetStoreSaleByStoreDTOMappers {

    public static GetSaleByStoreResponseDTO storeDTOListToGetSaleByStoreResponseDTO(Long idStore, List<PaymentMethodsOfStoreDTO> storeDTOList) {

        return GetSaleByStoreResponseDTO.builder()
                .paymentMethods(storeDTOList)
                .storeId(idStore)
                .build();
    }
}
