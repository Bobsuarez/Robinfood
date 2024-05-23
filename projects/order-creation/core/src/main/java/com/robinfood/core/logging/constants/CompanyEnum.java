package com.robinfood.core.logging.constants;

import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public enum CompanyEnum {

    ROBIN_FOOD_COLOMBIA(1, "Colombia"),
    ROBIN_FOOD_BRAZIL(5, "Brazil");
    private final int id;
    private final String name;

    CompanyEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String parseName(final Number idCompany) {
        return Arrays.stream(values()).filter(value -> value.getId() == idCompany.intValue())
                .findFirst()
                .orElseThrow(() -> new TransactionCreationException(
                        HttpStatus.BAD_REQUEST,
                        "this company does not exist",
                        TransactionCreationErrors.INCONSISTENT_TRANSACTION_ERROR
                )).getName();
    }


}
