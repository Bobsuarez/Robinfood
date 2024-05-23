package com.robinfood.repository.paymentmethods;

import static com.robinfood.core.constants.GlobalConstants.HYPHEN;
import static com.robinfood.core.constants.GlobalConstants.UNDERSCORE;

import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.PaymentMethodsEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.core.mappers.PaymentFilterDTOMappers;
import com.robinfood.network.api.OrderBcQueriesAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class PaymentMethodsRepository implements IPaymentMethodsRepository {

    private final OrderBcQueriesAPI orderBcQueriesAPI;

    public PaymentMethodsRepository(OrderBcQueriesAPI orderBcQueriesAPI) {
        this.orderBcQueriesAPI = orderBcQueriesAPI;
    }

    @Override
    public Result<List<PaymentMethodsFilterDTO>> getDataPaymentMethods(String token) {

        log.info("Invoke get rest order-bc-query to consult a list of payment methods");

        final Result<APIResponseEntity<List<PaymentMethodsEntity>>> result = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getPaymentMethodsList(
                        token
                )
        );

        if (result instanceof Result.Error) {

            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<List<PaymentMethodsEntity>>> data =
                ((Result.Success<APIResponseEntity<List<PaymentMethodsEntity>>>) result);

        List<PaymentMethodsEntity> paymentMethodsEntityList = data.getData().getData();
        List<PaymentMethodsFilterDTO> paymentMethodsFilterDTOS = paymentMethodsEntityList
                .stream().map(paymentMethodsEntity -> PaymentFilterDTOMappers
                        .toPaymentMethodDTO(
                                paymentMethodsEntity.getId(),
                                paymentMethodsEntity.getName().toLowerCase(Locale.ROOT)
                                        .replaceAll("\\s+", UNDERSCORE)
                                        .replaceAll(HYPHEN, UNDERSCORE)
                        )
                ).collect(Collectors.toList());

        return new Result.Success(
                paymentMethodsFilterDTOS
        );
    }
}
