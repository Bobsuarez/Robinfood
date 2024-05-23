package com.robinfood.paymentmethodsbc.services.steps.common;

import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Entity;
import com.robinfood.paymentmethodsbc.repositories.EntitiesRepository;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class GetEntityStep implements StepActions {
    private final EntitiesRepository entitiesRepository;

    public GetEntityStep(EntitiesRepository entitiesRepository) {
        this.entitiesRepository = entitiesRepository;
    }

    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof PaymentPipeDTO) {
            PaymentPipeDTO transactionCreatePipeDTO = (PaymentPipeDTO) pipe;

            transactionCreatePipeDTO.setEntity(
                getEntity(
                    transactionCreatePipeDTO
                        .getTransactionRequestDTO()
                        .getEntity()
                        .getId()
                )
            );

            return;
        }

        throw new PaymentStepException(
            StepType.GET_ENTITY,
            String.format("No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }

    private Entity getEntity(Long id) throws EntityNotFoundException {
        Optional<Entity> optional = entitiesRepository.findById(id);

        if (optional.isEmpty()) {
            throw new EntityNotFoundException(
                Entity.class.getSimpleName(),
                String.valueOf(id)
            );
        }

        return optional.get();
    }
}
