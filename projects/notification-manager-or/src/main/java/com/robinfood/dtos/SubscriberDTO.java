package com.robinfood.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SubscriberDTO {

    private String description;
    private Long id;
    private String name;
    private List<PropertyDTO> properties;
    private TypeDTO type;
}
