package com.robinfood.decorator.encabezado.decorator;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.decorator.encabezado.DatosEncabezadoFacturaDecorator;
import com.robinfood.decorator.encabezado.EncabezadoConcrete;
import com.robinfood.decorator.encabezado.IEncabezado;
import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.encabezado.EncabezadoDTO;
import com.robinfood.exceptions.ApiClientsException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;

import java.util.Arrays;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_PROCESS_BUILD_ENCABEZADO_SIMBA;

public class DecoratorEncabezado implements IDecoratorEncabezado {

    public DecoratorEncabezado() {
        // Empty
    }

    @Override
    public EncabezadoDTO invoke(TransactionRequestDTO transactionRequestDTO) {

        try {

            OrderDTO orderDTO = transactionRequestDTO.getOrders().get(DEFAULT_INTEGER);

            EncabezadoDTO encabezadoDTO = new EncabezadoDTO();

            IEncabezado encabezadoConcrete = new EncabezadoConcrete();
            IEncabezado datosEncabezadoFactur = new DatosEncabezadoFacturaDecorator(encabezadoConcrete, orderDTO);

            datosEncabezadoFactur.decorarEncabezado(encabezadoDTO);

            return encabezadoDTO;

        } catch (Exception e) {

            LogsUtil.error(
                    ERROR_APP_EXCEPTION.getMessageWithCode(), e.toString(), Arrays.toString(e.getStackTrace()),
                    ObjectMapperSingleton.objectToJson(e.getMessage())
            );

            throw new ApiClientsException(
                    ResponseMapper
                            .buildWithError(HttpStatusConstants.SC_PRECONDITION_FAILED.getCodeHttp(),
                                    ERROR_PROCESS_BUILD_ENCABEZADO_SIMBA
                                            .replaceComplement(e.getMessage()),
                                    DEFAULT_BOOLEAN_TRUE),
                    ERROR_PROCESS_BUILD_ENCABEZADO_SIMBA
                            .replaceComplement(e.getMessage()));
        }
    }
}
