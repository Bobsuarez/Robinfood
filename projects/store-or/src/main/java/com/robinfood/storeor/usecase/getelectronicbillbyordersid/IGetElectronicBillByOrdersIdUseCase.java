package com.robinfood.storeor.usecase.getelectronicbillbyordersid;

import com.robinfood.storeor.dtos.electronicbilling.ElectronicBillDTO;
import java.util.List;

public interface IGetElectronicBillByOrdersIdUseCase {

    List <ElectronicBillDTO>  invoke(List<Long> ordersId);
}
