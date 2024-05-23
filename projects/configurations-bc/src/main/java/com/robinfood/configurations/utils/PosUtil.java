package com.robinfood.configurations.utils;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.dto.v1.listposresponse.PosResponseDTO;
import com.robinfood.configurations.dto.v1.listposresponse.ResolutionResponseDTO;
import com.robinfood.configurations.dto.v1.listposresponse.StorePosResponseDTO;
import com.robinfood.configurations.dto.v1.models.CreatePosDTO;
import com.robinfood.configurations.dto.v1.models.UpdatePosDTO;
import com.robinfood.configurations.models.Pos;
import com.robinfood.configurations.models.PosType;
import lombok.extern.slf4j.Slf4j;

import static com.robinfood.configurations.constants.ConfigurationsBCConstants.DEFAULT_LONG;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.DEFAULT_LONG_ONE;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.DEFAULT_ONE_INTEGER;

@Slf4j
public final class PosUtil {

    private PosUtil() {
    }

    @BasicLog
    public static Pos buildFromPosDTOToPos(CreatePosDTO createPosDTO) {

        Long status = DEFAULT_LONG;

        if (createPosDTO.getStatus()) {
            status = DEFAULT_LONG_ONE;
        }

        return Pos.builder()
                .name(createPosDTO.getName())
                .code(createPosDTO.getCode())
                .posType(PosType.builder()
                        .id(createPosDTO.getPosTypeId())
                        .build())
                .status(status)
                .build();
    }

    @BasicLog
    public static Pos buildFromUpdateDTOToPos(UpdatePosDTO updatePosDTO) {

        Long status = DEFAULT_LONG;

        if (updatePosDTO.getStatus()) {
            status = DEFAULT_LONG_ONE;
        }

        return Pos.builder()
                .code(updatePosDTO.getCode())
                .name(updatePosDTO.getName())
                .posType(PosType.builder()
                        .id(updatePosDTO.getPosTypeId())
                        .build())
                .status(status)
                .build();
    }

    @BasicLog
    public static PosResponseDTO buildPosResponse(Pos pos) {

        PosResponseDTO responseDTO = PosResponseDTO.builder()
                .code(pos.getCode())
                .name(pos.getName())
                .posTypeId(pos.getPosType().getId())
                .status(pos.getStatus().equals(DEFAULT_LONG_ONE))
                .id(pos.getId())
                .store(
                        StorePosResponseDTO.builder()
                                .id(pos.getStore().getId())
                                .name(pos.getStore().getName())
                                .build()
                )
                .build();

        if (!pos.getResolutionList().isEmpty()) {
            responseDTO.setResolution(resolutionActive(pos));
        }

        if (pos.getResolutionList().isEmpty()) {
            responseDTO.setResolution(new ResolutionResponseDTO());
        }

        return responseDTO;
    }

    private static ResolutionResponseDTO resolutionActive(Pos pos) {

        return pos.getResolutionList().stream()
                .filter(resolution -> resolution.getStatus().equals(DEFAULT_ONE_INTEGER))
                .findFirst()
                .map(resolution -> ResolutionResponseDTO.builder()
                        .endDate(resolution.getEndDate())
                        .finalNumber(resolution.getFinalNumber())
                        .name(resolution.getName())
                        .prefix(resolution.getPrefix())
                        .startDate(resolution.getStartDate())
                        .id(resolution.getId())
                        .invoiceNumberResolutions(resolution.getInvoiceNumberResolutions())
                        .startingNumber(resolution.getStartingNumber())
                        .build())
                .orElse(new ResolutionResponseDTO());
    }
}
