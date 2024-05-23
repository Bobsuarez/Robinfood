package com.robinfood.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SubscriberChannelDTO {

    private FlowDTO flow;
    private Long id;
    private List<SubscriberDTO> subscribers;
}
