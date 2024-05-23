package com.robinfood.configurations.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.dto.v1.StorePosDTO;
import com.robinfood.configurations.dto.v1.models.ActivePosDTO;
import com.robinfood.configurations.dto.v1.models.UpdatePosDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.Pos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PosService {

    Long findPosId(Long storeId, Long posTypeId);

    Pos findById(Long posId);

    StorePosDTO create(StorePosDTO storePosDTO) throws JsonProcessingException, BusinessRuleException;

    Pos update(Long posId, UpdatePosDTO updatePosDTO) throws JsonProcessingException, BusinessRuleException;

    Pos activePos(ActivePosDTO activePosDTO, Long posId) throws JsonProcessingException, BusinessRuleException;

    /**
     * get all pos per store
     *
     * @param storeId identifier store
     * @return list of Pos
     */
    List<Pos> findByStoreId(Long storeId);

    Page<Pos> list(String name, Long status, Long storeId, Pageable pageable)
            throws JsonProcessingException;

}
