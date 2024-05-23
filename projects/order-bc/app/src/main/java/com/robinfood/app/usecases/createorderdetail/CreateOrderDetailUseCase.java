package com.robinfood.app.usecases.createorderdetail;

import com.robinfood.app.usecases.getsumaddproductprice.IGetSumAddProductPriceUseCase;
import com.robinfood.app.usecases.getsumproductsprice.IGetSumProductPriceUseCase;
import com.robinfood.app.usecases.getsumserviceprice.IGetSumServicePriceUseCase;
import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import com.robinfood.core.entities.OrderDetailEntity;
import com.robinfood.repository.orderdetail.IOrderDetailRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_VALUE;
import static com.robinfood.core.utilities.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of ICreateOrderDetailUseCase
 */
@Component
@Slf4j
public class CreateOrderDetailUseCase implements ICreateOrderDetailUseCase {

    private final IOrderDetailRepository orderDetailRepository;
    private final IGetSumAddProductPriceUseCase getSumAddProductPriceUseCase;
    private final IGetSumServicePriceUseCase getSumServicePriceUseCase;
    private final IGetSumProductPriceUseCase getSumProductPriceUseCase;

    public CreateOrderDetailUseCase(
            IOrderDetailRepository orderDetailRepository,
            IGetSumAddProductPriceUseCase getSumAddProductPriceUseCase,
            IGetSumServicePriceUseCase getSumServicePriceUseCase,
            IGetSumProductPriceUseCase getSumProductPriceUseCase
    ) {
        this.orderDetailRepository = orderDetailRepository;
        this.getSumAddProductPriceUseCase = getSumAddProductPriceUseCase;
        this.getSumServicePriceUseCase = getSumServicePriceUseCase;
        this.getSumProductPriceUseCase = getSumProductPriceUseCase;
    }

    @Override
    public CompletableFuture<Boolean> invoke(
            List<OrderDTO> orderDTOS,
            List<ResponseCreatedOrderDTO> createdOrderDTOS
    ) {
        log.info("Starting process to save order details with data: {}", objectToJson(orderDTOS));

        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();

        for (Integer i = 0; i < orderDTOS.size(); i++) {

            OrderDTO orderDTO = orderDTOS.get(i);
            ResponseCreatedOrderDTO responseCreatedOrderDTO = createdOrderDTOS.get(i);

            final OrderDetailEntity orderDetailEntity = new OrderDetailEntity();

            final Double sumAdditionalProductsSize = getSumAddProductPriceUseCase.invoke(
                    orderDTO.getFinalProducts());

            final Double sumServicePrice = getSumServicePriceUseCase.invoke(orderDTO.getServices());

            final BigDecimal sumFinalProductPrice = getSumProductPriceUseCase.invoke(
                    orderDTO.getFinalProducts());

            orderDetailEntity.setInvoice(responseCreatedOrderDTO.getOrderInvoiceNumber());
            orderDetailEntity.setAdditionalProductsPrice(sumAdditionalProductsSize);
            orderDetailEntity.setBasePriceNt(
                    orderDTO.getTotal() - responseCreatedOrderDTO.getTaxPrice());
            orderDetailEntity.setBaseTaxPrice(responseCreatedOrderDTO.getTaxPrice());
            orderDetailEntity.setBillingResolutionId(orderDTO.getBillingResolutionId());
            orderDetailEntity.setDiscountPrice(responseCreatedOrderDTO.getDiscountPrice());
            orderDetailEntity.setFinalProductsPrice(sumFinalProductPrice);
            orderDetailEntity.setMenuId(orderDTO.getMenuId());
            orderDetailEntity.setNotes(orderDTO.getNotes());
            orderDetailEntity.setOrderId(responseCreatedOrderDTO.getId());
            orderDetailEntity.setServicesPrice(sumServicePrice);
            orderDetailEntity.setServicePriceNt(sumServicePrice);
            orderDetailEntity.setServiceTaxPrice(DEFAULT_DOUBLE_VALUE);
            orderDetailEntity.setHasConsumption(orderDTO.getHasConsumption());
            orderDetailEntity.setConsumptionValue(orderDTO.getConsumptionValue());

            orderDetailEntities.add(orderDetailEntity);
        }

        final List<OrderDetailEntity> orderDetailCreateResultEntities = CollectionsKt.toList(
                orderDetailRepository.saveAll(orderDetailEntities)
        );

        Boolean wasSuccessful = orderDetailCreateResultEntities.size() == orderDTOS.size();

        return CompletableFuture.completedFuture(wasSuccessful);
    }
}
