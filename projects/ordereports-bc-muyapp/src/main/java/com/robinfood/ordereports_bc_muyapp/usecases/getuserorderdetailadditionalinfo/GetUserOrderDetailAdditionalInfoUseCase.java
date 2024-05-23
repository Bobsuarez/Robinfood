package com.robinfood.ordereports_bc_muyapp.usecases.getuserorderdetailadditionalinfo;

import com.robinfood.app.library.exception.ApplicationException;
import com.robinfood.ordereports_bc_muyapp.dto.OrderAddressDTO;
import com.robinfood.ordereports_bc_muyapp.dto.OrderDiscountDTO;
import com.robinfood.ordereports_bc_muyapp.dto.OrderPaymentDTO;
import com.robinfood.ordereports_bc_muyapp.dto.UserDataDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderServicesEntity;
import com.robinfood.ordereports_bc_muyapp.models.mapper.OrderDetailOrderMapper;
import com.robinfood.ordereports_bc_muyapp.repository.orderservices.IOrderServiceRepository;
import com.robinfood.ordereports_bc_muyapp.usecases.getorderaddressbyorderid.IGetOrderAddressByOrderIdUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.getorderdiscountbyorderids.IGetOrderDiscountsByOrderIdUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.getorderfinalproducts.IGetOrderFinalProductsUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.getorderpaymentbyorderids.IGetOrderPaymentByOrderIdUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.getresponsepaymentmethodbyorder.IGetResponsePaymentMethodByOrderUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.getresponseservicebyorder.IGetResponseServiceByOrderUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.getuserdatabyorderids.IGetUserDataByOrderIdsUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.ordereports_bc_muyapp.enums.OrderDetailLogEnum.GETTING_ORDER_ADDRESS;
import static com.robinfood.ordereports_bc_muyapp.enums.OrderDetailLogEnum.GETTING_ORDER_DETAIL_INFO;
import static com.robinfood.ordereports_bc_muyapp.enums.OrderDetailLogEnum.GETTING_ORDER_DISCOUNTS;
import static com.robinfood.ordereports_bc_muyapp.enums.OrderDetailLogEnum.GETTING_ORDER_PAYMENTS;
import static com.robinfood.ordereports_bc_muyapp.enums.OrderDetailLogEnum.GETTING_ORDER_SERVICES;
import static com.robinfood.ordereports_bc_muyapp.enums.OrderDetailLogEnum.GETTING_ORDER_USER_DATA;

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
    private final IGetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase;
    private final IOrderServiceRepository orderServiceRepository;
    private final OrderDetailOrderMapper orderDetailOrderMapper;
    private final IGetOrderFinalProductsUseCase getOrderFinalProductsUseCase;

    @Override
    public ResponseOrderDetailDTO invoke(ResponseOrderDetailDTO responseOrderDetailDTO) throws ApplicationException {

        Integer orderId = responseOrderDetailDTO.getId();

        log.info(
                GETTING_ORDER_DETAIL_INFO.getMessage(),
                responseOrderDetailDTO.getId(),
                responseOrderDetailDTO.getTransactionUuid()
        );

        responseOrderDetailDTO = getOrderFinalProductsUseCase.invoke(responseOrderDetailDTO, orderId);

        log.info(GETTING_ORDER_SERVICES.getMessage(), responseOrderDetailDTO.getTransactionUuid());

        CompletableFuture<List<OrderServicesEntity>> orderServicesFuture =
                orderServiceRepository.findAllByOrderId(orderId);

        log.info(GETTING_ORDER_DISCOUNTS.getMessage(), responseOrderDetailDTO.getTransactionUuid());

        CompletableFuture<List<OrderDiscountDTO>> orderDiscountsFuture =
                getOrderDiscountsByOrderIdUseCase.invoke(orderId);

        log.info(GETTING_ORDER_PAYMENTS.getMessage(), responseOrderDetailDTO.getTransactionUuid());

        CompletableFuture<List<OrderPaymentDTO>> orderPaymentsFuture =
                getOrderPaymentByOrderIdUseCase.invoke(orderId);

        log.info(GETTING_ORDER_ADDRESS.getMessage(), responseOrderDetailDTO.getTransactionUuid());

        CompletableFuture<OrderAddressDTO> orderAddressFuture =
                getOrderAddressByOrderIdUseCase.invoke(orderId);

        log.info(GETTING_ORDER_USER_DATA.getMessage(), responseOrderDetailDTO.getTransactionUuid());

        CompletableFuture<UserDataDTO> userDataFuture =
                getUserDataByOrderIdsUseCase.invoke(orderId);

        CompletableFuture.allOf(
                        orderServicesFuture, orderDiscountsFuture,
                        orderPaymentsFuture, orderAddressFuture,
                        userDataFuture
                )
                .join();


        List<OrderDiscountDTO> orderDiscounts = orderDiscountsFuture.join();
        List<OrderPaymentDTO> orderPayments = orderPaymentsFuture.join();
        List<OrderServicesEntity> orderServices = orderServicesFuture.join();
        OrderAddressDTO orderAddressDTO = orderAddressFuture.join();
        UserDataDTO userDataDTO = userDataFuture.join();

        return responseOrderDetailDTO.toBuilder()
                .payment(responseOrderDetailDTO.getPayment()
                                 .toBuilder()
                                 .discounts(orderDiscounts.stream()
                                                    .map(orderDetailOrderMapper::mapOrderDiscountToResponseDTO)
                                                    .toList())
                                 .methods(orderPayments.stream()
                                                  .map(getPaymentMethodByIdUseCase::invoke)
                                                  .filter(Optional::isPresent)
                                                  .map(Optional::get)
                                                  .toList())
                                 .build())
                .services(orderServices.stream()
                                  .map(getServiceByIdUseCase::invoke)
                                  .filter(Optional::isPresent)
                                  .map(Optional::get)
                                  .toList())
                .address(orderDetailOrderMapper.mapToResponseOrderAddressDTO(orderAddressDTO))
                .user(orderDetailOrderMapper.mapUserDataDTOToResponseDTO(userDataDTO))
                .build();
    }
}
