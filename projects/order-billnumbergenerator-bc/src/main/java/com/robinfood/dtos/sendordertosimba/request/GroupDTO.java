package com.robinfood.dtos.sendordertosimba.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class GroupDTO {

    private Long id;

    private String name;

    private List<PortionDTO> portions;

    private String sku;
}
