package com.robinfood.usecases.enabledisableresolution;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.dtos.v1.request.EnabledDisabledResolutionDTO;
import com.robinfood.entities.db.mysql.PosResolutionEntity;
import com.robinfood.repository.posresolution.IPosResolutionRepository;
import com.robinfood.repository.posresolution.PosResolutionRepository;
import com.robinfood.util.ObjectMapperSingletonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.constants.GeneralConstants.DEFAULT_ONE_INTEGER;
import static com.robinfood.enums.AppLogsEnum.UPDATE_RESOLUTION_START_PROCESS_DATA;
import static com.robinfood.enums.AppLogsEnum.UPDATE_RESOLUTION_SUCCESSFULLY;

@AllArgsConstructor
@Slf4j
public class EnabledDisabledResolutionUseCase implements IEnabledDisabledResolutionUseCase {

    private static EnabledDisabledResolutionUseCase instance;
    private final IPosResolutionRepository posResolutionRepository;
    private final ObjectMapper objectMapper;

    public EnabledDisabledResolutionUseCase() {
        objectMapper = new ObjectMapper();
        posResolutionRepository = PosResolutionRepository.getInstance();
    }

    public static EnabledDisabledResolutionUseCase getInstance() {

        if (Objects.isNull(instance)) {
            instance = new EnabledDisabledResolutionUseCase();
        }
        return instance;
    }

    @Override
    public String invoke(EnabledDisabledResolutionDTO alternateResolutionDTO, String resolutionIdPath) {

        log.info(
                UPDATE_RESOLUTION_START_PROCESS_DATA.getMessageWithCode(),
                ObjectMapperSingletonUtil.objectToJson(alternateResolutionDTO),
                resolutionIdPath
        );

        PosResolutionEntity resolutionFound =
                posResolutionRepository.findByResolutionId(Long.parseLong(resolutionIdPath));

        processAlternateResolution(resolutionFound, alternateResolutionDTO.isStatus());

        log.info(
                UPDATE_RESOLUTION_SUCCESSFULLY.getMessageWithCode(),
                ObjectMapperSingletonUtil.objectToJson(resolutionFound)
        );

        return objectMapper.createObjectNode().toString();

    }

    private void processAlternateResolution(PosResolutionEntity resolutionFound, boolean isStatus) {

        if (isStatus) {
            turnResolutionsListOnAndOff(resolutionFound.getPos_id());
            updateResolution(DEFAULT_ONE_INTEGER, DEFAULT_ONE_INTEGER, resolutionFound);
            return;
        }

        updateResolution(DEFAULT_INTEGER, DEFAULT_INTEGER, resolutionFound);
    }

    private void updateResolution(
            Integer current,
            Integer dicStatus,
            PosResolutionEntity resolutionFound
    ) {

        resolutionFound.setCurrent(current);
        resolutionFound.setDic_status_id(dicStatus);
        posResolutionRepository.updateCurrentAndDicStatusIdById(resolutionFound);
    }

    private void turnResolutionsListOnAndOff(Long posId) {

        List<PosResolutionEntity> allByPosIdAndStatus = posResolutionRepository.findAllByPosIdAndStatus(
                posId,
                DEFAULT_ONE_INTEGER
        );

        allByPosIdAndStatus.forEach((PosResolutionEntity posResolutionEntity) -> {
                    posResolutionEntity.setCurrent(DEFAULT_INTEGER);
                    posResolutionEntity.setDic_status_id(DEFAULT_INTEGER);
                    posResolutionRepository.updateCurrentAndDicStatusIdById(posResolutionEntity);
                }
        );
    }
}
