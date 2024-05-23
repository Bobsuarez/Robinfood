package com.robinfood.app.services.orderdiscarded;

import static com.robinfood.core.constants.GlobalConstants.UUID_DEFAULT_TEMPLATE_NAME;
import static org.mockito.Mockito.when;

import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.usecases.updateordertemplate.IUpdateOrderUuidTemplateUseCase;
import com.robinfood.core.dtos.OrderDiscardedInfoDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.core.exceptions.ResourceNotFoundException;
import com.robinfood.repository.orders.IOrdersRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class OrderDiscardedServiceTest {

    @Mock
    private IUpdateOrderUuidTemplateUseCase updateOrderUuidTemplateUseCase;

    @Mock
    private IOrdersRepository ordersRepository;


    @InjectMocks
    OrderDiscardedService orderDiscardedService;

    @Test
    void test_OrderDiscardedService_ValidateAndUpdate_OrderUuid_Not_Exits() {

        String uuid = UUID.randomUUID().toString();
        when(ordersRepository.findByUuid(uuid)).thenReturn(Optional.empty());

        OrderDiscardedInfoDTO orderDiscardedInfoDTO = orderDiscardedService.validateAndUpdate(uuid);

        Assertions.assertFalse(orderDiscardedInfoDTO.isDiscard());
        Assertions.assertNull(orderDiscardedInfoDTO.getUuid());

    }

    @Test
    void test_OrderDiscardedService_ValidateAndUpdate_OrderUuid_Exits_Paid() {

        String uuid = UUID.randomUUID().toString();
        String message = "Order with uuid" + uuid + " is already taken";
        OrderEntityMock orderEntityMock = new OrderEntityMock();
        OrderEntity orderEntity = orderEntityMock.getDataDefault();
        orderEntity.setPaid(true);


        when(ordersRepository.findByUuid(uuid)).thenReturn(Optional.of(orderEntity));

        GenericOrderBcException result = Assertions.assertThrows(GenericOrderBcException.class, () -> {
            OrderDiscardedInfoDTO orderDiscardedInfoDTO = orderDiscardedService.validateAndUpdate(uuid);
                }

        );

        Assertions.assertEquals(message, result.getMessage());
    }

    @Test
    void test_OrderDiscardedService_ValidateAndUpdate_OrderUuid_Exits_Not_Paid() throws ResourceNotFoundException {

        String uuid = UUID.randomUUID().toString();
        String updatedUuid = uuid.concat(UUID_DEFAULT_TEMPLATE_NAME);
        OrderEntityMock orderEntityMock = new OrderEntityMock();
        OrderEntity orderEntity = orderEntityMock.getDataDefault();
        orderEntity.setPaid(false);

        when(ordersRepository.findByUuid(uuid)).thenReturn(Optional.of(orderEntity));
        when(updateOrderUuidTemplateUseCase.invoke(uuid)).thenReturn(updatedUuid);

        OrderDiscardedInfoDTO orderDiscardedInfoDTO = orderDiscardedService.validateAndUpdate(uuid);

        Assertions.assertTrue(orderDiscardedInfoDTO.isDiscard());
        Assertions.assertEquals(updatedUuid, orderDiscardedInfoDTO.getUuid());
    }
}
