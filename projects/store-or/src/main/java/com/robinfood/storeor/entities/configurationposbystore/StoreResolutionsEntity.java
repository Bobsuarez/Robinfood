package com.robinfood.storeor.entities.configurationposbystore;

import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class StoreResolutionsEntity {

    private List<ResolutionEntity> resolutions;

    private Long storeId;
}
