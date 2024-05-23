package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuValidationMessageErrorDTO implements Serializable {
    private String code;
    private String message;
    private List<String> error;
    private List<MenuDataSkuDTO> data;
    private String request;

}
