package com.robinfood.app.usecases.createorder;


import com.robinfood.app.mappers.OrderMappers;
import com.robinfood.app.usecases.createorderfinalproduct.ICreateOrderFinalProductUseCase;
import com.robinfood.app.usecases.getstatusbyid.IGetStatusByIdUseCase;
import com.robinfood.core.dtos.request.order.BrandDTO;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.dtos.request.order.StoreDTO;
import com.robinfood.core.dtos.request.transaction.RequestCompanyDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.dtos.request.transaction.RequestOriginDTO;
import com.robinfood.core.dtos.request.transaction.RequestUserDTO;
import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.exceptions.CannotDivideByZeroException;
import com.robinfood.core.helpers.UuidHelper;
import com.robinfood.core.utilities.CalculateTimeZoneUtil;
import com.robinfood.repository.orders.IOrdersRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.ENABLED_TRIGGER;
import static com.robinfood.core.constants.GlobalConstants.FOUR_ZEROS_STRING;
import static com.robinfood.core.constants.GlobalConstants.ORDER_STATUS_CREATED;

/**
 * Implementation of ICreateOrderUseCase
 */
@Component
@Slf4j
public class CreateOrderUseCase implements ICreateOrderUseCase {

    private final IGetStatusByIdUseCase getStatusByIdUseCase;

    private final IOrdersRepository ordersRepository;

    private final ICreateOrderFinalProductUseCase createOrderFinalProductUseCase;

    @PersistenceContext()
    private final EntityManager entityManager;

    public CreateOrderUseCase(
            IGetStatusByIdUseCase getStatusByIdUseCase,
            IOrdersRepository ordersRepository,
            ICreateOrderFinalProductUseCase createOrderFinalProductUseCase,
            EntityManager entityManager
    ) {
        this.getStatusByIdUseCase = getStatusByIdUseCase;
        this.ordersRepository = ordersRepository;
        this.createOrderFinalProductUseCase = createOrderFinalProductUseCase;
        this.entityManager = entityManager;
    }

    @Override
    public List<ResponseCreatedOrderDTO> invoke(
            RequestOrderTransactionDTO orderTransactionDTO,
            List<OrderDTO> orderDTOS,
            Long transactionId,
            Double totalPaymentMethods
    ) throws CannotDivideByZeroException {

        log.info("Starting process to save orders: [{}] with transaction id: [{}]", orderDTOS,
                transactionId);

        final List<OrderEntity> orderEntities = new ArrayList<>();
        final RequestUserDTO userDataDTO = orderTransactionDTO.getUser();
        final RequestCompanyDTO companyDTO = orderTransactionDTO.getCompany();

        for (OrderDTO orderDTO : orderDTOS) {

            final BrandDTO brandDTO = orderDTO.getBrand();
            final RequestOriginDTO originDTO = orderDTO.getOrigin();
            final StoreDTO orderStoreDTO = orderDTO.getStore();

            final List<FinalProductDTO> finalProductsDTO = orderDTO.getFinalProducts();

            final Long orderId = orderDTO.getId();

            Long statusId = ORDER_STATUS_CREATED;

            final CalculateTimeZoneUtil calculateTimeZoneUtil = new CalculateTimeZoneUtil(
                    orderTransactionDTO.getDevice().getTimezone()
            );

            final OrderEntity orderEntity = new OrderEntity(
                    orderDTO.getBillingResolutionId(),
                    brandDTO.getId(),
                    brandDTO.getName(),
                    companyDTO.getId(),
                    null,
                    companyDTO.getCurrency(),
                    orderDTO.getDeliveryTypeId(),
                    orderDTO.getTotalDiscount(),
                    ENABLED_TRIGGER,
                    orderId,
                    calculateTimeZoneUtil.getLocalDate(),
                    calculateTimeZoneUtil.getTimeLocal(),
                    finalProductsDTO.size(),
                    calculateTimeZoneUtil.getLocalDate(),
                    FOUR_ZEROS_STRING,
                    FOUR_ZEROS_STRING,
                    originDTO.getId(),
                    originDTO.getName(),
                    orderDTO.getPaid(),
                    orderDTO.getPaymentModelId(),
                    "00:00:00",
                    orderStoreDTO.getPosId(),
                    false,
                    statusId,
                    orderStoreDTO.getId(),
                    orderStoreDTO.getName(),
                    orderDTO.getSubtotal(),
                    orderDTO.getTotalTaxes(),
                    transactionId,
                    Objects.requireNonNullElse((orderDTO.getCo2Total()), BigDecimal.ZERO),
                    orderDTO.getTotal(),
                    UuidHelper.getByLong(orderStoreDTO.getPosId()),
                    orderDTO.getUuid(),
                    null,
                    userDataDTO.getId(),
                    orderDTO.getWorkshiftId()
            );

            orderEntities.add(orderEntity);
        }

        final List<OrderEntity> orderCreateResultEntities = CollectionsKt.toList(
                ordersRepository.saveAll(orderEntities)
        );

        List<CompletableFuture<Boolean>> resultFinalProductUseCase = new ArrayList<>();

        for (int i = 0; i < orderCreateResultEntities.size(); i++) {
            final OrderEntity orderEntity = orderCreateResultEntities.get(i);
            final OrderDTO inputOrderDTO = orderDTOS.get(i);

            CompletableFuture<Boolean> booleanCompletableFuture = createOrderFinalProductUseCase.invoke(
                    inputOrderDTO.getFinalProducts(),
                    orderEntity.getId(),
                    companyDTO.getId(),
                    inputOrderDTO.getStore().getId(),
                    inputOrderDTO.getPaid()
            );
            resultFinalProductUseCase.add(booleanCompletableFuture);
        }

        CompletableFuture.allOf(resultFinalProductUseCase.toArray(new CompletableFuture[0])).join();

        entityManager.flush();
        entityManager.clear();

        final Iterable<OrderEntity> orderEntitiesFound = ordersRepository.findAllById(
                CollectionsKt.map(orderCreateResultEntities, OrderEntity::getId)
        );

        return CollectionsKt.map(orderEntitiesFound,
                (OrderEntity orderEntity) -> OrderMappers.toOrderCreateResultDTO(
                        orderEntity,
                        getStatusByIdUseCase.invoke(orderEntity.getStatusId())
                )
        );
    }
}
