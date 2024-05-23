package com.robinfood.decorator.lineas;

import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.dtos.request.simba.NombreDTO;
import com.robinfood.dtos.request.simba.lineas.LineasDTO;
import com.robinfood.dtos.request.simba.lineas.totalimpuestos.BaseImponibleDTO;
import com.robinfood.dtos.request.simba.lineas.totalimpuestos.CategoriaImpuestoDTO;
import com.robinfood.dtos.request.simba.lineas.totalimpuestos.SubTotalImpuestoDTO;
import com.robinfood.dtos.request.simba.lineas.totalimpuestos.TotalImpuestoDTO;
import com.robinfood.dtos.request.simba.lineas.totalimpuestos.ValorAjusteRedondeoDTO;
import com.robinfood.dtos.request.simba.lineas.totalimpuestos.ValorImpuestoDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.EsquemaTributarioDTO;
import com.robinfood.enums.TaxesEnum;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.constants.simba.SimbaGeneralConstants.CODIGO_MONEDA;

public class TotalImpuestoDecorator extends AbstractLineas {

    public TotalImpuestoDecorator(ILineas lineas) {
        super(lineas);
    }

    @Override
    public void decorarLineas(List<LineasDTO> lineasDTOs) {
        super.decorarLineas(lineasDTOs);
        buildLineaTotalImpuesto(lineasDTOs);
    }

    private void buildLineaTotalImpuesto(List<LineasDTO> lineasDTOs) {

        lineasDTOs.forEach((LineasDTO linea) ->
                linea.setTotalImpuesto(getTotalImpuestos(linea))
        );
    }

    private List<TotalImpuestoDTO> getTotalImpuestos(LineasDTO lineasDTO) {

        List<TotalImpuestoDTO> totalImpuestoDTOS = lineasDTO.getDatosAux().getFinalProductTax().stream()
                .map(finalProductTax -> {
                    TotalImpuestoDTO totalImpuestoDTO = new TotalImpuestoDTO();
                    totalImpuestoDTO.setValorImpuesto(getValorImpuesto(finalProductTax.getTaxPrice()));
                    totalImpuestoDTO.setValorAjusteRedondeo(getValorAjusteRedondeo());
                    totalImpuestoDTO.setSubTotalImpuesto(List.of(getSubTotalImpuestoDTO(lineasDTO,
                            finalProductTax.getTaxPrice())));
                    return totalImpuestoDTO;
                })
                .collect(Collectors.toList());

        if (totalImpuestoDTOS.isEmpty()) {
            totalImpuestoDTOS = null;
        }

        return totalImpuestoDTOS;
    }

    private @NonNull ValorAjusteRedondeoDTO getValorAjusteRedondeo() {
        return ValorAjusteRedondeoDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(new BigDecimal("0.00"))
                .build();
    }

    private @NonNull SubTotalImpuestoDTO getSubTotalImpuestoDTO(LineasDTO lineasDTO, BigDecimal taxPrice) {
        return SubTotalImpuestoDTO.builder()
                .baseImponible(getBaseImponible(lineasDTO))
                .valorImpuesto(getValorImpuesto(taxPrice))
                .categoriaImpuesto(getCategoriaImpuesto(lineasDTO))
                .build();
    }

    private @NonNull ValorImpuestoDTO getValorImpuesto(BigDecimal taxPrice) {

        return ValorImpuestoDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(taxPrice)
                .build();
    }

    private @NonNull BaseImponibleDTO getBaseImponible(LineasDTO lineasDTO) {

        return BaseImponibleDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(lineasDTO.getDatosAux().getFinalProductBaseImponible())
                .build();
    }

    private @NonNull CategoriaImpuestoDTO getCategoriaImpuesto(LineasDTO lineasDTO) {

        Long taxId = lineasDTO.getDatosAux().getTaxId();
        TaxesEnum taxesEnum = TaxesEnum.valueOfTaxesInDian(taxId);

        return CategoriaImpuestoDTO.builder()
                .porcentaje(lineasDTO.getDatosAux().getFinalProductPercentage())
                .esquemaTributario(EsquemaTributarioDTO.builder()
                        .id(IdDTO.builder().value(taxesEnum.getCodeDian()).build())
                        .nombre(NombreDTO.builder().value(taxesEnum.getNameDian()).build())
                        .build())
                .build();
    }

}

