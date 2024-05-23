package com.robinfood.localserver.app.crons;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.robinfood.localserver.app.configurations.StoreConfiguration;
import com.robinfood.localserver.commons.dtos.http.ApiResponseDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.AcceptLanguageDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.StoreDTO;
import com.robinfood.localserver.localstoreconfiguration.controllers.store.IStoreController;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
// Override the scheduling rate to something really short:
@TestPropertySource(properties = "scheduler.cron.store-configuration=*/3 * * * * *")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class StoreCronTest {

    @SpyBean
    private StoreCron storeCron;

    @MockBean
    private IStoreController storeController;

    @MockBean
    private StoreConfiguration storeConfiguration;

    @MockBean
    private StoreDTO storeDTO;

    @MockBean
    private AcceptLanguageDTO acceptLanguage;



    @Test
    void test_When_Store_Cron_Job_Runs() {

        Awaitility.await().atMost(6, TimeUnit.SECONDS).untilAsserted(() ->
                verify(storeCron, Mockito.atLeast(2)).configuration()
        );
    }

    @Test
    void test_When_Store_Cron_Configuration() {
        when(storeController.configuration(anyLong())).thenReturn(
                ApiResponseDTO.<StoreDTO>builder()
                        .code(HttpStatus.OK.value())
                        .data(new StoreDTO())
                        .locale(TimeZone.getTimeZone("Z").toString())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .build());

        storeCron.configuration();

        verify(storeController, Mockito.atLeast(1)).configuration(anyLong());
    }
}
