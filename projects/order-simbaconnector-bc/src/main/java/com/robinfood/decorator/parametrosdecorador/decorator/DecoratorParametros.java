package com.robinfood.decorator.parametrosdecorador.decorator;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.decorator.parametrosdecorador.ContactoReceptorDecorator;
import com.robinfood.decorator.parametrosdecorador.IParametros;
import com.robinfood.decorator.parametrosdecorador.IndicadoresAdicionalesDecorator;
import com.robinfood.decorator.parametrosdecorador.ParametrosConcrete;
import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.parametros.ParametrosDTO;
import com.robinfood.exceptions.ApiClientsException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;

import java.util.Arrays;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_PROCESS_BUILD_PARAMETROS_SIMBA;

public class DecoratorParametros implements IDecoratorParametros {

    public DecoratorParametros() {
        // Empy
    }

    @Override
    public ParametrosDTO invoke(TransactionRequestDTO transactionRequestDTO) {

        try {

            OrderDTO orderDTO = transactionRequestDTO.getOrders().get(DEFAULT_INTEGER);

            ParametrosDTO parametrosDTO = new ParametrosDTO();

            IParametros parametrosConcrete = new ParametrosConcrete();
            IParametros contactoRecepctorDecorator = new ContactoReceptorDecorator(parametrosConcrete, orderDTO);
            IParametros indicadoresAdicionales = new IndicadoresAdicionalesDecorator(contactoRecepctorDecorator);
            indicadoresAdicionales.decorarParametros(parametrosDTO);

            return parametrosDTO;

        } catch (Exception e) {

            LogsUtil.error(
                    ERROR_APP_EXCEPTION.getMessageWithCode(), e.toString(), Arrays.toString(e.getStackTrace()),
                    ObjectMapperSingleton.objectToJson(e.getMessage())
            );

            throw new ApiClientsException(
                    ResponseMapper
                            .buildWithError(HttpStatusConstants.SC_PRECONDITION_FAILED.getCodeHttp(),
                                    ERROR_PROCESS_BUILD_PARAMETROS_SIMBA
                                            .replaceComplement(e.getMessage()),
                                    DEFAULT_BOOLEAN_TRUE),
                    ERROR_PROCESS_BUILD_PARAMETROS_SIMBA
                            .replaceComplement(e.getMessage()));
        }
    }
}
