package com.robinfood.dtos.request.orderbill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PosResolutionDTO {
    private Object cancelledInvoices;
    private Integer current;
    private Integer dicStatusId;
    private Object effectiveInvoices;
    private Object endDate;
    private Object endNumber;
    private Integer id;
    private Object initialDate;
    private Object name;
    private Integer posId;
    private Object prefix;
    private Object startNumber;
    private Integer storeId;
    private Object typeDocument;
}
