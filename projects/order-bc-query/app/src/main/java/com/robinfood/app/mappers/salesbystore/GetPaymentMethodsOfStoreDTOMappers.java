package com.robinfood.app.mappers.salesbystore;

import com.robinfood.core.dtos.report.salebystore.CountAndValueByOrdersDTO;
import com.robinfood.core.dtos.report.salebystore.PaymentMethodsOfStoreDTO;

public class GetPaymentMethodsOfStoreDTOMappers {

    public static PaymentMethodsOfStoreDTO CountAndValueByOrdersDTOToPaymentMethodsOfStoreDTO(
            Long paymentMethodsId,
            CountAndValueByOrdersDTO valueByOrdersDTO
    ) {
        
        return PaymentMethodsOfStoreDTO
                .builder()
                .paymentMethodId(paymentMethodsId)
                .orders(valueByOrdersDTO)
                .build();
    }
}
