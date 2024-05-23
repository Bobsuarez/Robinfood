package com.robinfood.app.usecases.getorderfiscalidentifierbytransactionid;

import com.robinfood.app.datamocks.entity.OrderFiscalIdentifierEntityMock;
import com.robinfood.app.usecases.getorderfiscalidentifierbytransactionidusecase.GetOrderFiscalIdentifierByTransactionIdUseCase;
import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;
import com.robinfood.core.entities.OrderFiscalIdentifierEntity;
import com.robinfood.repository.orderfiscalidentifier.IOrderFiscalIdentifierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderFiscalIdentifierByTransactionIdUseCaseTest {

    @Mock
    private IOrderFiscalIdentifierRepository mockOrderFiscalIdentifierRepository;

    @InjectMocks
    private GetOrderFiscalIdentifierByTransactionIdUseCase mockOrderFiscalIdentifierByTransactionIdUseCase;

    @Test
    void test_GetOrderFiscalIdentifierByTransactionIdUseCase_Happy_Path() {

        // Arrange
        final Long transactionId = 1L;
        final OrderFiscalIdentifierEntity orderFiscalIdentifierEntity = new OrderFiscalIdentifierEntityMock()
                .getDataDefault();

        // Act
        when(mockOrderFiscalIdentifierRepository.findByTransactionId(transactionId)).thenReturn(orderFiscalIdentifierEntity);

        final OrderFiscalIdentifierDTO response = mockOrderFiscalIdentifierByTransactionIdUseCase.invoke(transactionId);

        //Assert
        assertNotNull(response);
    }

    @Test
    void test_GetOrderFiscalIdentifierByTransactionIdUseCase_Not_Found() {

        // Arrange
        final Long transactionId = 1L;

        // Act
        when(mockOrderFiscalIdentifierRepository.findByTransactionId(transactionId)).thenReturn(null);

        final OrderFiscalIdentifierDTO response = mockOrderFiscalIdentifierByTransactionIdUseCase.invoke(transactionId);

        //Assert
        assertNotNull(response);
    }
}
