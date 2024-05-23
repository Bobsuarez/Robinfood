package com.robinfood.app.usecases.getorderspaid;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.orderspaid.DataOrdersPaidRequestDTO;
import com.robinfood.core.dtos.orderspaid.GetOrdersPaidDTO;
import com.robinfood.core.dtos.orderspaid.OrdersPaidResponseDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.orderspaid.IOrdersPaidRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetOrdersPaidUseCase implements IGetOrdersPaidUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final IOrderPaidResponseUseCase orderPaidResponseUseCase;

    private final IOrdersPaidRepository ordersPaidRepository;

    public GetOrdersPaidUseCase (
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IOrderPaidResponseUseCase orderPaidResponseUseCase,
            IOrdersPaidRepository ordersPaidRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.orderPaidResponseUseCase = orderPaidResponseUseCase;
        this.ordersPaidRepository = ordersPaidRepository;
    }

    @Override
    public Result<OrdersPaidResponseDTO> invoke(DataOrdersPaidRequestDTO dataOrdersPaidRequestDTO) {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        Result<GetOrdersPaidDTO>  getOrdersPaidDTOResult = ordersPaidRepository.getDataOrdersPaid(
                dataOrdersPaidRequestDTO,
                token.getAccessToken()
        );

        GetOrdersPaidDTO getOrdersPaidDTO = ((Result.Success<GetOrdersPaidDTO>) getOrdersPaidDTOResult)
                .getData();

        return new Result.Success<>(
                this.orderPaidResponseUseCase.invoke(getOrdersPaidDTO, token.getAccessToken())
        ) ;
    }
}
