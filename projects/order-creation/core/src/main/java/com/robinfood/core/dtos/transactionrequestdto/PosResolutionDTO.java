package com.robinfood.core.dtos.transactionrequestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PosResolutionDTO {

    private String cancelledInvoices;

    private Short current;

    private Byte dicStatusId;

    private String effectiveInvoices;

    private String endDate;

    private String endNumber;

    private Integer id;

    private String initialDate;

    private String name;

    private Short posId;

    private String prefix;

    private String startNumber;

    private Short storeId;

    private String typeDocument;
}
