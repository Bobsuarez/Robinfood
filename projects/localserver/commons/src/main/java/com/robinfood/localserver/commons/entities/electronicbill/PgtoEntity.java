package com.robinfood.localserver.commons.entities.electronicbill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PgtoEntity {

    private List<Map<String,String>> MP;

}
