package com.robinfood.ordereports_bc_muyapp.usecases.getorderdiscountbyfinalproductids;

import com.robinfood.app.library.util.ObjectMapperSingleton;
import com.robinfood.ordereports_bc_muyapp.dto.OrderDiscountDTO;
import com.robinfood.ordereports_bc_muyapp.repository.orderdiscount.IOrderDiscountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of IGetOrderDiscountByFinalProductIdsUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
public class GetOrderDiscountByFinalProductIdsUseCase implements IGetOrderDiscountByFinalProductIdsUseCase {

    private final IOrderDiscountRepository orderDiscountRepository;

    @Override
    public CompletableFuture<List<OrderDiscountDTO>> invoke(List<Long> finaProductIds) {

        return CompletableFuture.supplyAsync(() -> getDataOrdersDiscountList(finaProductIds));
    }

    private List<OrderDiscountDTO> getDataOrdersDiscountList(List<Long> finaProductIds) {
        return orderDiscountRepository.findOrderDiscountEntitiesByOrderFinalProductIdIn(finaProductIds)
                .stream()
                .map(data -> ObjectMapperSingleton.objectToClassConvertValue(data, OrderDiscountDTO.class))
                .toList();
    }
}
