package com.robinfood.usecases.changeorderstatus;

import com.robinfood.dtos.request.RequestChangeOrderStatusDTO;
import com.robinfood.repository.changeorderstatus.ChangeOrderStatusRepository;
import com.robinfood.repository.changeorderstatus.IChangeOrderStatusRepository;

public class ChangeOrderStatusUseCase implements IChangeOrderStatusUseCase {

    private final IChangeOrderStatusRepository changeOrderStatusRepository;

    public ChangeOrderStatusUseCase() {
        this.changeOrderStatusRepository = new ChangeOrderStatusRepository();
    }

    public ChangeOrderStatusUseCase(IChangeOrderStatusRepository changeOrderStatusRepository) {
        this.changeOrderStatusRepository = changeOrderStatusRepository;
    }

    @Override
    public void invoke(
            RequestChangeOrderStatusDTO requestChangeOrderStatusDTO,
            String urlStackChannelOu,
            String token
    ) {
        changeOrderStatusRepository
                .changeOrderStatus(requestChangeOrderStatusDTO, urlStackChannelOu, token);
    }
}
