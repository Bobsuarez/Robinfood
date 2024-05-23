package com.robinfood.storeor.usecase.getcommandconfigurationbystoreid;

import com.robinfood.storeor.dtos.CommandConfiguration.CommandConfigurationResponseDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.CommandConfiguration.CommandConfigurationEntity;
import com.robinfood.storeor.mappers.ICommandConfigurationMapper;
import com.robinfood.storeor.mocks.dto.commandConfiguration.CommandConfigurationResponseDTOMock;
import com.robinfood.storeor.mocks.entity.CommandConfiguration.CommandConfigurationListEntityMock;
import com.robinfood.storeor.repositories.billingrepository.IBillingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCommandConfigurationByStoreIdUseCaseTest {

    @Mock
    private IBillingRepository billingRepository;

    @InjectMocks
    private GetCommandConfigurationByStoreIdUseCase getCommandConfigurationByStoreIdUseCase;

    @Spy
    private ICommandConfigurationMapper commandConfigurationMapper =
            Mappers.getMapper(ICommandConfigurationMapper.class);

    private final CommandConfigurationListEntityMock commandConfigurationListEntity=
            new CommandConfigurationListEntityMock();

    private final CommandConfigurationResponseDTOMock commandConfigurationResponseDTO=
            new CommandConfigurationResponseDTOMock();

    private final String token = "token";

    private final Long storeId = 1L;

    @Test
   void test_GetCommandConfigurationByStoreIdUseCase_Return_Succesfully(){

        final APIResponseEntity<List<CommandConfigurationEntity> > apiResponse = new APIResponseEntity<>(
                200,
                commandConfigurationListEntity.defaultData(),
                "locale",
                "Store command Configuration obtained",
                "Success"
        );

        when(billingRepository.getCommandConfiguration(storeId,token)).thenReturn(apiResponse);

        List<CommandConfigurationResponseDTO> response=
                getCommandConfigurationByStoreIdUseCase.invoke(storeId,token);

        assertEquals(commandConfigurationResponseDTO.getDefautlListCommandConfiguration(),response);
    }
}
