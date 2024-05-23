package com.robinfood.localserver.commons.dtos.storeconfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AcceptLanguageDTO {

    private String Language;
}
