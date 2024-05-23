package com.robinfood.configurationslocalserverbc.dtos.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class TemplateGroupsStoresDTO {

    private Boolean active;

    private LocalDateTime createdAt;

    private Long id;

    private Long groupId;

    private Long storeId;

    private LocalDateTime updatedAt;

}
