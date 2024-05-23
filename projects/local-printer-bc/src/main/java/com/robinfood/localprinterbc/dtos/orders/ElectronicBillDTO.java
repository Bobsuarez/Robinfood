package com.robinfood.localprinterbc.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ElectronicBillDTO {

    private OrderElectronicBillingDTO orderElectronicBilling;

    private OrderThirdPartyDTO orderThirdParty;
}
