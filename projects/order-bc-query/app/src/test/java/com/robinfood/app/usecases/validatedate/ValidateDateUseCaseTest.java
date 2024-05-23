package com.robinfood.app.usecases.validatedate;

import com.robinfood.core.exceptions.GenericOrderBcException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ValidateDateUseCaseTest {

    @InjectMocks
    private ValidateDateUseCase validateDateUseCase;

    @Test
    void When_Validate_Date_Ok_Should_Not_Return_Exception() {

        var localDateStart = LocalDate.parse("2023-01-16");
        var localDateEnd = LocalDate.parse("2023-01-16");
        validateDateUseCase.invoke(localDateStart, localDateEnd);
    }

    @Test
    void When_validate_One_More_Days_Should_Return_Exception() {

        var localDateStart = LocalDate.parse("2023-01-12");
        var localDateEnd = LocalDate.parse("2023-01-16");

        GenericOrderBcException exception = assertThrows(GenericOrderBcException.class, () -> {
            validateDateUseCase.invoke(localDateStart, localDateEnd);
        });

        assertEquals("The range of days must be one", exception.getMessage());

    }

    @Test
    void When_validate_DateFinal_IsBefore_The_DateInitial_Should_Return_Exception() {

        var localDateStart = LocalDate.parse("2023-01-17");
        var localDateEnd = LocalDate.parse("2023-01-15");

        GenericOrderBcException exception = assertThrows(GenericOrderBcException.class, () -> {
            validateDateUseCase.invoke(localDateStart, localDateEnd);
        });

        assertEquals("The start date cannot be greater than the end date", exception.getMessage());

    }

}