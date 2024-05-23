package com.robinfood.app.usecases.getoriginsbyids;

import com.robinfood.core.dtos.OriginDTO;

import java.util.List;

/**
 * Use Case Get origins by Identifiers
 */
public interface IGetOriginsByIdsUseCase {

    /**
     * Get origins by Identifiers
     *
     * @param originsIds Identifiers of the origins to search
     * @return List Origins
     */
    List<OriginDTO> invoke(List<Long> originsIds);

}
