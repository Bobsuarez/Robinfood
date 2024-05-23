package com.robinfood.localprinterbc.dtos.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailProductGroupDTO {

    private  Long id;

    private  String name;

    private  List<OrderDetailProductGroupPortionDTO> portions;

    private  List<OrderDetailRemovedPortionDTO> removedPortions;

    private String sku;

    private Boolean hasPortions;

    private Boolean hasRemovedPortions;

    public boolean hasReplacement() {
        return portions.stream().anyMatch(OrderDetailProductGroupPortionDTO::hasReplacement);
    }

    public Boolean getHasPortions() {
        return portions != null && !portions.isEmpty();
    }

    public Boolean getHasRemovedPortions() {
        return removedPortions != null && !removedPortions.isEmpty();
    }

}
