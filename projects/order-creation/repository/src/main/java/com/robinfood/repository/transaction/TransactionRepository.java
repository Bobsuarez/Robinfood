package com.robinfood.repository.transaction;

import com.robinfood.core.dtos.ConfigTransactionResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionResponseDTO;
import com.robinfood.core.entities.ConfigTransactionResponseEntity;
import com.robinfood.core.entities.transactionrequestentities.TransactionRequestEntity;
import com.robinfood.core.entities.transactionresponseentities.TransactionCreationResponseEntity;
import com.robinfood.core.mappers.TransactionRequestMappers;
import com.robinfood.core.models.domain.menu.Brand;
import com.robinfood.core.models.domain.pickuptime.PickupTime;
import com.robinfood.core.models.retrofit.order.pickuptime.PickupTimeModelResponseRest;
import com.robinfood.core.models.retrofit.order.pickuptime.PickupTimeModelRest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of ITransactionRepository
 */
@Slf4j
@Repository
public class TransactionRepository implements ITransactionRepository {

    private final ModelMapper modelMapper;
    private final ITransactionLocalDataSource transactionLocalDataSource;
    private final ITransactionRemoteDataSource transactionRemoteDataSource;

    public TransactionRepository(ModelMapper modelMapper, ITransactionLocalDataSource transactionLocalDataSource,
            ITransactionRemoteDataSource transactionRemoteDataSource) {
        this.modelMapper = modelMapper;
        this.transactionLocalDataSource = transactionLocalDataSource;
        this.transactionRemoteDataSource = transactionRemoteDataSource;
    }

    @Override
    public CompletableFuture<TransactionCreationResponseDTO> createTransaction(
            String token,
            List<Brand> brands,
            TransactionRequestDTO transactionRequest
    ) {
        log.info("Going out to create transaction with origin name [{}], company id [{}] and device platform [{}]",
                transactionRequest.getOrigin().getName(), transactionRequest.getCompany().getId(),
                transactionRequest.getDevice().getPlatform());

        final TransactionRequestEntity transactionRequestEntity = TransactionRequestMappers
                .toTransactionRequestEntity(transactionRequest, brands);

        transactionRequestEntity.setAlreadyValidated(
                transactionRequest.getUpdateOrder()
        );

        return transactionRemoteDataSource.createTransaction(token, transactionRequestEntity)
                .thenApply(
                        (TransactionCreationResponseEntity tran) -> modelMapper.map(tran,
                                TransactionCreationResponseDTO.class)
                );
    }

    @Override
    public ConfigTransactionResponseDTO getTransactionResponseDTO() {
        var transactionResponseDTO = modelMapper.map(
                transactionLocalDataSource
                        .getTransactionResponse()
                        .getTransaction(),
                TransactionResponseDTO.class
        );

        var transactionCreationResponseDto = TransactionCreationResponseDTO.builder()
                .transaction(transactionResponseDTO)
                .build();

        return ConfigTransactionResponseDTO.builder()
                .transactionCreationResponse(transactionCreationResponseDto)
                .build();
    }

    @Override
    public void setTransactionResponseDTO(
            ConfigTransactionResponseDTO configTransactionResponseDTO
    ) {
        var entity = modelMapper.map(
                configTransactionResponseDTO.getTransactionCreationResponse(),
                ConfigTransactionResponseEntity.class
        );

        transactionLocalDataSource.setTransactionResponse(entity);
    }

    @Override
    public List<Long> savePickupTime(
            String token,
            PickupTime pickupTime,
            Long transactionId
    ) {
        log.info("Process start to save pickup-time data: {}", objectToJson(pickupTime));

        var request = modelMapper.map(pickupTime, PickupTimeModelRest.class);
        request.setTransactionId(transactionId);

        var response = transactionRemoteDataSource.savePickupTime(token, request);

        return response.stream()
                .map(PickupTimeModelResponseRest::getId)
                .collect(Collectors.toList());
    }
}
