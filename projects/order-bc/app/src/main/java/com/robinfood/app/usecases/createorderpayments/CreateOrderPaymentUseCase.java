package com.robinfood.app.usecases.createorderpayments;

import com.robinfood.app.usecases.createorderpaymentdetail.ICreateOrderPaymentDetailUseCase;
import com.robinfood.core.dtos.request.transaction.RequestPaymentDetailDTO;
import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;
import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import com.robinfood.core.entities.OrderPaymentEntity;
import com.robinfood.repository.orderpayment.IOrderPaymentRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_VALUE_EPSILON;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_VALUE_ONE;
import static com.robinfood.core.utilities.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of ICreateOrderPaymentUseCase
 */
@Component
@Slf4j
public class CreateOrderPaymentUseCase implements ICreateOrderPaymentUseCase {

    private final ICreateOrderPaymentDetailUseCase createOrderPaymentDetailUseCase;
    private final IOrderPaymentRepository orderPaymentRepository;

    public CreateOrderPaymentUseCase(
            ICreateOrderPaymentDetailUseCase createOrderPaymentDetailUseCase,
            IOrderPaymentRepository orderPaymentRepository
    ) {
        this.createOrderPaymentDetailUseCase = createOrderPaymentDetailUseCase;
        this.orderPaymentRepository = orderPaymentRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(
            List<RequestPaymentMethodDTO> paymentMethodDTOList,
            List<ResponseCreatedOrderDTO> responseCreatedOrderDTOList
    ) {
        log.info("Starting process to save payment with data: {}", objectToJson(paymentMethodDTOList));

        Double total = CollectionsKt.sumByDouble(
                responseCreatedOrderDTOList,
                ResponseCreatedOrderDTO::getTotal
        );

        double totalEntity = DEFAULT_DOUBLE_VALUE;

        for (ResponseCreatedOrderDTO order : responseCreatedOrderDTOList) {

            total = total + order.getCo2Total().doubleValue();
            order.setTotal(order.getTotal() + order.getCo2Total().doubleValue());

            for (RequestPaymentMethodDTO paymentMethod : paymentMethodDTOList) {

                OrderPaymentEntity createdOrderPaymentEntity = saveOrderPayment(
                        paymentMethod, order, total
                );

                saveOrderPaymentDetail(paymentMethod.getDetail(), order, createdOrderPaymentEntity);

                totalEntity = totalEntity + createdOrderPaymentEntity.getValue();
            }
        }

        return CompletableFuture.completedFuture(
                (total - totalEntity) < DEFAULT_DOUBLE_VALUE_EPSILON
        );
    }

    private OrderPaymentEntity saveOrderPayment(
            RequestPaymentMethodDTO paymentMethod,
            ResponseCreatedOrderDTO order,
            Double total
    ) {
        Double totalValidate;

        if (total == DEFAULT_DOUBLE_VALUE) {
            totalValidate = DEFAULT_DOUBLE_VALUE_ONE;
        } else {
            totalValidate = total;
        }

        final Double percent = paymentMethod.getValue() / totalValidate;

        final Double paymentDiscount = order.getDiscountPrice() * percent;
        final Double paymentSubtotal = order.getSubtotal() * percent;
        final Double paymentTotal = order.getTotal() * percent;
        final Double paymentTax = order.getTaxPrice() * percent;

        return orderPaymentRepository.save(
                new OrderPaymentEntity(
                        null,
                        paymentDiscount,
                        null,
                        order.getId(),
                        paymentMethod.getOriginId(),
                        paymentMethod.getId(),
                        paymentSubtotal,
                        paymentTax,
                        null,
                        paymentTotal
                )
        );
    }

    private void saveOrderPaymentDetail(
            RequestPaymentDetailDTO orderPaymentDetailDTO,
            ResponseCreatedOrderDTO order,
            OrderPaymentEntity createdOrderPaymentEntity
    ) {
        if (Objects.nonNull(orderPaymentDetailDTO)) {
            orderPaymentDetailDTO.setOrderId(order.getId());
            orderPaymentDetailDTO.setOrderPaymentId(createdOrderPaymentEntity.getId());
            createOrderPaymentDetailUseCase.invoke(orderPaymentDetailDTO);
        }
    }
}
