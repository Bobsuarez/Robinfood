package com.robinfood.ordereports_bc_muyapp.usecases.getuserorderdetailbyuid;

import com.robinfood.app.library.exception.ApplicationException;
import com.robinfood.app.library.exception.business.TransactionExecutionException;
import com.robinfood.ordereports_bc_muyapp.datamock.dto.ResponseCouponsDTOMock;
import com.robinfood.ordereports_bc_muyapp.datamock.dto.ResponseOrderDetailDTOMock;
import com.robinfood.ordereports_bc_muyapp.datamock.dto.TransactionFlowDTOMock;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;
import com.robinfood.ordereports_bc_muyapp.usecases.getorderbyuid.IGetOrderByOrderIdUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.getordercouponbytransactionid.IGetOrderCouponByTransactionIdUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.getplatformbyorderid.IGetPlatformByOrderIdUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.gettransactionbyuuid.IGetTransactionByUuidUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.gettransactionflowbyid.IGetTransactionFlowByIdUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.getuserorderdetailadditionalinfo.IGetUserOrderDetailAdditionalInfoUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserOrderDetailByUIdUseCaseTest {

    @Mock
    private IGetOrderByOrderIdUseCase getOrderByUIdUseCase;

    @Mock
    private IGetPlatformByOrderIdUseCase getPlatformByOrderIdUseCase;

    @Mock
    private IGetTransactionFlowByIdUseCase getTransactionFlowByIdUseCase;

    @Mock
    private IGetUserOrderDetailAdditionalInfoUseCase getUserOrderDetailAdditionalInfoUseCase;

    @Mock
    private IGetTransactionByUuidUseCase getTransactionByUuidUseCase;

    @Mock
    private IGetOrderCouponByTransactionIdUseCase getOrderCouponByTransactionIdUseCase;

    @InjectMocks
    private GetUserOrderDetailByUIdUseCase getUserOrderDetailByUIdUseCase;

    @Test
    void test_GetUserOrderDetailByUIdUseCase_When_IsFull_Should_DataOK_Return() {

        when(getTransactionFlowByIdUseCase.invoke(anyLong()))
                .thenReturn(CompletableFuture.completedFuture(TransactionFlowDTOMock.getDataDefault()
                                                                      .getFlowId()));

        when(getOrderByUIdUseCase.invoke(anyInt()))
                .thenReturn(CompletableFuture.completedFuture(ResponseOrderDetailDTOMock.getDataDefault()));

        when(getOrderCouponByTransactionIdUseCase.invoke(anyInt()))
                .thenReturn(CompletableFuture.completedFuture(List.of(ResponseCouponsDTOMock.getDataDefault())));

        when(getPlatformByOrderIdUseCase.invoke(anyInt()))
                .thenReturn(CompletableFuture.completedFuture((short) 1));

        when(getTransactionByUuidUseCase.invoke(anyString()))
                .thenReturn(CompletableFuture.completedFuture(1));

        when(getUserOrderDetailAdditionalInfoUseCase.invoke(any(ResponseOrderDetailDTO.class)))
                .thenReturn(ResponseOrderDetailDTOMock.getDataDefault());

        ResponseOrderDetailDTO response =
                getUserOrderDetailByUIdUseCase.invoke("1927192b-c74e-4d7c-828e-991cd9d73fa7");

        assertNotNull(response);
        assertNotNull(response.getPayment());
        assertNotNull(response.getOrigin());
        assertNotNull(response.getServices());
        assertNotNull(response.getProducts());
        assertNotNull(response.getBrands());
    }

    @Test
    void test_GetUserOrderDetailByUIdUseCase_When_IsNull_Should_TransactionExecutionException_Return() {

        when(getTransactionByUuidUseCase.invoke(anyString()))
                .thenReturn(CompletableFuture.failedFuture(new TransactionExecutionException("Error data Null")));

        Assertions.assertThrows(TransactionExecutionException.class, () -> {

            getUserOrderDetailByUIdUseCase.invoke("1927192b-c74e-4d7c-828e-991cd9d73fa7");
        });
    }

    @Test
    void test_GetUserOrderDetailByUIdUseCase_When_IsDifferentError_Should_TransactionExecutionException_Return() {

        when(getTransactionByUuidUseCase.invoke(anyString()))
                .thenReturn(CompletableFuture.failedFuture(new Exception("Error data Null")));

        Assertions.assertThrows(ApplicationException.class, () -> {

            getUserOrderDetailByUIdUseCase.invoke("1927192b-c74e-4d7c-828e-991cd9d73fa7");
        });
    }
}