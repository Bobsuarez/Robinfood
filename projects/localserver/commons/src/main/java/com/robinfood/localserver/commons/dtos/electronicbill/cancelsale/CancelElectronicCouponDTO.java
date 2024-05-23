package com.robinfood.localserver.commons.dtos.electronicbill.cancelsale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CancelElectronicCouponDTO {

    private InfCfeCancelDTO infCFe;

}
