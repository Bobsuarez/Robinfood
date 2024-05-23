package com.robinfood.storeor.repositories.configurationrepository;

import com.robinfood.storeor.dtos.apiresponsebuilder.RestResponsePage;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.StoreEntity;
import com.robinfood.storeor.entities.UserStoreEntity;
import com.robinfood.storeor.entities.listposresponse.PosListResponseEntity;
import org.springframework.data.domain.Sort;

/**
 * Repository that handles data related to store settings
 */
public interface IConfigurationRepository {

    /**
     * Method that invokes the repository to obtain the detail of a store by its identifier
     *
     * @param storeId Identifier of the store
     * @return Store Detail
     */
    APIResponseEntity<StoreEntity> getStore(Long storeId, String token);

    APIResponseEntity<RestResponsePage<StoreEntity>> getStore(
            String name, Long companyCountryId,
            Integer page, Integer size, Sort sort, String accessToken
    );

    APIResponseEntity<UserStoreEntity> findStoreByUserId(Long userId, String token);

    APIResponseEntity<RestResponsePage<PosListResponseEntity>> getListPos(
            Integer page, String posName, Integer size,
            Long status, Long storeId, Sort sort, String accessToken
    );
}
