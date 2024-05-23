package com.robinfood.localserver.electronicbillbc.mocks.dto;

import com.robinfood.localserver.commons.dtos.sathub.response.CFe;
import com.robinfood.localserver.commons.dtos.sathub.response.EmitResponse;
import com.robinfood.localserver.commons.dtos.sathub.response.Ide;
import com.robinfood.localserver.commons.dtos.sathub.response.InfCFe;
import com.robinfood.localserver.commons.dtos.sathub.response.SatCFE;

public class SatCFEMock {

    public SatCFE satCFE = SatCFE.builder()
            .CFe(new CFe(
                    new InfCFe(
                            "1",
                            new Ide(),
                            new EmitResponse()
                    )
            ))
            .build();
}
