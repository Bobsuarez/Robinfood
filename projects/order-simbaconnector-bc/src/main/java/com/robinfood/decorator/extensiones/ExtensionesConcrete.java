package com.robinfood.decorator.extensiones;

import com.robinfood.dtos.request.simba.extensiones.ContenidoExtensionDTO;
import com.robinfood.dtos.request.simba.extensiones.ExtensionesDTO;
import com.robinfood.dtos.request.simba.extensiones.FabricanteSoftwareDTO;
import com.robinfood.dtos.request.simba.extensiones.InformacionDelFabricanteDelSoftwareDTO;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.robinfood.constants.GeneralConstants.NOMBRE_SOFTWARE;
import static com.robinfood.constants.simba.ExtensionesConstants.INFORMACION_SOFTWARE_NOMBRE_APELLIDO;
import static com.robinfood.constants.simba.ExtensionesConstants.INFORMACION_SOFTWARE_NOMBRE_SOFTWARE;
import static com.robinfood.constants.simba.ExtensionesConstants.INFORMACION_SOFTWARE_RAZON_SOCIAL;
import static com.robinfood.constants.simba.TercerosConstants.NOMBRE_EMPRESA;

public class ExtensionesConcrete implements IExtensiones {

    public ExtensionesConcrete() {
        // Empty
    }

    @Override
    public void decorarExtensiones(List<ExtensionesDTO> extensionesDTOList) {
        buildDataPropertiesDefault(extensionesDTOList);
    }

    private void buildDataPropertiesDefault(List<ExtensionesDTO> extensionesDTOList) {
        ExtensionesDTO extensionesDTO = new ExtensionesDTO();
        extensionesDTO.setContenidoExtension(getContenidoExtension());
        extensionesDTOList.add(extensionesDTO);
    }

    private @NonNull ContenidoExtensionDTO getContenidoExtension() {
        return ContenidoExtensionDTO.builder()
                .fabricanteSoftware(getFabricanteSoftware())
                .build();
    }

    private @NonNull FabricanteSoftwareDTO getFabricanteSoftware() {
        return FabricanteSoftwareDTO.builder()
                .informacionDelFabricanteDelSoftware(getInformacionDelFabricanteDelSofware())
                .build();
    }

    private @NonNull List<InformacionDelFabricanteDelSoftwareDTO> getInformacionDelFabricanteDelSofware() {

        List<InformacionDelFabricanteDelSoftwareDTO> informacionDelFabricanteDelSoftwareDTOList = new ArrayList<>();
        informacionDelFabricanteDelSoftwareDTOList.add(getInformacionDelFabricanteDelSofwareDTO(
                INFORMACION_SOFTWARE_NOMBRE_APELLIDO, NOMBRE_EMPRESA));
        informacionDelFabricanteDelSoftwareDTOList.add(getInformacionDelFabricanteDelSofwareDTO(
                INFORMACION_SOFTWARE_RAZON_SOCIAL, NOMBRE_EMPRESA));
        informacionDelFabricanteDelSoftwareDTOList.add(getInformacionDelFabricanteDelSofwareDTO(
                INFORMACION_SOFTWARE_NOMBRE_SOFTWARE, NOMBRE_SOFTWARE));

        return informacionDelFabricanteDelSoftwareDTOList;
    }

    private @NonNull InformacionDelFabricanteDelSoftwareDTO getInformacionDelFabricanteDelSofwareDTO(
            String name, String value
    ) {
        return InformacionDelFabricanteDelSoftwareDTO.builder()
                .name(name)
                .value(value)
                .build();
    }
}
