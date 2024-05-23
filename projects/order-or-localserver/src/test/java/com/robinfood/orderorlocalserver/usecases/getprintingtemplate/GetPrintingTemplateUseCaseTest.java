package com.robinfood.orderorlocalserver.usecases.getprintingtemplate;

import com.robinfood.orderorlocalserver.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.orderorlocalserver.entities.printingtemplate.PrintingTemplateBrandGroupsEntity;
import com.robinfood.orderorlocalserver.entities.printingtemplate.PrintingTemplateEntity;
import com.robinfood.orderorlocalserver.entities.printingtemplate.PrintingTemplateTypeEntity;
import com.robinfood.orderorlocalserver.entities.printingtemplate.TemplateEntity;
import com.robinfood.orderorlocalserver.entities.token.TokenModelEntity;
import com.robinfood.orderorlocalserver.mappers.printingtemplate.IPrintingTemplateMapper;
import com.robinfood.orderorlocalserver.repositories.printingtemplate.IPrintingTemplateRepository;
import com.robinfood.orderorlocalserver.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPrintingTemplateUseCaseTest {

    @InjectMocks
    private GetPrintingTemplateUseCase getPrintingTemplateUseCase;

    @Mock
    private IPrintingTemplateRepository iPrintingTemplateRepository;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Spy
    private IPrintingTemplateMapper iPrintingTemplateMapperMappers =
            Mappers.getMapper(IPrintingTemplateMapper.class);

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1MTYyMzkwMjIsImV4cCI6MTgxNjIzOTAyMiwiYXVkIjoiaW50ZXJuYWwiLCJwZXIiOlsicG9zX2NyZWF0ZV9vcmRlciIsIm9yZGVyc19yZWplY3Rfb3JkZXIiXX0.WzKjKJp--J75eAkaPWhLjnuchMTS0MgULNAtB4_lsDOF-T_f4pVPFKPH-mVCVDudCTqPjvIfv8J_VPTEI5AhVQ";

    private final Long storeId = 1L;

    PrintingTemplateEntity printingTemplateEntity = PrintingTemplateEntity.builder()
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
    @Test
    void test_GetPrintingTemplates_When_StoreHasTemplates_Should_PrintingTemplateFound() throws Exception {

        when(iPrintingTemplateRepository.getTemplatesByStore(TOKEN, storeId))
                .thenReturn(printingTemplateEntity);

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModelEntity.builder()
                        .accessToken(TOKEN)
                        .build()
        );

        PrintingTemplateDTO printingTemplateDTO = getPrintingTemplateUseCase.invoke(storeId);

        Assertions.assertEquals(printingTemplateDTO.getTemplates().size(), printingTemplateEntity.getTemplates().size() );
    }
}
