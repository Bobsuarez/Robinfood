package com.robinfood.app.usecases.savepickuptime;

import com.robinfood.app.mappers.PickupTimeMappers;
import com.robinfood.core.entities.PickupTimeEntity;
import com.robinfood.core.models.domain.PickupTime;
import com.robinfood.repository.pickuptime.IPickupTimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@Slf4j
public class SavePickupTimeUseCase implements ISavePickupTimeUseCase {

    private final PickupTimeMappers pickupTimeMappers;
    private final IPickupTimeRepository pickupTimeRepository;

    public SavePickupTimeUseCase(PickupTimeMappers pickupTimeMappers, IPickupTimeRepository pickupTimeRepository) {
        this.pickupTimeMappers = pickupTimeMappers;
        this.pickupTimeRepository = pickupTimeRepository;
    }

    @Override
    public List<PickupTime> invoke(PickupTime pickupTime) {

        log.info("Starting process to save the pickup-time");

        final List<PickupTime> pickupTimesFound = findByTransactionId(pickupTime);

        if (!pickupTimesFound.isEmpty()) {
            return pickupTimesFound;
        }

        final List<PickupTimeEntity> pickupTimeEntities = pickupTimeMappers.domainToEntities(
                pickupTime
        );

        final List<PickupTime> pickupTimes = StreamSupport
                .stream(pickupTimeRepository.saveAll(pickupTimeEntities).spliterator(), false)
                .map(pickupTimeMappers::entityToDomain)
                .collect(Collectors.toList());

        log.info("Saved data: [{}]", pickupTimes);

        return pickupTimes;
    }

    private List<PickupTime> findByTransactionId(PickupTime pickupTime) {
        return pickupTimeRepository.findByTransactionId(pickupTime.getTransactionId()).stream()
                .map(pickupTimeMappers::entityToDomain)
                .collect(Collectors.toList());
    }
}
