package com.robinfood.app.usecases.gettypedeductions;

import com.robinfood.core.dtos.deductions.TypeDeductionsDTO;
import com.robinfood.core.dtos.response.deductions.ResponseTypeDeductionsDTO;
import com.robinfood.core.entities.OrderTypeDeductionsEntity;
import com.robinfood.repository.typededuction.IOrderTypeDeductionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class GetAllActiveTypeDeductionsUseCaseTest {

    @InjectMocks
    private GetAllActiveTypeOrderDeductionsUseCaseUseCase getAllActiveTypeOrderDeductionsUseCase;

    @Mock
    private IOrderTypeDeductionRepository orderTypeDeductionRepository;

    private final List<OrderTypeDeductionsEntity> listTypeDeductionsEntity = new ArrayList<>();

    private final TypeDeductionsDTO typeDeductionsDTO = new TypeDeductionsDTO();

    private final List<TypeDeductionsDTO> listaTypeDeductionsDTO = new ArrayList<>();

    private final OrderTypeDeductionsEntity orderTypeDeductionsEntity = new OrderTypeDeductionsEntity(
    );

    @Test
    void test_traer_deducciones_activas_test() {

        orderTypeDeductionsEntity.setName("Prueba");
        orderTypeDeductionsEntity.setStatus(1L);
        orderTypeDeductionsEntity.setId(1L);
        listTypeDeductionsEntity.add(orderTypeDeductionsEntity);

        typeDeductionsDTO.setName("Prueba");
        typeDeductionsDTO.setStatus("1");
        listaTypeDeductionsDTO.add(typeDeductionsDTO);

         Map<Long,String> responseTypeDeductionsDTO = new HashMap<>();

         responseTypeDeductionsDTO.put(1L, "Prueba");

        when(orderTypeDeductionRepository.findAllByStatus(1L)).thenReturn(listTypeDeductionsEntity);

        Map<Long,String> resultTypeDeductionsDTO = getAllActiveTypeOrderDeductionsUseCase.invoke();

        assertEquals(responseTypeDeductionsDTO,resultTypeDeductionsDTO);
    }
}
