package com.robinfood.ordereports_bc_muyapp.usecases.getuserorderdetailadditionalinfo;

import com.robinfood.ordereports_bc_muyapp.datamock.dto.OrderDiscountDTOMock;
import com.robinfood.ordereports_bc_muyapp.datamock.dto.OrderPaymentDTOMock;
import com.robinfood.ordereports_bc_muyapp.datamock.dto.ResponseOrderDetailDTOMock;
import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderServicesEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.OrderAddressDTO;
import com.robinfood.ordereports_bc_muyapp.dto.OrderDiscountDTO;
import com.robinfood.ordereports_bc_muyapp.dto.OrderPaymentDTO;
import com.robinfood.ordereports_bc_muyapp.dto.UserDataDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderAddressDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponsePaymentDiscountDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponsePaymentMethodDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseServiceDTO;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserOrderDetailAdditionalInfoUseCaseTest {

    @Mock
    private IOrderServiceRepository orderServiceRepository;

    @Mock
    private IGetResponseServiceByOrderUseCase getServiceByIdUseCase;

    @Mock
    private IGetResponsePaymentMethodByOrderUseCase getPaymentMethodByIdUseCase;

    @Mock
    private IGetOrderDiscountsByOrderIdUseCase getOrderDiscountsByOrderIdUseCase;

    @Mock
    private IGetOrderPaymentByOrderIdUseCase getOrderPaymentByOrderIdUseCase;

    @Mock
    private IGetOrderAddressByOrderIdUseCase getOrderAddressByOrderIdUseCase;

    @Mock
    private IGetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase;

    @Mock
    private OrderDetailOrderMapper orderDetailOrderMapper;

    @Mock
    private IGetOrderFinalProductsUseCase getOrderFinalProductsUseCase;

    @InjectMocks
    private GetUserOrderDetailAdditionalInfoUseCase getUserOrderDetailAdditionalInfoUseCase;

    @Test
    void test_UserOrderDetailAdditionalInfoUseCase_When_IsFull_Should_DataOK_Return() {

        when(orderDetailOrderMapper.mapOrderDiscountToResponseDTO(any(OrderDiscountDTO.class)))
                .thenReturn(ResponsePaymentDiscountDTO.builder()
                                    .build());

        when(orderDetailOrderMapper.mapToResponseOrderAddressDTO(any(OrderAddressDTO.class)))
                .thenReturn(ResponseOrderAddressDTO.builder()
                                    .build());

        when(orderServiceRepository.findAllByOrderId(anyInt()))
                .thenReturn(CompletableFuture.completedFuture(
                        Collections.singletonList(OrderServicesEntityMock.getDataDefault())));

        when(getServiceByIdUseCase.invoke(any(OrderServicesEntity.class)))
                .thenReturn(Optional.of(ResponseServiceDTO.builder()
                                                .build()));

        when(getOrderDiscountsByOrderIdUseCase.invoke(anyInt()))
                .thenReturn(CompletableFuture.completedFuture(
                        Collections.singletonList(OrderDiscountDTOMock.getDataDefault())));

        when(getOrderPaymentByOrderIdUseCase.invoke(anyInt()))
                .thenReturn(CompletableFuture.completedFuture(
                        Collections.singletonList(OrderPaymentDTOMock.getDataDefault())));

        when(getPaymentMethodByIdUseCase.invoke(any(OrderPaymentDTO.class)))
                .thenReturn(Optional.of(ResponsePaymentMethodDTO.builder()
                                                .build()));

        when(getOrderAddressByOrderIdUseCase.invoke(anyInt()))
                .thenReturn(CompletableFuture.completedFuture(OrderAddressDTO.builder()
                                                                      .build()));

        when(getUserDataByOrderIdsUseCase.invoke(anyInt()))
                .thenReturn(CompletableFuture.completedFuture(UserDataDTO.builder()
                                                                      .build()));
        when(getOrderFinalProductsUseCase.invoke(any(ResponseOrderDetailDTO.class), anyInt()))
                .thenReturn(ResponseOrderDetailDTOMock.getDataDefault());

        ResponseOrderDetailDTO response =
                getUserOrderDetailAdditionalInfoUseCase.invoke(ResponseOrderDetailDTOMock.getDataDefault());

        assertNotNull(response);
        assertNotNull(response.getOrigin());
        assertNotNull(response.getServices());
        assertNotNull(response.getBrands());
        assertNotNull(response.getAddress());
    }
}