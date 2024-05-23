package com.robinfood.app.usecases.getorderproducttaxesbyfinalproductids;

import com.robinfood.core.dtos.OrderProductTaxDTO;
import com.robinfood.core.entities.OrderProductTaxEntity;
import com.robinfood.repository.orderproducttaxes.IOrderProductTaxesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderProductTaxesByFinalProductIdsUseCaseTest {

    @Mock
    private IOrderProductTaxesRepository orderProductTaxesRepository;

    @InjectMocks
    private GetOrderProductTaxesByFinalProductIdsUseCase getOrderProductTaxesByFinalProductIdsUseCase;

    private final List<Long> finalProductIds = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L));


    private final List<OrderProductTaxEntity> orderProductTaxEntities = new ArrayList<>(
        Arrays.asList(
            new OrderProductTaxEntity(
                    1L,
                    1L,
                    LocalDateTime.now(),
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    0.0,
                    1L,
                    "IMPOCONSUMO",
                    8900.0,
                    LocalDateTime.now()
            )
        )
    );

    private final List<OrderProductTaxDTO> orderProductTaxDTOS = new ArrayList<>(
        Arrays.asList(
            new OrderProductTaxDTO(
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    0.0,
                    1L,
                    "IMPOCONSUMO",
                    8900.0
            )
        )
    );

    @Test
    void test_GetOrderProductTaxesByFinalProductIds_Returns_Correctly() {

        when(orderProductTaxesRepository.findByOrderFinalProductIdIn(finalProductIds))
            .thenReturn(orderProductTaxEntities);

        final List<OrderProductTaxDTO> result = getOrderProductTaxesByFinalProductIdsUseCase.invoke(
            finalProductIds);

        assertEquals(orderProductTaxDTOS, result);
    }
}
