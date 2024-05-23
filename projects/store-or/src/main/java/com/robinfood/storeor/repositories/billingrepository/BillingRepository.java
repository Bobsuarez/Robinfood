package com.robinfood.storeor.repositories.billingrepository;

import com.robinfood.storeor.configs.apis.BillingBcApi;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.CommandConfiguration.CommandConfigurationEntity;
import com.robinfood.storeor.entities.TreasureDepartmentsEntity;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.robinfood.storeor.configs.constants.APIConstants.SUCCESS_CODE;

@Repository
@Slf4j
public class BillingRepository implements IBillingRepository{

    private final BillingBcApi billingBcApi;

    public BillingRepository(BillingBcApi billingBcApi) {
        this.billingBcApi = billingBcApi;
    }

    @Override
    public APIResponseEntity<TreasureDepartmentsEntity> getBillingConfiguration(Long storeId, String token) {

        log.info("BillingRepository execute getBillingConfiguration() Get billing Configuration by store: {}",
            storeId);

        try {

            return billingBcApi.getBillingConfiguration(storeId, token);

        } catch (FeignException.InternalServerError e) {

            log.info("Exception is {e} ", e);
            if (e.getMessage().contains("Not found Treasury Department Store")) {

                ZonedDateTime locale = ZonedDateTime.now(ZoneId.of("Z"));

                return new APIResponseEntity<>(SUCCESS_CODE, TreasureDepartmentsEntity.builder().build(),
                        locale.toString(),
                        "Billing configuration retrieved successfully",
                        "OK");
            }
            throw new FeignException.InternalServerError(e.getMessage(), e.request(), e.content(), e.responseHeaders());
        }
    }

    public APIResponseEntity<List<CommandConfigurationEntity>> getCommandConfiguration(Long storeId, String token) {

        log.info("ConfigurationRepository execute GetCommandConfiguration() Get Configuration by store: {}", storeId);

        return billingBcApi.getCommandConfigurationByStoreId(storeId, token);

    }
}
