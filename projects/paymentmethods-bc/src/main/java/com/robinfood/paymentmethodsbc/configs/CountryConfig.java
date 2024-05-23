package com.robinfood.paymentmethodsbc.configs;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class CountryConfig {
    private final int colombiaId;

    private final int mexicoId;

    private final int brasilId;

    public CountryConfig(
        @Value("${countries.co.id}") int colombiaId,
        @Value("${countries.mx.id}") int mexicoId,
        @Value("${countries.br.id}") int brasilId
    ) {
        this.colombiaId = colombiaId;
        this.mexicoId = mexicoId;
        this.brasilId = brasilId;
    }
}
