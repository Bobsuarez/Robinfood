package com.robinfood.core.dtos.posresolution;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "posId",
        "storeId",
        "dicStatusId",
        "name",
        "prefix",
        "current",
        "initialDate",
        "endDate",
        "typeDocument",
        "startNumber",
        "endNumber",
        "effectiveInvoices",
        "cancelledInvoices"
})
public class GetPosResolutionsDTO {

    public Long cancelledInvoices;

    public int current;

    public long dicStatusId;

    public Long effectiveInvoices;

    public String endDate;

    public Integer endNumber;

    public long id;

    public String initialDate;

    public String name;

    public long posId;

    public String prefix;

    public Integer startNumber;

    public long storeId;

    public String typeDocument;

}
