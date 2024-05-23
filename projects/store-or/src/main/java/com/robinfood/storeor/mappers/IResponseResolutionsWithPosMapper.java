package com.robinfood.storeor.mappers;

import com.robinfood.storeor.dtos.response.ResponseResolutionsWithPosDTO;
import com.robinfood.storeor.entities.response.ResponseResolutionsWithPosEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IResponseResolutionsWithPosMapper {

    List<ResponseResolutionsWithPosDTO> responseResolutionsWithPosEntityToResponseResolutionsWithPosDTO(
            List<ResponseResolutionsWithPosEntity> responseResolutionsWithPosEntities);
}
