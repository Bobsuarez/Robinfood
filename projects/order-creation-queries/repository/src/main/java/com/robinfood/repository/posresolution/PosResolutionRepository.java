package com.robinfood.repository.posresolution;

import com.robinfood.core.dtos.posresolution.DataPosResolutionRequestDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcQueriesAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class PosResolutionRepository implements IPosResolutionRepository {

    private final OrderBcQueriesAPI orderBcQueriesAPI;

    public PosResolutionRepository(OrderBcQueriesAPI orderBcQueriesAPI) {
        this.orderBcQueriesAPI = orderBcQueriesAPI;
    }

    @Override
    public Result<GetPosResolutionsDTO> getDataPosResolution(
            DataPosResolutionRequestDTO dataPosResolutionRequestDTO,
            String token
    ) {

        log.info("Invoke get rest order-bc {}", dataPosResolutionRequestDTO);

        final Result<APIResponseEntity<GetPosResolutionsDTO>> result = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getPosResolutionsSequence(
                        dataPosResolutionRequestDTO.getPosId(),
                        dataPosResolutionRequestDTO.getLocalDateEnd(),
                        dataPosResolutionRequestDTO.getLocalDateStart(),
                        dataPosResolutionRequestDTO.getTimeZone(),
                        token
                )
        );

        if (result instanceof Result.Error) {

            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<GetPosResolutionsDTO>> data =
                ((Result.Success<APIResponseEntity<GetPosResolutionsDTO>>) result);

        return new Result.Success(
                data.getData().getData()
        );

    }

    @Override
    public Result<List<GetPosResolutionsDTO>> getDataPosResolutionByStore(
            DataPosResolutionRequestDTO dataPosResolutionRequestDTO,
            String token
    ) {

        log.info("Invoke get rest order-bc {}", dataPosResolutionRequestDTO);

        final Result<APIResponseEntity<List<GetPosResolutionsDTO>>> result = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getPosResolutionsSequenceByStore(
                        dataPosResolutionRequestDTO.getStoreId(),
                        dataPosResolutionRequestDTO.getLocalDateEnd(),
                        dataPosResolutionRequestDTO.getLocalDateStart(),
                        dataPosResolutionRequestDTO.getTimeZone(),
                        token
                )
        );

        if (result instanceof Result.Error) {

            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<List<GetPosResolutionsDTO>>> data =
                ((Result.Success<APIResponseEntity<List<GetPosResolutionsDTO>>>) result);

        return new Result.Success(
                data.getData().getData()
        );
    }

}
