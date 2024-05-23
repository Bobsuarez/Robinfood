package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderToCreateDeliveryDTO implements Serializable {

    private static final long serialVersionUID = -2258063561714093457L;

    @JsonProperty("addressCity")
    private String addressCity;

    @JsonProperty("addressDescription")
    private String addressDescription;

    @JsonProperty("arrivalDate")
    private LocalDate arrivalDate;

    @JsonProperty("arrivalTime")
    private LocalTime arrivalTime;

    @JsonProperty("code")
    private String code;

    @JsonProperty("integrationId")
    private String integrationId;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("orderType")
    private Integer orderType;

    @JsonProperty("paymentMethod")
    private String paymentMethod;

    @JsonProperty("totalDelivery")
    private BigDecimal totalDelivery;

    @JsonProperty("totalDiscount")
    private BigDecimal totalDiscount;

    @JsonProperty("totalTip")
    private BigDecimal totalTip;

    private String type;

}
