package com.robinfood.app.usecases.getposresolution;

import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.datamocks.entity.PosResolutionEntityMock;
import com.robinfood.core.dtos.posresolution.DataPosResolutionRequestDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.orderpos.IOrderPosRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetPosResolutionUseCaseTest {

    @Mock
    private IOrderPosRepository orderPosRepository;
    @Mock
    private IOrdersRepository ordersRepository;

    @InjectMocks
    private GetPosResolutionUseCase getPosResolutionUseCase;

    @BeforeEach
    public void setUp() {

        ReflectionTestUtils.setField(
                getPosResolutionUseCase,
                "IDS_ORDERS_STATUS_CANCELLED",
                List.of(6L, 9L));

        when(orderPosRepository
                .findByPosIdAndCurrent(
                        dataRequestDTO().getPosId(),
                        DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT))
                .thenReturn(Optional.of(PosResolutionEntityMock.getDataDefault()));

        List<OrderEntity> orderEntityMocksList = new ArrayList<>();
        OrderEntity orderEntityDiscardStatus = new OrderEntityMock().getDataDefault();

        orderEntityDiscardStatus.setOrderInvoiceNumber("3");
        orderEntityDiscardStatus.setStatusId(1L);
        OrderEntity orderEntityOne = new OrderEntityMock().getDataDefault();
        orderEntityOne.setOrderInvoiceNumber("");
        orderEntityOne.setStatusId(6L);
        OrderEntity orderEntityFour = new OrderEntityMock().getDataDefault();
        orderEntityFour.setOrderInvoiceNumber("4");
        orderEntityFour.setStatusId(9L);

        orderEntityMocksList.add(orderEntityFour);
        orderEntityMocksList.add(orderEntityOne);
        orderEntityMocksList.add(orderEntityDiscardStatus);

        when(ordersRepository.findByCreatedAtBetweenAndPaidAndPosId(
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                eq(true),
                eq(1L)))
                .thenReturn(Optional.of(orderEntityMocksList));

    }

    @Test
    void test_GetDataResponse_Ok_When_DataPosResolution() {

        GetPosResolutionsDTO responseInvoke = getPosResolutionUseCase.invoke(dataRequestDTO());
        assertThat(responseInvoke.getPrefix(), is(equalTo(PosResolutionEntityMock.getDataDefault().getPrefix())));
    }

    @Test
    void test_GetData_Should_BadRequest_When_NotFoundPosResolution() {

        when(orderPosRepository
                .findByPosIdAndCurrent(
                        dataRequestDTO().getPosId(),
                        DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT))
                .thenReturn(Optional.ofNullable(null));

        GenericOrderBcException exception = assertThrows(GenericOrderBcException.class, () -> {
            getPosResolutionUseCase.invoke(dataRequestDTO());
        });

        assertEquals("Pos resolution not found", exception.getMessage());

    }

    @Test
    void test_GetData_Should_BadRequest_When_NotFoundStartAndEndNumbersInvoices() {

        when(ordersRepository.findByCreatedAtBetweenAndPaidAndPosId(
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                eq(true),
                eq(1L)))
                .thenReturn(Optional.ofNullable(null));

        GenericOrderBcException exception = assertThrows(GenericOrderBcException.class, () -> {
            getPosResolutionUseCase.invoke(dataRequestDTO());
        });

        assertEquals("orders not found", exception.getMessage());

    }

    private DataPosResolutionRequestDTO dataRequestDTO() {
        var localDateStart = LocalDate.parse("2023-01-15");
        var localDateEnd = LocalDate.parse("2023-01-16");
        return new DataPosResolutionRequestDTO(localDateStart, localDateEnd, 1L, 1L, "America/Bogota" );
    }

}