package com.robinfood.storeor.repositories.electronicbillingrepository;

import com.robinfood.storeor.configs.apis.BillingBcApi;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingRequestEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingResponseEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of IElectronicBillingRemoteDataSource
 */
@Slf4j
@Repository
public class ElectronicBillingRemoteDataSource implements IElectronicBillingRemoteDataSource {

    private final BillingBcApi billingBcApi;

    public ElectronicBillingRemoteDataSource(BillingBcApi billingBcApi) {
        this.billingBcApi = billingBcApi;
    }

    @Override
    public APIResponseEntity<ElectronicBillingResponseEntity> save(
            String token,
            ElectronicBillingRequestEntity orderElectronicBillingRequest
    ) {
        final APIResponseEntity<ElectronicBillingResponseEntity> responseEntityResult = billingBcApi
                .createElectronicBilling(token, orderElectronicBillingRequest);

        log.info("The order electronic billing response created successfully {}", responseEntityResult);

        return responseEntityResult;
    }
    @Override
    public APIResponseEntity<List<ElectronicBillingEntity>> findAllByOrderIdIn(String token, List<Long> orderId){

        final  APIResponseEntity<List<ElectronicBillingEntity>> responseEntityResult=
                billingBcApi.getElectronicBillByOrdersId(token,orderId);

        log.info("the response of electronic bill for orders successfully {}", responseEntityResult);

        return responseEntityResult;
    }
}
