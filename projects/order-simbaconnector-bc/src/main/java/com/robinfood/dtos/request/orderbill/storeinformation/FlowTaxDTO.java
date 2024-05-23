package com.robinfood.dtos.request.orderbill.storeinformation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class FlowTaxDTO {
    private Long value;
}
