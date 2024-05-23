package com.robinfood.app.usecases.sendpaidordertoqueue;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import com.robinfood.app.mappers.queue.OrderPaidToQueueMappers;
import com.robinfood.app.usecases.getorderaddressbyorderid.IGetOrderAddressByOrderIdUseCase;
import com.robinfood.app.usecases.getorderbyid.IGetOrderByIdUseCase;
import com.robinfood.app.usecases.getorderdiscountbyorderids.IGetOrderDiscountsByOrderIdUseCase;
import com.robinfood.app.usecases.getorderfinalproductbyorderid.IGetOrderFinalProductByOrderIdUseCase;
import com.robinfood.app.usecases.getorderpaymentbyorderids.IGetOrderPaymentByOrderIdUseCase;
import com.robinfood.app.usecases.getpickuptimebytransactionid.IGetPickupTimesByTransactionIdUseCase;
import com.robinfood.app.usecases.gettransactionbyid.IGetTransactionFlowByIdUseCase;
import com.robinfood.app.usecases.getuserdatalistbyuserid.IGetUserDataByOrderIdUseCase;
import com.robinfood.core.dtos.OrderAddressDTO;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.dtos.OrderFinalProductDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.TransactionFlowDTO;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.dtos.queue.OrderCreatedQueueDTO;
import com.robinfood.core.models.domain.PickupTime;
import com.robinfood.repository.queue.IProducerOrderRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class SendPaidOrderToQueueUseCase implements ISendPaidOrderToQueueUseCase {

    private final IGetOrderByIdUseCase getOrderByIdUseCase;
    private final IGetOrderDiscountsByOrderIdUseCase getOrderDiscountsByOrderIdUseCase;
    private final IGetOrderAddressByOrderIdUseCase getOrderAddressByOrderIdUseCase;
    private final IGetOrderFinalProductByOrderIdUseCase getOrderFinalProductByOrderIdUseCase;
    private final IGetUserDataByOrderIdUseCase getUserDataByOrderIdUseCase;
    private final IGetOrderPaymentByOrderIdUseCase getOrderPaymentByOrderIdUseCase;
    private final IGetPickupTimesByTransactionIdUseCase getPickupTimesByTransactionIdUseCase;
    private final OrderPaidToQueueMappers orderPaidToQueueMappers;
    private final IProducerOrderRepository producerOrderRepository;
    private final IGetTransactionFlowByIdUseCase getTransactionFlowByIdUseCase;

    @Override
    public void invoke(Long orderId) {

        log.info(
            "Start of the process that writes the paid order in the queue with orderId {}", orderId
        );

        CompletableFuture<OrderDTO> orderFuture = supplyAsync(()->getOrderByIdUseCase.invoke(orderId));

        CompletableFuture<List<PickupTime>> pickupTimesFuture = orderFuture
            .thenCompose(
                orderDTO -> supplyAsync(
                    () -> getPickupTimesByTransactionIdUseCase.invoke(orderDTO.getTransactionId())
                )
            );

        CompletableFuture<TransactionFlowDTO> transactionFlowFuture = orderFuture
            .thenCompose(
                orderDTO -> supplyAsync(
                    () -> getTransactionFlowByIdUseCase.invoke(orderDTO.getTransactionId())
                )
            );

        CompletableFuture<List<OrderDiscountDTO>> discountFuture = supplyAsync(
            () -> getOrderDiscountsByOrderIdUseCase.invoke(orderId)
        );

        CompletableFuture<OrderAddressDTO> addressFuture = supplyAsync(
            () -> getOrderAddressByOrderIdUseCase.invoke(orderId)
        );

        CompletableFuture<List<OrderFinalProductDTO>> finalProductFuture = supplyAsync(
            () -> getOrderFinalProductByOrderIdUseCase.invoke(orderId)
        );

        CompletableFuture<List<OrderPaymentDTO>> paymentMethodsFuture = supplyAsync(
            () -> getOrderPaymentByOrderIdUseCase.invoke(orderId)
        );

        CompletableFuture<UserDataDTO> userFuture = supplyAsync(
            () -> getUserDataByOrderIdUseCase.invoke(orderId)
        );

        OrderCreatedQueueDTO paidOrderQueue = executeCompletableFuturesAndGetOrderCreatedQueueDTO(
            orderFuture,
            pickupTimesFuture,
            transactionFlowFuture,
            discountFuture,
            addressFuture,
            finalProductFuture,
            paymentMethodsFuture,
            userFuture
        );

        producerOrderRepository.sendOrderCreatedMessage(paidOrderQueue);
    }

    private OrderCreatedQueueDTO executeCompletableFuturesAndGetOrderCreatedQueueDTO(
        CompletableFuture<OrderDTO> orderFuture,
        CompletableFuture<List<PickupTime>> pickupTimesFuture,
        CompletableFuture<TransactionFlowDTO> transactionFlowFuture,
        CompletableFuture<List<OrderDiscountDTO>> discountFuture,
        CompletableFuture<OrderAddressDTO> addressFuture,
        CompletableFuture<List<OrderFinalProductDTO>> finalProductFuture,
        CompletableFuture<List<OrderPaymentDTO>> paymentMethodsFuture,
        CompletableFuture<UserDataDTO> userFuture
    ) {
        CompletableFuture.allOf(
            orderFuture,
            pickupTimesFuture,
            transactionFlowFuture,
            discountFuture,
            addressFuture,
            finalProductFuture,
            paymentMethodsFuture,
            userFuture
        ).join();

        return orderPaidToQueueMappers.toOrderCreatedQueueDTO(
            orderFuture.join(),
            transactionFlowFuture.join(),
            discountFuture.join(),
            addressFuture.join(),
            finalProductFuture.join(),
            paymentMethodsFuture.join(),
            pickupTimesFuture.join(),
            userFuture.join()
        );
    }
}
