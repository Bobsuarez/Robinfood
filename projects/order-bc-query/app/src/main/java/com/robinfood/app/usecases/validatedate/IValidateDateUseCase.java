package com.robinfood.app.usecases.validatedate;

import java.time.LocalDate;

public interface IValidateDateUseCase {

    void invoke(LocalDate localDateStart, LocalDate localDateEnd);
}
