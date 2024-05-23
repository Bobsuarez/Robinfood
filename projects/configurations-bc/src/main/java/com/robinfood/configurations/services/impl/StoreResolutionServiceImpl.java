package com.robinfood.configurations.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.DEFAULT_INTEGER;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.DEFAULT_ONE_INTEGER;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.dto.v1.ActivateOrDeactivateDTO;
import com.robinfood.configurations.dto.v1.ResponseResolutionsWithPosDTO;
import com.robinfood.configurations.dto.v1.StoreResolutionDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.Resolution;
import com.robinfood.configurations.repositories.jpa.ResolutionRepository;
import com.robinfood.configurations.services.StoreResolutionService;
import com.robinfood.configurations.utils.StoreResolutionUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.robinfood.configurations.utils.StoreResolutionUtil.buildResolutionByUpdate;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class StoreResolutionServiceImpl implements StoreResolutionService {

    private final ResolutionRepository resolutionRepository;
    private final ObjectMapper objectMapper;

    public StoreResolutionServiceImpl(ResolutionRepository resolutionRepository, ObjectMapper objectMapper) {
        this.resolutionRepository = resolutionRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<ResponseResolutionsWithPosDTO> create(List<StoreResolutionDTO> storeResolutionDTOS) {

        validateActiveResolutions(storeResolutionDTOS);
        turnResolutionsOnAndOff(storeResolutionDTOS);

        final List<Resolution> resolutions = StoreResolutionUtil.buildResolutions(storeResolutionDTOS);
        final List<Resolution> saveResolutions = resolutionRepository.saveAll(resolutions);

        log.info("Saved resolutions: {}", saveResolutions);

        return StoreResolutionUtil
                .buildResponseResolutionsWithPosDTO(saveResolutions);
    }

    private void validateActiveResolutions(List<StoreResolutionDTO> storeResolutionDTOS) {

        storeResolutionDTOS.forEach((StoreResolutionDTO storeResolutionDTO) -> {

            final boolean isExistsByPosId = resolutionRepository.existsByPosIdAndStatus(
                    storeResolutionDTO.getPosId(),
                    DEFAULT_ONE_INTEGER
            );

            alreadyHasAnActiveResolutionException(isExistsByPosId, storeResolutionDTO);
        });
    }

    @SneakyThrows
    private void alreadyHasAnActiveResolutionException(
            Boolean isExistsByPosId,
            StoreResolutionDTO storeResolutionDTO
    ) {
        if (isExistsByPosId && storeResolutionDTO.getStatus() && !storeResolutionDTO.getConfirmed()) {
            throw new BusinessRuleException(
                    HttpStatus.PRECONDITION_FAILED,
                    String.format(
                            "The pos with identifier %s already has an active resolution in the system",
                            storeResolutionDTO.getPosId()
                    )
            );
        }
    }

    private void turnResolutionsOnAndOff(List<StoreResolutionDTO> storeResolutionDTOS) {

        storeResolutionDTOS.forEach((StoreResolutionDTO storeResolutionDTO) -> {

            final Optional<Resolution> resolution = resolutionRepository.findByPosIdAndStatus(
                    storeResolutionDTO.getPosId(),
                    DEFAULT_ONE_INTEGER);

            if (storeResolutionDTO.getStatus() && storeResolutionDTO.getConfirmed() && resolution.isPresent()) {
                resolution.get().setStatus(DEFAULT_INTEGER);
                resolutionRepository.save(resolution.get());
            }
        });
    }

    @Override
    public String update(StoreResolutionDTO storeResolutionDTOS, Long resolutionId)
            throws BusinessRuleException {

        Resolution resolutionFound = resolutionRepository.findById(resolutionId)
                .orElseThrow(() -> new BusinessRuleException(
                        HttpStatus.NOT_FOUND,
                        String.format(
                                "The resolution with identifier %s is not found",
                                resolutionId
                        )
                ));

        validateActiveResolutions(List.of(storeResolutionDTOS));
        turnResolutionsListOnAndOff(storeResolutionDTOS);

        final Resolution resolutionToSave = buildResolutionByUpdate(resolutionFound, storeResolutionDTOS);

        final Resolution resolutionSaved = resolutionRepository.save(resolutionToSave);

        log.info("Update resolutions: {}", resolutionSaved);

        JsonNode emptyJson = objectMapper.createObjectNode();

        return emptyJson.toString();
    }

    private void turnResolutionsListOnAndOff(StoreResolutionDTO storeResolutionDTOS) {

        List<Resolution> allByPosIdAndStatus = resolutionRepository.findAllByPosIdAndStatus(
                storeResolutionDTOS.getPosId(),
                DEFAULT_ONE_INTEGER
        ).orElse(Collections.emptyList());

        allByPosIdAndStatus.forEach(
                (Resolution resultResolution) -> {
                    resultResolution.setStatus(DEFAULT_INTEGER);
                    resolutionRepository.save(resultResolution);
                }
        );
    }

    @BasicLog
    @SneakyThrows
    public Boolean isActivateOrDeactivateByResolutionId(
            ActivateOrDeactivateDTO activateOrDeactivateDTO,
            Long resolutionId
    ) {
        final Optional<Resolution> findResolution = resolutionRepository.findById(resolutionId);

        if (findResolution.isEmpty()) {
            throw new BusinessRuleException("Resolution not found");
        }

        Integer status = DEFAULT_INTEGER;

        if (activateOrDeactivateDTO.getStatus().equals(Boolean.TRUE)) {

            status = DEFAULT_ONE_INTEGER;

            final Optional<Resolution> findResolutionActive = resolutionRepository.findByPosIdAndStatus(
                    findResolution.get().getPosId(), status
            );

            findResolutionActive.ifPresent((Resolution resolution) -> resolution.setStatus(DEFAULT_INTEGER));
        }

        findResolution.get().setStatus(status);
        resolutionRepository.save(findResolution.get());

        return Boolean.TRUE;
    }
}
