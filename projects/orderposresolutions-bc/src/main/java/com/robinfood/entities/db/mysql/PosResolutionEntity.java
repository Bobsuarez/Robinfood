package com.robinfood.entities.db.mysql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PosResolutionEntity implements Serializable {

    private Integer current;

    private Integer dic_status_id;

    private Date end_date;

    private Long id;

    private Integer invoice_number_initial;

    private String invoice_number_resolutions;

    private Integer invoice_number_end;

    private String invoice_text;

    private Date initial_date;

    private String name;

    private Long resolution_id;

    private Long store_id;

    private Integer order_number_initial;

    private String prefix;

    private Long pos_id;

    private String type_document;
}
