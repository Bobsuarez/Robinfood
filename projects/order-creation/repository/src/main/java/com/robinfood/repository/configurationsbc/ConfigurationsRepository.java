package com.robinfood.repository.configurationsbc;

import com.robinfood.core.dtos.transactionrequestdto.ChannelDTO;
import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.mappers.ConfigurationsMappers;
import com.robinfood.core.models.domain.configuration.Channel;
import com.robinfood.core.models.domain.configuration.Store;
import com.robinfood.core.models.domain.configuration.StoreInformation;
import com.robinfood.core.models.retrofit.configuration.PosResponse;
import com.robinfood.network.api.ConfigurationsBCAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.robinfood.core.enums.TransactionCreationErrors.GET_POS_ID_ERROR;
import static com.robinfood.core.extensions.NetworkExtensionsKt.safeAPICall;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@Repository
@Slf4j
public class ConfigurationsRepository implements IConfigurationRepository {

    private final ConfigurationsBCAPI configurationsBCAPI;

    private final IConfigurationsDataSource configurationsDataSource;

    public ConfigurationsRepository(
            ConfigurationsBCAPI configurationsBCAPI,
            IConfigurationsDataSource configurationsDataSource
    ) {
        this.configurationsBCAPI = configurationsBCAPI;
        this.configurationsDataSource = configurationsDataSource;
    }

    @Override
    public ChannelDTO getChannel(String token, Long channelId) {

        final Channel channel = configurationsDataSource.getChannelById(token, channelId);

        return ConfigurationsMappers.toChannelChannelDTO(channel);

    }

    @Override
    public Store getPosIdByStoreIdAndPaymentMethodIds(
            String token,
            Store store
    ) {

        log.info("Going out to consume pos id service with store {}", objectToJson(store));

        Result<ApiResponseEntity<PosResponse>> response =
                safeAPICall(configurationsBCAPI.getPosId(
                        token, store.getId(), store.getPosTypeId()
                ));

        if (response instanceof Result.Error) {
            final Result.Error error = (Result.Error) response;

            throw new TransactionCreationException(
                    error.component1().getMessage(),
                    format("Error obtain pos_id with store_id [%s]", store.getId()),
                    GET_POS_ID_ERROR,
                    PRECONDITION_FAILED
            );
        }

        Long posId = ((Result.Success<ApiResponseEntity<PosResponse>>) response).getData()
                .getData()
                .getPosId();

        return responseToDomain(store, posId);
    }

    @Override
    public StoreInformation getStoreConfiguration(String token, Long id) {

        log.info("getStoreConfiguration start with id {} ", id);

        Result<ApiResponseEntity<StoreInformation>> response =
                safeAPICall(configurationsBCAPI.getStore(token, id));

        if (response instanceof Result.Error) {
            final Result.Error error = (Result.Error) response;

            throw new TransactionCreationException(
                    error.component1().getMessage(),
                    format("Error obtain store info with store_id [%s]", id),
                    GET_POS_ID_ERROR,
                    PRECONDITION_FAILED
            );
        }
        return ((Result.Success<ApiResponseEntity<StoreInformation>>) response).getData()
                .getData();
    }

    private Store responseToDomain(Store store, Long posId) {
        store.setPosId(posId);
        return store;
    }

}
