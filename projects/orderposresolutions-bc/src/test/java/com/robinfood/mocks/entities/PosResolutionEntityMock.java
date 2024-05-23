package com.robinfood.mocks.entities;

import com.robinfood.entities.db.mysql.PosResolutionEntity;


public class PosResolutionEntityMock {

    public static PosResolutionEntity build() {
        return PosResolutionEntity.builder()
                .current(1)
                .dic_status_id(1)
                .id(1L)
                .invoice_number_initial(1)
                .invoice_number_resolutions("0000")
                .invoice_number_end(1)
                .invoice_text("test")
                .name("test")
                .resolution_id(1L)
                .store_id(1L)
                .order_number_initial(1)
                .prefix("M00")
                .pos_id(1L)
                .type_document("test")
                .build();
    }

}
