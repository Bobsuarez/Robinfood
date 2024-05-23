package com.robinfood.paymentmethodsbc.dto.bci;

import java.io.Serializable;
import java.util.Map;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BCISettingsDTO implements Serializable {
    private static final long serialVersionUID = 8034045755088517955L;

    @Valid
    private Map<String, String> orchestrator;

    @Valid
    private Map<String, String> gateway;

    private Map<String, String> terminal;
}
