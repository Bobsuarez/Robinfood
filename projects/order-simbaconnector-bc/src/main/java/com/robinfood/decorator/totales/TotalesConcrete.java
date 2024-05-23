package com.robinfood.decorator.totales;

import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.simba.lineas.LineasDTO;
import com.robinfood.dtos.request.simba.lineas.totalimpuestos.ValorAjusteRedondeoDTO;
import com.robinfood.dtos.request.simba.totales.TotalCargosDTO;
import com.robinfood.dtos.request.simba.totales.TotalMasImpuestosDTO;
import com.robinfood.dtos.request.simba.totales.TotalMonetarioDTO;
import com.robinfood.dtos.request.simba.totales.TotalesDTO;
import com.robinfood.dtos.request.simba.totales.ValorAPagarAlternativoDTO;
import com.robinfood.dtos.request.simba.totales.ValorAPagarDTO;
import com.robinfood.dtos.request.simba.totales.ValorBaseImpuestosDTO;
import com.robinfood.dtos.request.simba.totales.ValorBrutoDTO;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.constants.simba.SimbaGeneralConstants.CODIGO_MONEDA;

public class TotalesConcrete implements ITotales {

    private final OrderDTO orderDTO;
    private final List<LineasDTO> lineasDTOS;

    public TotalesConcrete(OrderDTO orderDTO, List<LineasDTO> lineasDTOS) {
        this.orderDTO = orderDTO;
        this.lineasDTOS = lineasDTOS;
    }

    @Override
    public void decorarTotales(TotalesDTO totalesDTO) {
        adicionarPropiedadTotalesPorDefecto(totalesDTO);
    }

    private void adicionarPropiedadTotalesPorDefecto(TotalesDTO totalesDTO) {
        TotalMonetarioDTO totalMonetarioDTO = new TotalMonetarioDTO();

        AtomicReference<BigDecimal> valorBruto = new AtomicReference<>(BigDecimal.ZERO);
        AtomicReference<BigDecimal> valorBaseImpuestosFinal = new AtomicReference<>(BigDecimal.ZERO);
        AtomicReference<BigDecimal> totalMasImpuestosFinal = new AtomicReference<>(BigDecimal.ZERO);

        lineasDTOS.forEach((LineasDTO linea) -> {
            BigDecimal totalPrice = linea.getDatosAux().getFinalProductTotalPrice();
            BigDecimal baseImponible = linea.getDatosAux().getFinalProductBaseImponible();

            valorBruto.updateAndGet(oldValue -> oldValue.add(baseImponible));
            valorBaseImpuestosFinal.updateAndGet(oldValue -> oldValue.add(baseImponible));
            totalMasImpuestosFinal.updateAndGet(oldValue -> oldValue.add(totalPrice));
        });

        totalMonetarioDTO.setValorBruto(getValorBruto(valorBruto.get()));
        totalMonetarioDTO.setValorBaseImpuestos(getValorBaseImpuestos(valorBaseImpuestosFinal.get()));
        totalMonetarioDTO.setTotalMasImpuestos(getTotalMasImpuestos(orderDTO.getTotal()));
        totalMonetarioDTO.setValorAPagar(getValorAPagar(orderDTO.getTotal()));
        totalMonetarioDTO.setTotalCargos(getTotalCargos(new BigDecimal("0.00")));
        totalMonetarioDTO.setValorAjusteRedondeo(getValorAjusteRedondeo(new BigDecimal("0.00")));
        totalMonetarioDTO.setValorAPagarAlternativo(getValorAPagarAlternativo(orderDTO.getTotal()));
        totalesDTO.setTotalMonetario(totalMonetarioDTO);
    }

    private @NonNull ValorAjusteRedondeoDTO getValorAjusteRedondeo(BigDecimal valorBruto) {
        return ValorAjusteRedondeoDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(valorBruto)
                .build();
    }

    private @NonNull TotalCargosDTO getTotalCargos(BigDecimal valorBruto) {
        return TotalCargosDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(valorBruto)
                .build();
    }

    private @NonNull ValorBrutoDTO getValorBruto(BigDecimal valorBruto) {
        return ValorBrutoDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(valorBruto)
                .build();
    }

    private @NonNull ValorBaseImpuestosDTO getValorBaseImpuestos(BigDecimal valorBaseImpuestosFinal) {
        return ValorBaseImpuestosDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(valorBaseImpuestosFinal)
                .build();
    }

    private @NonNull TotalMasImpuestosDTO getTotalMasImpuestos(BigDecimal totalMasImpuestos) {
        return TotalMasImpuestosDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(totalMasImpuestos)
                .build();
    }

    private @NonNull ValorAPagarDTO getValorAPagar(BigDecimal valorAPagar) {
        return ValorAPagarDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(valorAPagar)
                .build();
    }

    private @NonNull ValorAPagarAlternativoDTO getValorAPagarAlternativo(BigDecimal valorAPagar) {
        return ValorAPagarAlternativoDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(valorAPagar)
                .build();
    }
}
