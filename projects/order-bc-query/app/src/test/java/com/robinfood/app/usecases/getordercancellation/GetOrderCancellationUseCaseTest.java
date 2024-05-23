package com.robinfood.app.usecases.getordercancellation;

import com.robinfood.app.datamocks.dto.input.DataIdsToFindOrderCancellationDTOMock;
import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.datamocks.entity.OrderIntegrationEntityMock;
import com.robinfood.app.datamocks.entity.OrderUserDataEntityMock;
import com.robinfood.app.datamocks.entity.PosResolutionEntityMock;
import com.robinfood.app.usecases.ordersquerystatement.OrdersQueryStatementUseCase;
import com.robinfood.core.dtos.report.ordercancellation.DataToSearchIdsCanceledOrdersDTO;
import com.robinfood.core.dtos.report.ordercancellation.GetOrderCancellationResponseDTO;
import com.robinfood.core.dtos.response.EntityDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orderintegration.IOrderIntegrationRepository;
import com.robinfood.repository.orderpos.IOrderPosRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetOrderCancellationUseCaseTest {

    @Mock
    private OrdersQueryStatementUseCase ordersQueryStatementUseCase;

    @Mock
    private IOrderPosRepository orderPosRepository;

    @InjectMocks
    private GetOrderCancellationUseCase GetOrderCancellationUseCase;

    @Test
    void test_invoke_Should_ReturnOrderCancellation_WhenInvokeUseCase() {

        OrderEntity ordersEntities = OrderEntityMock.getDataDefault();
        ordersEntities.setUserDataEntity(List.of(OrderUserDataEntityMock.build()));
        ordersEntities.setOrderIntegrationEntityList(List.of(OrderIntegrationEntityMock.getDefault()));

        when(ordersQueryStatementUseCase.invoke(any()))
                .thenReturn(new PageImpl<>(Arrays.asList(ordersEntities)));

        when(orderPosRepository.findByPosIdAndCurrent(anyLong(), anyInt()))
                .thenReturn(Optional.of(PosResolutionEntityMock.getDataDefault()));

        EntityDTO<GetOrderCancellationResponseDTO> responseDTOEntityDTO = GetOrderCancellationUseCase
                .invoke(DataIdsToFindOrderCancellationDTOMock.getDefault());

        Assertions.assertNotNull(responseDTOEntityDTO);
    }

    @Test
    void test_invoke_Should_ReturnOrderCancellation_When_LocalStarAndLocalEnd_IsNull() {

        OrderEntity ordersEntities = OrderEntityMock.getDataDefault();
        ordersEntities.setUserDataEntity(List.of(OrderUserDataEntityMock.build()));
        ordersEntities.setOrderIntegrationEntityList(List.of(OrderIntegrationEntityMock.getDefault()));

        DataToSearchIdsCanceledOrdersDTO toFindOrderCancellation = DataIdsToFindOrderCancellationDTOMock.getDefault();
        toFindOrderCancellation.setLocalDateStartDTO(null);
        toFindOrderCancellation.setLocalDateEndDTO(null);

        when(ordersQueryStatementUseCase.invoke(any()))
                .thenReturn(new PageImpl<>(Arrays.asList(ordersEntities)));

        when(orderPosRepository.findByPosIdAndCurrent(anyLong(), anyInt()))
                .thenReturn(Optional.of(PosResolutionEntityMock.getDataDefault()));

        EntityDTO<GetOrderCancellationResponseDTO> responseDTOEntityDTO = GetOrderCancellationUseCase
                .invoke(toFindOrderCancellation);

        Assertions.assertNotNull(responseDTOEntityDTO);
    }
}
