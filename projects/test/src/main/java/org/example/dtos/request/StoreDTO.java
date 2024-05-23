package org.example.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class StoreDTO {

    private String code;

    private Long id;

    private String name;

    private Long posId;

    private String postalCode;

}
