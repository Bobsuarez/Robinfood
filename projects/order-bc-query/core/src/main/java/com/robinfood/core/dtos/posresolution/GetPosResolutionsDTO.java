package com.robinfood.core.dtos.posresolution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetPosResolutionsDTO {

    public Long cancelledInvoices;

    public int current;

    public long dicStatusId;

    public Long effectiveInvoices;

    public LocalDate endDate;

    public Integer endNumber;

    public long id;

    public LocalDate initialDate;

    public String name;

    public long posId;

    public String prefix;

    public Integer startNumber;

    public long storeId;

    public String typeDocument;

}
