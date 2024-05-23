package com.robinfood.app.usecases.createorderpaymentdetail;

import com.robinfood.app.mappers.input.PaymentDetailMappers;
import com.robinfood.core.dtos.request.transaction.RequestPaymentDetailDTO;
import com.robinfood.core.entities.OrderPaymentDetailEntity;
import com.robinfood.repository.orderpaymentdetail.IOrderPaymentDetailRepository;
import kotlin.collections.CollectionsKt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CreateOrderPaymentDetailUseCaseTest {

    @Mock
    private IOrderPaymentDetailRepository orderPaymentDetailRepository;

    @InjectMocks
    private CreateOrderPaymentDetailUseCase createOrderPaymentDetailUseCase;

    private final List<RequestPaymentDetailDTO> orderPaymentDetailDTOList = new ArrayList<>(Arrays.asList(

            new RequestPaymentDetailDTO(
                    "Account Type",
                    "Additional Information",
                    "Approval Code",
                    "Franchise Type",
                    1L,
                    1L,
                    "Terminal Code",
                    1L,
                    "Self Management Code",
                    1L
            )
    ));

    List<OrderPaymentDetailEntity> orderPaymentDetailEntityList = CollectionsKt.map(
            orderPaymentDetailDTOList,
            PaymentDetailMappers::toOrderPaymentDetailEntity
    );

    @Test
     void test_Create_Order_Payment_Details(){
        Mockito.when(orderPaymentDetailRepository.saveAll(orderPaymentDetailEntityList))
                .thenReturn(orderPaymentDetailEntityList);

        Boolean result = createOrderPaymentDetailUseCase
                .invoke(orderPaymentDetailDTOList.get(0))
                .join();

        Mockito.verify(orderPaymentDetailRepository)
                .saveAll(orderPaymentDetailEntityList);

        assertTrue(result);
    }
}
