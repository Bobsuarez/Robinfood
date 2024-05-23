package com.robinfood.configurations.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelDTO {
    private Long id;
    private String name;
    private String logo;
}
