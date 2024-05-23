package com.robinfood.decorator.encabezado;

import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.simba.encabezado.EncabezadoDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DatosEncabezadoFacturaDecorator extends AbstractEncabezado {

    private final OrderDTO orderDTO;

    public DatosEncabezadoFacturaDecorator(IEncabezado encabezado, OrderDTO orderDTO) {
        super(encabezado);
        this.orderDTO = orderDTO;
    }

    @Override
    public void decorarEncabezado(EncabezadoDTO encabezadoDTO) {
        super.decorarEncabezado(encabezadoDTO);
        buildDatosEncabezadoFacturaDecorator(encabezadoDTO);
    }

    private void buildDatosEncabezadoFacturaDecorator(EncabezadoDTO encabezadoDTO) {
        encabezadoDTO.setPrefijoDocumento(orderDTO.getPosResolution().getPrefix().toString());
        encabezadoDTO.setNumeroDocumento(orderDTO.getInvoiceNumber());
        encabezadoDTO.setCantidadLineas(new BigDecimal(orderDTO.getFinalProducts().size() +
                orderDTO.getServices().size()));
    }
}

