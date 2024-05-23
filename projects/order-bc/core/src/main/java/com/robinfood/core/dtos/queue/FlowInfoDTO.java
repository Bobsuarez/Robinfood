package com.robinfood.core.dtos.queue;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowInfoDTO implements Serializable {

    private static final long serialVersionUID = -8133003200203333168L;

    private String address;

    private String comment;

    private String pickUpTime;
}
