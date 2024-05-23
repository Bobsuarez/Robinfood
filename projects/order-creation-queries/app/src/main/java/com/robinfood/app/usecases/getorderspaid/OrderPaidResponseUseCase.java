package com.robinfood.app.usecases.getorderspaid;

import com.robinfood.core.dtos.configuration.BrandsDTO;
import com.robinfood.core.dtos.configuration.ChannelsDTO;
import com.robinfood.core.dtos.configuration.StoreDTO;
import com.robinfood.core.dtos.orderspaid.GetOrdersPaidDTO;
import com.robinfood.core.dtos.orderspaid.OrderPaidDTO;
import com.robinfood.core.dtos.orderspaid.OrderPaidResponseDTO;
import com.robinfood.core.dtos.orderspaid.OrdersPaidResponseDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.mappers.OrdersPaidResponseMappers;
import com.robinfood.repository.configuration.brands.IBrandsRepository;
import com.robinfood.repository.configuration.channels.IChannelRepository;
import com.robinfood.repository.configuration.store.IStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderPaidResponseUseCase  implements IOrderPaidResponseUseCase{

    private final IBrandsRepository brandsRepository;

    private final IChannelRepository channelRepository;

    private final IStoreRepository storeRepository;

    public OrderPaidResponseUseCase (
            IBrandsRepository brandsRepository,
            IChannelRepository channelRepository,
            IStoreRepository storeRepository
    ){
        this.brandsRepository = brandsRepository;
        this.channelRepository = channelRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public OrdersPaidResponseDTO invoke(GetOrdersPaidDTO getOrdersPaidDTO, String token) {

        List<OrderPaidResponseDTO> listOrderPaidResponseDTO = new ArrayList<>();

        BrandsDTO brandsDTOResult = ((Result.Success<BrandsDTO>) brandsRepository.getAll(token)).getData();

        ChannelsDTO channelsDTOResult = ((Result.Success<ChannelsDTO>)channelRepository.getChannels(token)).getData();

        for (OrderPaidDTO orderPaidDTO : getOrdersPaidDTO.getItems()) {

            StoreDTO storeDTOResult = ((Result.Success<StoreDTO>) storeRepository
                    .getStore(orderPaidDTO.getStoreId(), token)).getData();

            listOrderPaidResponseDTO.add(OrdersPaidResponseMappers.orderPaidDTOToOrderPaidResponseDTO(
                            brandsDTOResult,
                            channelsDTOResult,
                            storeDTOResult,
                            orderPaidDTO
                    )
            );
        }

        return OrdersPaidResponseDTO.builder()
                .items(listOrderPaidResponseDTO)
                .pagination(getOrdersPaidDTO.getPagination())
                .build();

    }
}
