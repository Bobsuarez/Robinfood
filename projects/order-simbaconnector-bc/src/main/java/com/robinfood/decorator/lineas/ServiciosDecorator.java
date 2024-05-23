package com.robinfood.decorator.lineas;

import com.robinfood.dtos.request.orderbill.FinalProductTaxDTO;
import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.orderbill.ServiceDTO;
import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.dtos.request.simba.lineas.CantidadDTO;
import com.robinfood.dtos.request.simba.lineas.DatosAuxDTO;
import com.robinfood.dtos.request.simba.lineas.LineasDTO;
import com.robinfood.dtos.request.simba.lineas.NotaDTO;
import com.robinfood.dtos.request.simba.lineas.ValorNetoDTO;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER_ONE;
import static com.robinfood.constants.GeneralConstants.DEFAULT_LONG_VALUE_ONE;
import static com.robinfood.constants.simba.SimbaGeneralConstants.CODIGO_MONEDA;
import static com.robinfood.constants.simba.SimbaGeneralConstants.UNIDAD_MEDIDA;

public class ServiciosDecorator extends AbstractLineas {

    private final OrderDTO orderDTO;

    public ServiciosDecorator(ILineas lineas, OrderDTO orderDTO) {
        super(lineas);
        this.orderDTO = orderDTO;
    }

    @Override
    public void decorarLineas(List<LineasDTO> lineasDTOs) {
        super.decorarLineas(lineasDTOs);
        buildLineaTotalImpuesto(lineasDTOs);
    }

    private void buildLineaTotalImpuesto(List<LineasDTO> lineasDTOs) {

        AtomicInteger id = new AtomicInteger(lineasDTOs.size());

        orderDTO.getServices().forEach((ServiceDTO service) -> {
                    LineasDTO lineasDTO = new LineasDTO();
                    DatosAuxDTO datosAuxDTO = getDatosAux(service);
                    lineasDTO.setId(setIdLinea(id.incrementAndGet()));
                    lineasDTO.setNota(setNotaLinea(service.getId(), "Domicilios"));
                    lineasDTO.setCantidad(setCantidadLinea());
                    lineasDTO.setPrecioUnitario(service.getPriceNt());
                    lineasDTO.setDatosAux(datosAuxDTO);
                    lineasDTO.setValorNeto(setValorNetoLinea(datosAuxDTO));
                    lineasDTOs.add(lineasDTO);
                }
        );
    }

    private @NonNull IdDTO setIdLinea(Integer id) {
        return IdDTO.builder()
                .value(id.toString())
                .build();
    }

    private @NonNull List<NotaDTO> setNotaLinea(Long serviceId, String serviceName) {

        return List.of(NotaDTO.builder()
                .value(serviceName)
                .build());
    }

    private @NonNull CantidadDTO setCantidadLinea() {
        return CantidadDTO.builder()
                .codUnidad(UNIDAD_MEDIDA)
                .value(new BigDecimal(DEFAULT_INTEGER_ONE + ".00"))
                .build();
    }

    private @NonNull ValorNetoDTO setValorNetoLinea(DatosAuxDTO datosAuxDTO) {

        return ValorNetoDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(datosAuxDTO.getFinalProductBaseImponible())
                .build();
    }

    private @NonNull DatosAuxDTO getDatosAux(ServiceDTO serviceDTO) {

        FinalProductTaxDTO finalProductTaxDTO = new FinalProductTaxDTO();
        finalProductTaxDTO.setTaxPrice(serviceDTO.getTaxPrice());

        return DatosAuxDTO.builder()
                .finalProductDiscount(serviceDTO.getDiscount())
                .finalProductTaxes(serviceDTO.getTaxPrice())
                .finalProductId(serviceDTO.getId())
                .finalProductTotalPrice(serviceDTO.getPriceNt())
                .finalProductPercentage(serviceDTO.getTaxPercentage())
                .taxId(DEFAULT_LONG_VALUE_ONE)
                .finalProductBaseImponible(serviceDTO.getPriceNt())
                .finalProductTax(List.of(finalProductTaxDTO))
                .precioUnitario(serviceDTO.getPriceNt())
                .build();
    }
}

