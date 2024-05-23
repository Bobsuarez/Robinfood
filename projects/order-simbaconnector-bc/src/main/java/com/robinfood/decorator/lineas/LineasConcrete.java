package com.robinfood.decorator.lineas;

import com.robinfood.dtos.request.orderbill.FinalProductDTO;
import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.dtos.request.simba.lineas.CantidadDTO;
import com.robinfood.dtos.request.simba.lineas.DatosAuxDTO;
import com.robinfood.dtos.request.simba.lineas.LineasDTO;
import com.robinfood.dtos.request.simba.lineas.NotaDTO;
import com.robinfood.dtos.request.simba.lineas.ValorNetoDTO;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.constants.simba.SimbaGeneralConstants.CODIGO_MONEDA;
import static com.robinfood.constants.simba.SimbaGeneralConstants.UNIDAD_MEDIDA;

public class LineasConcrete implements ILineas {

    private final OrderDTO orderDTO;

    public LineasConcrete(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    @Override
    public void decorarLineas(List<LineasDTO> lineasDTOs) {
        adicionarPropiedadLineasPorDefecto(lineasDTOs);
    }

    private void adicionarPropiedadLineasPorDefecto(List<LineasDTO> lineasDTOs) {

        AtomicInteger id = new AtomicInteger(DEFAULT_INTEGER);
        orderDTO.getFinalProducts().forEach((FinalProductDTO finalProductDTO) -> {
                    LineasDTO lineasDTO = new LineasDTO();
                    DatosAuxDTO datosAuxDTO = getDatosAux(finalProductDTO);
                    lineasDTO.setId(setIdLinea(id.incrementAndGet()));
                    lineasDTO.setNota(setNotaLinea(finalProductDTO.getName()));
                    lineasDTO.setCantidad(setCantidadLinea(finalProductDTO));
                    lineasDTO.setPrecioUnitario(finalProductDTO.getPrice());
                    lineasDTO.setDatosAux(datosAuxDTO);
                    lineasDTO.setValorNeto(setValorNetoLinea(datosAuxDTO));
                    lineasDTOs.add(lineasDTO);
                }
        );
    }

    private @NonNull CantidadDTO setCantidadLinea(FinalProductDTO finalProductDTO) {
        return CantidadDTO.builder()
                .codUnidad(UNIDAD_MEDIDA)
                .value(new BigDecimal(finalProductDTO.getQuantity()))
                .build();
    }

    private @NonNull List<NotaDTO> setNotaLinea(String productName) {

        return List.of(NotaDTO.builder()
                .value(productName)
                .build());
    }

    private @NonNull IdDTO setIdLinea(Integer id) {
        return IdDTO.builder()
                .value(id.toString())
                .build();
    }

    private @NonNull ValorNetoDTO setValorNetoLinea(DatosAuxDTO datosAuxDTO) {

        return ValorNetoDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(datosAuxDTO.getFinalProductBaseImponible())
                .build();
    }

    private @NonNull DatosAuxDTO getDatosAux(FinalProductDTO finalProductDTO) {
        
        AtomicReference<BigDecimal> sumTax = new AtomicReference<>(BigDecimal.ZERO);
        AtomicReference<BigDecimal> sumDiscount = new AtomicReference<>(BigDecimal.ZERO);

        finalProductDTO.getTaxes().forEach(finalProductTaxDTO ->
                sumTax.updateAndGet(oldValue -> oldValue.add(finalProductTaxDTO.getTaxPrice()))
        );

        finalProductDTO.getDiscounts().forEach(finalProductDiscountDTO ->
                sumDiscount.updateAndGet(oldValue -> oldValue.add(finalProductDiscountDTO.getValue()))
        );

        BigDecimal percentage = new BigDecimal("0.00");
        Long taxId = null;
        if (!finalProductDTO.getTaxes().isEmpty()) {
            percentage = finalProductDTO.getTaxes().get(DEFAULT_INTEGER).getTaxValue();
            taxId = finalProductDTO.getTaxes().get(DEFAULT_INTEGER).getTaxId();
        }

        return DatosAuxDTO.builder()
                .finalProductDiscount(sumDiscount.get().multiply(new BigDecimal(finalProductDTO.getQuantity())))
                .finalProductTaxes(sumTax.get())
                .finalProductId(finalProductDTO.getId())
                .finalProductTotalPrice(finalProductDTO.getTotalPrice())
                .finalProductPercentage(percentage)
                .taxId(taxId)
                .finalProductBaseImponible(getBaseImponible(finalProductDTO, sumTax.get(),
                        sumDiscount.get()))
                .precioUnitario(getCalculatePrice(finalProductDTO, sumTax.get()))
                .finalProductTax(finalProductDTO.getTaxes())
                .build();
    }

    private BigDecimal getBaseImponible(FinalProductDTO finalProductDTO, BigDecimal sumTaxes, BigDecimal sumDiscount) {
        BigDecimal baseImponible = finalProductDTO.getTotalPrice().subtract(sumDiscount.multiply(
                new BigDecimal(finalProductDTO.getQuantity())
        ));
        return baseImponible.subtract(sumTaxes);
    }

    private BigDecimal getCalculatePrice(FinalProductDTO finalProductDTO, BigDecimal sumTaxes) {
        return finalProductDTO.getTotalPrice().subtract(sumTaxes)
                .divide(new BigDecimal(finalProductDTO.getQuantity()), 5, RoundingMode.HALF_UP);
    }
}
