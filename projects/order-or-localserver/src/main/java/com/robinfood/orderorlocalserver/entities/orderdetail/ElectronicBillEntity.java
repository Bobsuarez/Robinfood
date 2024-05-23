package com.robinfood.orderorlocalserver.entities.orderdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElectronicBillEntity {

    private DataElectronicBillingEntity orderElectronicBilling;

    private OrderThirdPartyEntity orderThirdParty;
}
