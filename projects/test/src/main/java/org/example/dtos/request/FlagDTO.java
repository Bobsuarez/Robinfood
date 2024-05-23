package org.example.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class FlagDTO {

    private CorporateDTO corporate;

    private PitsDTO pits;

    private SubmarineDTO submarine;

    private TogoDTO togo;
}
