package com.robinfood.decorator.totales;

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
import com.robinfood.dtos.request.simba.totales.TotalesDTO;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.constants.simba.SimbaGeneralConstants.CODIGO_MONEDA;

public class TotalesTotalImpuestoDecorator extends AbstractTotales {

    private final List<LineasDTO> lineasDTOS;

    public TotalesTotalImpuestoDecorator(ITotales totales, List<LineasDTO> lineasDTOS) {
        super(totales);
        this.lineasDTOS = lineasDTOS;
    }

    @Override
    public void decorarTotales(TotalesDTO totalesDTO) {
        super.decorarTotales(totalesDTO);
        buildTotalesTotalImpuesto(totalesDTO);
    }

    private void buildTotalesTotalImpuesto(TotalesDTO totalesDTO) {
        List<String> loCodTax = new ArrayList<>();

        lineasDTOS.stream()
                .filter(linea -> linea.getTotalImpuesto() != null)
                .map(linea -> linea.getTotalImpuesto().get(DEFAULT_INTEGER))
                .map(totalImpuesto -> totalImpuesto.getSubTotalImpuesto().get(DEFAULT_INTEGER))
                .map(subTotalImpuesto -> subTotalImpuesto.getCategoriaImpuesto()
                        .getEsquemaTributario().getId().getValue())
                .forEach(loCodTax::add);

        Set<String> uniqueCodTaxSet = new HashSet<>(loCodTax);
        List<String> uniqueCodTaxList = new ArrayList<>(uniqueCodTaxSet);

        List<TotalImpuestoDTO> totalImpuestoDTOS = new ArrayList<>();
        for (String code : uniqueCodTaxList) {
            totalImpuestoDTOS.add(getTotalImpuestos(code));
        }

        if (totalImpuestoDTOS.isEmpty()) {
            totalImpuestoDTOS = null;
        }

        totalesDTO.setTotalImpuestos(totalImpuestoDTOS);
    }

    private void procesarTotalImpuesto(TotalImpuestoDTO totalImpuesto,
                                       String codTaxRegistradoInLine,
                                       AtomicReference<BigDecimal> valorImpuesto,
                                       AtomicReference<BigDecimal> baseImponible,
                                       AtomicReference<CategoriaImpuestoDTO> categoriaImpuestoDTO) {

        totalImpuesto.getSubTotalImpuesto().forEach(subtotalimpuesto -> {
            if (subtotalimpuesto.getCategoriaImpuesto().getEsquemaTributario().getId().getValue()
                    .equals(codTaxRegistradoInLine)) {
                valorImpuesto.updateAndGet(oldValue -> oldValue.add(totalImpuesto.getValorImpuesto().getValue()));
                baseImponible.updateAndGet(oldValue -> oldValue.add(subtotalimpuesto.getBaseImponible().getValue()));
                categoriaImpuestoDTO.updateAndGet(oldValue -> subtotalimpuesto.getCategoriaImpuesto());
            }
        });
    }

    private TotalImpuestoDTO getTotalImpuestos(String codTaxRegistradoInLine) {
        AtomicReference<BigDecimal> valorImpuesto = new AtomicReference<>(BigDecimal.ZERO.setScale(2));
        AtomicReference<BigDecimal> baseImponible = new AtomicReference<>(BigDecimal.ZERO.setScale(2));
        AtomicReference<CategoriaImpuestoDTO> categoriaImpuestoDTO = new AtomicReference<>(new CategoriaImpuestoDTO());

        lineasDTOS.stream()
                .filter(linea -> linea.getTotalImpuesto() != null && linea.getTotalImpuesto().size() > DEFAULT_INTEGER)
                .forEach(linea ->
                        setTotales(linea, codTaxRegistradoInLine, valorImpuesto, baseImponible, categoriaImpuestoDTO)
                );

        TotalImpuestoDTO totalImpuestoDTO = new TotalImpuestoDTO();
        totalImpuestoDTO.setValorImpuesto(getValorImpuesto(valorImpuesto.get()));
        totalImpuestoDTO.setValorAjusteRedondeo(getValorAjusteRedondeo());
        totalImpuestoDTO.setSubTotalImpuesto(List.of(
                getSubTotalImpuesto(categoriaImpuestoDTO.get(), valorImpuesto.get(), baseImponible.get())));

        return totalImpuestoDTO;
    }

    private void setTotales(LineasDTO lineasDTO, String codTaxRegistradoInLine,
                            AtomicReference<BigDecimal> valorImpuesto,
                            AtomicReference<BigDecimal> baseImponible,
                            AtomicReference<CategoriaImpuestoDTO> categoriaImpuestoDTO) {

        lineasDTO.getTotalImpuesto().forEach(totalImpuesto ->
                procesarTotalImpuesto(totalImpuesto, codTaxRegistradoInLine, valorImpuesto, baseImponible, categoriaImpuestoDTO));

    }

    private @NonNull ValorImpuestoDTO getValorImpuesto(BigDecimal valorImpuesto) {
        return ValorImpuestoDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(valorImpuesto)
                .build();
    }

    private @NonNull ValorAjusteRedondeoDTO getValorAjusteRedondeo() {
        return ValorAjusteRedondeoDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(new BigDecimal("0.00"))
                .build();
    }

    private SubTotalImpuestoDTO getSubTotalImpuesto(CategoriaImpuestoDTO categoriaImpuestoDTO,
                                                    BigDecimal valorImpuesto,
                                                    BigDecimal baseImpible) {


        return SubTotalImpuestoDTO.builder()
                .valorImpuesto(getValorImpuesto(valorImpuesto))
                .baseImponible(getBaseImponible(baseImpible))
                .categoriaImpuesto(getCategoriaImpuesto(categoriaImpuestoDTO))
                .build();
    }

    private @NonNull BaseImponibleDTO getBaseImponible(BigDecimal precioConDescuento) {

        return BaseImponibleDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(precioConDescuento)
                .build();
    }

    private @NonNull CategoriaImpuestoDTO getCategoriaImpuesto(CategoriaImpuestoDTO categoriaImpuestoDTO) {

        return CategoriaImpuestoDTO.builder()
                .porcentaje(categoriaImpuestoDTO.getPorcentaje())
                .esquemaTributario(EsquemaTributarioDTO.builder()
                        .id(IdDTO.builder().value(categoriaImpuestoDTO.getEsquemaTributario().getId().getValue())
                                .build())
                        .nombre(NombreDTO.builder().value(categoriaImpuestoDTO.getEsquemaTributario()
                                .getNombre().getValue()).build())
                        .build())
                .build();
    }
}

