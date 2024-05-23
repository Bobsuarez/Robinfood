package com.robinfood.storeor.mocks.entity.CommandConfiguration;

import com.robinfood.storeor.entities.CommandConfiguration.CommandConfigurationEntity;
import com.robinfood.storeor.entities.CommandConfiguration.PoliticsReprocessEntity;
import com.robinfood.storeor.entities.CommandConfiguration.PreconditionCommandEntity;
import com.robinfood.storeor.entities.CommandConfiguration.ProcessFlowEntity;

import java.util.List;

public class CommandConfigurationListEntityMock {

    public List<CommandConfigurationEntity> defaultData(){

       return List.of(
                CommandConfigurationEntity
                        .builder()
                        .id(1L)
                        .name("VALIDATE_ORDER_DETAIL_COMMAND")
                        .description("comando de pruebas")
                        .sequence(1)
                        .preconditionCommands(List.of())
                        .policiesReprocess(List.of())
                        .processFlow(ProcessFlowEntity.builder().build())
                        .build(),
                CommandConfigurationEntity
                        .builder()
                        .id(2l)
                        .name("PRINT_KITECHEN_COMMAND")
                        .description("comando de pruebas")
                        .sequence(2)
                        .preconditionCommands(List.of(
                                PreconditionCommandEntity
                                        .builder()
                                        .id(1l)
                                        .name("VALIDATE_ORDER_DETAIL_COMMAND")
                                        .build()))
                        .policiesReprocess(List.of(
                                PoliticsReprocessEntity
                                        .builder()
                                        .id(1l)
                                        .description("no conexion con impresora")
                                        .build()))
                        .processFlow(ProcessFlowEntity.builder()
                                .id(1L)
                                .name("PROCESS_TEST")
                                .proxyFlow(Boolean.TRUE)
                                .build())
                        .build()
        );
    }
}
