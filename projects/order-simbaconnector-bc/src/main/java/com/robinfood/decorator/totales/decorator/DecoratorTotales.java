package com.robinfood.decorator.totales.decorator;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.decorator.totales.ITotales;
import com.robinfood.decorator.totales.TotalesConcrete;
import com.robinfood.decorator.totales.TotalesTotalImpuestoDecorator;
import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.lineas.LineasDTO;
import com.robinfood.dtos.request.simba.totales.TotalesDTO;
import com.robinfood.exceptions.ApiClientsException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;

import java.util.Arrays;
import java.util.List;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_PROCESS_BUILD_TOTALES_SIMBA;

public class DecoratorTotales implements IDecoratorTotales {

    public DecoratorTotales() {
        // Empty
    }

    @Override
    public TotalesDTO invoke(TransactionRequestDTO transactionRequestDTO, List<LineasDTO> lineasDTOS) {

        try {

            OrderDTO orderDTO = transactionRequestDTO.getOrders().get(DEFAULT_INTEGER);

            TotalesDTO totalesDTO = new TotalesDTO();
            ITotales totalesConcrete = new TotalesConcrete(orderDTO, lineasDTOS);
            ITotales totalImpuestoDecorador = new TotalesTotalImpuestoDecorator(totalesConcrete, lineasDTOS);
            totalImpuestoDecorador.decorarTotales(totalesDTO);

            return totalesDTO;

        } catch (Exception e) {

            LogsUtil.error(
                    ERROR_APP_EXCEPTION.getMessageWithCode(), e.toString(), Arrays.toString(e.getStackTrace()),
                    ObjectMapperSingleton.objectToJson(e.getMessage())
            );

            throw new ApiClientsException(
                    ResponseMapper
                            .buildWithError(HttpStatusConstants.SC_PRECONDITION_FAILED.getCodeHttp(),
                                    ERROR_PROCESS_BUILD_TOTALES_SIMBA
                                            .replaceComplement(e.getMessage()),
                                    DEFAULT_BOOLEAN_TRUE),
                    ERROR_PROCESS_BUILD_TOTALES_SIMBA
                            .replaceComplement(e.getMessage()));
        }
    }
}
