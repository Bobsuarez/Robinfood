package com.robinfood.decorator.extensiones.decorator;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.decorator.extensiones.BeneficiosCompradorDecorador;
import com.robinfood.decorator.extensiones.ExtensionesConcrete;
import com.robinfood.decorator.extensiones.IExtensiones;
import com.robinfood.decorator.extensiones.PuntoVentaDecorator;
import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.extensiones.ExtensionesDTO;
import com.robinfood.exceptions.ApiClientsException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_PROCESS_BUILD_EXTENSIONES_SIMBA;

public class DecoratorExtensiones implements IDecoratorExtensiones {

    public DecoratorExtensiones() {
        // Empty
    }

    @Override
    public List<ExtensionesDTO> invoke(TransactionRequestDTO transactionRequestDTO) {

        try {

            List<ExtensionesDTO> extensionesDTOList = new ArrayList<>();
            OrderDTO orderDTO = transactionRequestDTO.getOrders().get(DEFAULT_INTEGER);

            IExtensiones extensionesConcrete = new ExtensionesConcrete();
            IExtensiones beneficiosCompradorDecorator = new BeneficiosCompradorDecorador(extensionesConcrete, orderDTO);
            IExtensiones puntoVentaDecorator = new PuntoVentaDecorator(beneficiosCompradorDecorator);

            puntoVentaDecorator.decorarExtensiones(extensionesDTOList);

            return extensionesDTOList;
        } catch (Exception e) {

            LogsUtil.error(
                    ERROR_APP_EXCEPTION.getMessageWithCode(), e.toString(), Arrays.toString(e.getStackTrace()),
                    ObjectMapperSingleton.objectToJson(e.getMessage())
            );

            throw new ApiClientsException(
                    ResponseMapper
                            .buildWithError(HttpStatusConstants.SC_PRECONDITION_FAILED.getCodeHttp(),
                                    ERROR_PROCESS_BUILD_EXTENSIONES_SIMBA
                                            .replaceComplement(e.getMessage()),
                                    DEFAULT_BOOLEAN_TRUE),
                    ERROR_PROCESS_BUILD_EXTENSIONES_SIMBA
                            .replaceComplement(e.getMessage()));
        }
    }
}
