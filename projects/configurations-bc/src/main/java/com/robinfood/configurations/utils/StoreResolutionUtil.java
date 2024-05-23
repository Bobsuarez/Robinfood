package com.robinfood.configurations.utils;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.dto.v1.ResponseResolutionsWithPosDTO;
import com.robinfood.configurations.dto.v1.StoreResolutionDTO;
import com.robinfood.configurations.models.Resolution;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.robinfood.configurations.constants.ConfigurationsBCConstants.DEFAULT_INTEGER;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.DEFAULT_ONE_INTEGER;

@Slf4j
public final class StoreResolutionUtil {

    private StoreResolutionUtil() {
    }

    @BasicLog
    public static List<ResponseResolutionsWithPosDTO> buildResponseResolutionsWithPosDTO(List<Resolution> resolutions) {

        List<ResponseResolutionsWithPosDTO> responseResolutionsWithPosDTOS = new ArrayList<>();

        resolutions.forEach(resolution -> responseResolutionsWithPosDTOS.add(
                ResponseResolutionsWithPosDTO.builder()
                        .id(resolution.getId())
                        .posId(resolution.getPosId())
                        .build())
        );

        return responseResolutionsWithPosDTOS;
    }

    @BasicLog
    public static List<Resolution> buildResolutions(List<StoreResolutionDTO> storeResolutionDTOS) {

        log.info("Generating mappedStore for store resolutions: {}", JsonUtils.convertToJson(storeResolutionDTOS));

        List<Resolution> resolutions = new ArrayList<>();

        storeResolutionDTOS.forEach((StoreResolutionDTO storeResolutionDTO) -> {

            int status = DEFAULT_INTEGER;

            if (storeResolutionDTO.getStatus().equals(Boolean.TRUE)) {
                status = DEFAULT_ONE_INTEGER;
            }

            resolutions.add(buildResolutionWithStatus(storeResolutionDTO, status));
        });

        return resolutions;
    }

    public static Resolution buildResolutionWithStatus(StoreResolutionDTO storeResolutionDTO, Integer status) {
        return Resolution.builder()
                .document(storeResolutionDTO.getDocument())
                .endDate(storeResolutionDTO.getEndDate())
                .finalNumber(storeResolutionDTO.getFinalNumber().toString())
                .invoiceNumberResolutions(storeResolutionDTO.getInvoiceNumber())
                .invoiceText(storeResolutionDTO.getInvoiceText())
                .name(storeResolutionDTO.getName())
                .posId(storeResolutionDTO.getPosId())
                .prefix(storeResolutionDTO.getPrefix())
                .serial(storeResolutionDTO.getSerial())
                .startDate(storeResolutionDTO.getStartDate())
                .startingNumber(storeResolutionDTO.getStartingNumber().toString())
                .status(status)
                .build();
    }

    public static Resolution buildResolutionByUpdate(
            Resolution resolution,
            StoreResolutionDTO storeResolutionDTOS
    ) {

        return resolution.toBuilder()
                .document(storeResolutionDTOS.getDocument())
                .endDate(storeResolutionDTOS.getEndDate())
                .finalNumber(storeResolutionDTOS.getFinalNumber().toString())
                .invoiceNumberResolutions(storeResolutionDTOS.getInvoiceNumber())
                .invoiceText(storeResolutionDTOS.getInvoiceText())
                .name(storeResolutionDTOS.getName())
                .posId(storeResolutionDTOS.getPosId())
                .prefix(storeResolutionDTOS.getPrefix())
                .serial(storeResolutionDTOS.getSerial())
                .startDate(storeResolutionDTOS.getStartDate())
                .startingNumber(String.valueOf(storeResolutionDTOS.getStartingNumber()))
                .status(DataToolsUtil.convertToStatusInt(storeResolutionDTOS.getStatus()))
                .build();
    }
}
