package com.robinfood.usecase.buildcontratsimba;

import com.robinfood.decorator.agregadocomercial.decorator.IDecoratorAgregadoComercial;
import com.robinfood.decorator.encabezado.decorator.IDecoratorEncabezado;
import com.robinfood.decorator.extensiones.decorator.IDecoratorExtensiones;
import com.robinfood.decorator.lineas.decorator.IDecoratorLineas;
import com.robinfood.decorator.parametrosdecorador.decorator.IDecoratorParametros;
import com.robinfood.decorator.terceros.decorator.IDecoratorTerceros;
import com.robinfood.decorator.totales.decorator.IDecoratorTotales;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.SimbaContractDTO;
import com.robinfood.dtos.request.simba.agregadocomercial.AgregadoComercialDTO;
import com.robinfood.dtos.request.simba.encabezado.EncabezadoDTO;
import com.robinfood.dtos.request.simba.lineas.LineasDTO;
import com.robinfood.dtos.request.simba.parametros.ParametrosDTO;
import com.robinfood.dtos.request.simba.terceros.TercerosDTO;
import com.robinfood.dtos.request.simba.totales.TotalesDTO;
import com.robinfood.util.LogsUtil;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class BuildContractSimbaUseCaseTest {

    @Mock
    private IDecoratorParametros decoratorParametros;

    @Mock
    private IDecoratorEncabezado decoratorEncabezado;

    @Mock
    private IDecoratorTerceros decoratorTerceros;

    @Mock
    private IDecoratorLineas decoratorLineas;

    @Mock
    private IDecoratorAgregadoComercial agregadoComercial;

    @Mock
    private IDecoratorTotales decorarTotales;

    @Mock
    private IDecoratorExtensiones decoratorextensiones;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString(), any());
    }

    @AfterEach
    public void setupAfter() {
        clearAllCaches();
    }

    private TransactionRequestDTO transactionRequestDTO = TransactionRequestDTO.builder().id(1L).build();

    @Test
    void test_BuildContractSimbaUseCase_Accepted() {

        List<LineasDTO> lineasDTOS = new ArrayList<>();

        when(decoratorParametros.invoke(transactionRequestDTO))
                .thenReturn(any(ParametrosDTO.class));

        when(decoratorEncabezado.invoke(transactionRequestDTO))
                .thenReturn(any(EncabezadoDTO.class));


        when(decoratorTerceros.invoke(transactionRequestDTO))
                .thenReturn(any(TercerosDTO.class));

        when(decoratorLineas.invoke(transactionRequestDTO))
                .thenReturn(new ArrayList<>());

        when(agregadoComercial.invoke(transactionRequestDTO))
                .thenReturn(AgregadoComercialDTO.builder().build());

        when(decorarTotales.invoke(transactionRequestDTO, lineasDTOS))
                .thenReturn(any(TotalesDTO.class));

        when(decoratorextensiones.invoke(transactionRequestDTO))
                .thenReturn(new ArrayList<>());

        BuildContractSimbaUseCase buildContractSimbaUseCase = new BuildContractSimbaUseCase(
                decoratorParametros,
                decoratorEncabezado,
                decoratorTerceros,
                decoratorLineas,
                agregadoComercial,
                decorarTotales,
                decoratorextensiones);

        SimbaContractDTO simbaContractDTO = buildContractSimbaUseCase.invoke(transactionRequestDTO);

        Assertions.assertNotNull(simbaContractDTO);
    }

    @Test
    void test_Invoke_BuildContractSimbaUseCase_Accepted_Should_When_Instance() {

        BuildContractSimbaUseCase buildContractSimbaUseCase =
                new BuildContractSimbaUseCase();

        Assert.notNull(buildContractSimbaUseCase);
    }
}
