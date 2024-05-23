package com.robinfood.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SubscriberEntity {

    private String description;
    private Long id;
    private String name;
    private List<PropertyEntity> properties;
    private TypeEntity type;
}
