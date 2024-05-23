package com.robinfood.mappers;

import com.robinfood.constants.GeneralConstants;
import com.robinfood.dtos.v1.request.ResolutionUpdateDTO;
import com.robinfood.entities.db.mysql.PosResolutionEntity;

import java.time.ZoneId;
import java.util.Date;

public class PosResolutionMapper {

    public static PosResolutionEntity buildPosResolutionEntity(ResolutionUpdateDTO resolution) {

        Date initialDate = Date.from(resolution.getStartDate().atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(resolution.getEndDate().atZone(ZoneId.systemDefault()).toInstant());

        PosResolutionEntity.PosResolutionEntityBuilder builder = PosResolutionEntity.builder()
                .current(GeneralConstants.DEFAULT_INTEGER)
                .dic_status_id(GeneralConstants.DEFAULT_INTEGER)
                .end_date(endDate)
                .id(resolution.getId())
                .initial_date(initialDate)
                .invoice_number_end(resolution.getFinalNumber())
                .invoice_number_initial(resolution.getStartingNumber())
                .invoice_number_resolutions(resolution.getInvoiceNumber())
                .invoice_text(resolution.getInvoiceText())
                .name(resolution.getName().trim())
                .pos_id(resolution.getPosId())
                .prefix(resolution.getPrefix());

        if (Boolean.TRUE.equals(resolution.getStatus())) {
            builder.dic_status_id(GeneralConstants.DEFAULT_ONE_INTEGER)
                    .current(GeneralConstants.DEFAULT_ONE_INTEGER);
        }

        return builder.build();
    }
}
