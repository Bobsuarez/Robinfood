package com.robinfood.app.usecases.getposresolution;

import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.PosResolutionDTO;
import com.robinfood.core.dtos.transactionrequestdto.StoreDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.InformationPosResolutionsResponseEntity;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.util.ObjectMapperSingleton;
import com.robinfood.repository.resolutions.IPosResolutionRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class GetPosResolutionUseCase implements IGetPosResolutionUseCase {

    private final IPosResolutionRepository posResolutionRepository;

    @Override
    public void invoke(@NonNull String token, @NonNull TransactionRequestDTO transactionRequest) {

        OrderDTO orderDTO = transactionRequest.getOrders()
                .get(0);

        if (Objects.isNull(orderDTO.getStore())) {
            throw new TransactionCreationException(HttpStatus.BAD_REQUEST, "Not found store");
        }

        StoreDTO storeDTO = orderDTO.getStore();

        if (Objects.isNull(storeDTO.getPosId())) {
            throw new TransactionCreationException(HttpStatus.BAD_REQUEST, "Not found pos id");
        }

        InformationPosResolutionsResponseEntity resolutionsResponse =
                posResolutionRepository.getInformationPosResolution(token, storeDTO.getPosId());

        PosResolutionDTO posResolutionDTO = ObjectMapperSingleton
                .objectToClassConvertValue(resolutionsResponse, PosResolutionDTO.class);

        orderDTO.setPosResolution(posResolutionDTO);

        transactionRequest.setOrders(List.of(orderDTO));
    }
}
