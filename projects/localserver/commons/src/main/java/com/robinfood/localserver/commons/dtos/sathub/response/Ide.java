package com.robinfood.localserver.commons.dtos.sathub.response;

import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlType(propOrder = {
        "cnpj",
        "signAC",
        "numeroCaixa"
})
public final class Ide {

    private String nserieSAT;

    private String CNPJ;

    private String signAC;

    private String numeroCaixa;

}
