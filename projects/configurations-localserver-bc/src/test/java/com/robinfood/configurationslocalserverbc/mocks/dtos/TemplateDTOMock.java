package com.robinfood.configurationslocalserverbc.mocks.dtos;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTypesDTO;
import com.robinfood.configurationslocalserverbc.dtos.templateResponse.TemplateDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemplateDTOMock {

    public List<TemplateDTO> templateDTOSMock = Arrays.asList(
            TemplateDTO
                    .builder()
                    .templateId(1L)
                    .groupId(1L)
                    .groupName("Group")
                    .templateString("<template>test</template>")
                    .rules(new ArrayList<>())
                    .templateType(PrintingTemplateTypesDTO.builder().build())
                    .build(),
            TemplateDTO
                    .builder()
                    .templateId(2L)
                    .groupId(1L)
                    .groupName("Group")
                    .templateString("<template>test two</template>")
                    .rules(new ArrayList<>())
                    .templateType(PrintingTemplateTypesDTO.builder().build())
                    .build()
    );

    public List<TemplateDTO> templateDTOSResponseControllerMock = Arrays.asList(
            TemplateDTO
                    .builder()
                    .templateId(1L)
                    .groupId(1L)
                    .groupName("Group")
                    .templateString("<template>test</template>")
                    .rules(new ArrayList<>())
                    .templateType(PrintingTemplateTypesDTO.builder().build())
                    .build(),
            TemplateDTO
                    .builder()
                    .templateId(2L)
                    .groupId(1L)
                    .groupName("Group")
                    .templateString("<template>test two</template>")
                    .rules(new ArrayList<>())
                    .templateType(PrintingTemplateTypesDTO.builder().build())
                    .build()
    );

}
