package com.robinfood.decorator.lineas;

import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.dtos.request.simba.lineas.BaseDTO;
import com.robinfood.dtos.request.simba.lineas.CargoDescuentoDTO;
import com.robinfood.dtos.request.simba.lineas.DatosAuxDTO;
import com.robinfood.dtos.request.simba.lineas.LineasDTO;
import com.robinfood.dtos.request.simba.lineas.RazonCargoDescuentoCodDTO;
import com.robinfood.dtos.request.simba.lineas.RazonCargoDescuentoTextoDTO;
import com.robinfood.dtos.request.simba.lineas.ValorCargoDescuentoDTO;
import com.robinfood.enums.DescuentoEnum;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.constants.simba.SimbaGeneralConstants.CODIGO_MONEDA;

public class CargoDescuentoDecorator extends AbstractLineas {

    public CargoDescuentoDecorator(ILineas lineas) {
        super(lineas);
    }

    @Override
    public void decorarLineas(List<LineasDTO> lineasDTOs) {
        super.decorarLineas(lineasDTOs);
        buildLineaCargoDescuento(lineasDTOs);
    }

    private void buildLineaCargoDescuento(List<LineasDTO> lineasDTOs) {
        AtomicInteger id = new AtomicInteger(DEFAULT_INTEGER);

        lineasDTOs.forEach((LineasDTO linea) -> {

            if (Objects.nonNull(linea.getDatosAux()) &&
                    linea.getDatosAux().getFinalProductDiscount().compareTo(BigDecimal.ZERO) == 0) {
                return;
            }

            CargoDescuentoDTO cargoDescuentoDTO = new CargoDescuentoDTO();
            cargoDescuentoDTO.setId(getIdLinea(id.incrementAndGet()));
            cargoDescuentoDTO.setIndicaCargoODescuento(Boolean.FALSE);

            cargoDescuentoDTO.setRazonCargoDescuentoCod(getRazonCargoDescuentoCod());
            cargoDescuentoDTO.setRazonCargoDescuentoTexto(getRazonCargoDescuentoTextoDTO());
            cargoDescuentoDTO.setValor(getValorCargoDescuentoDTO(linea.getDatosAux()));
            cargoDescuentoDTO.setBase(getBase(linea));

            buildPorcentaje(cargoDescuentoDTO);
            linea.setCargoDescuento(List.of(cargoDescuentoDTO));
        });
    }

    private void buildPorcentaje(CargoDescuentoDTO cargoDescuentoDTO) {
        cargoDescuentoDTO.setPorcentajeSpecified(Boolean.TRUE);
        cargoDescuentoDTO.setPorcentaje(calculateDiscountPercentage(cargoDescuentoDTO.getBase().getValue(),
                cargoDescuentoDTO.getValor().getValue()));
    }

    private @NonNull IdDTO getIdLinea(Integer id) {
        return IdDTO.builder()
                .value(id.toString())
                .build();
    }

    private @NonNull RazonCargoDescuentoCodDTO getRazonCargoDescuentoCod() {
        return RazonCargoDescuentoCodDTO.builder()
                .value(DescuentoEnum.GENERAL.getCodeDian())
                .build();
    }

    private @NonNull RazonCargoDescuentoTextoDTO getRazonCargoDescuentoTextoDTO() {
        return RazonCargoDescuentoTextoDTO.builder()
                .value(DescuentoEnum.GENERAL.getNameDian())
                .build();
    }

    private @NonNull ValorCargoDescuentoDTO getValorCargoDescuentoDTO(DatosAuxDTO datosAuxDTO) {
        return ValorCargoDescuentoDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(datosAuxDTO.getFinalProductDiscount())
                .build();
    }

    private @NonNull BaseDTO getBase(LineasDTO lineasDTO) {

        BigDecimal base = lineasDTO.getDatosAux().getFinalProductBaseImponible().add(
                lineasDTO.getDatosAux().getFinalProductDiscount());

        return BaseDTO.builder()
                .idMoneda(CODIGO_MONEDA)
                .value(base)
                .build();
    }

    private static BigDecimal calculateDiscountPercentage(BigDecimal originalPrice, BigDecimal discountAmount) {
        return discountAmount.divide(originalPrice, 5, RoundingMode.HALF_UP)
                .multiply(new BigDecimal("100"));
    }
}

