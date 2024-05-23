package com.robinfood.app.usecases.getorderdetailsplushistory;

import com.robinfood.app.mocks.PaymentMethodsFilterDTOMock;
import com.robinfood.app.mocks.statusorderhistory.StatusOrderHistoryDTOMock;
import com.robinfood.app.usecases.getlistpaymentmethods.IGetListPaymentMethodsUseCase;
import com.robinfood.app.usecases.getorderdetailprint.IGetOrderDetailPrintUseCase;
import com.robinfood.app.usecases.getstatusorderhistory.IGetStatusOrderHistoryUseCase;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.enums.Result;
import com.robinfood.core.mocks.dto.OrderDetailPrintDTOMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetConsolidatedOrderDetailUseCaseTest {

    @Mock
    private IGetStatusOrderHistoryUseCase getStatusOrderHistoryUseCase;

    @Mock
    private IGetListPaymentMethodsUseCase getListPaymentMethodsUseCase;

    @Mock
    private IGetOrderDetailPrintUseCase orderDetailPrintUseCase;

    @InjectMocks
    private GetConsolidatedOrderDetailUseCase getConsolidatedOrderDetailUseCase;

    @Test
    void Test_invoke_Should_Return_When_InvokeTheUseCase() {

        final String uuId = "uuId";

        when(orderDetailPrintUseCase.invoke(
                anyList(),
                anyList(),
                anyList())
        ).thenReturn(new Result.Success<>(OrderDetailPrintDTOMock.getOrderDetailDTOS()));

        when(getListPaymentMethodsUseCase.invoke())
                .thenReturn(new Result.Success<>(List.of(PaymentMethodsFilterDTOMock.getDataDefault())));

        when(getStatusOrderHistoryUseCase.invoke(anyString()))
                .thenReturn(List.of(StatusOrderHistoryDTOMock.getDataDefault()));

        getConsolidatedOrderDetailUseCase.invoke(uuId);

    }

    @Test
    void Test_invoke_Should_Return_List_Empty_When_InvokeTheUseCase() {

        final String uuId = "uuId";

        when(orderDetailPrintUseCase.invoke(
                anyList(),
                anyList(),
                anyList())
        ).thenReturn(new Result.Success<>(List.of()));


        getConsolidatedOrderDetailUseCase.invoke(uuId);

    }
}
