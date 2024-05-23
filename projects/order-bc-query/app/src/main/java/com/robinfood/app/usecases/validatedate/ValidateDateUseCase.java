package com.robinfood.app.usecases.validatedate;

import com.robinfood.core.exceptions.GenericOrderBcException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.robinfood.core.constants.GlobalConstants.LIMIT_DAYS;
import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class ValidateDateUseCase implements IValidateDateUseCase {

    @Override
    public void invoke(LocalDate localDateStart, LocalDate localDateEnd) {

        if (localDateEnd.isBefore(localDateStart)) {
            throw new GenericOrderBcException("The start date cannot be greater than the end date");
        }

        final long days = DAYS.between(localDateStart, localDateEnd);

        if (days > LIMIT_DAYS) {
            throw new GenericOrderBcException("The range of days must be one");
        }
    }
}
