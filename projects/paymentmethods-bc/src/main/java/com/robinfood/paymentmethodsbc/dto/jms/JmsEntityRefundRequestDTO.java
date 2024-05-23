package com.robinfood.paymentmethodsbc.dto.jms;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JmsEntityRefundRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long entityId;

    private Long entitySourceId;

    private String entitySourceReference;

    private String reason;
}

