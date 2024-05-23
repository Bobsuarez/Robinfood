package com.robinfood.core.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InformationPosResolutionsResponseEntity {

    private final String cancelledInvoices;

    private final Short current;

    private final Byte dicStatusId;

    private final String effectiveInvoices;

    private final String endDate;

    private final String endNumber;

    private final Integer id;

    private final String initialDate;

    private final String name;

    private final Short posId;

    private final String prefix;

    private final String startNumber;

    private final Short storeId;

    private final String typeDocument;
}
