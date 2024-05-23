package com.robinfood.app.usecases.getchannelbyid;

import com.robinfood.core.dtos.transactionrequestdto.ChannelDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.mappers.ConfigurationsMappers;
import com.robinfood.repository.configurationsbc.IConfigurationRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Service
@Slf4j
public class GetChannelByIdUseCase implements IGetChannelByIdUseCase {

    private final IConfigurationRepository configurationRepository;

    public GetChannelByIdUseCase(IConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Override
    public CompletableFuture<TransactionCreationResult> invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequestDTO
    ) {

        log.info("Starting process to get the channelId with data: {}", objectToJson(transactionRequestDTO));

        for (OrderDTO orderDto : transactionRequestDTO.getOrders()) {

            final ChannelDTO channelDTO = configurationRepository.getChannel(
                    token,
                    orderDto.getOrigin().getId()
            );

            log.info("Channel found: {}", objectToJson(channelDTO));

            orderDto.setOrigin(ConfigurationsMappers.toChannelDTOtoOriginDTO(channelDTO));

        }

        return CompletableFuture.completedFuture(
                TransactionCreationResult.StepValidationSuccess.INSTANCE
        );
    }
}
