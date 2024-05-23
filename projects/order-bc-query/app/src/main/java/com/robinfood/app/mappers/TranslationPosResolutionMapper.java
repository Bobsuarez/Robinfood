package com.robinfood.app.mappers;

import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
import com.robinfood.core.entities.PosResolutionsEntity;

public class TranslationPosResolutionMapper {

    public static GetPosResolutionsDTO buildGetPosResponseDTO(PosResolutionsEntity posEntity) {

        return GetPosResolutionsDTO.builder()
                .current(posEntity.getCurrent())
                .dicStatusId(posEntity.getDicStatusId())
                .endDate(posEntity.getEndDate())
                .id(posEntity.getId())
                .initialDate(posEntity.getInitialDate())
                .name(posEntity.getName())
                .posId(posEntity.getPosId())
                .prefix(posEntity.getPrefix())
                .storeId(posEntity.getStoreId())
                .typeDocument(posEntity.getTypeDocument())
                .build();
    }
}
