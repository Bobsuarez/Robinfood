package com.robinfood.app.usecases.getorderdetailbyidsanduids;

import com.robinfood.app.usecases.getdetailorderbyuids.IGetOrderDetailByUidsUseCase;
import com.robinfood.app.usecases.getorderdetailbyuuids.IGetOrderDetailByUuidsUseCase;
import com.robinfood.app.usecases.getorderdetailorder.IGetOrderDetailOrderUseCase;
import com.robinfood.core.dtos.GetOrderDetailDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class GetOrderDetailByIdsAndUidsTest {

    @Mock
    private IGetOrderDetailByUidsUseCase getOrderDetailByUidsUseCase;

    @Mock
    private IGetOrderDetailOrderUseCase getOrderDetailOrderUseCase;

    @Mock
    private IGetOrderDetailByUuidsUseCase getOrderDetailByUuidsUseCase;

    @InjectMocks
    private GetOrderDetailByIdsAndUids getOrderDetailByIdsAndUids;

    @Test
    void test_Get_Order_Detail_By_Ids_And_Uids() {

        Mockito.when(getOrderDetailByUidsUseCase.invoke(List.of("uid")))
                .thenReturn(List.of());

        Mockito.when(getOrderDetailOrderUseCase.invoke(List.of(1L)))
                .thenReturn(List.of());

        Mockito.when(getOrderDetailByUuidsUseCase.invoke(List.of("uuid")))
                .thenReturn(List.of());

        final List<GetOrderDetailDTO> orderDetailDTOS = getOrderDetailByIdsAndUids.invoke(
                List.of(1L),
                List.of("uid"),
                List.of("uuid")
        );

        verify(getOrderDetailByUidsUseCase).invoke(anyList());
        verify(getOrderDetailOrderUseCase).invoke(anyList());
        verify(getOrderDetailByUuidsUseCase).invoke(anyList());
    }

    @Test
    void test_Get_Order_Detail_By_Ids_And_Uids_Not_IdsAndUids() {

        final List<GetOrderDetailDTO> orderDetailDTOS = getOrderDetailByIdsAndUids.invoke(
                List.of(),
                List.of(),
                List.of()
        );

        verifyNoInteractions(
                getOrderDetailByUidsUseCase,
                getOrderDetailByUuidsUseCase,
                getOrderDetailByUuidsUseCase
        );
    }

}