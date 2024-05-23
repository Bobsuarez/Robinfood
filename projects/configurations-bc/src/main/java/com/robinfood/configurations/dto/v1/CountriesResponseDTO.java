package com.robinfood.configurations.dto.v1;

import com.robinfood.configurations.models.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CountriesResponseDTO implements Serializable {

    private static final long serialVersionUID = 4637180375132385617L;

    private Long id;

    private String name;

    public static CountriesResponseDTO fromCountriesRepository(Country countryFound) {
        return CountriesResponseDTO.builder()
                .id(countryFound.getId())
                .name(countryFound.getName())
                .build();
    }
}
