package com.robinfood.app.usecases.getuserorderdetail;

import com.robinfood.app.mappers.OrderDetailOrderMapper;
import com.robinfood.app.usecases.getorderaddressbyorderid.IGetOrderAddressByOrderIdUseCase;
import com.robinfood.app.usecases.getorderdiscountbyorderids.IGetOrderDiscountsByOrderIdUseCase;
import com.robinfood.app.usecases.getorderpaymentbyorderids.IGetOrderPaymentByOrderIdUseCase;
import com.robinfood.app.usecases.getresponsepaymentmethodbyorder.IGetResponsePaymentMethodByOrderUseCase;
import com.robinfood.app.usecases.getresponseservicebyorder.IGetResponseServiceByOrderUseCase;
import com.robinfood.core.dtos.OrderAddressDTO;
import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDetailDTO;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.core.entities.OrderServicesEntity;
import com.robinfood.repository.orderfinalproductportions.IOrderFinalProductPortionRepository;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.repository.orderservices.IOrderServiceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of IGetUserOrderDetailAdditionalInfoUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
@Transactional(readOnly = true)
public class GetUserOrderDetailAdditionalInfoUseCase implements IGetUserOrderDetailAdditionalInfoUseCase {

    private final IGetOrderAddressByOrderIdUseCase getOrderAddressByOrderIdUseCase;
    private final IGetOrderDiscountsByOrderIdUseCase getOrderDiscountsByOrderIdUseCase;
    private final IGetOrderPaymentByOrderIdUseCase getOrderPaymentByOrderIdUseCase;
    private final IGetResponsePaymentMethodByOrderUseCase getPaymentMethodByIdUseCase;
    private final IGetResponseServiceByOrderUseCase getServiceByIdUseCase;
    private final IOrderFinalProductPortionRepository orderFinalProductPortionRepository;
    private final IOrderFinalProductRepository orderFinalProductRepository;
    private final IOrderServiceRepository orderServiceRepository;

    @Override
    public ResponseOrderDetailDTO invoke(
        ResponseOrderDetailDTO responseOrderDetailDTO
    ) {

        log.info("Getting order detail additional info with id: {}", responseOrderDetailDTO.getId());

        List<OrderFinalProductEntity> finalProducts = orderFinalProductRepository.
            findAllByOrderId(responseOrderDetailDTO.getId());

        List<OrderServicesEntity> orderServices = orderServiceRepository.
            findAllByOrderId(responseOrderDetailDTO.getId());

        List<OrderDiscountDTO> orderDiscounts = getOrderDiscountsByOrderIdUseCase.invoke(
            responseOrderDetailDTO.getId()
        );

        List<OrderPaymentDTO> orderPayments = getOrderPaymentByOrderIdUseCase.invoke(
            responseOrderDetailDTO.getId()
        );

        OrderAddressDTO orderAddressDTO = getOrderAddressByOrderIdUseCase.invoke(responseOrderDetailDTO.getId());

        return responseOrderDetailDTO.toBuilder()
            .payment(
                responseOrderDetailDTO.getPayment().toBuilder()
                    .discounts(
                        orderDiscounts.stream()
                            .map(OrderDetailOrderMapper::mapOrderDiscountToResponseDTO)
                            .collect(Collectors.toList())
                    )
                    .methods(
                        orderPayments.stream()
                            .map(getPaymentMethodByIdUseCase::invoke)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .collect(Collectors.toList())
                    )
                    .build()
            )
            .services(
                orderServices.stream()
                    .map(getServiceByIdUseCase::invoke)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList())
            )
            .address(
                OrderDetailOrderMapper.mapToResponseOrderAddressDTO(orderAddressDTO)
            )
            .brands(
                finalProducts.stream()
                    .map(OrderDetailOrderMapper::getBrandFinalProduct)
                    .distinct()
                    .collect(Collectors.toList())
            )
            .products(
                finalProducts.stream()
                    .map(OrderDetailOrderMapper::mapFinalProductToResponseDTO)
                    .map(product ->
                        product.toBuilder()
                            .groups(
                                OrderDetailOrderMapper.mapFinalProductGroupsToResponseDTO(
                                    orderFinalProductPortionRepository
                                        .findOrderFinalProductPortionEntityByOrderFinalProductId(product.getId())
                                )
                            )
                            .build()
                    )
                    .collect(Collectors.toList())
            )
            .build();
    }
}
