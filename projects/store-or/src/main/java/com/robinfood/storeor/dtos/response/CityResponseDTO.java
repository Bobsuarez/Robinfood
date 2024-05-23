package com.robinfood.storeor.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CityResponseDTO {

    Long id;
    String name;
    String timezone;

}
