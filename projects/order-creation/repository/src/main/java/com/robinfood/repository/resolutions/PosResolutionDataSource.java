package com.robinfood.repository.resolutions;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.InformationPosResolutionsResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcQueryAPI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.TIMEZONE_BY_DEVICE_DEFAULT;

@Slf4j
@Component
@AllArgsConstructor
public class PosResolutionDataSource implements IPosResolutionDataSource {

    private OrderBcQueryAPI orderBcQueryAPI;

    @Override
    public CompletableFuture<InformationPosResolutionsResponseEntity> getInformationPosResolution(
            String token, Long posId
    ) {

        log.info("Going out to get resolution with posId: {}", posId);

        final Result<ApiResponseEntity<InformationPosResolutionsResponseEntity>> result =
                NetworkExtensionsKt.safeAPICall(
                        orderBcQueryAPI.getResolutionByPos(
                                token,
                                TIMEZONE_BY_DEVICE_DEFAULT,
                                posId,
                                LocalDate.now(),
                                LocalDate.now()
                        )
                );

        if (result instanceof Result.Error) {

            final Result.Error error = ((Result.Error) result);

            throw new TransactionCreationException(
                    error.getHttpStatus(), error.getException()
                    .getLocalizedMessage(), TransactionCreationErrors.POS_RESOLUTIONS_GET_POS_ID);
        }

        return CompletableFuture.completedFuture(
                ((Result.Success<ApiResponseEntity<InformationPosResolutionsResponseEntity>>) result)
                        .getData()
                        .getData()
        );
    }
}
