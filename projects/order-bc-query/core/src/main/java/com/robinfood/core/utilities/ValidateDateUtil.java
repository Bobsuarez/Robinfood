package com.robinfood.core.utilities;

import com.robinfood.core.exceptions.GenericOrderBcException;

import java.time.LocalDate;
import java.util.Objects;

public class ValidateDateUtil {

    public static void isEmptyDateStartAndDateFinal(LocalDate localDateStart, LocalDate localDateEnd) {

        if ((Objects.isNull(localDateStart) && Objects.nonNull(localDateEnd))
                || (Objects.nonNull(localDateStart) && Objects.isNull(localDateEnd))
        ) {
            throw new GenericOrderBcException("The date is required to have start and end");
        }
    }
}
