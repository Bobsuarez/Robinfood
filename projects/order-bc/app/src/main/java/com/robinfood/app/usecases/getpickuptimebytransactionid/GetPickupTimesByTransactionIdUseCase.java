package com.robinfood.app.usecases.getpickuptimebytransactionid;

import com.robinfood.app.mappers.PickupTimeMappers;
import com.robinfood.core.models.domain.PickupTime;
import com.robinfood.repository.pickuptime.IPickupTimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GetPickupTimesByTransactionIdUseCase implements IGetPickupTimesByTransactionIdUseCase {

    private final PickupTimeMappers pickupTimeMappers;
    private final IPickupTimeRepository pickupTimeRepository;

    public GetPickupTimesByTransactionIdUseCase(
            IPickupTimeRepository pickupTimeRepository,
            PickupTimeMappers pickupTimeMappers
    ) {
        this.pickupTimeRepository = pickupTimeRepository;
        this.pickupTimeMappers = pickupTimeMappers;
    }

    @Override
    public List<PickupTime> invoke(Long transactionId) {

        log.info(
                "Start of the process that obtains the pickupTimes of a transaction by its id: {}",
                transactionId
        );

        List<PickupTime> pickupTimes = pickupTimeRepository.findByTransactionId(transactionId).stream()
                .map(pickupTimeMappers::entityToDomain)
                .collect(Collectors.toList());

        log.info("PickupTimes found: {}", pickupTimes);

        return pickupTimes;
    }

}
