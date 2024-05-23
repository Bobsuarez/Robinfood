package com.robinfood.mappers;

import com.robinfood.dtos.v1.request.ResolutionDTO;
import com.robinfood.dtos.v1.request.StoreResolutionDTO;
import com.robinfood.entities.db.mysql.PosResolutionEntity;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.constants.GeneralConstants.DEFAULT_ONE_INTEGER;

public class StoreResolutionDTOMappers {

    public static List<PosResolutionEntity> buildPosResolutionEntity(StoreResolutionDTO storeResolutionDTO) {

        List<PosResolutionEntity> posResolutionEntities = new ArrayList<>();

        storeResolutionDTO.getResolutions().forEach((ResolutionDTO resolutionDTO) -> {

            int status = DEFAULT_INTEGER;

            if (resolutionDTO.getStatus().equals(Boolean.TRUE)) {
                status = DEFAULT_ONE_INTEGER;
            }

            Instant endDate = resolutionDTO.getEndDate().atZone(ZoneId.systemDefault()).toInstant();
            Instant startDate = resolutionDTO.getStartDate().atZone(ZoneId.systemDefault()).toInstant();

            posResolutionEntities.add(
                    PosResolutionEntity.builder()
                            .current(status)
                            .dic_status_id(status)
                            .end_date(Date.from(endDate))
                            .id(resolutionDTO.getId())
                            .initial_date(Date.from(startDate))
                            .invoice_number_initial(resolutionDTO.getStartingNumber())
                            .invoice_number_resolutions(resolutionDTO.getInvoiceNumber())
                            .invoice_number_end(resolutionDTO.getFinalNumber())
                            .invoice_text(resolutionDTO.getInvoiceText())
                            .name(resolutionDTO.getName())
                            .resolution_id(resolutionDTO.getId())
                            .store_id(storeResolutionDTO.getStoreId())
                            .order_number_initial(DEFAULT_INTEGER)
                            .prefix(resolutionDTO.getPrefix())
                            .pos_id(resolutionDTO.getPosId())
                            .type_document(resolutionDTO.getDocument())
                            .build()
            );
        });

        return posResolutionEntities;
    }
}
