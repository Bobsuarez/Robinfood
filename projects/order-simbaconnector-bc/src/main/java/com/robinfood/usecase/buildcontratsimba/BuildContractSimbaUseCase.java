package com.robinfood.usecase.buildcontratsimba;

import com.robinfood.decorator.agregadocomercial.decorator.DecoratorAgregadoComercial;
import com.robinfood.decorator.agregadocomercial.decorator.IDecoratorAgregadoComercial;
import com.robinfood.decorator.encabezado.decorator.DecoratorEncabezado;
import com.robinfood.decorator.encabezado.decorator.IDecoratorEncabezado;
import com.robinfood.decorator.extensiones.decorator.DecoratorExtensiones;
import com.robinfood.decorator.extensiones.decorator.IDecoratorExtensiones;
import com.robinfood.decorator.lineas.decorator.DecoratorLineas;
import com.robinfood.decorator.lineas.decorator.IDecoratorLineas;
import com.robinfood.decorator.parametrosdecorador.decorator.DecoratorParametros;
import com.robinfood.decorator.parametrosdecorador.decorator.IDecoratorParametros;
import com.robinfood.decorator.terceros.decorator.DecoratorTerceros;
import com.robinfood.decorator.terceros.decorator.IDecoratorTerceros;
import com.robinfood.decorator.totales.decorator.DecoratorTotales;
import com.robinfood.decorator.totales.decorator.IDecoratorTotales;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.SimbaContractDTO;
import com.robinfood.dtos.request.simba.lineas.LineasDTO;

import java.util.List;

public class BuildContractSimbaUseCase implements IBuildContractSimbaUseCase {

    private final IDecoratorParametros decoratorParametros;
    private final IDecoratorEncabezado decoratorEncabezado;
    private final IDecoratorTerceros decoratorTerceros;
    private final IDecoratorLineas decoratorLineas;
    private final IDecoratorAgregadoComercial agregadoComercial;
    private final IDecoratorTotales decorarTotales;
    private final IDecoratorExtensiones decoratorextensiones;

    public BuildContractSimbaUseCase() {
        this.decoratorParametros = new DecoratorParametros();
        this.decoratorEncabezado = new DecoratorEncabezado();
        this.decoratorTerceros = new DecoratorTerceros();
        this.decoratorLineas = new DecoratorLineas();
        this.agregadoComercial = new DecoratorAgregadoComercial();
        this.decorarTotales = new DecoratorTotales();
        this.decoratorextensiones = new DecoratorExtensiones();
    }

    public BuildContractSimbaUseCase(
            IDecoratorParametros decoratorParametros,
            IDecoratorEncabezado decoratorEncabezado,
            IDecoratorTerceros decoratorTerceros,
            IDecoratorLineas decoratorLineas,
            IDecoratorAgregadoComercial agregadoComercial,
            IDecoratorTotales decorarTotales,
            IDecoratorExtensiones extensiones) {
        this.decoratorParametros = decoratorParametros;
        this.decoratorEncabezado = decoratorEncabezado;
        this.decoratorTerceros = decoratorTerceros;
        this.decoratorLineas = decoratorLineas;
        this.agregadoComercial = agregadoComercial;
        this.decorarTotales = decorarTotales;
        this.decoratorextensiones = extensiones;
    }

    @Override
    public SimbaContractDTO invoke(TransactionRequestDTO transactionRequestDTO) {

        List<LineasDTO> lineasDTOS = decoratorLineas.invoke(transactionRequestDTO);

        return SimbaContractDTO.builder()
                .parametros(decoratorParametros.invoke(transactionRequestDTO))
                .extensiones(decoratorextensiones.invoke(transactionRequestDTO))
                .encabezado(decoratorEncabezado.invoke(transactionRequestDTO))
                .terceros(decoratorTerceros.invoke(transactionRequestDTO))
                .lineas(lineasDTOS)
                .agregadoComercial(agregadoComercial.invoke(transactionRequestDTO))
                .totales(decorarTotales.invoke(transactionRequestDTO, lineasDTOS))
                .build();
    }
}
