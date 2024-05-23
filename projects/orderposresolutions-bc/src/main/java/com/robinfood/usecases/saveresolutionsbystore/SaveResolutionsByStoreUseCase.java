package com.robinfood.usecases.saveresolutionsbystore;

import com.robinfood.constants.GeneralConstants;
import com.robinfood.dtos.v1.request.ResolutionDTO;
import com.robinfood.dtos.v1.request.StoreResolutionDTO;
import com.robinfood.entities.db.mysql.PosResolutionEntity;
import com.robinfood.exceptions.BusinessRuleException;
import com.robinfood.mappers.StoreResolutionDTOMappers;
import com.robinfood.repository.posresolution.IPosResolutionRepository;
import com.robinfood.repository.posresolution.PosResolutionRepository;
import com.robinfood.util.ObjectMapperSingletonUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Slf4j
public class SaveResolutionsByStoreUseCase implements ISaveResolutionsByStoreUseCase {

    private static SaveResolutionsByStoreUseCase instance;
    private final IPosResolutionRepository posResolutionRepository;

    public SaveResolutionsByStoreUseCase() {
        posResolutionRepository = PosResolutionRepository.getInstance();
    }

    public static SaveResolutionsByStoreUseCase getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SaveResolutionsByStoreUseCase();
        }
        return instance;
    }

    @SneakyThrows
    private static void alreadyHasAnActiveResolutionException(
            Boolean isExistsByPosId,
            ResolutionDTO resolutionDTO
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

    @Override
    public void invoke(StoreResolutionDTO storeResolutionDTO) {

        log.info("Start saving a store's resolution {}", ObjectMapperSingletonUtil.objectToJson(storeResolutionDTO));

        final List<PosResolutionEntity> posResolutionEntities = StoreResolutionDTOMappers
                .buildPosResolutionEntity(storeResolutionDTO);

        log.info(
                "Convert StoreResolutionDTO to list of PosResolutionEntity {}",
                ObjectMapperSingletonUtil.objectToJson(posResolutionEntities)
        );

        validateActiveResolutions(storeResolutionDTO.getResolutions());
        turnResolutionsOnAndOff(storeResolutionDTO.getResolutions());

        final List<BigInteger> ids = posResolutionRepository.saveAll(posResolutionEntities);

        log.info("Were successfully stored as resolutions with the following identifiers {} in the database", ids);
    }

    private void validateActiveResolutions(List<ResolutionDTO> resolutionDTOS) {

        resolutionDTOS.forEach((ResolutionDTO resolutionDTO) -> {
            if(resolutionDTO.getPosId()!=null){
                final boolean isExistsByPosId = posResolutionRepository.existsByPosIdAndStatus(
                        resolutionDTO.getPosId(),
                        Boolean.TRUE
                );
                alreadyHasAnActiveResolutionException(isExistsByPosId, resolutionDTO);
            }
        });
    }


    private void turnResolutionsOnAndOff(List<ResolutionDTO> resolutionDTOS) {
        resolutionDTOS.stream()
                .filter(resolutionDTO -> Objects.nonNull(resolutionDTO.getPosId()) &&
                        Boolean.TRUE.equals(resolutionDTO.getStatus()) &&
                        Boolean.TRUE.equals(resolutionDTO.getConfirmed()))
                .map(resolutionDTO -> findStatusTrueByPosId(resolutionDTO.getPosId()))
                .filter(posResolutionEntity -> Objects.nonNull(posResolutionEntity.getId()))
                .forEach((PosResolutionEntity posResolutionEntity) -> {
                    posResolutionEntity.setDic_status_id(GeneralConstants.DEFAULT_INTEGER);
                    posResolutionEntity.setCurrent(GeneralConstants.DEFAULT_INTEGER);
                    posResolutionRepository.updateCurrentAndDicStatusIdById(posResolutionEntity);
                });
    }


    private PosResolutionEntity findStatusTrueByPosId(Long posId) {
        return posResolutionRepository.findByPosIdAndStatus(posId, Boolean.TRUE);
    }
}
