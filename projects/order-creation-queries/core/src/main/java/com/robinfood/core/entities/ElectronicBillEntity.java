package com.robinfood.core.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ElectronicBillEntity {

    private OrderThirdPartyEntity orderThirdParty;
    private DataElectronicBillingEntity orderElectronicBilling;
}
