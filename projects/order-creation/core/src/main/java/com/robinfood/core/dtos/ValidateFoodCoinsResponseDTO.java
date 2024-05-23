package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
public class ValidateFoodCoinsResponseDTO  implements Serializable {
    private final String message;
    private final int transactionCredits;
    private final boolean transactionStatus ;
    private final int userCurrentCredits;
}
