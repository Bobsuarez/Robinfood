package com.robinfood.core.dtos.orderpayment;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "typeId",
        "name",
        "shortName",
        "value",
        "transactions"
})
public class OrderPaymentDTO {

    public Integer id;

    public String name;

    public String shortName;

    public Integer transactions;

    public String typeId;

    public Double value;

}
