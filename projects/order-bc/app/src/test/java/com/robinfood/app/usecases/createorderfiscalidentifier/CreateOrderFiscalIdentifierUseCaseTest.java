package com.robinfood.app.usecases.createorderfiscalidentifier;

import com.robinfood.app.datamocks.dto.input.OrderFiscalIdentifierDTODataMock;
import com.robinfood.app.datamocks.entity.OrderFiscalIdentifierEntityMock;
import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;
import com.robinfood.core.entities.OrderFiscalIdentifierEntity;
import com.robinfood.repository.orderfiscalidentifier.IOrderFiscalIdentifierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateOrderFiscalIdentifierUseCaseTest {

    @Mock
    private IOrderFiscalIdentifierRepository mockOrderFiscalIdentifierRepository;

    @InjectMocks
    private CreateOrderFiscalIdentifierUseCase createOrderFiscalIdentifierUseCase;

    @Test
    void test_CreateOrderFiscalIdentifierUseCase_Happy_Path() {

        // Arrange
        final OrderFiscalIdentifierEntity orderFiscalIdentifierEntity = new OrderFiscalIdentifierEntityMock()
                .getDataDefault();
        final OrderFiscalIdentifierDTO orderFiscalIdentifierDTO = new OrderFiscalIdentifierDTODataMock()
                .getDataDefault();

        // Act
        when(mockOrderFiscalIdentifierRepository.save(any())).thenReturn(orderFiscalIdentifierEntity);
        final CompletableFuture<Boolean> response = createOrderFiscalIdentifierUseCase.invoke(
                orderFiscalIdentifierDTO, anyLong()
        );

        // Assert
        assertNotNull(response);
    }
}
