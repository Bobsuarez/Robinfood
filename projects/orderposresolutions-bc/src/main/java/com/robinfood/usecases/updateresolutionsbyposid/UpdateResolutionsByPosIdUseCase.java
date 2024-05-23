package com.robinfood.usecases.updateresolutionsbyposid;

import com.robinfood.entities.db.mysql.PosResolutionEntity;
import com.robinfood.enums.AppLogsEnum;
import com.robinfood.repository.posresolution.IPosResolutionRepository;
import com.robinfood.repository.posresolution.PosResolutionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@AllArgsConstructor
@Slf4j
public class UpdateResolutionsByPosIdUseCase implements IUpdateResolutionsByPosIdUseCase{

    private final IPosResolutionRepository posResolutionRepository;

    private static UpdateResolutionsByPosIdUseCase instance;

    public UpdateResolutionsByPosIdUseCase() {
        posResolutionRepository = PosResolutionRepository.getInstance();
    }

    public static UpdateResolutionsByPosIdUseCase getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UpdateResolutionsByPosIdUseCase();
        }
        return instance;
    }

    @Override
    public void invoke(Long id, Long posId) {

        log.info(AppLogsEnum.UPDATE_BY_POS_ID_INVOKE.getMessageWithCode(), id, posId);

        final PosResolutionEntity posResolutionEntityFound = posResolutionRepository.findById(id);
        posResolutionEntityFound.setPos_id(posId);

        posResolutionRepository.updateByResolutionId(posResolutionEntityFound);

    }
}
