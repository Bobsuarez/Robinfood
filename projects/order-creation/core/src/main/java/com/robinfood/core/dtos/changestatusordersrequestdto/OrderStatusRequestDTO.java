package com.robinfood.core.dtos.changestatusordersrequestdto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderStatusRequestDTO implements Serializable {

    @NotBlank
    private String notes;

    @NotNull
    @Positive
    private Long orderId;

    @NotNull
    @Min(1)
    @Max(14)
    private Long statusId;

    @NotNull
    @Positive
    private Long userId;
}
