package com.robinfood.core.dtos.report.salebysegment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ChannelsDTOResponse {

    private final List<ItemDTOResponse> items;

    private final TotalDTOResponse total;

}
