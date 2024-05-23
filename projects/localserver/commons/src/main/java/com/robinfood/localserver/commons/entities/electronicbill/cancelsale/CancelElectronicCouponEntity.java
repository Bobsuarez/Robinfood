package com.robinfood.localserver.commons.entities.electronicbill.cancelsale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CancelElectronicCouponEntity {

    private InfCfeCancelEntity infCFe;

}
