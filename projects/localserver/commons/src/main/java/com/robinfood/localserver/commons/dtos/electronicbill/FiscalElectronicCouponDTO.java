package com.robinfood.localserver.commons.dtos.electronicbill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class FiscalElectronicCouponDTO {

    private InfCfeDTO infCFe;

}
