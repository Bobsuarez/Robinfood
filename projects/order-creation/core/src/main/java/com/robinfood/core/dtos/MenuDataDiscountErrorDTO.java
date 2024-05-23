package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDataDiscountErrorDTO implements Serializable {

    @JsonProperty("discount_errors")
    @SerializedName(value = "discount_errors")
    List<MenuDiscountErrorDTO> discountErrors;
}
