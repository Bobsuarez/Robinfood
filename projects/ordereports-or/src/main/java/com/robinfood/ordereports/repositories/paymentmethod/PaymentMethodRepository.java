package com.robinfood.ordereports.repositories.paymentmethod;

import com.robinfood.app.library.comunication.ClientRetroFit;
import com.robinfood.app.library.dto.Result;
import com.robinfood.app.library.enums.EMessageStandard;
import com.robinfood.app.library.exception.app.NetworkConnectionException;
import com.robinfood.ordereports.dtos.orders.PaymentDetailDTO;
import com.robinfood.ordereports.entities.ApiResponseEntity;
import com.robinfood.ordereports.exception.PaymentMethodBcException;
import com.robinfood.ordereports.network.api.PaymentMethodBcAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import static com.robinfood.ordereports.enums.OrderDetailLogEnum.ERROR_GET_SERVICES_REQUEST;
import static com.robinfood.ordereports.enums.OrderDetailLogEnum.GETTING_PAYMENT_METHOD_REQUEST;

@Slf4j
@Repository
public class PaymentMethodRepository implements IPaymentMethodRepository{

    private final PaymentMethodBcAPI paymentMethodBcAPI;

    public PaymentMethodRepository(PaymentMethodBcAPI paymentMethodBcAPI) {
        this.paymentMethodBcAPI = paymentMethodBcAPI;
    }

    @Override
    public PaymentDetailDTO getPaymentMethod(String token, String transactionUuid) {
        try{
            PaymentDetailDTO paymentDetailDTO =
                    ((Result.Success<ApiResponseEntity<PaymentDetailDTO>>)  ClientRetroFit.safeAPICall(
                    paymentMethodBcAPI.getPaymentMethod(transactionUuid, token)
            )).getData().getData();

            log.info(GETTING_PAYMENT_METHOD_REQUEST.getMessage(), transactionUuid, paymentDetailDTO.getId());

            return paymentDetailDTO;
        } catch (NetworkConnectionException e){
            log.info(ERROR_GET_SERVICES_REQUEST.getMessage(), transactionUuid, e);
            if(e.getCode() != HttpStatus.NOT_FOUND.value()){
                throw new PaymentMethodBcException(e, EMessageStandard.ERROR_INTERNAL_SERVER);
            }
        }
        return PaymentDetailDTO.builder().build();
    }
}
