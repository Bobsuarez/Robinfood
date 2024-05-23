package com.robinfood.localserver.app.crons;

import com.robinfood.localserver.commons.dtos.http.ApiResponseDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.StoreDTO;
import com.robinfood.localserver.localstoreconfiguration.controllers.store.IStoreController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@ConditionalOnProperty(name = "spring.enable.scheduling")
@Slf4j

public class StoreCron {

    private final IStoreController storeController;

    @Value("${scheduler.store-id}")
    private Long storeId;

    public StoreCron(IStoreController storeController) {

        this.storeController = storeController;
    }

    @Scheduled(cron = "${scheduler.cron.store-configuration}", zone = "${scheduler.timezone.store-configuration}")
    public void configuration() {

        log.info("Run cron to get store configuration");
        ApiResponseDTO<StoreDTO> response = storeController.configuration(storeId);
        log.info("Store info {}", response.getData());
    }
}
