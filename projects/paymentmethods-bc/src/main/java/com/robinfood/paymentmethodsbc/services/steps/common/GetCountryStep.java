package com.robinfood.paymentmethodsbc.services.steps.common;

import com.robinfood.paymentmethodsbc.dto.steps.CreditCardCreatePipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardUserListPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Country;
import com.robinfood.paymentmethodsbc.repositories.CountriesRepository;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class GetCountryStep implements StepActions {
    private final CountriesRepository countriesRepository;

    public GetCountryStep(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    public void invoke(Object pipe)
        throws  PaymentStepException, EntityNotFoundException {

        if (pipe instanceof PaymentPipeDTO) {
            PaymentPipeDTO transactionCreatePipeDTO = (PaymentPipeDTO) pipe;
            transactionCreatePipeDTO.setCountry(
                getCountry(
                    transactionCreatePipeDTO
                        .getTransactionRequestDTO()
                        .getCountryId()
                )
            );
            return;
        }

        if (pipe instanceof CreditCardCreatePipeDTO) {
            CreditCardCreatePipeDTO creditCardCreatePipeDTO = (CreditCardCreatePipeDTO) pipe;
            getCountry(
                creditCardCreatePipeDTO.getCreditCardRequestDTO().getCountryId()
            );
            return;
        }

        if (pipe instanceof CreditCardUserListPipeDTO) {
            CreditCardUserListPipeDTO creditCardUserListPipeDTO = (CreditCardUserListPipeDTO) pipe;
            getCountry(
                creditCardUserListPipeDTO.getCountryId()
            );
            return;
        }

        throw new PaymentStepException(
            StepType.GET_COUNTRY,
            String.format("No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }

    private Country getCountry(Long id) throws EntityNotFoundException {
        Optional<Country> optional = countriesRepository.findById(id);

        if (optional.isEmpty()) {
            throw new EntityNotFoundException(
                Country.class.getSimpleName(),
                String.valueOf(id)
            );
        }

        return optional.get();
    }
}
