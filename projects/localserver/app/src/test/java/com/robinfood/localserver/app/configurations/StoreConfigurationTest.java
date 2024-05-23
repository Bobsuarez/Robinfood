package com.robinfood.localserver.app.configurations;

import com.robinfood.localserver.commons.dtos.http.ApiResponseDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.AcceptLanguageDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.StoreDTO;
import com.robinfood.localserver.commons.utilities.StoreInfoConfiguration;
import com.robinfood.localserver.localstoreconfiguration.controllers.store.IStoreController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.TimeZone;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StoreConfigurationTest {

    @InjectMocks
    private StoreConfiguration storeConfiguration;

    @Mock
    private IStoreController storeController;

    @MockBean
    private StoreDTO storeDTO;

    @Autowired
    StoreInfoConfiguration storeInfoConfiguration;


    @Test
    void test_When_Store_Configuration() {

        when(storeController.configuration(any())).thenReturn(
                ApiResponseDTO.<StoreDTO>builder()
                        .code(HttpStatus.OK.value())
                        .data(StoreDTO.builder().name("prueba").build())
                        .locale(TimeZone.getTimeZone("Z").toString())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .build());

        StoreDTO config = storeConfiguration.setStoreConfiguration();

        Assertions.assertEquals(config, StoreDTO.builder().name("prueba").build());
    }

    @Test
    void test_When_AcceptLanguage() {

        AcceptLanguageDTO acceptLanguage = storeConfiguration.setAcceptLanguage("es","col");

        Assertions.assertEquals("es-col", acceptLanguage.getLanguage());

    }
}
