package com.robinfood.ordereports_bc_muyapp.usecases.getorderfinalproducts;

import com.robinfood.ordereports_bc_muyapp.datamock.dto.ResponseOrderDetailDTOMock;
import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderFinalProductEntityMock;
import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderFinalProductPortionEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.OrderDiscountDTO;
import com.robinfood.ordereports_bc_muyapp.dto.OrderProductTaxDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseFinalProductDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseFinalProductGroupDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseTaxesDTO;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderFinalProductEntity;
import com.robinfood.ordereports_bc_muyapp.models.mapper.OrderDetailOrderMapper;
import com.robinfood.ordereports_bc_muyapp.repository.orderfinalproductportions.IOrderFinalProductPortionRepository;
import com.robinfood.ordereports_bc_muyapp.repository.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.ordereports_bc_muyapp.usecases.getorderdiscountbyfinalproductids.IGetOrderDiscountByFinalProductIdsUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.getorderproducttaxesbyfinalproductid.IGetOrderProductTaxesByFinalProductIdUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrderFinalProductsUseCaseTest {

    @Mock
    private IOrderFinalProductRepository orderFinalProductRepository;

    @Mock
    private IOrderFinalProductPortionRepository orderFinalProductPortionRepository;

    @Mock
    private IGetOrderProductTaxesByFinalProductIdUseCase getOrderProductTaxesByFinalProductIdUseCase;

    @Mock
    private IGetOrderDiscountByFinalProductIdsUseCase getOrderDiscountByFinalProductIdsUseCase;

    @Mock
    private OrderDetailOrderMapper orderDetailOrderMapper;

    @InjectMocks
    private GetOrderFinalProductsUseCase getOrderFinalProductsUseCase;

    @Test
    void test_GetOrderFinalProductsUseCase_When_IsFull_Should_DataOK_Return() {

        when(orderDetailOrderMapper.mapFinalProductToResponseDTO(any(OrderFinalProductEntity.class)))
                .thenReturn(ResponseFinalProductDTO.builder()
                                    .id(10L)
                                    .build());

        when(orderDetailOrderMapper.mapFinalProductGroupsToResponseDTO(anyList()))
                .thenReturn(Collections.singletonList(ResponseFinalProductGroupDTO.builder()
                                                              .build()));

        when(orderDetailOrderMapper.mapTaxesDTOToResponseDTO(anyList()))
                .thenReturn(Collections.singletonList(ResponseTaxesDTO.builder()
                                                              .build()));


        when(orderFinalProductRepository.findAllByOrderId(anyInt()))
                .thenReturn(CompletableFuture.completedFuture(
                        Collections.singletonList(OrderFinalProductEntityMock.getDataDefault())));


        when(orderFinalProductPortionRepository.findByOrderFinalProductIdIn(anyList()))
                .thenReturn(CompletableFuture.completedFuture(
                        Collections.singletonList(OrderFinalProductPortionEntityMock.getDataDefault())));


        when(getOrderProductTaxesByFinalProductIdUseCase.invoke(anyList()))
                .thenReturn(CompletableFuture.completedFuture(List.of(OrderProductTaxDTO.builder()
                                                                              .orderFinalProductId(1L)
                                                                              .taxValue(2.0)
                                                                              .build())));

        when(getOrderDiscountByFinalProductIdsUseCase.invoke(anyList()))
                .thenReturn(CompletableFuture.completedFuture(List.of(OrderDiscountDTO.builder()
                                                                              .orderFinalProductId(1L)
                                                                              .discountValue(2.0)
                                                                              .build())));

        ResponseOrderDetailDTO orderDetailDTO =
                getOrderFinalProductsUseCase.invoke(ResponseOrderDetailDTOMock.getDataDefault(), 1);

        Assertions.assertEquals(orderDetailDTO.getProducts()
                                        .size(), 1);
    }

}