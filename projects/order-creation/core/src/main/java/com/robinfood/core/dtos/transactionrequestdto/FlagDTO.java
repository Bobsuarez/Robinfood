package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlagDTO implements Serializable {

    private static final long serialVersionUID = 3158434526865887025L;

    @Valid
    private CorporateDTO corporate;

    @Valid
    private PitsDTO pits;

    @Valid
    private SubmarineDTO submarine;

    @Valid
    private TogoDTO togo;
}
