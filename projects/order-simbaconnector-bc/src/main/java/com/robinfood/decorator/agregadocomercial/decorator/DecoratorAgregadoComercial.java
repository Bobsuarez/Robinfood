package com.robinfood.decorator.agregadocomercial.decorator;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.decorator.agregadocomercial.AgregadoComercialConcrete;
import com.robinfood.decorator.agregadocomercial.IAgregadoComercial;
import com.robinfood.decorator.agregadocomercial.IdPagoDecorator;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.agregadocomercial.AgregadoComercialDTO;
import com.robinfood.exceptions.ApiClientsException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;

import java.util.Arrays;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_PROCESS_BUILD_AGREGADO_COMERCIAL_SIMBA;

public class DecoratorAgregadoComercial implements IDecoratorAgregadoComercial {

    public DecoratorAgregadoComercial() {
        // Empty
    }

    @Override
    public AgregadoComercialDTO invoke(TransactionRequestDTO transactionRequestDTO) {

        try {

            AgregadoComercialDTO agregadoComercialDTO = new AgregadoComercialDTO();

            IAgregadoComercial agregadoComercialConcrete = new AgregadoComercialConcrete(transactionRequestDTO);
            IAgregadoComercial idPagoDecorator = new IdPagoDecorator(agregadoComercialConcrete);
            idPagoDecorator.decorarAgregadoComercial(agregadoComercialDTO);

            return agregadoComercialDTO;

        } catch (Exception e) {

            LogsUtil.error(
                    ERROR_APP_EXCEPTION.getMessageWithCode(), e.toString(), Arrays.toString(e.getStackTrace()),
                    ObjectMapperSingleton.objectToJson(e.getMessage())
            );

            throw new ApiClientsException(
                    ResponseMapper
                            .buildWithError(HttpStatusConstants.SC_PRECONDITION_FAILED.getCodeHttp(),
                                    ERROR_PROCESS_BUILD_AGREGADO_COMERCIAL_SIMBA
                                            .replaceComplement(e.getMessage()),
                                    DEFAULT_BOOLEAN_TRUE),
                    ERROR_PROCESS_BUILD_AGREGADO_COMERCIAL_SIMBA
                            .replaceComplement(e.getMessage()));
        }
    }
}
