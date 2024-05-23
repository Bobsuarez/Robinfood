package com.robinfood.decorator.terceros;

import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.dtos.request.simba.NombreDTO;
import com.robinfood.dtos.request.simba.terceros.TercerosDTO;
import com.robinfood.dtos.request.simba.terceros.TextoLineaDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.DireccionParaImpuestosDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.EsquemaTributarioTerceroDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.CiudadDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.CodigoDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.DepartamentoDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.LineaDireccionDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.PaisDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.SubdivisionPaisCodigoDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.SubdivisionPaisDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.ZonaPostalDTO;
import lombok.NonNull;

import java.util.List;

import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_CIUDAD;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_DEPARTAMENTO;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_ID;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_PAIS_CODIGO;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_PAIS_LENGUAJE;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_PAIS_NOMBRE;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_SUBDIVISION_PAIS;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_SUBDIVISION_PAIS_CODIGO;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_SUBDIVISION_TEXT_LINEA;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_ZONA_POSTAL;

public class EsquemaTributarioTerceroProveedorDireccionDecorator extends AbstractTerceroProveedor {

    public EsquemaTributarioTerceroProveedorDireccionDecorator(
            ITerceros terceroProveedor
    ) {
        super(terceroProveedor);
    }

    @Override
    public void decorarTerceroProveedor(TercerosDTO tercerosDTOO) {
        super.decorarTerceroProveedor(tercerosDTOO);
        buildUbicacionFisicaTercerosProveedorDecorator(tercerosDTOO);
    }

    private void buildUbicacionFisicaTercerosProveedorDecorator(TercerosDTO tercerosDTO) {

        DireccionParaImpuestosDTO direccionParaImpuestosDTO = DireccionParaImpuestosDTO.builder()
                .id(getId())
                .departamento(getDepartamento())
                .ciudad(getCiudad())
                .zonaPostal(getZonaPostal())
                .subdivisionPais(getSubDivisionPais())
                .subdivisionPaisCodigo(getSubdivisionPaisCodigo())
                .lineaDireccion(getLineaDireccion())
                .pais(getPais())
                .build();

        EsquemaTributarioTerceroDTO esquemaTributarioTerceroDTO = tercerosDTO.getTerceroProveedorContable()
                .getTercero().getEsquemaTributarioTercero().get(0);

        esquemaTributarioTerceroDTO.setDireccionParaImpuestos(direccionParaImpuestosDTO);

        tercerosDTO.getTerceroProveedorContable().getTercero().setEsquemaTributarioTercero(
                List.of(esquemaTributarioTerceroDTO)
        );
    }

    private @NonNull IdDTO getId() {
        return IdDTO.builder()
                .value(UBICACION_FISICA_ID)
                .build();
    }

    private @NonNull DepartamentoDTO getDepartamento() {
        return DepartamentoDTO.builder()
                .value(UBICACION_FISICA_DEPARTAMENTO)
                .build();
    }

    private @NonNull CiudadDTO getCiudad() {
        return CiudadDTO.builder()
                .value(UBICACION_FISICA_CIUDAD)
                .build();
    }

    private @NonNull ZonaPostalDTO getZonaPostal() {
        return ZonaPostalDTO.builder()
                .value(UBICACION_FISICA_ZONA_POSTAL)
                .build();
    }

    private @NonNull SubdivisionPaisDTO getSubDivisionPais() {
        return SubdivisionPaisDTO.builder()
                .value(UBICACION_FISICA_SUBDIVISION_PAIS)
                .build();
    }

    private @NonNull SubdivisionPaisCodigoDTO getSubdivisionPaisCodigo() {
        return SubdivisionPaisCodigoDTO.builder()
                .value(UBICACION_FISICA_SUBDIVISION_PAIS_CODIGO)
                .build();
    }

    private @NonNull List<LineaDireccionDTO> getLineaDireccion() {
        return List.of(LineaDireccionDTO.builder()
                .textoLinea(TextoLineaDTO.builder()
                        .value(UBICACION_FISICA_SUBDIVISION_TEXT_LINEA)
                        .build())
                .build());
    }

    private @NonNull PaisDTO getPais() {
        return PaisDTO.builder()
                .codigo(CodigoDTO.builder()
                        .value(UBICACION_FISICA_PAIS_CODIGO)
                        .build())
                .nombre(NombreDTO.builder().value(UBICACION_FISICA_PAIS_NOMBRE)
                        .idLenguaje(UBICACION_FISICA_PAIS_LENGUAJE)
                        .build())
                .build();
    }
}

