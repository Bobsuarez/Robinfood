package com.robinfood.app.usecases.getorderdieductionbyfinalproductids;

import com.robinfood.app.mappers.FinalProductDeductionsMapper;
import com.robinfood.core.dtos.OrderDeductionFinalProductDTO;
import com.robinfood.repository.orderproductodeduction.IOrderProductFinalDeductionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GetOrderDeductionsByFinalProductIdsUseCase implements
        IGetOrderDeductionsByFinalProductIdsUseCase {

    private final IOrderProductFinalDeductionRepository orderProductFinalDeductionRepository;

    public GetOrderDeductionsByFinalProductIdsUseCase(
            IOrderProductFinalDeductionRepository orderProductFinalDeductionRepository
    ) {
        this.orderProductFinalDeductionRepository = orderProductFinalDeductionRepository;
    }

    @Override
    public List<OrderDeductionFinalProductDTO> invoke(List<Long> finaProductIds) {

        log.info("Starting process to get order deductions by final product ids: {}", finaProductIds);

        return orderProductFinalDeductionRepository.
                findOrderDeductionFinalProductEntitiesByProductFinalIdIn(finaProductIds)
                .stream()
                .map(FinalProductDeductionsMapper::orderDeductionProductEntityToDTO)
                .collect(Collectors.toList());
    }
}
