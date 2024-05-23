package com.robinfood.configurations.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.dto.v1.ResolutionsIdsDTO;
import com.robinfood.configurations.dto.v1.StorePosDTO;
import com.robinfood.configurations.dto.v1.models.ActivePosDTO;
import com.robinfood.configurations.dto.v1.models.CreatePosDTO;
import com.robinfood.configurations.dto.v1.models.UpdatePosDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.Pos;
import com.robinfood.configurations.models.Resolution;
import com.robinfood.configurations.models.Store;
import com.robinfood.configurations.repositories.jpa.PosRepository;
import com.robinfood.configurations.repositories.jpa.ResolutionRepository;
import com.robinfood.configurations.services.PosService;
import com.robinfood.configurations.services.StoreService;
import com.robinfood.configurations.utils.PosUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.robinfood.configurations.constants.ConfigurationsBCConstants.DEFAULT_LONG;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.DEFAULT_LONG_ONE;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PosServiceImpl implements PosService {

    private final PosRepository posRepository;
    private final ResolutionRepository resolutionRepository;
    private final StoreService storeService;


    public PosServiceImpl(
            PosRepository posRepository,
            ResolutionRepository resolutionRepository,
            StoreService storeService
    ) {
        this.posRepository = posRepository;
        this.resolutionRepository = resolutionRepository;
        this.storeService = storeService;
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public Long findPosId(Long storeId, Long posTypeId) {
        Long posId = posRepository.findPosId(storeId, posTypeId);
        if (posId == null) {
            throw new EntityNotFoundException(
                    String.format("POS id with store id %d and pos type id %d does not exists.",
                            storeId, posTypeId));
        }

        return posId;
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public Pos findById(Long posId) {
        return posRepository.findById(posId).orElseThrow(() ->
                new EntityNotFoundException(String.format("POS with id %d not found.", posId)));
    }

    @Override
    @BasicLog
    public StorePosDTO create(StorePosDTO storePosDTO) throws JsonProcessingException, BusinessRuleException {

        Store store = this.storeService.findById(storePosDTO.getStoreId());

        List<Pos> listPos = new ArrayList<>();

        storePosDTO.getPos().stream().forEach((CreatePosDTO createPosDTO) -> {

            Pos createPos = PosUtil.buildFromPosDTOToPos(createPosDTO);

            createPos.setStore(store);

            listPos.add(createPos);

            Pos posSaved = posRepository.saveAndFlush(createPos);
            savePosResolutions(posSaved.getId(), createPosDTO.getResolutionsIds());
        });


        List<CreatePosDTO> createPosDTOList = new ArrayList<>();

        for (Pos pos : listPos) {
            CreatePosDTO createPosDTO = mapPosToDTO(pos);
            createPosDTOList.add(createPosDTO);
        }

        storePosDTO.setPos(createPosDTOList);

        return storePosDTO;
    }

    private void savePosResolutions(Long posId, ResolutionsIdsDTO resolutionsIds) {

        if (Objects.isNull(resolutionsIds.getResolutionId())) {
            return;
        }

        final Long resolutionId = resolutionsIds.getResolutionId();

        Optional<Resolution> optionalResolution = Optional.ofNullable(resolutionRepository.findById(resolutionId)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Resolutions not found %d", resolutionId))));

        Resolution resolution = optionalResolution.get();
        resolution.setPosId(posId);

        resolutionRepository.saveAndFlush(resolution);
    }

    @Override
    @BasicLog
    public Pos update(Long posId, UpdatePosDTO updatePosDTO) throws JsonProcessingException, BusinessRuleException {

        final Pos posToSaveDB = findById(posId);

        Pos posCurrent = PosUtil.buildFromUpdateDTOToPos(updatePosDTO);

        posToSaveDB.setCode(posCurrent.getCode());
        posToSaveDB.setName(posCurrent.getName());
        posToSaveDB.setPosType(posCurrent.getPosType());
        posToSaveDB.setStatus(posCurrent.getStatus());

        Pos posUpdated = this.posRepository.save(posToSaveDB);
        savePosResolutions(posUpdated.getId(), updatePosDTO.getResolutionsIds());

        return posUpdated;
    }

    @Override
    @BasicLog
    public Pos activePos(ActivePosDTO activePosDTO, Long posId) throws JsonProcessingException, BusinessRuleException {

        final Pos posToSaveDB = findById(posId);

        Long status = DEFAULT_LONG;

        if (activePosDTO.getStatus()) {
            status = DEFAULT_LONG_ONE;
        }

        posToSaveDB.setStatus(status);

        return this.posRepository.save(posToSaveDB);
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public List<Pos> findByStoreId(Long storeId) {
        Optional<List<Pos>> listResponse = Optional.ofNullable(posRepository.findAllByStoreId(storeId)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("POS with StoreId %d not found.", storeId))));
        return listResponse.get();
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public Page<Pos> list(String name, Long status, Long storeId, Pageable pageable)
            throws JsonProcessingException {
        return posRepository
                .findByStoreIdAndNameAndStatus(name, status, storeId, pageable);
    }

    private CreatePosDTO mapPosToDTO(Pos pos) {
        return CreatePosDTO.builder()
                .status(pos.getStatus().equals(1L))
                .code(pos.getCode())
                .name(pos.getName())
                .posTypeId(pos.getPosType().getId())
                .id(pos.getId())
                .build();
    }

}
