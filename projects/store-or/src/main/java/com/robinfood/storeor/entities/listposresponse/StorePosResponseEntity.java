package com.robinfood.storeor.entities.listposresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class StorePosResponseEntity {

    private Long id;

    private String name;
}
