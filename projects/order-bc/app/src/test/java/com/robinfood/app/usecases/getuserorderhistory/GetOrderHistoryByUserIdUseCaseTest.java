package com.robinfood.app.usecases.getuserorderhistory;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

/**
 * Test of GetOrderHistoryByUserIdUseCase
 */
@ExtendWith(MockitoExtension.class)
public class GetOrderHistoryByUserIdUseCaseTest {

    @Mock
    private IOrdersRepository ordersRepository;
    @Mock
    private IGetUserResponseOrderByEntityUseCase getUserResponseOrderByEntityUseCase;
    @InjectMocks
    private GetUserOrderHistoryUseCase useCase;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(
            useCase,
            "notStatusIds",
            List.of(9L)
        );
    }

    @Test
    void testGetOrderHistoryByUserIdWithResultShouldBeOk() {

        Pageable pageable = PageRequest.of(0, 10);
        List<OrderEntity> orders = new OrderEntityMock().getDataDefaultList();

        when(
            ordersRepository.findAllByUserIdAndStatusIdNotInOrderByCreatedAtDesc(1L, List.of(9L), pageable)
        ).thenReturn(
            new PageImpl<>(orders, pageable, 1)
        );

        when(
            getUserResponseOrderByEntityUseCase.invoke(orders.get(0))
        ).thenReturn(
            getResponseOrder()
        );

        Page<ResponseOrderDTO> response = useCase.invoke(1, 10, 1L);
        assertNotNull(response);
        assertNotNull(response.getContent());
        assertThat(response.getContent().get(0).getPlatformId(), is(equalTo(1L)));
        assertThat(response.getContent().size(), is(equalTo(1)));
        assertThat(response.getContent().get(0).getBrands().size(), is(equalTo(1)));
        assertThat(response.getContent().get(0).getProducts().size(), is(equalTo(1)));
    }

    @Test
    void testGetOrderHistoryByUserIdWithoutResultShouldBeOk() {
        Pageable pageable = PageRequest.of(0, 10);

        when(
            ordersRepository.findAllByUserIdAndStatusIdNotInOrderByCreatedAtDesc(1L, List.of(9L), pageable)
        ).thenReturn(
            new PageImpl<>(List.of(), pageable, 0)
        );

        Page<ResponseOrderDTO> response = useCase.invoke(1, 10, 1L);
        assertNotNull(response);
        assertNotNull(response.getContent());
        assertThat(response.getTotalElements(), is(equalTo(0L)));
        assertThat(response.getTotalPages(), is(equalTo(0)));
        assertThat(response.getContent().size(), is(equalTo(0)));
    }

    @Test
    void testGetOrderHistoryByUserIdWithoutStatusAndTransactionFlowShouldBeError() {

        Pageable pageable = PageRequest.of(0, 10);
        List<OrderEntity> orders = new OrderEntityMock().getDataDefaultList();

        when(
            ordersRepository.findAllByUserIdAndStatusIdNotInOrderByCreatedAtDesc(1L, List.of(9L), pageable)
        ).thenReturn(
            new PageImpl<>(orders, pageable, 1)
        );

        when(
            getUserResponseOrderByEntityUseCase.invoke(orders.get(0))
        ).thenReturn(
            getResponseOrderWithoutStatusAndFlowId()
        );

        Page<ResponseOrderDTO> response = useCase.invoke(1, 10, 1L);
        assertNotNull(response);
        assertNotNull(response.getContent());
        assertThat(response.getContent().get(0).getPlatformId(), is(equalTo(1L)));
        assertThat(response.getContent().size(), is(equalTo(1)));
        assertThat(response.getContent().get(0).getBrands().size(), is(equalTo(1)));
        assertThat(response.getContent().get(0).getProducts().size(), is(equalTo(1)));
        assertNull(response.getContent().get(0).getFlowId());
        assertNull(response.getContent().get(0).getStatus());
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

    private ResponseOrderDTO getResponseOrderWithoutStatusAndFlowId() {
        return ResponseOrderDTO.builder()
            .id(1L)
            .uid("12345abcde")
            .paid(true)
            .platformId(1L)
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
