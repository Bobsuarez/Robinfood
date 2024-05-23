package com.robinfood.storeor.mocks.dto.commandConfiguration;

import com.robinfood.storeor.dtos.CommandConfiguration.CommandConfigurationResponseDTO;
import com.robinfood.storeor.dtos.CommandConfiguration.PoliticsReprocessResponseDTO;
import com.robinfood.storeor.dtos.CommandConfiguration.PreconditionCommandResponseDTO;
import com.robinfood.storeor.dtos.CommandConfiguration.ProcessFlowDTO;

import java.util.List;

public class CommandConfigurationResponseDTOMock {

    public List<CommandConfigurationResponseDTO> getDefautlListCommandConfiguration() {

        return List.of(
                CommandConfigurationResponseDTO
                        .builder()
                        .id(1L)
                        .name("VALIDATE_ORDER_DETAIL_COMMAND")
                        .description("comando de pruebas")
                        .sequence(1)
                        .preconditionCommands(List.of())
                        .policiesReprocess(List.of())
                        .processFlow(ProcessFlowDTO.builder().build())
                        .build(),
                CommandConfigurationResponseDTO
                        .builder()
                        .id(2l)
                        .name("PRINT_KITECHEN_COMMAND")
                        .description("comando de pruebas")
                        .sequence(2)
                        .preconditionCommands(List.of(
                                PreconditionCommandResponseDTO
                                        .builder()
                                        .id(1l)
                                        .name("VALIDATE_ORDER_DETAIL_COMMAND")
                                        .build()))
                        .policiesReprocess(List.of(
                                PoliticsReprocessResponseDTO
                                        .builder()
                                        .id(1l)
                                        .description("no conexion con impresora")
                                        .build()))
                        .processFlow(ProcessFlowDTO.builder()
                                .id(1L)
                                .name("PROCESS_TEST")
                                .proxyFlow(Boolean.TRUE)
                                .build())
                        .build());
    }
}
