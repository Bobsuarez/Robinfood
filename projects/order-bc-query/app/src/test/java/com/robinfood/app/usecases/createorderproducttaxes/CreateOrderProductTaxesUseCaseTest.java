package com.robinfood.app.usecases.createorderproducttaxes;

import com.robinfood.app.mappers.input.InputFinalProductTaxesMappers;
import com.robinfood.core.dtos.request.order.FinalProductTaxDTO;
import com.robinfood.core.entities.OrderProductTaxEntity;
import com.robinfood.repository.orderproducttaxes.IOrderProductTaxesRepository;
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
public class CreateOrderProductTaxesUseCaseTest {

    @Mock
    private IOrderProductTaxesRepository orderProductTaxesRepository;


    @InjectMocks
    private CreateOrderProductTaxesUseCase createProductTaxesUseCase;

    private final List<FinalProductTaxDTO> orderRequestFinalProductTaxDTOList = new ArrayList<>(Arrays.asList(

            new FinalProductTaxDTO(
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    10.000,
                    1L,
                    "IMPOCOSUMO",
                    1L,
                    12.000
            ),

            new FinalProductTaxDTO(
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    10.000,
                    1L,
                    "IMPOCOSUMO",
                    1L,
                    12.000
            )
    ));

    List<OrderProductTaxEntity> orderProductTaxEntityList = CollectionsKt.map(
            orderRequestFinalProductTaxDTOList,
            InputFinalProductTaxesMappers::toOrderProductTaxEntity
    );

    @Test
    public void test_Create_Product_Taxes(){
        Mockito.when(orderProductTaxesRepository.saveAll(orderProductTaxEntityList))
                .thenReturn(orderProductTaxEntityList);

        Boolean result = createProductTaxesUseCase
                .invoke(orderRequestFinalProductTaxDTOList)
                .join();

        Mockito.verify(orderProductTaxesRepository)
                .saveAll(orderProductTaxEntityList);

        assertTrue(result);
    }
}
