package com.robinfood.core.dtos.menuhallproductdetail;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuHallProductDetailGroupDTO implements Serializable {

    private static final long serialVersionUID = -2574951755729433412L;

    private Long free;

    private Long id;

    private Long groupTypeId;

    private Long max;

    private Long min;

    private String nameSingular;

    private String namePlural;

    private Long position;

    private List<MenuHallProductDetailPortionDTO> portions;

    private String selectionNameSingular;

    private String selectionNamePlural;

    private String sku;

    private Long subsidy;

    private Long visible;
}
