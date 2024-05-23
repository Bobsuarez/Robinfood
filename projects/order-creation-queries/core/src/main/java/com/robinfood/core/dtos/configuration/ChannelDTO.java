package com.robinfood.core.dtos.configuration;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChannelDTO {

    public final Integer id;

    public final String name;
}
