package com.robinfood.changestatusor.network.api;

import com.robinfood.changestatusor.config.network.api.ChangeStatusBCAPI;
import com.robinfood.changestatusor.datamock.ChangeStateOrderRequestEntityMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

public class ChangeStatusBCAPITest {

    @InjectMocks
    private ChangeStatusBCAPI changeStatusBCAPI;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_ChangeState_Should_ReturnAPIResponseEntity_When_InvokeTheAPI() throws NoSuchFieldException, IllegalAccessException {

        Field field = changeStatusBCAPI.getClass().getDeclaredField("URL_CHANGE_STATUS_BC_API");

        field.setAccessible(true);
        field.set(changeStatusBCAPI,"https://a06siqgrqd.execute-api.us-east-2.amazonaws.com/");

        changeStatusBCAPI.changeState(ChangeStateOrderRequestEntityMock.getDefault(), "Token");
    }
}
