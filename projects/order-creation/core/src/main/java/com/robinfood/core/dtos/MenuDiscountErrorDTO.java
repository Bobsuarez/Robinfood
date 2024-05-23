package com.robinfood.core.dtos;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDiscountErrorDTO implements Serializable {

    private String entity;
    private String quantity;
    private String reason;
    private String sourceId;
    private String value;
    private String valid;
}
