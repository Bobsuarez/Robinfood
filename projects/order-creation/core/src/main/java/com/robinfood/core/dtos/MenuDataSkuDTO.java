package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDataSkuDTO implements Serializable {
    private String id;
    private String sku;
    private String errorMessage;
}
