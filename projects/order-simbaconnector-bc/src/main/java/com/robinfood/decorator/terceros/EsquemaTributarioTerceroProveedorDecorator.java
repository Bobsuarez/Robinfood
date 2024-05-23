package com.robinfood.decorator.terceros;

import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.dtos.request.simba.NombreDTO;
import com.robinfood.dtos.request.simba.terceros.TercerosDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.EsquemaTributarioDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.EsquemaTributarioTerceroDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.NivelTributarioDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.NombreRegistradoDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.NumeroIdTributarioDTO;
import com.robinfood.enums.TaxesEnum;
import lombok.NonNull;

import java.util.List;

import static com.robinfood.constants.simba.TercerosConstants.NIT;
import static com.robinfood.constants.simba.TercerosConstants.NIVEL_TRIBUTARIO_LISTA_NOMBRE;
import static com.robinfood.constants.simba.TercerosConstants.NIVEL_TRIBUTARIO_LISTA_VALUE;
import static com.robinfood.constants.simba.TercerosConstants.NOMBRE_EMPRESA;
import static com.robinfood.constants.simba.TercerosConstants.SMA_ID_CODIGO;
import static com.robinfood.constants.simba.TercerosConstants.SMA_ID_NOMBRE;

public class EsquemaTributarioTerceroProveedorDecorator extends AbstractTerceroProveedor {

    public EsquemaTributarioTerceroProveedorDecorator(ITerceros terceroProveedor) {
        super(terceroProveedor);
    }

    @Override
    public void decorarTerceroProveedor(TercerosDTO tercerosDTOO) {
        super.decorarTerceroProveedor(tercerosDTOO);
        buildUbicacionFisicaTercerosProveedorDecorator(tercerosDTOO);
    }

    private void buildUbicacionFisicaTercerosProveedorDecorator(TercerosDTO tercerosDTO) {

        EsquemaTributarioTerceroDTO esquemaTributarioTerceroDTO = new EsquemaTributarioTerceroDTO();

        esquemaTributarioTerceroDTO.setNombreRegistrado(getNombreRegistrado());
        esquemaTributarioTerceroDTO.setNumeroIdTributario(getNumeroIdTributario());
        esquemaTributarioTerceroDTO.setNivelTributario(getNivelTributario());
        esquemaTributarioTerceroDTO.setEsquemaTributario(getEsquemaTributario());

        tercerosDTO.getTerceroProveedorContable().getTercero().setEsquemaTributarioTercero(
                List.of(esquemaTributarioTerceroDTO)
        );

    }

    private @NonNull NombreRegistradoDTO getNombreRegistrado() {
        return NombreRegistradoDTO.builder()
                .value(NOMBRE_EMPRESA)
                .build();
    }

    private @NonNull NumeroIdTributarioDTO getNumeroIdTributario() {
        return NumeroIdTributarioDTO.builder()
                .smaIdCodigo(SMA_ID_CODIGO)
                .smaIdNombre(SMA_ID_NOMBRE)
                .value(NIT)
                .build();
    }

    private @NonNull NivelTributarioDTO getNivelTributario() {
        return NivelTributarioDTO.builder()
                .listaNombre(NIVEL_TRIBUTARIO_LISTA_NOMBRE)
                .value(NIVEL_TRIBUTARIO_LISTA_VALUE)
                .build();
    }

    private @NonNull EsquemaTributarioDTO getEsquemaTributario() {
        return EsquemaTributarioDTO.builder()
                .id(IdDTO.builder().value(TaxesEnum.IMPO_CONSUMO.getCodeDian()).build())
                .nombre(NombreDTO.builder().value(TaxesEnum.IMPO_CONSUMO.getNameDian()).build())
                .build();
    }
}

