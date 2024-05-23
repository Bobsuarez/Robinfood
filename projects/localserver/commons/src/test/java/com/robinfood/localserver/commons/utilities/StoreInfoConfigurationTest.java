package com.robinfood.localserver.commons.utilities;

import com.robinfood.localserver.commons.dtos.storeconfiguration.AcceptLanguageDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.StoreDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



@ExtendWith(MockitoExtension.class)
class StoreInfoConfigurationTest {

    @InjectMocks
    private  StoreInfoConfiguration storeInfoConfiguration;

    @Test
    void test_Storeconfiguration_ok (){

        storeInfoConfiguration.setStoreConfiguration(StoreDTO.builder().name("prueba").build());

        storeInfoConfiguration.setAcceptLanguage(AcceptLanguageDTO.builder().Language("es-col").build());

        StoreDTO storeDTO= storeInfoConfiguration.getStoreConfiguration();

        AcceptLanguageDTO acceptLanguageDTO=storeInfoConfiguration.getAcceptLanguage();

        assertNotNull(storeDTO);

        assertNotNull(acceptLanguageDTO);

        assertEquals(StoreDTO.builder().name("prueba").build(),storeDTO);

        assertEquals("es-col",acceptLanguageDTO.getLanguage());
    }
}
