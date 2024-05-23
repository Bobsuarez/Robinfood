package com.robinfood.app.usecases.getordercategory;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.ordercategories.DataRequestOrderCategoryDTO;
import com.robinfood.core.dtos.ordercategories.OrderCategoryDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.ordercategory.IOrderCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrderCategoryUseCase implements IGetOrderCategoryUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IOrderCategoryRepository orderCategoryRepository;

    public GetOrderCategoryUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IOrderCategoryRepository iOrderCategoryRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.orderCategoryRepository = iOrderCategoryRepository;
    }

    @Override
    public Result<List<OrderCategoryDTO>> invoke(DataRequestOrderCategoryDTO dataRequestDTO) {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();
        return orderCategoryRepository.getOrderListCategories(dataRequestDTO, token.getAccessToken());
    }

}
