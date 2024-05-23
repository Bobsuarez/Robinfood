package com.robinfood.repository.resolutions;

import com.robinfood.core.entities.InformationPosResolutionsResponseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class PosResolutionRepository implements IPosResolutionRepository {

    private final IPosResolutionDataSource posResolutionDataSource;

    @Override
    public InformationPosResolutionsResponseEntity getInformationPosResolution(
            String token, Long posId
    ) {
        return posResolutionDataSource.getInformationPosResolution(token, posId)
                .join();
    }
}
