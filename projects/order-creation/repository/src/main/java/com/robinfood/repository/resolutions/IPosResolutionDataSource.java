package com.robinfood.repository.resolutions;

import com.robinfood.core.entities.InformationPosResolutionsResponseEntity;

import java.util.concurrent.CompletableFuture;

/**
 * Represents information about a repository that has been successfully
 */
public interface IPosResolutionDataSource {

    /**
     * Returns information of a pos id of the repository
     *
     * @param token security token that identifies the repository
     * @param posId the id of the repository to retrieve information
     *
     * @return information about the repository
     */
    CompletableFuture<InformationPosResolutionsResponseEntity> getInformationPosResolution(
            String token,
            Long posId
    );

}
