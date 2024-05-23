package com.robinfood.localserver.commons.dtos.sathub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class InfCFe {

    private String Id;

    private Ide ide;

    private EmitResponse emit;

}
