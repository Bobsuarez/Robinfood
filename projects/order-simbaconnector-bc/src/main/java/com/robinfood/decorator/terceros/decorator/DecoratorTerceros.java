package com.robinfood.decorator.terceros.decorator;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.decorator.terceros.EntidadLegalTerceroClienteDecorator;
import com.robinfood.decorator.terceros.EntidadLegalTerceroProveedorDecorator;
import com.robinfood.decorator.terceros.EsquemaTributarioTerceroClienteDecorator;
import com.robinfood.decorator.terceros.EsquemaTributarioTerceroProveedorDecorator;
import com.robinfood.decorator.terceros.EsquemaTributarioTerceroProveedorDireccionDecorator;
import com.robinfood.decorator.terceros.ITerceros;
import com.robinfood.decorator.terceros.TerceroClienteDecorator;
import com.robinfood.decorator.terceros.TerceroProveedorConcrete;
import com.robinfood.decorator.terceros.TerceroProveedorContableFacturaDecorator;
import com.robinfood.decorator.terceros.UbicacionFisicaTerceroProveedorDecorator;
import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.terceros.TercerosDTO;
import com.robinfood.exceptions.ApiClientsException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;

import java.util.Arrays;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_PROCESS_BUILD_TERCEROS_SIMBA;

public class DecoratorTerceros implements IDecoratorTerceros {

    public DecoratorTerceros() {
        // Emptty

    }

    @Override
    public TercerosDTO invoke(TransactionRequestDTO transactionRequestDTO) {

        try {

            OrderDTO orderDTO = transactionRequestDTO.getOrders().get(DEFAULT_INTEGER);

            TercerosDTO tercerosDTO = new TercerosDTO();

            ITerceros tercerosConcrete = new TerceroProveedorConcrete();
            ITerceros tercerosProveedorContableFactura = new TerceroProveedorContableFacturaDecorator(tercerosConcrete);
            ITerceros ubicacionFisicaTerceroProveedor = new UbicacionFisicaTerceroProveedorDecorator(
                    tercerosProveedorContableFactura, transactionRequestDTO);
            ITerceros esquemaTributarioTerceroProveedor = new EsquemaTributarioTerceroProveedorDecorator(
                    ubicacionFisicaTerceroProveedor);
            ITerceros esquemaTributarioTerceroProveedorDireccionImpuestosDecorator =
                    new EsquemaTributarioTerceroProveedorDireccionDecorator(esquemaTributarioTerceroProveedor);
            ITerceros entidadLegalTerceroProveedorDecorator = new EntidadLegalTerceroProveedorDecorator(
                    esquemaTributarioTerceroProveedorDireccionImpuestosDecorator,
                    transactionRequestDTO);

            ITerceros tercerosClienteDecorator = new TerceroClienteDecorator(entidadLegalTerceroProveedorDecorator,
                    orderDTO);
            ITerceros esquemaTributarioTerceroClienteDecorator = new EsquemaTributarioTerceroClienteDecorator(
                    tercerosClienteDecorator, orderDTO);
            ITerceros entidadLegalTerceroClienterDecorator = new EntidadLegalTerceroClienteDecorator(
                    esquemaTributarioTerceroClienteDecorator, orderDTO);


            entidadLegalTerceroClienterDecorator.decorarTerceroProveedor(tercerosDTO);

            return tercerosDTO;

        } catch (Exception e) {

            LogsUtil.error(
                    ERROR_APP_EXCEPTION.getMessageWithCode(), e.toString(), Arrays.toString(e.getStackTrace()),
                    ObjectMapperSingleton.objectToJson(e.getMessage())
            );

            throw new ApiClientsException(
                    ResponseMapper
                            .buildWithError(HttpStatusConstants.SC_PRECONDITION_FAILED.getCodeHttp(),
                                    ERROR_PROCESS_BUILD_TERCEROS_SIMBA
                                            .replaceComplement(e.getMessage()),
                                    DEFAULT_BOOLEAN_TRUE),
                    ERROR_PROCESS_BUILD_TERCEROS_SIMBA
                            .replaceComplement(e.getMessage()));
        }
    }
}
