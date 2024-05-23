package org.example.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class FinalProductArticleDTO {

    private Long id;

    private Long menuHallProductId;

    private Long typeId;

    private String typeName;
}
