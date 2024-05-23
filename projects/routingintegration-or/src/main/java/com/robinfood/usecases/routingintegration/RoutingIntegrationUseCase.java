package com.robinfood.usecases.routingintegration;

import com.robinfood.dtos.routinintegration.RoutingIntegrationDTO;
import com.robinfood.entities.api.routingintegration.ResponseRoutingIntegrationEntity;
import com.robinfood.repository.routingintegration.IRoutingIntegrationRepository;
import com.robinfood.repository.routingintegration.RoutingIntegrationRepository;
import com.robinfood.util.ObjectMapperSingleton;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import static com.robinfood.enums.AppLogsTraceEnum.RESPONSE_DATA_OK;
import static com.robinfood.util.LogsUtil.info;

public class RoutingIntegrationUseCase implements IRoutingIntegrationUseCase {

    private final IRoutingIntegrationRepository routingIntegrationRepository;

    public RoutingIntegrationUseCase() {
        this.routingIntegrationRepository = new RoutingIntegrationRepository();
    }

    public RoutingIntegrationUseCase(IRoutingIntegrationRepository routingIntegrationRepository) {
        this.routingIntegrationRepository = routingIntegrationRepository;
    }

    @Override
    public RoutingIntegrationDTO invoke(
            @Required Long id,
            @Required String token,
            @Required String uuId
    ) {

        info(
                RESPONSE_DATA_OK.getMessageWithCode(),
                RoutingIntegrationUseCase.class.getName(),
                "id", id,
                "uuId", uuId
        );

        final ResponseRoutingIntegrationEntity responseRoutingIntegrationEntity = routingIntegrationRepository
                .getChannelIntegration(id, token, uuId);

        return ObjectMapperSingleton
                .objectToClassConvertValue(responseRoutingIntegrationEntity, RoutingIntegrationDTO.class);
    }
}
