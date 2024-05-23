package com.robinfood.app.usecases.getuseractiveorder;

import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.usecases.getuserorder.IGetUserResponseOrderByEntityUseCase;
import com.robinfood.core.dtos.response.orderhistory.ResponseBrandDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseFinalProductDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderStatusDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Test of GetOrderHistoryByUserIdUseCase
 */
@ExtendWith(MockitoExtension.class)
public class GetUserActiveOrderUseCaseTest {

    @Mock
    private IOrdersRepository ordersRepository;
    @Mock
    private IGetUserResponseOrderByEntityUseCase getUserResponseOrderByEntityUseCase;
    @InjectMocks
    private GetUserActiveOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(
            useCase,
            "notActiveStatusIds",
            List.of(5L)
        );

        ReflectionTestUtils.setField(
            useCase,
            "originId",
            2L
        );

        ReflectionTestUtils.setField(
            useCase,
            "minusHours",
            4
        );
    }

    @Test
    void testGetActiveOrdersByUserIdWithResultShouldBeOk() {

        List<OrderEntity> orders = new OrderEntityMock().getDataDefaultList();

        when(
            ordersRepository
                .findAllByUserIdAndOriginIdAndStatusIdNotInAndCreatedAtBetweenOrderByCreatedAtDesc(
                    eq(1L),
                    eq(2L),
                    eq(List.of(5L)),
                    any(LocalDateTime.class),
                    any(LocalDateTime.class)
                )
        ).thenReturn(
            orders
        );

        when(
            getUserResponseOrderByEntityUseCase.invoke(orders.get(0))
        ).thenReturn(
            getResponseOrder()
        );

        List<ResponseOrderDTO> response = useCase.invoke(1L);
        assertNotNull(response);
        assertThat(response.get(0).getPlatformId(), is(equalTo(1L)));
        assertThat(response.size(), is(equalTo(1)));
        assertThat(response.get(0).getBrands().size(), is(equalTo(1)));
        assertThat(response.get(0).getProducts().size(), is(equalTo(1)));
    }

    @Test
    void testGetActiveOrdersByUserIdWithoutResultShouldBeOk() {

        when(
            ordersRepository
                .findAllByUserIdAndOriginIdAndStatusIdNotInAndCreatedAtBetweenOrderByCreatedAtDesc(
                    eq(1L),
                    eq(2L),
                    eq(List.of(5L)),
                    any(LocalDateTime.class),
                    any(LocalDateTime.class)
                )
        ).thenReturn(
            List.of()
        );

        List<ResponseOrderDTO> response = useCase.invoke(1L);
        assertNotNull(response);
        assertThat(response.size(), is(equalTo(0)));
    }

    private ResponseOrderDTO getResponseOrder() {
        return ResponseOrderDTO.builder()
            .id(1L)
            .uid("12345abcde")
            .paid(true)
            .flowId(1L)
            .platformId(1L)
            .status(
                ResponseOrderStatusDTO.builder()
                    .id(3L)
                    .name("En proceso")
                    .build()
            )
            .brands(
                List.of(
                    ResponseBrandDTO.builder()
                        .id(1L)
                        .name("MUY")
                        .build()
                )
            )
            .products(
                List.of(
                    ResponseFinalProductDTO.builder()
                        .id(1L)
                        .name("Final product")
                        .quantity(1)
                        .build()
                )
            )
            .datetime(LocalDateTime.now())
            .build();
    }

}
