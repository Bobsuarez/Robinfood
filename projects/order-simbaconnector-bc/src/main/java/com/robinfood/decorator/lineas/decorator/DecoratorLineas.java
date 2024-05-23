package com.robinfood.decorator.lineas.decorator;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.decorator.lineas.CargoDescuentoDecorator;
import com.robinfood.decorator.lineas.ILineas;
import com.robinfood.decorator.lineas.ItemDecorator;
import com.robinfood.decorator.lineas.LineasConcrete;
import com.robinfood.decorator.lineas.PrecioDecorator;
import com.robinfood.decorator.lineas.ServiciosDecorator;
import com.robinfood.decorator.lineas.TotalImpuestoDecorator;
import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.lineas.LineasDTO;
import com.robinfood.exceptions.ApiClientsException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;

import java.util.ArrayList;
import java.util.List;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_PROCESS_BUILD_LINEAS_SIMBA;

public class DecoratorLineas implements IDecoratorLineas {

    public DecoratorLineas() {
        // Empty
    }

    @Override
    public List<LineasDTO> invoke(TransactionRequestDTO transactionRequestDTO) {

        try {
            OrderDTO orderDTO = transactionRequestDTO.getOrders().get(DEFAULT_INTEGER);

            List<LineasDTO> lineasDTOS = new ArrayList<>();

            ILineas lineasConcrete = new LineasConcrete(orderDTO);
            ILineas servicioDecoraator = new ServiciosDecorator(lineasConcrete, orderDTO);
            ILineas itemDecorator = new ItemDecorator(servicioDecoraator);
            ILineas cargoDescuentoDecorator = new CargoDescuentoDecorator(itemDecorator);
            ILineas totalImpuestoDecorador = new TotalImpuestoDecorator(cargoDescuentoDecorator);
            ILineas precioDecorator = new PrecioDecorator(totalImpuestoDecorador);

            precioDecorator.decorarLineas(lineasDTOS);

            return lineasDTOS;

        } catch (Exception e) {

            String stackTrace = LogsUtil.getStackTraceAsString(e);

            LogsUtil.error(ERROR_APP_EXCEPTION.getMessageWithCode(), e.toString(),
                    stackTrace, ObjectMapperSingleton.objectToJson(e.getMessage()));

            throw new ApiClientsException(
                    ResponseMapper
                            .buildWithError(HttpStatusConstants.SC_PRECONDITION_FAILED.getCodeHttp(),
                                    ERROR_PROCESS_BUILD_LINEAS_SIMBA
                                            .replaceComplement(ObjectMapperSingleton.objectToJson(e.getMessage())),
                                    DEFAULT_BOOLEAN_TRUE),
                    ERROR_PROCESS_BUILD_LINEAS_SIMBA
                            .replaceComplement(ObjectMapperSingleton.objectToJson(e.getMessage())));
        }
    }
}
