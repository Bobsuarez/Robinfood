package com.robinfood.localserver.electronicbillresponse.repositories.electronicbillingrepository;

import com.robinfood.localserver.commons.entities.electronicbill.ElectronicBillingRequestEntity;
import com.robinfood.localserver.commons.entities.electronicbill.ElectronicBillingResponseEntity;
import com.robinfood.localserver.commons.entities.http.ApiResponseEntity;
import com.robinfood.localserver.electronicbillresponse.configs.network.feignclients.StoreOrApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Implementation of ICreateOrdersElectronicBillingRemoteDataSource
 */
@Slf4j
@Repository
public class CreateElectronicBillingRemoteDataSource implements ICreateElectronicBillingRemoteDataSource {

    private final StoreOrApi storeOrApi;

    public CreateElectronicBillingRemoteDataSource(StoreOrApi storeOrApi) {

        this.storeOrApi = storeOrApi;
    }

    @Override
    public ApiResponseEntity<ElectronicBillingResponseEntity> invoke(
            ElectronicBillingRequestEntity orderElectronicBillingRequest,
            String tokenUser
    ) {

        ApiResponseEntity<ElectronicBillingResponseEntity> responseEntityResult = null;
        try {
            log.info("Execute create electronic billing remote Data Source {}", orderElectronicBillingRequest);

            responseEntityResult = storeOrApi
                    .createElectronicBilling(tokenUser, orderElectronicBillingRequest);

            log.info("The order electronic billing response created successfully {}", responseEntityResult);
        } catch (Exception e) {
            log.error("Error at Execute create electronic billing remote Data Source", e);
        }

        return responseEntityResult;
    }
}
