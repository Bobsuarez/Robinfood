package com.robinfood.changestatusor.repository.changestateorders;

import com.robinfood.changestatusor.config.network.api.ChangeStatusBCAPI;
import com.robinfood.changestatusor.datamock.ChangeOrderStatusDTOMock;
import com.robinfood.changestatusor.datamock.ChangeStateOrderRespondEntityMock;
import com.robinfood.changestatusor.entities.ApiResponseEntity;
import com.robinfood.changestatusor.entities.changestateorderequestentities.ChangeStateOrderRequestEntity;
import com.robinfood.changestatusor.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ChangeSateOrderRepositoryTest {

    @Mock
    ChangeStatusBCAPI changeStatusBCAPI;

    @InjectMocks
    ChangeSateOrderRepository changeSateOrderRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_changeOrderStatus_Should_ReturnEntity_When_InvokeTheRepository(){

        ApiResponseEntity<ChangeStateOrderRespondEntity> expectedResponse =
                new ApiResponseEntity<ChangeStateOrderRespondEntity>(
                        200,
                        ChangeStateOrderRespondEntityMock.getDefault(),
                        false,
                        "",
                        ""
                ) ;

                ChangeStateOrderRespondEntityMock.getDefault();

        when(changeStatusBCAPI.changeState(any(ChangeStateOrderRequestEntity.class), anyString()))
                .thenReturn(expectedResponse);

        changeSateOrderRepository = new ChangeSateOrderRepository(changeStatusBCAPI);

        assertAll(()-> changeSateOrderRepository.changeOrderStatus(
                ChangeOrderStatusDTOMock.getDefault(),
                "token"
        ));

    }
}
