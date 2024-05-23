package com.robinfood.ordereports_bc_muyapp.usecases.getorderproducttaxesbyfinalproductid;

import com.robinfood.app.library.util.ObjectMapperSingleton;
import com.robinfood.ordereports_bc_muyapp.dto.OrderProductTaxDTO;
import com.robinfood.ordereports_bc_muyapp.repository.orderproducttaxes.IOrderProductTaxesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of IGetOrderProductTaxesByFinalProductIdsUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
public class GetOrderProductTaxesByFinalProductIdUseCase implements IGetOrderProductTaxesByFinalProductIdUseCase {

    private final IOrderProductTaxesRepository orderProductTaxesRepository;

    @Async
    @Override
    public CompletableFuture<List<OrderProductTaxDTO>> invoke(List<Long> finalProductIds) {

        return CompletableFuture.supplyAsync(() -> findOrderProductsDTOList(finalProductIds));
    }

    private List<OrderProductTaxDTO> findOrderProductsDTOList(List<Long> finalProductIds) {
        return orderProductTaxesRepository.findByOrderFinalProductIdIn(finalProductIds)
                .map(productTaxEntityList -> productTaxEntityList
                        .stream()
                        .map(orderProductTaxEntity ->
                                     ObjectMapperSingleton
                                             .objectToClassConvertValue(orderProductTaxEntity, OrderProductTaxDTO.class)
                        )
                        .toList())
                .orElse(Collections.emptyList());
    }
}
