package com.robinfood.orderorlocalserver.repositories.printingtemplate;

import com.robinfood.orderorlocalserver.entities.APIResponseEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailEntity;
import com.robinfood.orderorlocalserver.entities.printingtemplate.PrintingTemplateBrandGroupsEntity;
import com.robinfood.orderorlocalserver.entities.printingtemplate.PrintingTemplateEntity;
import com.robinfood.orderorlocalserver.entities.printingtemplate.PrintingTemplateTypeEntity;
import com.robinfood.orderorlocalserver.entities.printingtemplate.TemplateEntity;
import com.robinfood.orderorlocalserver.network.ConfigurationsLocalServerBcAPI;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrintingTemplateRepositoryTest {

    @Mock
    private ConfigurationsLocalServerBcAPI configurationsLocalServerBcAPI;

    @InjectMocks
    private PrintingTemplateRepository printingTemplateRepository;

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
    void test_GetTemplatesByStore_Returns_Successfully() {

        when(configurationsLocalServerBcAPI.getTemplatesByStore(token, storeId))
                .thenReturn(apiResponseEntity);

        PrintingTemplateEntity printingTemplateEntity =
                printingTemplateRepository.getTemplatesByStore(token, storeId);

        Assertions.assertEquals(
                printingTemplateEntity.getTemplates().size(),
                apiResponseEntity.getData().getTemplates().size()
        );
    }
}
