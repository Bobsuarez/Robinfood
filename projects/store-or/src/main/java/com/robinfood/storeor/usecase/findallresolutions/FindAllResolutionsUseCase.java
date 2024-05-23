package com.robinfood.storeor.usecase.findallresolutions;

import com.robinfood.storeor.dtos.findAllResolutions.SearchResolutionDTO;
import com.robinfood.storeor.dtos.response.DataResolutionResponseDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.DataResolutionEntity;
import com.robinfood.storeor.entities.configurationposbystore.SearchResolutionEntity;
import com.robinfood.storeor.mappers.IFindAllResolutionsMapper;
import com.robinfood.storeor.repositories.resolutionsrepository.IResolutionsOrderPosRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class FindAllResolutionsUseCase implements IFindAllResolutionsUseCase{

    private final IResolutionsOrderPosRepository resolutionsOrderPosRepository;

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final IFindAllResolutionsMapper findAllResolutionsMapper;

    public FindAllResolutionsUseCase(
            IResolutionsOrderPosRepository resolutionsOrderPosRepository,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IFindAllResolutionsMapper findAllResolutionsMapper
    ) {
        this.resolutionsOrderPosRepository = resolutionsOrderPosRepository;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.findAllResolutionsMapper = findAllResolutionsMapper;
    }

    @Override
    public DataResolutionResponseDTO invoke(SearchResolutionDTO searchResolutionDTO) {

        var token = getTokenBusinessCapabilityUseCase.invoke();

        SearchResolutionEntity searchResolutionEntity =findAllResolutionsMapper
                .searchResolutionEntityToResolutionsListResponseDTO(searchResolutionDTO);

        APIResponseEntity<DataResolutionEntity> response = resolutionsOrderPosRepository
                .findAllResolutions(searchResolutionEntity,token.getAccessToken());

        return findAllResolutionsMapper.dataResolutionEntityToDataResolutionResponseDTO(
                response.getData());
    }
}
