package com.robinfood.localserver.app.configurations;

import com.robinfood.localserver.commons.dtos.http.ApiResponseDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.AcceptLanguageDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.StoreDTO;
import com.robinfood.localserver.commons.utilities.StoreInfoConfiguration;
import com.robinfood.localserver.localstoreconfiguration.controllers.store.IStoreController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Configuration
@Slf4j
@RequiredArgsConstructor
@ComponentScan(basePackageClasses = StoreInfoConfiguration.class)
public class StoreConfiguration {

    @Value("${websocket.client.storeid}")
    private Long storeId;

    @NotNull
    private final IStoreController storeController;


    @Bean()
    public StoreDTO setStoreConfiguration() {
        log.info("init storeInfoConfiguration object configuration");

        ApiResponseDTO<StoreDTO> response = storeController.configuration(storeId);

        log.info("configuration find: {}", response.getData());

        return response.getData();
    }

    @Bean()
    public AcceptLanguageDTO setAcceptLanguage(@Value("${app.locale.language}") String language,
                                               @Value("${app.locale.country}") String country) {

        log.info("acceptLanguage configuration is {}",language.concat("-").concat(country));

        return AcceptLanguageDTO.builder()
                .Language(language.concat("-").concat(country)).build();
    }
}
