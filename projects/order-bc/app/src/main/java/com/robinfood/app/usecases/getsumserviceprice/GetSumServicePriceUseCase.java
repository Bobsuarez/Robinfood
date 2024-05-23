package com.robinfood.app.usecases.getsumserviceprice;

import com.robinfood.core.dtos.request.order.ServiceDTO;
import java.util.List;
import kotlin.collections.CollectionsKt;
import org.springframework.stereotype.Component;

/**
 * Implementation of IGetSumServicePriceUseCase
 */
@Component
public class GetSumServicePriceUseCase implements IGetSumServicePriceUseCase {

    @Override
    public Double invoke(List<ServiceDTO> serviceDTOS) {
        return CollectionsKt
            .sumByDouble(serviceDTOS, ServiceDTO::getValue);
    }
}
