package com.robinfood.app.usecases.gettaxescriteria;

import com.robinfood.app.usecases.getstoreinfo.IGetStoreInfoUseCase;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.models.domain.configuration.StoreInformation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetTaxCriteriaUseCase implements IGetTaxCriteriaUseCase {

    private final IGetStoreInfoUseCase getStoreInfoUseCase;

    public GetTaxCriteriaUseCase(IGetStoreInfoUseCase getStoreInfoUseCase) {
        this.getStoreInfoUseCase = getStoreInfoUseCase;
    }

    @Override
    public void invoke(String token, OrderDTO order) {

        StoreInformation store = getStoreInfoUseCase.invoke(token,order.getStore().getId());
        order.getTaxCriteria().put(GlobalConstants.CRITERIA_LOCATION, store.getState().getId());

    }
}
