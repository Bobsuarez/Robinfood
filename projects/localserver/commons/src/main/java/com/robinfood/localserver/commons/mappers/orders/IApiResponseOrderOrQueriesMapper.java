package com.robinfood.localserver.commons.mappers.orders;

import com.robinfood.localserver.commons.dtos.http.ApiResponseOrderOrQueriesDTO;
import com.robinfood.localserver.commons.entities.http.ApiResponseOrderOrQueriesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IApiResponseOrderOrQueriesMapper {

    ApiResponseOrderOrQueriesDTO apiResponseOrderOrQueriesEntityToApiResponseOrderOrQueriesDTO(
            ApiResponseOrderOrQueriesEntity apiResponseOrderOrQueriesEntity);
}
