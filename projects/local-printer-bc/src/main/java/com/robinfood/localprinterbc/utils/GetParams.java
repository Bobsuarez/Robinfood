package com.robinfood.localprinterbc.utils;

import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public final class GetParams {

    public GetParams() {
    }

    public static List<String> getParamsTemplate(TemplateDTO templateDTO, String name) {
        List<String> params = new ArrayList<>();

        templateDTO.getRules().forEach(transformRulesDTO -> {
            if (transformRulesDTO.getName().equals(name)) {
                params.add(transformRulesDTO.getParams());
            }
        });

        return params;
    }
}
