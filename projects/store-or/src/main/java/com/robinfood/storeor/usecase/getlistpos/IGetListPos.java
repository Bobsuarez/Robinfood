package com.robinfood.storeor.usecase.getlistpos;

import com.robinfood.storeor.dtos.listposresponse.PosListResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface IGetListPos {

    Page<PosListResponseDTO> invoke(
            Integer page, String posName, Integer size,
            Long status, Long storeId, Sort sort
    );
}
