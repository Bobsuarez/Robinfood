package com.robinfood.app.usecases.getposresolutionbystore;

import com.google.gson.Gson;
import com.robinfood.app.usecases.getposresolution.IGetPosResolutionUseCase;
import com.robinfood.core.dtos.posresolution.DataPosResolutionRequestDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
import com.robinfood.core.entities.PosResolutionsEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.orderpos.IOrderPosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_EMPTY_VALUE;

@Component
@Slf4j
public class GetPosResolutionByStoreUseCase implements IGetPosResolutionByStoreUseCase {

    private final IOrderPosRepository orderPosRepository;

    private final IGetPosResolutionUseCase posResolutionUseCase;

    public GetPosResolutionByStoreUseCase(IOrderPosRepository orderPosRepository,
                                          IGetPosResolutionUseCase posResolutionUseCase
    ) {
        this.orderPosRepository = orderPosRepository;
        this.posResolutionUseCase = posResolutionUseCase;
    }

    @Override
    public List<GetPosResolutionsDTO> invoke(DataPosResolutionRequestDTO dataPosResolutionRequestDTO) {

        log.info("Start of the process that obtains the resolution by store with {}",
                new Gson().toJson(dataPosResolutionRequestDTO));

        List<PosResolutionsEntity> posResolutionsEntityList =
                orderPosRepository.findByStoreIdAndCurrent(dataPosResolutionRequestDTO.getStoreId(),
                        DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT).orElseThrow(() -> new GenericOrderBcException("Pos " +
                        "resolution not found of the store :" + dataPosResolutionRequestDTO.getStoreId()));

        List<GetPosResolutionsDTO> getPosResolutionsDTOS = new ArrayList<>();

        posResolutionsEntityList.forEach(dataPosEntity -> {
            dataPosResolutionRequestDTO.setPosId(dataPosEntity.getPosId());
            getPosResolutionsDTOS.add(posResolutionUseCase.invoke(dataPosResolutionRequestDTO));
        });

         getPosResolutionsDTOS.removeIf(dataPosResolution ->
                Objects.equals(dataPosResolution.getEffectiveInvoices(), DEFAULT_LONG_EMPTY_VALUE) &&
                        Objects.equals(dataPosResolution.getCancelledInvoices(), DEFAULT_LONG_EMPTY_VALUE));

        return getPosResolutionsDTOS;
    }

}
