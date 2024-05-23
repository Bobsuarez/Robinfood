package com.robinfood.usecases.updateresolutionsbyresolutionid;

import com.robinfood.constants.GeneralConstants;
import com.robinfood.dtos.v1.request.ResolutionUpdateDTO;
import com.robinfood.entities.db.mysql.PosResolutionEntity;
import com.robinfood.enums.AppLogsEnum;
import com.robinfood.exceptions.BusinessRuleException;
import com.robinfood.mappers.PosResolutionMapper;
import com.robinfood.repository.posresolution.IPosResolutionRepository;
import com.robinfood.repository.posresolution.PosResolutionRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import java.util.Objects;

@AllArgsConstructor
@Slf4j
public class UpdateResolutionsByResolutionIdUseCase implements IUpdateResolutionsByResolutionIdUseCase {

    private final IPosResolutionRepository posResolutionRepository;

    private static UpdateResolutionsByResolutionIdUseCase instance;

    public UpdateResolutionsByResolutionIdUseCase() {
        posResolutionRepository = PosResolutionRepository.getInstance();
    }

    public static UpdateResolutionsByResolutionIdUseCase getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UpdateResolutionsByResolutionIdUseCase();
        }
        return instance;
    }

    @Override
    public void invoke(Long resolutionId, ResolutionUpdateDTO resolution) {

        log.info(AppLogsEnum.UPDATE_INVOKE.getMessageWithCode(), resolutionId, resolution);

        final PosResolutionEntity posResolutionEntityFound = posResolutionRepository.findByResolutionId(resolutionId);

        validateActiveResolutions(resolution);

        turnResolutionsOnAndOff(resolution);

        resolution.setId(posResolutionEntityFound.getId());

        PosResolutionEntity posResolutionEntity = PosResolutionMapper.buildPosResolutionEntity(resolution);

        posResolutionRepository.updateByResolutionId(posResolutionEntity);

    }

    private void validateActiveResolutions(ResolutionUpdateDTO resolution) {
        final boolean isExistsByPosId = posResolutionRepository.existsByPosIdAndStatus(resolution.getPosId(),
                Boolean.TRUE);
        alreadyHasAnActiveResolutionException(isExistsByPosId, resolution);
    }

    @SneakyThrows
    private static void alreadyHasAnActiveResolutionException(
            Boolean isExistsByPosId,
            ResolutionUpdateDTO resolutionDTO
    ) {
        if (isExistsByPosId && resolutionDTO.getStatus() && !resolutionDTO.getConfirmed().equals(Boolean.TRUE)) {
            throw new BusinessRuleException(
                    HttpStatus.SC_BAD_REQUEST,
                    String.format(
                            "The pos with identifier %s already has an active resolution in the system",
                            resolutionDTO.getPosId()
                    )
            );
        }
    }


    private void turnResolutionsOnAndOff(ResolutionUpdateDTO resolution) {
        final PosResolutionEntity posResolutionEntity = findStatusTrueByPosId(resolution.getPosId());

        if (resolution.getStatus() && resolution.getConfirmed() &&
                Objects.nonNull(posResolutionEntity.getId())
        ) {
            posResolutionEntity.setDic_status_id(GeneralConstants.DEFAULT_INTEGER);
            posResolutionEntity.setCurrent(GeneralConstants.DEFAULT_INTEGER);
            posResolutionRepository.updateCurrentAndDicStatusIdById(posResolutionEntity);
        }

    }

    private PosResolutionEntity findStatusTrueByPosId(Long posId) {
        return posResolutionRepository.findByPosIdAndStatus(posId, Boolean.TRUE);
    }

}
