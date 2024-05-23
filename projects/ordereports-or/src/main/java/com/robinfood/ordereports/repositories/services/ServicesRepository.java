package com.robinfood.ordereports.repositories.services;

import com.robinfood.app.library.comunication.ClientRetroFit;
import com.robinfood.app.library.dto.Result;
import com.robinfood.app.library.enums.EMessageStandard;
import com.robinfood.app.library.exception.app.NetworkConnectionException;
import com.robinfood.ordereports.dtos.orders.DeliveryCourierServiceDTO;
import com.robinfood.ordereports.entities.ApiResponseEntity;
import com.robinfood.ordereports.exception.ServicesBcException;
import com.robinfood.ordereports.network.api.ServicesBcAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import static com.robinfood.ordereports.enums.OrderDetailLogEnum.ERROR_GET_SERVICES_REQUEST;
import static com.robinfood.ordereports.enums.OrderDetailLogEnum.GETTING_SERVICES_REQUEST;


@Slf4j
@Repository
public class ServicesRepository implements IServicesRepository{

    private final ServicesBcAPI servicesBcAPI;

    public ServicesRepository(ServicesBcAPI servicesBcAPI) {
        this.servicesBcAPI = servicesBcAPI;
    }

    @Override
    public DeliveryCourierServiceDTO getServices(String token, String transactionUuid) {
        try{
            DeliveryCourierServiceDTO deliveryCourierServiceDTO =
                    ((Result.Success<ApiResponseEntity<DeliveryCourierServiceDTO>>)  ClientRetroFit.safeAPICall(
                    servicesBcAPI.getServices(transactionUuid, token)
            )).getData().getData();

            log.info(GETTING_SERVICES_REQUEST.getMessage(), transactionUuid, deliveryCourierServiceDTO.getId());

            return deliveryCourierServiceDTO;
        } catch (NetworkConnectionException e){
            log.info(ERROR_GET_SERVICES_REQUEST.getMessage(), transactionUuid, e);
            if(e.getCode() != HttpStatus.NOT_FOUND.value()){
                throw new ServicesBcException(e, EMessageStandard.ERROR_INTERNAL_SERVER);
            }
        }

        return null;
    }
}
