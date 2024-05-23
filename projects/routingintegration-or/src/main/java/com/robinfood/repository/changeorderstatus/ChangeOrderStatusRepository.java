package com.robinfood.repository.changeorderstatus;

import com.robinfood.dtos.request.RequestChangeOrderStatusDTO;
import com.robinfood.network.api.ChangeOrderStatusBcApi;

public class ChangeOrderStatusRepository implements IChangeOrderStatusRepository {

    private final ChangeOrderStatusBcApi changeOrderStatusBcApi;

    public ChangeOrderStatusRepository() {
        this.changeOrderStatusBcApi = new ChangeOrderStatusBcApi();
    }

    public ChangeOrderStatusRepository(ChangeOrderStatusBcApi changeOrderStatusBcApi) {
        this.changeOrderStatusBcApi = changeOrderStatusBcApi;
    }

    @Override
    public void changeOrderStatus(
            RequestChangeOrderStatusDTO requestChangeOrderStatusDTO,
            String urlStackChannelOu,
            String token
    ) {
        changeOrderStatusBcApi.changeOrderStatus(
                requestChangeOrderStatusDTO,
                token,
                urlStackChannelOu
        );
    }
}
