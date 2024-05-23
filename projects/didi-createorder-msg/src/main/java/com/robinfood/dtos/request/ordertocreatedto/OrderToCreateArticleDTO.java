package com.robinfood.dtos.request.ordertocreatedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderToCreateArticleDTO implements Serializable {

    private static final long serialVersionUID = 4561188572400143378L;

    private Long id;

    private Long menuHallProductId;

    private Long typeId;

    private String typeName;

}
