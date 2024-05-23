package com.robinfood.core.dtos.queue;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
@Data
public class ChannelDTO {

    private final Long id;

    private final String name;
}
