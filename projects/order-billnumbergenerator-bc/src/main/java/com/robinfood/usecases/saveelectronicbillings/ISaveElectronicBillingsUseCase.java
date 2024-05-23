package com.robinfood.usecases.saveelectronicbillings;

import com.robinfood.entities.OrderElectronicBillingsEntity;

public interface ISaveElectronicBillingsUseCase {

    void invoke(OrderElectronicBillingsEntity orderElectronicBillingsEntity);

}
