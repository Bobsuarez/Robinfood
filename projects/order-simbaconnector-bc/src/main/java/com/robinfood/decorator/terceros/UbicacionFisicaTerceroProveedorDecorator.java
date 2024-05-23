package com.robinfood.decorator.terceros;

import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.dtos.request.simba.NombreDTO;
import com.robinfood.dtos.request.simba.terceros.TercerosDTO;
import com.robinfood.dtos.request.simba.terceros.TextoLineaDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.CiudadDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.CodigoDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.DepartamentoDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.DireccionDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.LineaDireccionDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.PaisDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.SubdivisionPaisCodigoDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.SubdivisionPaisDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.UbicacionFisicaDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.ZonaPostalDTO;
import lombok.NonNull;

import java.util.List;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_CIUDAD;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_ID;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_PAIS_CODIGO;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_PAIS_LENGUAJE;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_PAIS_NOMBRE;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_SUBDIVISION_PAIS;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_SUBDIVISION_PAIS_CODIGO;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_SUBDIVISION_TEXT_LINEA;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_ZONA_POSTAL;

public class UbicacionFisicaTerceroProveedorDecorator extends AbstractTerceroProveedor {

    private final TransactionRequestDTO transactionRequestDTO;

    public UbicacionFisicaTerceroProveedorDecorator(
            ITerceros terceroProveedor,
            TransactionRequestDTO transactionRequestDTO) {

        super(terceroProveedor);
        this.transactionRequestDTO = transactionRequestDTO;
    }

    @Override
    public void decorarTerceroProveedor(TercerosDTO tercerosDTOO) {
        super.decorarTerceroProveedor(tercerosDTOO);
        buildUbicacionFisicaTercerosProveedorDecorator(tercerosDTOO);
    }

    private void buildUbicacionFisicaTercerosProveedorDecorator(TercerosDTO tercerosDTO) {

        final OrderDTO orderDTO = transactionRequestDTO.getOrders().get(DEFAULT_INTEGER);

        UbicacionFisicaDTO ubicacionFisicaDTO = UbicacionFisicaDTO.builder()
                .direccion(DireccionDTO.builder()
                        .id(getId(orderDTO))
                        .departamento(getDepartamento())
                        .ciudad(getCiudad())
                        .zonaPostal(getZonaPostal(orderDTO))
                        .subdivisionPais(getSubDivisionPais())
                        .subdivisionPaisCodigo(getSubdivisionPaisCodigo())
                        .lineaDireccion(getLineaDireccion())
                        .pais(getPais())
                        .build())
                .build();

        tercerosDTO.getTerceroProveedorContable().getTercero().setUbicacionFisica(ubicacionFisicaDTO);
    }

    private @NonNull IdDTO getId(OrderDTO orderDTO) {
        return IdDTO.builder()
                .value(orderDTO.getStore().getCode())
                .build();
    }

    private @NonNull DepartamentoDTO getDepartamento() {
        return DepartamentoDTO.builder()
                .value(transactionRequestDTO.getStoreInfo().getState().getName())
                .build();
    }

    private @NonNull CiudadDTO getCiudad() {
        return CiudadDTO.builder()
                .value(transactionRequestDTO.getStoreInfo().getCity().getName())
                .build();
    }

    private @NonNull ZonaPostalDTO getZonaPostal(OrderDTO orderDTO) {
        return ZonaPostalDTO.builder()
                .value(orderDTO.getStore().getPostalCode())
                .build();
    }

    private @NonNull SubdivisionPaisDTO getSubDivisionPais() {
        return SubdivisionPaisDTO.builder()
                .value(transactionRequestDTO.getStoreInfo().getCity().getName())
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
                        .value(transactionRequestDTO.getStoreInfo().getAddress())
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

