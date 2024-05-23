package com.robinfood.localserver.commons.entities.electronicbill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DetEntity {

    private Map<String,String> prod;

    private Map <String,Object> imposto;

    private String numItem;

}
