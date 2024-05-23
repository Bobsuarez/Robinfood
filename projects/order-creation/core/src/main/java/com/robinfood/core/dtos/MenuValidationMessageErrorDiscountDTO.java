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
public class MenuValidationMessageErrorDiscountDTO implements Serializable {

    private String code;
    private MenuDataDiscountErrorDTO data;
    private List<String> error;
    private String message;
    private String request;

}
