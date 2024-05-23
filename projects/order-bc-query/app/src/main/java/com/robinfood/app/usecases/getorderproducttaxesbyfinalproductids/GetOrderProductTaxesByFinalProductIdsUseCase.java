package com.robinfood.app.usecases.getorderproducttaxesbyfinalproductids;

import com.robinfood.app.mappers.OrderProductTaxesMappers;
import com.robinfood.core.dtos.OrderProductTaxDTO;
import com.robinfood.repository.orderproducttaxes.IOrderProductTaxesRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of IGetOrderProductTaxesByFinalProductIdsUseCase
 */
@Component
@Slf4j
public class GetOrderProductTaxesByFinalProductIdsUseCase implements
    IGetOrderProductTaxesByFinalProductIdsUseCase {

    private final IOrderProductTaxesRepository orderProductTaxesRepository;

    public GetOrderProductTaxesByFinalProductIdsUseCase(IOrderProductTaxesRepository orderProductTaxesRepository) {
        this.orderProductTaxesRepository = orderProductTaxesRepository;
    }

    public List<OrderProductTaxDTO> invoke(List<Long> finalProductIds) {

        log.info("Starting process to get order product taxes by final products ids: {}",
            finalProductIds);

        return CollectionsKt.map(
            orderProductTaxesRepository.findByOrderFinalProductIdIn(finalProductIds),
            OrderProductTaxesMappers::toOrderProductTaxDTO
        );
    }
}
