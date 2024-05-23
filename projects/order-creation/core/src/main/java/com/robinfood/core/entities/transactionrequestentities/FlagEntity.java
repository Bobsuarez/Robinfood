package com.robinfood.core.entities.transactionrequestentities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlagEntity {

    private  CorporateEntity corporate;

    private  PitsEntity pits;

    private  SubmarineEntity submarine;

    private  TogoEntity togo;
}
