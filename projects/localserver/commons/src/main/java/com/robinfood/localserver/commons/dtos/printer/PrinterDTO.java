package com.robinfood.localserver.commons.dtos.printer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PrinterDTO {

    private String ip;

    private String name;

}
