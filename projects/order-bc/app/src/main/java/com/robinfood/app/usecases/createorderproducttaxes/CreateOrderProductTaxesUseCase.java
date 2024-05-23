package com.robinfood.app.usecases.createorderproducttaxes;

import com.robinfood.app.mappers.input.InputFinalProductTaxesMappers;
import com.robinfood.core.dtos.request.order.FinalProductTaxDTO;
import com.robinfood.core.entities.OrderProductTaxEntity;
import com.robinfood.repository.orderproducttaxes.IOrderProductTaxesRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.utilities.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of ICreateOrderProductTaxesUseCase
 */
@Component
@Slf4j
public class CreateOrderProductTaxesUseCase implements ICreateOrderProductTaxesUseCase {

    private final IOrderProductTaxesRepository orderProductTaxesRepository;

    public CreateOrderProductTaxesUseCase(IOrderProductTaxesRepository orderProductTaxesRepository) {
        this.orderProductTaxesRepository = orderProductTaxesRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(List<FinalProductTaxDTO> finalProductTaxDTOList) {

        log.info("Creation products taxes has started with data: {}", objectToJson(finalProductTaxDTOList));

        List<OrderProductTaxEntity> orderProductTaxEntityList = CollectionsKt.map(
            finalProductTaxDTOList,
            InputFinalProductTaxesMappers::toOrderProductTaxEntity
        );

        orderProductTaxesRepository.saveAll(orderProductTaxEntityList);

        return CompletableFuture.completedFuture(true);
    }
}
