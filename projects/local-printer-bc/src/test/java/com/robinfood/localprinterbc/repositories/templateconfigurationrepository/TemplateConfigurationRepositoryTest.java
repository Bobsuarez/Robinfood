package com.robinfood.localprinterbc.repositories.templateconfigurationrepository;

import com.robinfood.localprinterbc.entities.PrintingTemplateEntity;
import com.robinfood.localprinterbc.entities.TemplateEntity;
import com.robinfood.localprinterbc.entities.PrintingTemplateBrandGroupsEntity;
import com.robinfood.localprinterbc.entities.PrintingTemplateTypeEntity;
import com.robinfood.localprinterbc.entities.APIResponseEntity;
import com.robinfood.localprinterbc.feings.ConfigurationOrderOrLocalServerAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TemplateConfigurationRepositoryTest {

    @Mock
    private ConfigurationOrderOrLocalServerAPI configurationOrderOrLocalServerAPI;

    @InjectMocks
    private TemplateConfigurationRepository templateConfigurationRepository;
    private final String token = "token";

    private final Long storeId = 1L;

    private final PrintingTemplateEntity printingTemplateEntity = PrintingTemplateEntity.builder()
            .printingTemplateBrands(
                    Arrays.asList(
                            PrintingTemplateBrandGroupsEntity
                                    .builder()
                                    .id(1L)
                                    .groupId(1L)
                                    .active(Boolean.TRUE)
                                    .printingTemplateId(1L)
                                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                                    .build()
                    )
            )
            .templates(
                    Arrays.asList(
                            TemplateEntity
                                    .builder()
                                    .templateId(1L)
                                    .groupId(1L)
                                    .groupName("Group")
                                    .templateString("<template>test</template>")
                                    .rules(new ArrayList<>())
                                    .templateType(PrintingTemplateTypeEntity.builder().build())
                                    .build(),
                            TemplateEntity
                                    .builder()
                                    .templateId(2L)
                                    .groupId(1L)
                                    .groupName("Group")
                                    .templateString("<template>test two</template>")
                                    .rules(new ArrayList<>())
                                    .templateType(PrintingTemplateTypeEntity.builder().build())
                                    .build()
                    )
            )
            .build();

    private final APIResponseEntity<PrintingTemplateEntity> apiResponseEntity =
            APIResponseEntity.<PrintingTemplateEntity>builder()
                    .code(200)
                    .data(printingTemplateEntity)
                    .locale("CO")
                    .message("")
                    .status("Order detail returned")
                    .build();

    @Test
    void findTemplateByIdStore() {

        when(configurationOrderOrLocalServerAPI.getTemplatesByStore(token, storeId))
                .thenReturn(apiResponseEntity);

        PrintingTemplateEntity printingTemplateEntity = templateConfigurationRepository.findTemplateByIdStore(token, storeId);

        Assertions.assertEquals(
                printingTemplateEntity.getTemplates().size(),
                apiResponseEntity.getData().getTemplates().size());
    }
}