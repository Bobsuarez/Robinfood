package com.robinfood.decorator.terceros;

import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.dtos.request.simba.NombreDTO;
import com.robinfood.dtos.request.simba.terceros.TercerosDTO;
import com.robinfood.dtos.request.simba.terceros.contacto.ContactoDTO;
import com.robinfood.dtos.request.simba.terceros.contacto.GenericContactoDTO;
import com.robinfood.dtos.request.simba.terceros.entidadlegaltercero.EntidadLegalTerceroDTO;
import com.robinfood.dtos.request.simba.terceros.entidadlegaltercero.EsquemaRegistroCorporativoDTO;
import com.robinfood.dtos.request.simba.terceros.entidadlegaltercero.NumeroIdLegalDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.NombreRegistradoDTO;
import lombok.NonNull;

import java.util.List;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.constants.simba.ParametrosConstants.EMAIL;
import static com.robinfood.constants.simba.TercerosConstants.NIT;
import static com.robinfood.constants.simba.TercerosConstants.NOMBRE_EMPRESA;
import static com.robinfood.constants.simba.TercerosConstants.SMA_ID_CODIGO;
import static com.robinfood.constants.simba.TercerosConstants.SMA_ID_NOMBRE;
import static com.robinfood.constants.simba.TercerosConstants.TELEFONO_EMPRESA;
import static com.robinfood.constants.simba.TercerosConstants.UBICACION_FISICA_SUBDIVISION_TEXT_LINEA;

public class EntidadLegalTerceroProveedorDecorator extends AbstractTerceroProveedor {

    private final TransactionRequestDTO transactionRequestDTO;

    public EntidadLegalTerceroProveedorDecorator(ITerceros terceroProveedor,
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

        EntidadLegalTerceroDTO entidadLegalTerceroDTO = new EntidadLegalTerceroDTO();

        entidadLegalTerceroDTO.setNombreRegistrado(getNombreRegistrado());
        entidadLegalTerceroDTO.setNumeroIdLegal(getNumeroIdLegal());
        entidadLegalTerceroDTO.setEsquemaRegistroCorporativo(getEsquemaRegistroCorporativo());

        tercerosDTO.getTerceroProveedorContable().getTercero().setEntidadLegalTercero(
                List.of(entidadLegalTerceroDTO)
        );

        tercerosDTO.getTerceroProveedorContable().getTercero().setContacto(getContacto());
    }

    private @NonNull NombreRegistradoDTO getNombreRegistrado() {
        return NombreRegistradoDTO.builder()
                .value(NOMBRE_EMPRESA)
                .build();
    }

    private @NonNull NumeroIdLegalDTO getNumeroIdLegal() {
        return NumeroIdLegalDTO.builder()
                .smaIdCodigo(SMA_ID_CODIGO)
                .smaIdNombre(SMA_ID_NOMBRE)
                .value(NIT)
                .build();
    }

    private @NonNull EsquemaRegistroCorporativoDTO getEsquemaRegistroCorporativo() {
        final OrderDTO orderDTO = transactionRequestDTO.getOrders().get(DEFAULT_INTEGER);
        return EsquemaRegistroCorporativoDTO.builder()
                .id(IdDTO.builder().value(orderDTO.getPosResolution().getPrefix().toString()).build())
                .nombre(NombreDTO.builder().value(transactionRequestDTO.getStoreInfo().getName()).build())
                .build();
    }

    private @NonNull ContactoDTO getContacto() {

        return ContactoDTO.builder()
                .nombre(GenericContactoDTO.builder().value(NOMBRE_EMPRESA).build())
                .telefono(GenericContactoDTO.builder().value(TELEFONO_EMPRESA).build())
                .email(GenericContactoDTO.builder().value(EMAIL).build())
                .build();
    }
}

