package com.robinfood.localprinterbc.usecases.createcustomlog;

import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.loggings.CreateOrderCustomLog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateCustomLogUseCaseTest {

    @Spy
    @InjectMocks
    private CreateCustomLogUseCase createCustomLogUseCase;

    @Test
    void test_When_Send_Order_Detail_Use_Case_invoke_Is_Successful() throws Exception {
        OrderDetailDTO orderDetailDTO = OrderDetailDTO.builder()
                .originName("testOrigin")
                .storeName("testStore")
                .brandName("testBrand")
                .orderUuid("testUuid")
                .build();

        createCustomLogUseCase.invoke(orderDetailDTO);
        verify(createCustomLogUseCase).invoke(orderDetailDTO);
    }

    @Test
    void testPrivateConstructor() throws NoSuchMethodException {

        Class<CreateOrderCustomLog> clazz = CreateOrderCustomLog.class;
        Constructor<CreateOrderCustomLog> constructor = ReflectionUtils.accessibleConstructor(clazz);

        assert constructor != null;
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }
}
