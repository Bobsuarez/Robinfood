package com.robinfood.app.usecases.getpickuptime;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.models.domain.pickuptime.PickupTime;
import com.robinfood.repository.pickuptime.IPickupTimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetPickupTimeUseCase implements IGetPickupTimeUseCase {

    private final IPickupTimeRepository pickupTimeRepository;

    public GetPickupTimeUseCase(IPickupTimeRepository pickupTimeRepository) {
        this.pickupTimeRepository = pickupTimeRepository;
    }

    @Override
    public PickupTime invoke(
            String token,
            TransactionRequestDTO transactionRequestDTO
    ) {

        return pickupTimeRepository.getByTransaction(token, transactionRequestDTO);
    }
}
